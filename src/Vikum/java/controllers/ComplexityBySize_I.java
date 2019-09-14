package Vikum.java.controllers;   ////https://regexr.com/4id2l

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexityBySize_I {
    private static int Cs = 0;
    private int localCs = 0;
    private List<String> arrMethodsDetected = new ArrayList<>();
    private List<String> variablesDetected = new ArrayList<>();

    public int ReadFromFile(String selectedFilePath) throws IOException {

        File file = new File(selectedFilePath);
        Scanner sc = new Scanner(file);

        boolean skipStatement = false;

        while (sc.hasNextLine()) {
            localCs = 0;
            String statement = sc.nextLine();
            System.out.println("");
            System.out.print(statement);

            /* skip empty statements */
            if (checkEmptyStatement(statement)) {
                continue;
            }

            /* check statement for single line comments; line can consist of other code as well */
            statement = checkForSingleLineComments(statement);
            if (checkEmptyStatement(statement)) {
                continue;
            }

            /* check block comments; line can consist of other code as well */
            statement = checkForBlockComments(statement);
            if (checkEmptyStatement(statement)) {
                continue;
            }

/*
            check statement for multi line comments
            it is assumed that the statements which have multiline comments, have only multiline comments
            and no other code between or beyond
*/
            if (checkMultiLineCommentStart(statement)) {
                skipStatement = true;
                continue;
            }
            if (checkMultiLineCommentEnd(statement)) {
                skipStatement = false;
                continue;
            }

            if (skipStatement) {
                continue;
            }

            /* check statement for strings; line can consist of other code as well */
            statement = checkForStrings(statement);
            if (checkEmptyStatement(statement)) {
                continue;
            }

            //check for method declarations
            statement = checkForMethodDeclaration(statement);

            //check for method calls
            checkForMethodCalls(statement);

            //check for variable declarations
            statement = checkForVariableDeclaration(statement);

            //check for multiple variable declarations
            statement = checkForMultipleVariableDeclaration(statement);

            String[] arrWords = statement.split(" ");

            checkForOperators(arrWords);

            System.out.print("                  Statement Cs: " + localCs);
            Cs += localCs;
        }
        return Cs;
    }

    private String checkForStrings(String checkStatement) {
        String str = "\"(.*)\"";
        if (matchPattern(checkStatement, str)) {
            String textInString = getMatchedPattern(checkStatement, str);
            checkStatement = checkStatement.replaceAll(str, "");
            System.out.print("    Text inside String: " + textInString);
//            localCs++;
        }
        return checkStatement;
    }

    private String checkForSingleLineComments(String checkStatement) {
        String str = "\\/\\/(.*)";
        if (matchPattern(checkStatement, str)) {
            checkStatement = checkStatement.replaceAll(str, "");
        }
        return checkStatement;
    }

    private String checkForBlockComments(String checkStatement) {
        String str = "\\/\\*(.*)\\*\\/";
        if (matchPattern(checkStatement, str)) {
            checkStatement = checkStatement.replaceAll(str, "");
        }
        return checkStatement;
    }

    public boolean checkEmptyStatement(String checkStatement) {
        return checkStatement.equals("");
    }

    public boolean checkMultiLineCommentStart(String checkStatement) {
        return checkStatement.contains("/*");
    }

    public boolean checkMultiLineCommentEnd(String checkStatement) {
        return checkStatement.contains("*/");
    }

    private String checkForMethodDeclaration(String checkStatement) {
        if (matchPattern(checkStatement, Operators.methodIdentifier)) {
            String matchedMethod = getMatchedPattern(checkStatement, Operators.methodIdentifier);
            if (matchedMethod != null) {
                String[] arrMethodNames = matchedMethod.split("\\(");
                arrMethodsDetected.add(arrMethodNames[0]);
                checkStatement = checkStatement.replaceAll(arrMethodNames[0], "");
                System.out.print("    Method Detected: " + matchedMethod);
            }
        }
        return checkStatement;
    }

    private void checkForMethodCalls(String checkStatement) {
        for (String method : arrMethodsDetected) {
            String matchedMethodCall = null;
            String operator = "\\b" + method + "\\b";
            if (matchPattern(checkStatement, operator)) {
                matchedMethodCall = getMatchedPattern(checkStatement, operator);
                System.out.print("    Method Call Detected: " + matchedMethodCall);

            }
        }
    }

    //flak
    private String checkForVariableDeclaration(String checkStatement) {
        if (matchPattern(checkStatement, Operators.variableIdentifier)) {
            String matchedVariable = getMatchedPattern(checkStatement, Operators.variableIdentifier);
            if (matchedVariable != null) {
                variablesDetected.add(matchedVariable);
                checkStatement = checkStatement.replaceAll(matchedVariable, "");
                System.out.print("    Variable Detected: " + matchedVariable);
            }
        }
        return checkStatement;
    }

    private String checkForMultipleVariableDeclaration(String checkStatement) {
        if (matchPattern(checkStatement, Operators.multiVariableIdentifier)) {
            String matchedVariables = getMatchedPattern(checkStatement, Operators.multiVariableIdentifier);
            if (matchedVariables != null) {
                localCs--;
                String[] splitElements = matchedVariables.trim().split(",");
                List<String> splitElementsList = Arrays.asList(splitElements);
                variablesDetected.addAll(splitElementsList);
                for (String element : splitElementsList) {
                    System.out.print("    Variable Detected: " + element);
                    localCs++;
                }
                checkStatement = checkStatement.replaceAll(matchedVariables, "");
            }
        }
        return checkStatement;
    }

    private void checkForOperators(String[] arrWords) {
        for (String word : arrWords) {
            for (String operator : Operators.arithmeticOperators) {
                checkOperatorContains(word, operator);
            }
            for (String operator : Operators.relationOperators) {
                checkOperatorContains(word, operator);
            }
            for (String operator : Operators.logicalOperators) {
                checkOperatorContains(word, operator);
            }
            for (String operator : Operators.bitwiseOperators) {
                checkOperatorContains(word, operator);
            }
            for (String operator : Operators.miscellaneousOperators) {
                checkOperatorContains(word, operator);
            }
            for (String operator : Operators.assignmentOperators) {
                checkOperatorContains(word, operator);
            }
            for (String operator : Operators.keyWords) {
                checkOperatorContains(word, operator);
            }
            for (String operator : Operators.manipulators) {
                checkOperatorContains(word, operator);
            }
            for (String operator : Operators.randomOperators) {
                checkOperatorContains(word, operator);
            }
            for (String operator : Operators.specialKeyWords) {
                checkSpecialKeyWordContains(word, operator);
            }
            for (String operator : variablesDetected) {
                checkOperatorContains(word, operator);
            }
        }
    }

    private void checkOperatorContains(String word, String operator) {
        if (matchPattern(word, operator)) {
            String token = getMatchedPattern(word, operator);
            System.out.println("    Token Detected: " + token);
        }
    }

    private void checkSpecialKeyWordContains(String word, String operator) {
        if (matchPattern(word, operator)) {
            String token = getMatchedPattern(word, operator);
            System.out.println("    (Special)Token Detected: " + token);
            localCs++;
        }
    }

    public boolean matchPattern(String word, String operator) {
        Matcher m = setMatcher(word, operator);
        return m.find();
    }

    private Matcher setMatcher(String word, String operator) {
        Pattern p = Pattern.compile(operator);
        return p.matcher(word);
    }

    private String getMatchedPattern(String word, String operator) {
        Matcher m = setMatcher(word, operator);

        while (m.find()) {
            localCs++;
            String replaced = word.replaceFirst(operator, "");
            getMatchedPattern(replaced, operator);
            return m.group();
        }
        return null;
    }
}

package Vikum.java.controllers;   ////https://regexr.com/4id2l

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexityBySize_I {
    private static int Cs = 0;
    private int localCs = 0;
    private ArrayList<String> arrMethodsDetected = new ArrayList<>();

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

            String[] arrWords = statement.split(" ");

            checkAssignmentOperators(arrWords);
            checkMiscellaneousOperators(arrWords);
            checkBitwiseOperators(arrWords);
            checkLogicalOperators(arrWords);
            checkRelationOperators(arrWords);
            checkArithmeticOperators(arrWords);

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
                checkStatement = checkStatement.replaceAll(Operators.methodIdentifier, "");
                System.out.print("    Method Detected: " + matchedMethod);
//            localCs++;
            }
        }
        return checkStatement;
    }

    private void checkForMethodCalls(String checkStatement) {
        for (String method : arrMethodsDetected) {
            String matchedMethodCall = null;
            String operator = "\\b" + method + "\\b";
            if (matchPattern(checkStatement, operator)) {
//                Matcher m = setMatcher(checkStatement, operator);
//                if (m.find()) {
//                    matchedMethodCall = m.group(0);
//                    localCs++;
//                }
//                System.out.print("    Method Call Detected: " + matchedMethodCall);
                matchedMethodCall = getMatchedPattern(checkStatement, operator);
                System.out.print("    Method Call Detected: " + matchedMethodCall);

            }
        }
    }

    private void checkArithmeticOperators(String[] arrWords) {
        for (String word : arrWords) {
            for (String operator : Operators.arithmeticOperators) {
                checkOperatorContains(word, operator);
            }
        }
    }

    private void checkRelationOperators(String[] arrWords) {
        for (String word : arrWords) {
            for (String operator : Operators.relationOperators) {
                checkOperatorContains(word, operator);
            }
        }
    }

    private void checkLogicalOperators(String[] arrWords) {
        for (String word : arrWords) {
            for (String operator : Operators.logicalOperators) {
                checkOperatorContains(word, operator);
            }
        }
    }

    private void checkBitwiseOperators(String[] arrWords) {
        for (String word : arrWords) {
            for (String operator : Operators.bitwiseOperators) {
                checkOperatorContains(word, operator);
            }
        }
    }

    private void checkMiscellaneousOperators(String[] arrWords) {
        for (String word : arrWords) {
            for (String operator : Operators.miscellaneousOperators) {
                checkOperatorContains(word, operator);
            }
//            checkDotOperatorContains(word); // .
        }

    }

    private void checkAssignmentOperators(String[] arrWords) {
        for (String word : arrWords) {
            for (String operator : Operators.assignmentOperators) {
                checkOperatorContains(word, operator);
            }
        }
    }

    private void checkKeyWords(String[] arrWords) {
        for (String word : arrWords) {
            for (String operator : Operators.keyWords) {
                checkOperatorContains(word, operator);
            }
        }
    }

    private void checkManipulators(String[] arrWords) {
        for (String word : arrWords) {
            for (String operator : Operators.manipulators) {
                checkOperatorContains(word, operator);
            }
        }
    }

    private void checkOperatorContains(String word, String operator) {
        if (matchPattern(word, operator)) {
//            localCs++;
            String token = getMatchedPattern(word, operator);
            System.out.println("    Token Detected: "+token);
        }
    }

    private void checkDotOperatorContains(String word) {
        if (matchPattern(word, "(?<![-+!%^&*<>=:/|~^.])\\.(?![-+!%^&*<>=:/|~^.])")) {
            String[] words = word.split("\\.");
            for (int i = 0; i < words.length - 1; i++) {
                words[i] = words[i].concat(".");
                localCs++;
            }
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
//        if (m.find()) {
//            return m.group();
//        }
//        return null;
        while (m.find()) {
//            System.out.println(m.group());
            localCs++;
            String replaced = word.replaceFirst(operator, "");
            getMatchedPattern(replaced, operator);
            return m.group();
        }
        return null;
    }
}

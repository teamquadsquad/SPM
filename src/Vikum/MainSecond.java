package Vikum;//public class MainSecond {
//}

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainSecond {
    private static int Cs = 0;
    private String statement;

    public static void main(String[] args) throws IOException {

        new MainSecond().ReadFromFileUsingScanner();
        System.out.println("The value of Cs = " + Cs);

    }

    private void ReadFromFileUsingScanner() throws IOException {

        String path = "C:\\Users\\User\\Documents\\SPM\\SPM\\src\\sampleText.txt";
        File file = new File(path);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            statement = sc.nextLine();
            System.out.println(statement);

            checkArithmeticOperators();
            checkRelationOperators();
            checkLogicalOperators();
            checkBitwiseOperators();
            checkMiscellaneousOperators();
            checkAssignmentOperators();
        }
    }

    private String checkOperator(String word, String operator) {
        if (word.contains(operator)) {
            Cs++;
            word = word.replace(operator, "");
        }
        return word;
    }

    private void checkAssignmentOperators() {
        String[] arrWord = statement.trim().split(";");
        StringBuilder conCatStatement = new StringBuilder();
        for (int i = 0; i < arrWord.length; i++) {
            arrWord[i] = checkOperator(arrWord[i], "+=");
            arrWord[i] = checkOperator(arrWord[i], "-=");
            arrWord[i] = checkOperator(arrWord[i], "*=");
            arrWord[i] = checkOperator(arrWord[i], "/=");
            arrWord[i] = checkOperator(arrWord[i], ">>>=");
            arrWord[i] = checkOperator(arrWord[i], "|=");
            arrWord[i] = checkOperator(arrWord[i], "&=");
            arrWord[i] = checkOperator(arrWord[i], "%=");
            arrWord[i] = checkOperator(arrWord[i], "<<=");
            arrWord[i] = checkOperator(arrWord[i], ">>=");
            arrWord[i] = checkOperator(arrWord[i], "^=");
            arrWord[i] = checkOperator(arrWord[i], "=");

            conCatStatement.append(arrWord[i]).append(";");
        }
        statement = conCatStatement.toString();
//        System.out.println("statement after concat: " +statement);
    }

    private void checkMiscellaneousOperators() {
        String[] arrWord = statement.trim().split(";");
        StringBuilder conCatStatement = new StringBuilder();
        for (int i = 0; i < arrWord.length; i++) {
            arrWord[i] = checkOperator(arrWord[i], "+=");
            arrWord[i] = checkOperator(arrWord[i], ",");
            arrWord[i] = checkOperator(arrWord[i], "->");
            arrWord[i] = checkOperator(arrWord[i], ".");
            arrWord[i] = checkOperator(arrWord[i], "::");

            conCatStatement.append(arrWord[i]).append(";");
        }
        statement = conCatStatement.toString();
//        System.out.println("statement after concat: " +statement);
    }

    private void checkBitwiseOperators() {
        String[] arrWord = statement.trim().split(";");
        StringBuilder conCatStatement = new StringBuilder();
        for (int i = 0; i < arrWord.length; i++) {
            arrWord[i] = checkOperator(arrWord[i], "|");
            arrWord[i] = checkOperator(arrWord[i], "^");
            arrWord[i] = checkOperator(arrWord[i], "~");
            arrWord[i] = checkOperator(arrWord[i], "<<");
            arrWord[i] = checkOperator(arrWord[i], ">>");
            arrWord[i] = checkOperator(arrWord[i], ">>>");
            arrWord[i] = checkOperator(arrWord[i], "<<<");

            conCatStatement.append(arrWord[i]).append(";");
        }
        statement = conCatStatement.toString();
//        System.out.println("statement after concat: " +statement);
    }

    private void checkLogicalOperators() {
        String[] arrWord = statement.trim().split(";");
        StringBuilder conCatStatement = new StringBuilder();
        for (int i = 0; i < arrWord.length; i++) {
            arrWord[i] = checkOperator(arrWord[i], "&&");
            arrWord[i] = checkOperator(arrWord[i], "||");
            arrWord[i] = checkOperator(arrWord[i], "!");

            conCatStatement.append(arrWord[i]).append(";");
        }
        statement = conCatStatement.toString();
//        System.out.println("statement after concat: " +statement);
    }

    private void checkRelationOperators() {
        String[] arrWord = statement.trim().split(";");
        StringBuilder conCatStatement = new StringBuilder();
        for (int i = 0; i < arrWord.length; i++) {
            arrWord[i] = checkOperator(arrWord[i], "==");
            arrWord[i] = checkOperator(arrWord[i], "!=");
            arrWord[i] = checkOperator(arrWord[i], ">=");
            arrWord[i] = checkOperator(arrWord[i], "<=");
            arrWord[i] = checkOperator(arrWord[i], ">");
            arrWord[i] = checkOperator(arrWord[i], "<");

            conCatStatement.append(arrWord[i]).append(";");
        }
        statement = conCatStatement.toString();
//        System.out.println("statement after concat: " +statement);
    }

    private void checkArithmeticOperators() {
        String[] arrWord = statement.trim().split(";");
        StringBuilder conCatStatement = new StringBuilder();
        for (int i = 0; i < arrWord.length; i++) {
            arrWord[i] = checkOperator(arrWord[i], "++");
            arrWord[i] = checkOperator(arrWord[i], "--");
            arrWord[i] = checkOperator(arrWord[i], "+");
            arrWord[i] = checkOperator(arrWord[i], "-");
            arrWord[i] = checkOperator(arrWord[i], "*");
            arrWord[i] = checkOperator(arrWord[i], "/");
            arrWord[i] = checkOperator(arrWord[i], "%");

            conCatStatement.append(arrWord[i]).append(";");
        }
        statement = conCatStatement.toString();
//        System.out.println("statement after concat: " +statement);
    }
}


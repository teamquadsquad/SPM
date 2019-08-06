package Vikum;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static int Cs = 0;
    private String statement;

    public static void main(String[] args) throws IOException {

        new Main().ReadFromFileUsingScanner();
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

    private void checkAssignmentOperators() {
        String[] arrWord = statement.trim().split(";");
        StringBuilder conCatStatement = new StringBuilder();
        for (int i = 0; i < arrWord.length; i++) {
            if (arrWord[i].contains("+=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("+=", "");
//                System.out.println("Split word: " +arrWord[i]);
            } else if (arrWord[i].contains("-=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("-=", "");
            } else if (arrWord[i].contains("*=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("*=", "");
            }else if (arrWord[i].contains("/=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("/=", "");
            }else if (arrWord[i].contains(">>>=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace(">>>=", "");
            }else if (arrWord[i].contains("|=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("|=", "");
            }else if (arrWord[i].contains("&=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("&=", "");
            }else if (arrWord[i].contains("%=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("%=", "");
            }else if (arrWord[i].contains("<<=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("<<=", "");
            }else if (arrWord[i].contains(">>=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace(">>=", "");
            }else if (arrWord[i].contains("^=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("^=", "");
            }else if (arrWord[i].contains("=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("=", "");
            }
            conCatStatement.append(arrWord[i]).append(";");
        }
        statement = conCatStatement.toString();
//        System.out.println("statement after concat: " +statement);
    }

    private void checkMiscellaneousOperators() {
        String[] arrWord = statement.trim().split(";");
        StringBuilder conCatStatement = new StringBuilder();
        for (int i = 0; i < arrWord.length; i++) {
            if (arrWord[i].contains(",")) {
                Cs++;
                arrWord[i] = arrWord[i].replace(",", "");
//                System.out.println("Split word: " +arrWord[i]);
            } else if (arrWord[i].contains("->")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("->", "");
            } else if (arrWord[i].contains(".")) {
                Cs++;
                arrWord[i] = arrWord[i].replace(".", "");
            }else if (arrWord[i].contains("::")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("::", "");
            }
            conCatStatement.append(arrWord[i]).append(";");
        }
        statement = conCatStatement.toString();
//        System.out.println("statement after concat: " +statement);
    }

    private void checkBitwiseOperators() {
        String[] arrWord = statement.trim().split(";");
        StringBuilder conCatStatement = new StringBuilder();
        for (int i = 0; i < arrWord.length; i++) {
            if (arrWord[i].contains("|")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("|", "");
//                System.out.println("Split word: " +arrWord[i]);
            } else if (arrWord[i].contains("^")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("^", "");
            } else if (arrWord[i].contains("~")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("~", "");
            }else if (arrWord[i].contains("<<")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("<<", "");
            }else if (arrWord[i].contains(">>")) {
                Cs++;
                arrWord[i] = arrWord[i].replace(">>", "");
            }else if (arrWord[i].contains(">>>")) {
                Cs++;
                arrWord[i] = arrWord[i].replace(">>>", "");
            }else if (arrWord[i].contains("<<<")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("<<<", "");
            }
            conCatStatement.append(arrWord[i]).append(";");
        }
        statement = conCatStatement.toString();
//        System.out.println("statement after concat: " +statement);
    }

    private void checkLogicalOperators() {
        String[] arrWord = statement.trim().split(";");
        StringBuilder conCatStatement = new StringBuilder();
        for (int i = 0; i < arrWord.length; i++) {
            if (arrWord[i].contains("&&")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("&&", "");
//                System.out.println("Split word: " +arrWord[i]);
            } else if (arrWord[i].contains("||")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("||", "");
            } else if (arrWord[i].contains("!")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("!", "");
            }
            conCatStatement.append(arrWord[i]).append(";");
        }
        statement = conCatStatement.toString();
//        System.out.println("statement after concat: " +statement);
    }

    private void checkRelationOperators() {
        String[] arrWord = statement.trim().split(";");
        StringBuilder conCatStatement = new StringBuilder();
        for (int i = 0; i < arrWord.length; i++) {
            if (arrWord[i].contains("==")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("==", "");
//                System.out.println("Split word: " +arrWord[i]);
            } else if (arrWord[i].contains("!=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("!=", "");
            } else if (arrWord[i].contains(">=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace(">=", "");
            } else if (arrWord[i].contains("<=")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("<=", "");
            } else if (arrWord[i].contains(">")) {
                Cs++;
                arrWord[i] = arrWord[i].replace(">", "");
            } else if (arrWord[i].contains("<")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("<", "");
            }
            conCatStatement.append(arrWord[i]).append(";");
        }
        statement = conCatStatement.toString();
//        System.out.println("statement after concat: " +statement);
    }

    private void checkArithmeticOperators() {
        String[] arrWord = statement.trim().split(";");
        StringBuilder conCatStatement = new StringBuilder();
        for (int i = 0; i < arrWord.length; i++) {
            if (arrWord[i].contains("++")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("++", "");
//                System.out.println("Split word: " +arrWord[i]);
            } else if (arrWord[i].contains("--")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("--", "");
            } else if (arrWord[i].contains("+")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("+", "");
            } else if (arrWord[i].contains("-")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("-", "");
            } else if (arrWord[i].contains("*")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("*", "");
            } else if (arrWord[i].contains("/")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("/", "");
            } else if (arrWord[i].contains("%")) {
                Cs++;
                arrWord[i] = arrWord[i].replace("%", "");
            }
            conCatStatement.append(arrWord[i]).append(";");
        }
        statement = conCatStatement.toString();
//        System.out.println("statement after concat: " +statement);
    }
}


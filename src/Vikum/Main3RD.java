package Vikum;   ////https://regexr.com/4id2l

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main3RD {
    private static int Cs = 0;

    public static void main(String[] args) throws IOException {

        new Main3RD().ReadFromFile();
        System.out.println("The value of Cs = " + Cs);

    }

    private void ReadFromFile() throws IOException {

//Exclude comments and Strings

        String path = "C:\\Users\\User\\Documents\\SPM\\SPM\\src\\Vikum\\sampleText.txt";
        File file = new File(path);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String statement = sc.nextLine();
            System.out.println(statement);
            String[] arrWords = statement.split(" ");

            checkAssignmentOperators(arrWords);
            checkMiscellaneousOperators(arrWords);
            checkBitwiseOperators(arrWords);
            checkLogicalOperators(arrWords);
            checkRelationOperators(arrWords);
            checkArithmeticOperators(arrWords);
        }
    }

    private void checkArithmeticOperators(String[] arrWords) {
        for (String word : arrWords) {
            checkOperatorContains(word, "(?<!\\+)\\+(?![+=])"); // +
            checkOperatorContains(word, "(?<!-)-(?![-=>])"); // -
            checkOperatorContains(word, "\\*(?!=)"); // *
            checkOperatorContains(word, "\\/(?!=)"); // /
            checkOperatorContains(word, "%(?!=)"); // %
            checkOperatorContains(word, "\\+\\+"); // ++
            checkOperatorContains(word, "--"); // --
        }
    }

    private void checkRelationOperators(String[] arrWords) {
        for (String word : arrWords) {
            checkOperatorContains(word, "=="); // ==
            checkOperatorContains(word, "!="); // !=
            checkOperatorContains(word, "(?<![->])>(?![>=])"); // >
            checkOperatorContains(word, "(?<![<])<(?![<=])"); // <
            checkOperatorContains(word, "(?<!>)>="); // >=
            checkOperatorContains(word, "(?<!<)<="); // <=
        }
    }

    private void checkLogicalOperators(String[] arrWords) {
        for (String word : arrWords) {
            checkOperatorContains(word, "&&"); // &&
            checkOperatorContains(word, "\\|\\|"); // ||
            checkOperatorContains(word, "!(?!=)"); // !
        }
    }

    private void checkBitwiseOperators(String[] arrWords) {
        for (String word : arrWords) {
            checkOperatorContains(word, "(?<!\\|)\\|((?![|=]))"); // |
            checkOperatorContains(word, "\\^(?!=)"); // ^
            checkOperatorContains(word, "~"); // ~
            checkOperatorContains(word, "(?<![<])<<(?![<=])"); // <<
            checkOperatorContains(word, "(?<![>])>>(?![>=])"); // >>
            checkOperatorContains(word, ">>>(?!=)"); // >>>
            checkOperatorContains(word, "<<<"); // <<<
        }
    }

    private void checkMiscellaneousOperators(String[] arrWords) {
        for (String word : arrWords) {
            checkOperatorContains(word, "(?<![-+!%^&*<>=:/|~^.]),(?![-+!%^&*<>=:/|~^.])"); // ,
            checkOperatorContains(word, "->"); //->
            checkOperatorContains(word, "(?<![-+!%^&*<>=:/|~^.])\\.(?![-+!%^&*<>=:/|~^.])"); // .
            checkOperatorContains(word, "::"); // ::
        }
    }

    private void checkAssignmentOperators(String[] arrWords) {
        for (String word : arrWords) {
            checkOperatorContains(word, "\\+="); //+=
            checkOperatorContains(word, "-="); //-=
            checkOperatorContains(word, "\\*="); //*=
            checkOperatorContains(word, "\\/=");  // /=
            checkOperatorContains(word, "(?<!>)>>>="); // >>>=
            checkOperatorContains(word, "\\|=");  //|=
            checkOperatorContains(word, "&=");  //&=
            checkOperatorContains(word, "%=");  //%=
            checkOperatorContains(word, "(?<!<)<<=");  //<<=
            checkOperatorContains(word, "(?<!>)>>="); // >>=
            checkOperatorContains(word, "\\^=");  //^=
            checkOperatorContains(word, "(?<![!=<^%&|/*+>-])=(?!=)"); // =
        }
    }

    //    "\\b" + operator + "\\b";
    private void checkOperatorContains(String word, String operator) {
        Pattern p = Pattern.compile(operator);
        Matcher m = p.matcher(word);
        if (m.find()) {
            Cs++;
        }
    }
}
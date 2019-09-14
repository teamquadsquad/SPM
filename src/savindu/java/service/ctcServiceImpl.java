package savindu.java.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ctcServiceImpl implements ctcService {

    private int totCtc = 0;
    private int lineCtc = 0;
    private String statement;
    boolean skipStatement = false;
    //String[] arrWords; //matches each word, resulting duplicate increments // eg- if, else if // 2 increments for 'if' regex

    @Override
    public int findCtc ( String path ) throws FileNotFoundException {

        File file = new File(path);
        Scanner sc = new Scanner(file);

        while ( sc.hasNextLine() ) {

            statement = sc.nextLine();
            //arrWords = statement.split(" ");
            CheckControlStructures( statement );

            System.out.println(statement + "  ***Ctc: " + lineCtc);
            lineCtc = 0;
        }

        return totCtc;
    }

    public void CheckControlStructures( String statement ) {
        //for (String word : arrWords) {}

        //Conditional Control Structures
        CheckConditionalControlStructures( statement, "(?<!(\\belse\\s\\b))if" ); //if
        CheckConditionalControlStructures( statement, "\\b(else)\\s(if)\\b" ); //else if
        //CheckConditionalControlStructures( statement, "else(?!(\\b\\sif\\b))" ); //else

        //Iterative Control Structures
        CheckIterativeControlStructures( statement, "\\bfor\\b" ); //for
        CheckIterativeControlStructures( statement, "\\bwhile\\b" ); //while & do while

        //catch
        CheckCatch( statement, "\\bcatch\\b" );

        //switch case
        CheckSwitch( statement, "\\bcase\\b" );
    }

    public void CheckConditionalControlStructures(String statement, String operator ) {

        boolean result = matchPattern( statement, operator );

        if ( result ) {
            totCtc += 1;
            lineCtc += 1;

            //for (String word : arrWords) {}

            checkLogicalBitwiseOperatorsForCCS( statement, "(?<!&)&(?!&)" ); //&
            checkLogicalBitwiseOperatorsForCCS( statement, "&&" ); //&&
            checkLogicalBitwiseOperatorsForCCS( statement, "(?<![|])[|](?![|])" ); //|
            checkLogicalBitwiseOperatorsForCCS( statement, "\\|\\|" ); //||
        }
    }

    public void CheckIterativeControlStructures(String statement, String operator ) {

        boolean result = matchPattern( statement, operator );

        if ( result ) {
            totCtc += 2;
            lineCtc += 2;

            //for (String word : arrWords) {}

            checkLogicalBitwiseOperatorsForICS( statement, "(?<!&)&(?!&)" ); //&
            checkLogicalBitwiseOperatorsForICS( statement, "&&" ); //&&
            checkLogicalBitwiseOperatorsForICS( statement, "(?<![|])[|](?![|])" ); //|
            checkLogicalBitwiseOperatorsForICS( statement, "\\|\\|" ); //||
        }

    }

    public void checkLogicalBitwiseOperatorsForCCS(String statement, String operator ){

        boolean result = matchPattern( statement, operator );

        if ( result ) {
            totCtc += 1;
            lineCtc += 1;
        }
    }

    public void checkLogicalBitwiseOperatorsForICS(String statement, String operator ){

        boolean result = matchPattern( statement, operator );

        if ( result ) {
            totCtc += 2;
            lineCtc += 2;
        }
    }

    public void CheckCatch(String statement, String operator ) {

        boolean result = matchPattern( statement, operator );

        if ( result ) {
            totCtc += 1;
            lineCtc += 1;
        }

    }

    public void CheckSwitch(String statement, String operator ) {

        boolean result = matchPattern( statement, operator );

        if ( result ) {
            totCtc += 1;
            lineCtc += 1;
        }
    }

    public boolean matchPattern(String statement, String operator) {

        Pattern p = Pattern.compile(operator);
        Matcher m = p.matcher(statement);
        return m.find();
    }

    public void skipString(){

        //TODO
        // -skip comments
        // -skip print statements

    }
}
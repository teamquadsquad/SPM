package savindu.java.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ctcServiceImpl implements ctcService {

    private int Ctc = 0;
    private String statement;
    boolean skipStatement = false;
    String[] arrWords;

    @Override
    public int findCtc ( String path ) throws FileNotFoundException {

        File file = new File(path);
        Scanner sc = new Scanner(file);

        while ( sc.hasNextLine() ) {
            statement = sc.nextLine();

            arrWords = statement.split(" ");

            CheckControlStructures(arrWords);
        }

        return Ctc;
    }

    public void CheckControlStructures(String[] arrWords ) {
        for (String word : arrWords) {

            //Conditional Control Structures
            CheckConditionalControlStructures( word, "\\bif\\b" ); //if
            CheckConditionalControlStructures( word, "\\belse if\\b" ); //else if
<<<<<<< HEAD
            //CheckConditionalControlStructures( word, "\\belse(?! if)\\b" ); //else not working
=======
            CheckConditionalControlStructures( word, "\\belse(?! if)\\b" ); //else not working
>>>>>>> 27e6d9ac611d426074e0fcb7101cc12ce316b631

            //Iterative Control Structures
            CheckIterativeControlStructures( word, "\\bfor\\b" ); //for
            CheckIterativeControlStructures( word, "\\bwhile\\b" ); //while &d0wl

            //catch
            CheckCatch( word, "\\bcatch\\b" );

            //switch case
            CheckSwitch( word, "\\bcase\\b" );
        }
    }

    public void CheckConditionalControlStructures(String word, String operator ) {

        boolean result = matchPattern( word, operator );

        if ( result ) {
            Ctc += 1;

            for (String stword : arrWords) {

                checkLogicalBitwiseOperatorsForCCS( stword, "(?<!&)&(?!&)" ); //&
                checkLogicalBitwiseOperatorsForCCS( stword, "&&" ); //&&
                checkLogicalBitwiseOperatorsForCCS( stword, "\\|" ); //|
                checkLogicalBitwiseOperatorsForCCS( stword, "\\|\\|" ); //||
            }
        }
    }

    public void CheckIterativeControlStructures(String word, String operator ) {

        boolean result = matchPattern( word, operator );

        if ( result ) {
            Ctc += 2;

            for (String stword : arrWords) {

                checkLogicalBitwiseOperatorsForICS( stword, "(?<!&)&(?!&)" ); //&
                checkLogicalBitwiseOperatorsForICS( stword, "&&" ); //&&
                checkLogicalBitwiseOperatorsForICS( stword, "\\|" ); //|
                checkLogicalBitwiseOperatorsForICS( stword, "\\|\\|" ); //||
            }
        }

    }

    public void checkLogicalBitwiseOperatorsForCCS(String stword, String operator ){

        boolean result = matchPattern( stword, operator );

        if ( result ) {
            Ctc += 1;
        }
    }

    public void checkLogicalBitwiseOperatorsForICS(String stword, String operator ){

        boolean result = matchPattern( stword, operator );

        if ( result ) {
            Ctc += 2;
        }
    }

    public void CheckCatch(String word, String operator ) {

        boolean result = matchPattern( word, operator );

        if ( result ) {
            Ctc += 1;
        }

    }

    public void CheckSwitch(String word, String operator ) {

        boolean result = matchPattern( word, operator );

        if ( result ) {
            Ctc += 1;
        }
    }

    public boolean matchPattern(String word, String operator) {

        Pattern p = Pattern.compile(operator);
        Matcher m = p.matcher(word);
        return m.find();
    }

    public void skipString(){

        //TODO
        // -skip comments
        // -skip print statements

    }

    public void checkLogicalBitwiseOperators(){

        //TODO
        // -general function for checking all Logical & Bitwise Operators

    }

}
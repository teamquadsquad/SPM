package Recursion;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recursion {
    private static int Cs = 0;

    public static void main(String[] args) throws IOException {

        new Recursion().ReadFromFile();
        System.out.println("The value of Cs = " + Cs);

    }

    private void ReadFromFile() throws IOException {

        String path = "C:\\Users\\hwarlk\\Desktop\\sampleText.txt";
        File file = new File(path);
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String statement = sc.nextLine();
//            System.out.println(statement);
            if (checkColon(statement) == true) {
            	
            	if(checkOpenBrace(statement) == true) {
            		
                  String[] arrWords = statement.split(" ");

            	}
            }
//            String[] arrWords = statement.split(" ");

//            checkRecursion(arrWords);
        }
    }

    private boolean checkOpenBrace(String statement) {
    	if (statement.contains("("))
    			return true;
    	return false;
	}

	private boolean checkColon(String statement) {
    	
    	int size = statement.length() - 1;

    	if (size >= 0) {
    		if(statement.charAt(size) == ';')
                return true;
    	}
    	return false;
    }

//    private void checkRecursion(String[] arrWords) {
//
//
//        for (String word : arrWords) {
//            checkOperatorContains(word, "(?<!\\+)\\+(?![+=])"); // +
//
//        }
//    }

//    private void checkOperatorContains(String word, String operator) {
//        Pattern p = Pattern.compile(operator);
//        Matcher m = p.matcher(word);
//        if (m.find()) {
//            Cs++;
//        }
//    }
}

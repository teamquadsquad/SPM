package Recursion;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.*;

public class Recursion {

    private static int Cs = 0;
    private static boolean isInheritance = false;
    static String path = "C:\\Users\\hwarlk\\Desktop\\sampleText.txt";

    public static void main(String[] args) throws IOException {

        File file = new File(path);

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(file);
        new Recursion().ReadFromFile(sc);

        System.out.println("The value of Cs = " + Cs);
        System.out.println("Inheritance = " + isInheritance);
    }

    /**
     *
     * @param sc
     * @throws IOException
     */
    private void ReadFromFile(Scanner sc) throws IOException {

        String outerMethodName = "outer";
        String innerMethodName = "inner";

        HashMap<String, Integer> outerMethodNameArray = new HashMap<String, Integer>();
        HashMap<String, Integer> innerMethodNameArray = new HashMap<String, Integer>();
        int count = 0;
        while (sc.hasNextLine()) {

            count++;
            String statement = sc.nextLine();
//            System.out.println(statement);
//            System.out.println(Pattern.matches("(//.*?$)|(/\\\\*.*?\\\\*/)", statement));
            if (!Pattern.matches("(//.*?$)|(/\\\\*.*?\\\\*/)", statement)) {
                
                if (checkColon(statement) == false) {

                    if (checkOpenBrace(statement) == true) {

                        outerMethodName = getMethodName(statement);

                        outerMethodNameArray.put(outerMethodName, count);

                    }
                } else if (checkColon(statement) == true) {

                    if (checkOpenBrace(statement) == true) {

                        innerMethodName = getMethodName(statement);

                        innerMethodNameArray.put(innerMethodName, count);

                    }
                }
            }

            if (checkInheritance(statement)) {

                isInheritance = true;
            }
        }
        Iterator it = outerMethodNameArray.entrySet().iterator();

        while (it.hasNext()) {

            HashMap.Entry pair = (HashMap.Entry) it.next();

            Iterator itInner = innerMethodNameArray.entrySet().iterator();//change innerMethodNameArray 
            while (itInner.hasNext()) {

                HashMap.Entry pairInner = (HashMap.Entry) itInner.next();
//                System.out.println(pair.getKey() + " --------- " + pairInner.getKey());
                String innerKey = pair.getKey().toString();
                String outerKey = pairInner.getKey().toString();
                if (innerKey.equals(outerKey)) {

                    it.remove();
                    itInner.remove();

                    Cs++;
                }
            }

        }
    }

    /**
     *
     * @param statement
     * @return
     */
    private String getMethodName(String statement) {

        String[] name = statement.split("\\(");
        String methodName = "";
        for (int i = 0; i < name.length; i++) {

            if (name[i + 1].contains(")")) {

                String[] arrWords = name[i].split(" ");
                methodName = arrWords[arrWords.length - 1];
//                System.out.println("-->" + methodName);
                break;
            }
        }

        return methodName;
    }

    /**
     *
     * @param statement
     * @return
     */
    private boolean checkOpenBrace(String statement) {

        if (statement.contains("(")) {

            return true;
        }
        return false;
    }

    /**
     *
     * @param statement
     * @return
     */
    private boolean checkCloseBrace(String statement) {

        if (statement.contains(")")) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param statement
     * @return
     */
    private boolean checkColon(String statement) {

        if (statement.contains(";")) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param statement
     * @return
     */
    public boolean checkInheritance(String statement) {

        String[] classCode = statement.split(" ");

        for (String name : classCode) {

            if (name.equals("implements") || name.equals("extends")) {

                return true;
            }
        }
        return false;
    }
    
    @Test
    public void testCheckInheritance(){
        
        String lineName = "class";
        boolean expresult = true; 
        Recursion re = new Recursion();
        boolean result = re.checkInheritance(lineName);
        Assert.assertEquals(expresult, result);
    }
}

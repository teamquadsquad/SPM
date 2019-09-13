package hashini.java.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Recursion {

    private static int recCount = 0;
    static String path = "C:\\Users\\hwarlk\\Desktop\\sampleText.txt";

    public boolean detectRecursion(Scanner sc) throws IOException {

        String outerMethodName;
        String innerMethodName;

        HashMap<String, Integer> outerMethodNameArray = new HashMap<>();
        HashMap<String, Integer> innerMethodNameArray = new HashMap<>();
        int count = 0;

        while (sc.hasNextLine()) {
            count++;
            String statement = sc.nextLine();

            if(!statement.contains("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)")){

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
            }}

        Iterator it = outerMethodNameArray.entrySet().iterator();

        while (it.hasNext()) {

            HashMap.Entry pairOuter = (HashMap.Entry) it.next();

            Iterator itInner = innerMethodNameArray.entrySet().iterator();//change innerMethodNameArray
            while (itInner.hasNext()) {
                HashMap.Entry pairInner = (HashMap.Entry) itInner.next();
//                System.out.println(pairOuter.getKey() + " --------- " + pairInner.getKey());
                String innerKey = pairOuter.getKey().toString();
                String outerKey = pairInner.getKey().toString();
                if (innerKey.equals(outerKey)) {
                    it.remove();
                    itInner.remove();

                    recCount++;
                }
            }
        }

        if(recCount > 0)
            return true;

        return false;
    }

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

    private boolean checkOpenBrace(String statement) {

        if(statement.contains("(")) {
            return true;
        }
        return false;
    }

    private boolean checkColon(String statement) {

        if(statement.contains(";")) {
            return true;
        }
        return false;
    }
}

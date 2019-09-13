package hashini.java.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Inheritance {

    public int[] checkCi(String filePath) throws Exception{

        FileReader file=new FileReader(filePath);
        String fileType = getFileExtension(filePath);
        BufferedReader reader=new BufferedReader(file);

//        int arraySize = Size;

        //System.out.println(Size);

        int Ci[] = new int[2];

        int defaultValue = 0;

        if("java".equals(fileType)){
            defaultValue = 1;

        }
        else{
            defaultValue = 0;
        }

        String[] lines = reader.readLine().split("\\r?\\n");
        int i=0;

        for(String line : lines){

            //System.out.println(line);
            if (line.contains("class ")) {
                defaultValue = defaultValue + 1;
            }
            if (line.contains("extends ")) {
                defaultValue = defaultValue + 1;
            }
            if (line.contains("implements ")) {
                defaultValue = defaultValue + 1;
            }
        }

//        for( i = 0 ; i<arraySize ; i++){
//            Ci[i] = defaultValue;
//        }

        int z = 0;

        for(String line : lines){

//            if(line.contains("class ")){
////                Ci[z] = 0;
////            }
//            else if(line.contains("else { ")){
////                Ci[z] = 0;
//            }else if(line.contains("}")){
////                Ci[z] = 0;
//            }

            z = z + 1;

        }

        return Ci;

    }

    public String getFileExtension(String fullName) {

        if(!(fullName == "")) {
            String fileName = new File(fullName).getName();
            int dotIndex = fileName.lastIndexOf('.');
            return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
        }

        return fullName;
    }
}

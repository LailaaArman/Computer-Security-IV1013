// This program counts the same bits might from two inputfiles 

import java.io.*;

public class CountBits {
    public static void main (String [] args ) {
 
        File inputOriginal = new File(args[0]); 
        File inputFlippedBit = new File(args[1]);  

        int length = (int) inputOriginal.length(); 
         
        try (
            InputStream inputStream1 = new FileInputStream(inputOriginal); 
            InputStream inputStream2 = new FileInputStream(inputFlippedBit); 
            
        ) {
         
          byte[] inputOr = new byte[length]; 
          byte[] inputFl = new byte[length]; 

           int count = 0; 
           int bytesRead; 

            bytesRead = inputStream2.read(inputFl); 
       
            while ((bytesRead = inputStream1.read(inputOr)) != -1 ) {
                  
                for (int i=0; i < length; i++) {
                        if (inputOr[i] == inputFl[i]) {
                            count++; 
                            
                        }
                        System.out.println(count);     
                 }
                
            }       
            inputStream1.close();
            inputStream2.close();
            System.exit(0); 

        } catch (IOException e) {
            System.out.println("Error reading inputFile!");
            System.exit(1); 
        }

    }
}
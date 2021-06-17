import java.util.Random;
import java.io.*;

public class StreamCipher {
    public static void main (String [] args ) {

        String key = args[0];  
        File input = new File(args[1]); 
        File output = new File(args[2]); 
        long keyLong = 0; 

        int length = (int) input.length(); 

        try {
            keyLong = Long.parseLong(key); 
        } catch (NumberFormatException e) {
                System.out.println("Key not valid"); 
                System.exit(1);
        }

        // This try catch implementation is taken from codejava.net 
        try (
            InputStream inputStream = new FileInputStream(input); 
            OutputStream outputStream = new FileOutputStream(output); 
        ) {
            byte[] plainText = new byte[length]; 
            byte[] cipherText = new byte [length]; 
            int bytesRead = -1;

            Random r = new Random(keyLong); 
            
            while ((bytesRead = inputStream.read(plainText)) != -1) {
                int i = 0; 
                for (byte b : plainText) {
                    cipherText[i++] = (byte) (b ^ r.nextInt(256)); 
                } 
                outputStream.write(cipherText, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();
            System.exit(0); 

        } catch (IOException e) {
            System.out.println("Error reading inputFile!");
            System.exit(1); 
        }

    }
}

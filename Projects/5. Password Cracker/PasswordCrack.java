// This program counts the same bits from two inputfiles 

import java.io.*;
import java.util.ArrayList;

public class PasswordCrack {

    public static ArrayList<String> dictionary = new ArrayList<String>();
    public static ArrayList<String> userPasswds = new ArrayList<String>();
    public static char[] letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    public static ArrayList<String> getDict(String dict) {

        ArrayList<String> temp = new ArrayList<String>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dict))) {

            temp.add("123");
            temp.add("1234");
            temp.add("12345");
            temp.add("123456");
            temp.add("1234567");
            temp.add("12345678");
            temp.add("123456789");
            temp.add("abc123");
            temp.add("qwerty");
            temp.add("password");
            temp.add("picture1");
            temp.add("iloveyou");
            temp.add("aaron431");
            temp.add("123456789");
            temp.add("password1");
            temp.add("qqww1122");
            temp.add("admin");
            temp.add("secret");
            temp.add("111111");
            temp.add("qqww1122");

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                temp.add(line);
            }
        } catch (Exception e) {
            System.out.print("Error reading input file");
            System.exit(1);
        }
        return temp;
    }

    public static void getPasswords(String passwords) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(passwords))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitted = line.split(":");
                String password = splitted[1];
                String[] fullName = splitted[4].split(" ");
                String firstName = fullName[0];
                String lastName = fullName[1];

                userPasswds.add(password);
                dictionary.add(firstName);
                dictionary.add(lastName);

            }

        } catch (Exception e) {
            System.out.println("Error reading file");
            System.exit(1);
        }
    }

    public static String checkPassword(String word) {

        ArrayList<String> removeHash = new ArrayList<String>();

        for (int i = 0; i < userPasswds.size(); i++) {
            String hash = jcrypt.crypt(userPasswds.get(i), word);

            if (userPasswds.contains(hash)) {
                System.out.println(word);
                userPasswds.remove(hash);
            }

        }

        return word;
    }

    public static ArrayList mangle(ArrayList<String> dictionary) {

        ArrayList<String> mangleList = new ArrayList<String>();

        for (int i = 0; i < dictionary.size(); i++) {

            mangleList.add((checkPassword(reverse(dictionary.get(i)))));
            mangleList.add(checkPassword(toLowercase(dictionary.get(i))));
            mangleList.add(checkPassword(toUpperCase(dictionary.get(i))));
            mangleList.add(checkPassword(capitalize(dictionary.get(i))));
            mangleList.add(checkPassword(ncapitalize(dictionary.get(i))));
            mangleList.add(checkPassword(toggle1(dictionary.get(i))));
            mangleList.add(checkPassword(toggle2(dictionary.get(i))));

            if (dictionary.get(i).length() <= 8) {
                mangleList.add(checkPassword(duplicate(dictionary.get(i))));
                mangleList.add(checkPassword(delete1(dictionary.get(i))));
                mangleList.add(checkPassword(delete2(dictionary.get(i))));
                mangleList.add(checkPassword(reflect1(dictionary.get(i))));
                mangleList.add(checkPassword(reflect2(dictionary.get(i))));

                for (int j = 0; j < letters.length; j++) {
                    mangleList.add(checkPassword(append(dictionary.get(i), j)));
                    mangleList.add(checkPassword(prepend(dictionary.get(i), j)));
                }

            }

        }
        return mangleList;
    }

    // methods for mangle
    public static String duplicate(String word) {
        return word + word;
    }

    public static String reverse(String word) {
        StringBuilder sb = new StringBuilder(word);
        return sb.reverse().toString();
    }

    public static String reflect1(String word) {
        StringBuilder sb = new StringBuilder(word);
        return word + sb.reverse().toString();
    }

    public static String reflect2(String word) {
        StringBuilder sb = new StringBuilder(word);
        return sb.reverse().toString() + word;
    }

    public static String toLowercase(String word) {
        return word.toLowerCase();
    }

    public static String toUpperCase(String word) {
        return word.toUpperCase();
    }

    public static String delete1(String word) {
        return word.substring(1);
    }

    public static String delete2(String word) {
        return word.substring(0, word.length() - 1);
    }

    public static String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public static String ncapitalize(String word) {
        return word.substring(0, 1).toLowerCase() + word.substring(1).toUpperCase();
    }

    public static String prepend(String word, int i) {
        char c = letters[i];
        return c + word;
    }

    public static String append(String word, int i) {
        char c = letters[i];
        return word + c;
    }

    public static String toggle1(String word) {
        String toggled = "";

        for (int i = 0; i < word.length(); i++) {
            if (i % 2 == 0) {
                toggled += word.substring(i, i + 1).toUpperCase();
            } else {
                toggled += word.substring(i, i + 1);
            }
        }
        return toggled;
    }

    public static String toggle2(String word) {
        String toggled = "";
        for (int i = 0; i < word.length(); i++) {
            if (i % 2 != 0) {
                toggled += word.substring(i, i + 1).toUpperCase();
            } else {
                toggled += word.substring(i, i + 1);
            }
        }
        return toggled;
    }

    public static void crackPass(ArrayList<String> dictionary) {

        for (String word : dictionary) {
            checkPassword(word);
        }
        if (userPasswds.isEmpty() == false) {
            crackPass(mangle(dictionary));
        }

    }

    public static void main(String[] args) {

        dictionary = getDict(args[0]);

        getPasswords(args[1]);

        crackPass(dictionary);

    }
}

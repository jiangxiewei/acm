package codeforce.CodeforcesRound497;

import java.util.Scanner;

public class ARomaji {

    private static char[] vowel = new char[]{'a', 'i', 'u', 'e', 'o'};

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        System.out.println(isRomaji(s)?"YES":"NO");

    }

    public static boolean isRomaji(String s) {

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!isVowel(c) && c != 'n') {
                if (i + 1 >= s.length() || !isVowel(s.charAt(i + 1))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isVowel(char x) {
        for (int i = 0; i < vowel.length; i++) {
            if (vowel[i] == x) {
                return true;
            }
        }
        return false;
    }

}

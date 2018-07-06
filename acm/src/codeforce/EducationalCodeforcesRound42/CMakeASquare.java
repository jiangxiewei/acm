package codeforce.EducationalCodeforcesRound42;

import java.util.Scanner;

public class CMakeASquare {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String str = String.valueOf(n);
        int ans = -1;

        int binSize = 1 << str.length();
        for (int i = 1; i < binSize; i++) {
            StringBuilder numstr = new StringBuilder();
            for (int j = str.length() - 1; j >= 0; j--) {
//                System.out.println(String.format("i:%d,j:%d,ans:", i, j )+((i & (1 << j))!=0));
                if ((i & (1 << j)) != 0) {
                    numstr.append(str.charAt(str.length() - j - 1));
                }
            }
            if (judgePreZero(numstr.toString())) {
                continue;
            }
            double num = Double.valueOf(numstr.toString());
            double sqr = Math.sqrt(num);
//            System.out.println(String.format("nun:%f,sqr:%f,judge:", num, sqr) + (sqr - (int) sqr < 0.0001));
            if (sqr - (int) sqr < 0.0000001) {
                int curAns = countOpe(i, str.length());
                if (curAns < ans || ans == -1) {
                    ans = curAns;
                }
            }
        }
        System.out.println(ans);
    }

    private static int countOpe(int bin, int strSize) {
//        System.out.println(bin);
        int count = 0;
        for (int j = strSize - 1; j >= 0; j--) {
            if ((bin & (1 << j)) != 0) {
                count++;
            }
        }
        return strSize - count;
    }

    private static boolean judgePreZero(String numstr) {
        boolean flag = false;
        if (numstr.charAt(0) == '0') {
            flag = true;
        }
        return flag;
    }
}

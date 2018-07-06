package codeforce.TinkoffInternshipWarmupRound2018andCodeforcesRound475Div2;

import java.util.Scanner;

public class BMessages {

    public static void main(String[] args) {
        int[] nabcT = new int[5];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            nabcT[i] = scan.nextInt();
        }
        int t[] = new int[nabcT[0]];
        long value = nabcT[1] * t.length;
        for (int i = 0; i < t.length; i++) {
            t[i] = scan.nextInt();
            if (nabcT[3] > nabcT[2]) {
                //囤积价值比阅读得到价值高
                value += (nabcT[4] - t[i]) * (nabcT[3] - nabcT[2]);
            } else if (nabcT[2] >= nabcT[3]) {
                //不阅读贬值,马上阅读
            }
        }
        System.out.println(value);
    }
}

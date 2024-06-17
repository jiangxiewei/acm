package codeforce.CodeforcesRound498Div3;

import java.util.Scanner;

public class CThreePartsOfTheArray {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        int a = 0, b = n - 1;
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        long ans = 0, sumLeft = arr[a], sumRight = arr[b];
        while (a < b) {
            if (sumLeft > sumRight) {
                sumRight += arr[--b];
            } else {
                if (sumLeft == sumRight) {
                    ans = sumLeft;
                }
                sumLeft += arr[++a];
            }
        }
        System.out.println(ans);
    }

}

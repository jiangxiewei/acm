package codeforce.CodeforcesRound497;

import java.util.Arrays;
import java.util.Scanner;

public class CReorderTheArray {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        Arrays.sort(arr);
        int count = 0;
        for (int i = 0, j = 0; i < arr.length; i++) {
            while (j < arr.length) {
                if (arr[j] > arr[i]) {
                    count++;
                    j++;
                    break;
                }
                j++;
            }
            if (j == arr.length) {
                break;
            }
        }
        System.out.println(count);

    }

}

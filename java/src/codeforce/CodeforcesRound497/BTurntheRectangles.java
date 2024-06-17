package codeforce.CodeforcesRound497;

import java.util.Scanner;

public class BTurntheRectangles {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        boolean flag = true;
        int[] arr = new int[n];
        int x, y, max, min;
        for (int i = 0; i < n; i++) {
            x = scan.nextInt();
            y = scan.nextInt();
            if (!flag) continue;
            if (i == 0) {
                arr[i] = Math.max(x, y);
            } else {
                max = Math.max(x, y);
                min = Math.min(x, y);
                if (min > arr[i - 1]) {
                    flag = false;
                } else {
                    arr[i] = max <= arr[i - 1] ? max : min;
                }
            }
        }
        System.out.println(flag?"YES":"NO");

    }

}

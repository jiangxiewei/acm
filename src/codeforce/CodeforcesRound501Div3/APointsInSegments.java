package codeforce.CodeforcesRound501Div3;

import java.util.Scanner;

/**
 * A. Points in Segments
 * 找出所有不在任何线段中的点
 *
 * 前缀合
 */
public class APointsInSegments {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        int[] arr = new int[m+2];

        for (int i = 0; i < n; i++) {
            arr[scan.nextInt()] += 1;
            arr[scan.nextInt()+1] -= 1;
        }

        int preSum = 0, count = 0;
        int[] target = new int[m+2];
        for (int i = 1; i <= m; i++) {
            preSum += arr[i];
            if (preSum == 0) {
                target[count++] = i;
            }
        }
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            System.out.print(target[i] + " ");
        }

    }


}

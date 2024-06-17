package codeforce.EducationalCodeforcesRound47;


import java.util.Scanner;

/**
 * 1.要求图联通,既 m>n-1
 * 2.连通的两个点要求互质gcd
 * 3.明明是n^2*lg的复杂度,然而因为m的限制导致暴力可过
 */
public class DRelativelyPrimeGraph {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        int[][] ansArr = new int[m][2];
        int ansCount = 0;
        if (m < n - 1) {
            System.out.println("Impossible");
            return;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (gcd(i, j) == 1) {
                    ansArr[ansCount][0] = i;
                    ansArr[ansCount++][1] = j;
                    if (ansCount >= m) {
                        System.out.println("Possible");
                        for (int k = 0; k < ansCount; k++) {
                            System.out.println(ansArr[k][0] + " " + ansArr[k][1]);
                        }
                        return;
                    }
                }
            }
        }
        System.out.println("Impossible");
    }

    public static int gcd(int a, int b) {
        return (a == 0) ? b : gcd(b % a, a);
    }
}

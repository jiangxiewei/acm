package codeforce.EducationalCodeforcesRound42;

import java.util.Scanner;

public class AEquator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int tot = 0;
        int[] a = new int[n];
        for (int i=0;i<n;i++) {
            a[i] = scanner.nextInt();
            tot += a[i];
        }
        int over = 0;
        for (int i=0;i<n;i++) {
            over += a[i];
            if (over >= (tot / 2 + (tot & 1)) ) {
                System.out.println(i+1);
                break;
            }
        }
    }
}

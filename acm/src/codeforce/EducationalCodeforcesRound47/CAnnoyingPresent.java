package codeforce.EducationalCodeforcesRound47;

import java.util.Scanner;

/**
 * 输入n,m 以及m个 xi,di
 * avg(变换后)-avg(原来的) = Xi+Di*( (n*n+n)/2 + i*i - (n+1)*i )/n 故后半段括号内最小值为 (n+1)/2 , 最大值为1或n
 * 仅需根据Di正负形取值即可
 * 注意精度问题,n可最后再除
 */
public class CAnnoyingPresent {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long n, m, xi, di;
        n = scan.nextInt();
        m = scan.nextInt();
        long avg = 0;
        for (int i = 0; i < m; i++) {
            xi = scan.nextInt();
            di = scan.nextInt();
            avg += xi * n;
            if (0 == di) {
            } else if (di > 0) {
                avg += afterDiMultiply(n, di, 1);
            } else {
                avg += afterDiMultiply(n, di, ((n + 1) >> 1));
            }
        }
        System.out.println(String.format("%.15f", (double) avg / n));
    }

    public static long afterDiMultiply(long n, long d, long i) {
        return d * ((n * n + n) / 2 + i * (i - 1 - n));
    }

}

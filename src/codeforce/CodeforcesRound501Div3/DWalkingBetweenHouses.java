package codeforce.CodeforcesRound501Div3;

import java.util.Scanner;

public class DWalkingBetweenHouses {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong(), k = scan.nextLong(), s = scan.nextLong();

        newWay(n, k, s);
//        oldWay(n, k, s);

    }

    /**
     * 假设k步中每步都是走1步,然后往上面加步数的方案,思路清晰巧妙,代码简短
     *
     * @param n n
     * @param k k
     * @param s s
     */
    private static void newWay(long n, long k, long s) {
        //先判断是否需要打印NO
        long divideAns = s / (n - 1);
        long remainder = s % (n - 1);
        if (divideAns + (remainder > 0 ? 1 : 0) > k || k > s) {
            System.out.println("NO");
            return;
        }
        //开始
        System.out.println("YES");
        //默认先分配好k个1步
        s -= k;
        int cur = 1;
        long step;
        while (k > 0) {
            //当n-1>剩余步伐时,走最长路
            step = Math.min(s, n - 2);
//            System.out.printf("s:%d,step:%d\n",s, step);
            if (cur + step + 1 <= n) {
                cur += step + 1;
            } else if (cur - step - 1 > 0) {
                cur -= step + 1;
            }
            //1.因为事前先分配好一步,所以实际s减少量为step-1
            //2.当s=1时,将不会再减少,也就是步伐保持1
            s -= step;
            System.out.print(cur + " ");
            k--;
        }
    }

    /**
     * 使用除法与余数推敲该走的步
     *
     * @param n n
     * @param k k
     * @param s s
     */
    public static void oldWay(long n, long k, long s) {
        long divideAns = s / (n - 1);
        long remainder = s % (n - 1);

        if (divideAns + (remainder > 0 ? 1 : 0) > k || k > s) {
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
        while (remainder < (k - divideAns)) {
            divideAns--;
            remainder += n - 1;
        }
        long cur = 1;
        while (divideAns > 0) {
            cur = n - cur + 1;
            System.out.print(cur + " ");
            divideAns--;
            k--;
        }
        long extraStep = 0, stepDis = 0;
        if (k > 0) {
            extraStep = remainder % k;
            stepDis = remainder / k;
        }
        while (k > 0) {
            long nextStepDis = (stepDis < remainder ? stepDis : remainder) + (extraStep > 0 ? 1 : 0);
            extraStep--;
            if (cur + nextStepDis <= n) {
                cur += nextStepDis;
                remainder -= nextStepDis;
            } else if (cur - nextStepDis > 0) {
                cur -= nextStepDis;
                remainder -= nextStepDis;
            }
            k--;
            System.out.print(cur + " ");
        }
    }

}

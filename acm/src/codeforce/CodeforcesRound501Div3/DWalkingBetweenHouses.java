package codeforce.CodeforcesRound501Div3;

import java.util.Scanner;

public class DWalkingBetweenHouses {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong(), k = scan.nextLong(), s = scan.nextLong();

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
        long extraStep=0, stepDis=0;
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

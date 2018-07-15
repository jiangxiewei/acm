package codeforce.EducationalCodeforcesRound47;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class AGameShopping {

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        int[] cost = new int[n];
        Queue<Integer> q = new LinkedBlockingQueue<>();
        for (int i = 0; i < n; i++) {
            cost[i] = scan.nextInt();
        }
        for (int i = 0; i < m; i++) {
            ((LinkedBlockingQueue<Integer>) q).put(scan.nextInt());
        }
//        System.out.println(q);
        int ans = 0;
        Integer head = null;
        for (int i = 0; i < n; i++) {
            head = q.peek();
            if (null == head) {
                break;
            }
            if (head >= cost[i]) {
                q.poll();
                ans++;
            }
        }

        System.out.println(ans);
    }
}

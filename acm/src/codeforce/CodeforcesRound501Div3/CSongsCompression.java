package codeforce.CodeforcesRound501Div3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CSongsCompression {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        long capability = scan.nextInt();
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(scan.nextInt(), scan.nextInt());
            capability -= pairs[i].y;
        }
        if (capability < 0) {
            System.out.println("-1");
            return;
        }
        int tar = n;
        Arrays.sort(pairs, Comparator.comparing(pair -> pair.comp));
        for (int i = 0; i < n; i++) {
            if (pairs[i].comp <= capability) {
                capability -= pairs[i].comp;
                tar--;
            } else {
                break;
            }
        }
        System.out.println(tar);
    }

    static class Pair {
        public int x, y, comp;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
            comp = x - y;
        }

    }

}

package leetCode.repository;

import java.util.Arrays;
import java.util.Comparator;

public class No3169 {

    public int countDays(int days, int[][] meetings) {
        int r = 0, sum = 0;
        Arrays.sort(meetings, Comparator.comparingInt(o -> o[0]));
        for (int[] se : meetings) {
            if (se[0] > r) {
                sum += se[0] - r - 1;
                r = se[1];
            } else if (se[1] > r) {
                r = se[1];
            }
        }
        sum += days - r;
        return sum;
    }


    public static void main(String[] args) {
        No3169 no3169 = new No3169();
        System.out.println(no3169.countDays(10, new int[][]{{5, 7}, {1, 3}, {9, 10}}) == 2);
        System.out.println(no3169.countDays(5, new int[][]{{2, 4}, {1, 3}}) == 1);
        System.out.println(no3169.countDays(6, new int[][]{{1, 6}}) == 0);
    }


}

package leetCode.repository;

import java.util.Arrays;

public class No3290 {

    public long maxScore(int[] a, int[] b) {

        long[][] dp = new long[a.length][b.length];
        for (long[] longs : dp) {
            Arrays.fill(longs, Long.MIN_VALUE);
        }

        dp[0][0] = (long) a[0] * b[0];
        dp[1][1] = dp[0][0] + (long) a[1] * b[1];
        dp[2][2] = dp[1][1] + (long) a[2] * b[2];
        dp[3][3] = dp[2][2] + (long) a[3] * b[3];
        for (int i = 1; i < b.length; i++) {
            dp[0][i] = Math.max((long) a[0] * b[i], dp[0][i - 1]);
        }

        long result = Long.MIN_VALUE;
        for (int i = 1; i < a.length; i++) {
            for (int j = i + 1; j < b.length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - 1] + (long) a[i] * b[j]);
                result = Math.max(result, dp[i][j]);
            }
        }
        return Arrays.stream(dp[3]).max().getAsLong();
    }


}

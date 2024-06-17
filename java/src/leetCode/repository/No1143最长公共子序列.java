package leetCode.repository;

import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * @author jiangxiewei
 * @since 2021/10/29
 */
public class No1143最长公共子序列 {

    public static void main(String[] args) {
        int result = new No1143最长公共子序列().longestCommonSubsequence("abcde", "ace");
        System.out.println(result);
        assert result == 3;
        result = new No1143最长公共子序列().longestCommonSubsequence("abc", "abc");
        System.out.println(result);
        assert result == 3;
        result = new No1143最长公共子序列().longestCommonSubsequence("abc", "def");
        System.out.println(result);
        assert result == 0;
    }

    public int longestCommonSubsequence(String text1, String text2) {
        return new 动态规划法().apply(text1, text2);
    }

    /**
     * O(n^2)
     */
    public class 动态规划法 implements BiFunction<String,String,Integer> {

        @Override
        public Integer apply(String s, String s2) {
            //初始化数组,边界设置为0.省去边界判断. 从1开始
            int[][] dp = new int[s.length() + 1][s2.length() + 1];
            for (int i = 0; i < dp.length; i++) {
                dp[i][0] = 0;
            }
            Arrays.fill(dp[0], 0);
            //开始遍历
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[i].length; j++) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + (s.charAt(i-1) == s2.charAt(j-1) ? 1 : 0),
                            Math.max(dp[i - 1][j], dp[i][j - 1]));
                }
            }
            return dp[s.length()][s2.length()];
        }
    }

}

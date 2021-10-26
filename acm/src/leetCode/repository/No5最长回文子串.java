package leetCode.repository;

import java.util.function.Function;

/**
 * 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * @author jiangxiewei
 * @since 2021/10/27
 */
public class No5最长回文子串 {

    public static void main(String[] args) {
        No5最长回文子串 最长回文子串 = new No5最长回文子串();
        System.out.println(最长回文子串.longestPalindrome("babad")); //aba or bab
        System.out.println(最长回文子串.longestPalindrome("cbbd")); // bb
        System.out.println(最长回文子串.longestPalindrome("a")); //a
        System.out.println(最长回文子串.longestPalindrome("ac")); //a or c
    }

    public String longestPalindrome(String s) {
        return new 动态规划法().apply(s);
    }

    public class 动态规划法 implements Function<String, String> {

        @Override
        public String apply(String s) {
            //状态转移方程 dp[i][j] = (dp[i+1][j-1] or (j - i == 1) ) && (s[i]==s[j])
            boolean isPalindrome[][] = new boolean[s.length()][s.length()];
            //初始化,单字符一定是回文串
            for (int i = 0; i < isPalindrome.length; i++) {
                isPalindrome[i][i] = true;
            }
            int max = 0, maxL = 0, maxR = 0;
            for (int r = 1; r < s.length(); r++) {
                for (int l = 0; l < r; l++) {
                    isPalindrome[l][r] = s.charAt(l) == s.charAt(r) && (r - l == 1 || isPalindrome[l + 1][r - 1]);
                    if (isPalindrome[l][r] && r - l + 1 > max) {
                        max = r - l + 1;
                        maxL = l;
                        maxR = r;
                    }
                }
            }
            return s.substring(maxL, maxR + 1);
        }

    }



}

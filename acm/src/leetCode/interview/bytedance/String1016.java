package leetCode.interview.bytedance;

import java.util.Arrays;

/**
 * 字符串的排列
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 * 注意：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 * @author jiang
 * @date 2020/4/29
 */
public class String1016 {

    public static void main(String[] args) {
        String1016 str = new String1016();
        System.out.println(str.checkInclusion("ab", "eidbaooo"));
        System.out.println(str.checkInclusion("ab", "eidboaoo"));
        System.out.println(str.checkInclusion("adc", "dcda"));
    }

    public boolean checkInclusion(String s1, String s2) {
        return firstWay(s1, s2);
    }

    public boolean firstWay(String s1, String s2) {
        SearchStatus ss = new SearchStatus();
        for (char c : s1.toCharArray()) {
            ss.add(c);
        }
        SearchStatus sscp = ss.clone();
        char[] chars = s2.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!sscp.find(chars[i])) {
                i -= (ss.total - sscp.total) - 1;
                sscp = ss.clone();
            }
            if (sscp.total == 0) {
                return true;
            }
        }
        return false;
    }

    public class SearchStatus {
        int[] mapping = new int[256];
        int total = 0;

        public void add(char c) {
            this.total++;
            this.mapping[c]++;
        }

        public boolean find(char c) {
            --this.mapping[c];
            if (--this.total < 0 || this.mapping[c] < 0) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        protected SearchStatus clone() {
            SearchStatus ss = new SearchStatus();
            ss.mapping = Arrays.copyOf(this.mapping, 256);
            ss.total = this.total;
            return ss;
        }
    }

}

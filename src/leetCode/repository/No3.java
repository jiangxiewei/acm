package leetCode.repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * <p>
 * 示例：
 * <p>
 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
 * <p>
 * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
 * <p>
 * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
 */
public class No3 {

    /**
     * 查找最长不重复子串
     *
     * @param s 字符串
     * @return 最长不重复子串长度
     */
    public int lengthOfLongestSubstring(String s) {
        return windowSlide(s);
    }

    public int windowSlide(String s) {
        //记录每个数字最后出现的位置
        Map<Character, Integer> lastPos = new HashMap<>(s.length());
        int max = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            Integer lastJ = lastPos.get(s.charAt(j));
            if (null != lastJ) {
                //如果j在之前出现过,则将左窗口向右快速移动至j之前出现过的位置之后.
                // (注意:当然如果不是向右移动则不移动,即i可能>lastJ+1)
                i = Math.max(lastJ + 1, i);
            }
            max = Math.max(max, j - i + 1);
            //记录每个字符最后一次出现的坐标
            lastPos.put(s.charAt(j), j);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new No3().lengthOfLongestSubstring("tmmzuxt"));
        System.out.println(new No3().lengthOfLongestSubstring("abcabcbb"));//3
        System.out.println(new No3().lengthOfLongestSubstring("bbbbb"));//1
        System.out.println(new No3().lengthOfLongestSubstring("pwwkew"));//3
        System.out.println(new No3().lengthOfLongestSubstring(" "));//1
        System.out.println(new No3().lengthOfLongestSubstring("abba"));//2
    }

}

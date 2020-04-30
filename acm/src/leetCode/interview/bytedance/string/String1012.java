package leetCode.interview.bytedance.string;

/**
 * 无重复字符的最长子串
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @apiNote [https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1012/]
 * @author jiang
 * @date 2020/4/29
 */
public class String1012 {

    public static void main(String[] args) {
        String1012 string1012 = new String1012();
        int result = string1012.lengthOfLongestSubstring("abcabcbb");
        System.out.println(result == 3 ? true : result);
        result = string1012.lengthOfLongestSubstring("bbbbb");
        System.out.println(result == 1 ? true : result);
        result = string1012.lengthOfLongestSubstring("pwwkew");
        System.out.println(result == 3 ? true : result);
    }

    public int lengthOfLongestSubstring(String s) {
        return firstWay(s);
    }

    public int firstWay(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int maxCount = 1, count = 1, start = 0;
        int[] ccMap = new int[256];
        ccMap[chars[start]]++;
        for (int end = 1; end < chars.length; end++) {
            int currentAdded = ++ccMap[chars[end]];
            count++;
            if (currentAdded > 1) {
                //开go
                while (ccMap[chars[end]] > 1 && start < end) {
                    ccMap[chars[start++]]--;
                    count--;
                }
            } else {
                //继续
                maxCount = Math.max(maxCount, count);
            }
        }
        return maxCount;
    }

}

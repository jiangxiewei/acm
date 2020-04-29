package leetCode.interview.bytedance;

/**
 * 最长公共前缀 ,所有输入只包含小写字母 a-z 。
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * @apiNote https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1014/
 * @author jiang
 * @date 2020/4/29
 */
public class String1014 {

    public static void main(String[] args) {
        String1014 str = new String1014();
        System.out.println(str.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(str.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        System.out.println(str.longestCommonPrefix(new String[]{"c", "c"}));
    }

    public String longestCommonPrefix(String[] strs) {
        return firstWay(strs);
    }

    public String firstWay(String[] strs) {
        if (strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }
        int min = Integer.MAX_VALUE;
        for (String s : strs) {
            min = Math.min(s.length(), min);
        }
        int[] mappingCount = new int[256];
        int maxCommon = 0;
        for (int i = 0; i < min; i++) {
            char lastChar = 0;
            for (String s : strs) {
                lastChar = s.charAt(i);
                mappingCount[lastChar]++;
            }
            if (mappingCount[lastChar] % strs.length != 0) {
                return strs[0].substring(0, maxCommon);
            } else {
                maxCommon++;
            }
        }
        return strs[0].substring(0, maxCommon);
    }

}

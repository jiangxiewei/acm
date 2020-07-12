package leetCode.repository;

/**
 * 给你一个二进制字符串 s（仅由 '0' 和 '1' 组成的字符串）。
 * 返回所有字符都为 1 的子字符串的数目。
 * 由于答案可能很大，请你将它对 10^9 + 7 取模后返回
 * <p>
 * 输入：s = "0110111"
 * 输出：9
 * 解释：共有 9 个子字符串仅由 '1' 组成
 * "1" -> 5 次
 * "11" -> 3 次
 * "111" -> 1 次
 * <p>
 * 输入：s = "101"
 * 输出：2
 * 解释：子字符串 "1" 在 s 中共出现 2 次
 * <p>
 * 输入：s = "111111"
 * 输出：21
 * 解释：每个子字符串都仅由 '1' 组成
 * <p>
 * 提示：
 * <p>
 * s[i] == '0' 或 s[i] == '1'
 * 1 <= s.length <= 10^5
 *
 * @author jxw
 * @date 2020/7/12
 */
public class No5461NumberOfSubstringsWithOnly1s {

    public static void main(String[] args) {
        No5461NumberOfSubstringsWithOnly1s no = new No5461NumberOfSubstringsWithOnly1s();
        //9
        System.out.println(no.numSub("0110111"));
        //2
        System.out.println(no.numSub("101"));
        //21
        System.out.println(no.numSub("111111"));
        //62
        System.out.println(no.numSub("1111111111011010011"));
    }

    public int numSub(String s) {
        char[] chars = s.toCharArray();
        int count = 0, result = 0;
        for (int i = 0; i < chars.length; i++) {
            //连续的1部分等差数列求和即可.并且可边遍历边求和.
            if (chars[i] == '1') {
                count++;
                //读题要仔细,别漏了这个MOD.
                result = (result + count) % (int) (1e9 + 7);
            } else {
                count = 0;
            }
        }
        return result;
    }

}

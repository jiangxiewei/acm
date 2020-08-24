package leetCode.repository;


import java.util.Stack;
import java.util.function.Function;

/**
 * 给你一个由大小写英文字母组成的字符串 s 。
 * <p>
 * 一个整理好的字符串中，两个相邻字符 s[i] 和 s[i + 1] 不会同时满足下述条件：
 * <p>
 * 0 <= i <= s.length - 2
 * s[i] 是小写字符，但 s[i + 1] 是相同的大写字符；反之亦然 。
 * 请你将字符串整理好，每次你都可以从字符串中选出满足上述条件的 两个相邻 字符并删除，直到字符串整理好为止。
 * <p>
 * 请返回整理好的 字符串 。题目保证在给出的约束条件下，测试样例对应的答案是唯一的。
 * <p>
 * 注意：空字符串也属于整理好的字符串，尽管其中没有任何字符。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "leEeetcode"
 * 输出："leetcode"
 * 解释：无论你第一次选的是 i = 1 还是 i = 2，都会使 "leEeetcode" 缩减为 "leetcode" 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/make-the-string-great
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author jxw
 * @date 2020/8/25
 */
public class No1544MakeTheStringGreat {

    public static void main(String[] args) {
        No1544MakeTheStringGreat no = new No1544MakeTheStringGreat();
        System.out.println(no.makeGood("leEeetcode"));
        System.out.println(no.makeGood("abBAcC"));
    }


    public String makeGood(String s) {
        Function<String, String> function = new StackWay();
        return function.apply(s);
    }

    /**
     * 第二种方式,其实这题很像括号匹配,可以用栈来做.
     */
    static class StackWay implements Function<String, String> {

        @Override
        public String apply(String s) {
            Stack<Character> stack = new Stack<>();
            char[] chars = s.toCharArray();
            for (char c : chars) {
                //空栈直接入
                if (stack.isEmpty()) {
                    stack.push(c);
                } else if (Character.toLowerCase(stack.peek()) == Character.toLowerCase(c) && stack.peek() != c) {
                    //与栈顶相同则出栈.
                    stack.pop();
                } else {
                    //否则入栈
                    stack.push(c);
                }
            }
            //出栈生成结果
            char[] result = new char[stack.size()];
            while (!stack.isEmpty()) {
                result[stack.size() - 1] = stack.pop();
            }
            return new String(result);
        }

    }

    /**
     * 第一种递归方式,层层递归.
     */
    static class MyFuckingDfs implements Function<String, String> {

        private boolean[] vis;

        @Override
        public String apply(String s) {
            vis = new boolean[s.length()];
            char[] chars = s.toCharArray();
            findAndMerge(chars, 0);
            int offset = 0;
            for (int i = 0; i < chars.length; i++) {
                if (!vis[i]) {
                    chars[offset++] = chars[i];
                }
            }
            return new String(chars, 0, offset);
        }

        private void findAndMerge(char[] chars, int i) {
            int j = i + 1;
            if (j < chars.length) {
                findAndMerge(chars, j);
            } else {
                return;
            }
            for (; j < chars.length; j++) {
                if (!vis[j]) {
                    break;
                }
            }
            if (j < chars.length && (chars[i] + 32 == chars[j] || chars[i] - 32 == chars[j])) {
                vis[i] = vis[j] = true;
            }
        }

    }
}

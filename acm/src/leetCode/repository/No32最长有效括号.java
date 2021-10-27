package leetCode.repository;

import java.util.Stack;
import java.util.function.Function;

/**
 * 32. 最长有效括号
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 * @author jiangxiewei
 * @since 2021/10/27
 */
public class No32最长有效括号 {

    public static void main(String[] args) {
        System.out.println(new No32最长有效括号().longestValidParentheses("(()"));
        System.out.println(new No32最长有效括号().longestValidParentheses(")()())"));
        System.out.println(new No32最长有效括号().longestValidParentheses(""));
    }

    public int longestValidParentheses(String s) {
        return new 栈().apply(s);
    }

    public static class 栈 implements Function<String, Integer> {

        private static final char LEFT = '(';
        private static final char RIGHT = ')';

        @Override
        public Integer apply(String s) {
            Stack<Integer> stack = new Stack<>();
            //当出现不匹配右括号时,左边的都不用看了. 此时leftOffset更新,栈意味着针对leftOffset之后的数据进行统计.
            int leftOffset = -1, maxLength = 0;
            for (int i = 0; i < s.length(); i++) {
                if (LEFT == s.charAt(i)) {
                    //左,压栈,等待右来匹配
                    stack.push(i);
                } else if (RIGHT == s.charAt(i)) {
                    if (stack.isEmpty()) {
                        //右,如果栈里为空. 则刷新leftOffset,意味着后续匹配最多只能到leftOffset
                        leftOffset = i;
                    } else if (s.charAt(stack.peek()) == LEFT) {
                        //右,如果栈顶为左,则匹配消除. 此时栈顶如果存在,则计算i到栈顶为匹配长度. 如果栈为空,则计算i到leftOffset
                        stack.pop();
                        maxLength = Math.max(i - (stack.isEmpty() ? leftOffset : stack.peek()), maxLength);
                    }
                }
            }
            return maxLength;
        }

    }

}

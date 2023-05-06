package leetCode.repository;

import java.util.Stack;
import java.util.function.Function;

/**
 *
 * 样例: "(()(()))"
 * <br/>
 * 原题链接:<a href="https://leetcode-cn.com/problems/score-of-parentheses/">https://leetcode-cn.com/problems/score-of-parentheses/</a>
 * @author jiang
 * @date 2022/4/28
 */
public class No856括号的分数 {

    public static void main(String[] args) {
        System.out.println(new No856括号的分数().scoreOfParentheses("(()(()))"));
    }

    /**
     * 括号的分数
     * @param s 字符串,由 "("和")"组成
     * @return 分数
     */
    public int scoreOfParentheses(String s) {
        return new MyOwnStack().apply(s);
    }

    /**
     * 方法三: 我的栈思路 <br/>
     * 每遇到一个'('压栈,并给此'('维护一个对应的结果值,然后当遇到')'时,出栈'('并将结果统计至栈顶的'('结果内
     *
     */
    static class MyOwnStack implements Function<String, Integer> {

        @Override
        public Integer apply(String s) {
            Stack<LeftParenthese> stack = new Stack<>();
            int result = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.add(new LeftParenthese(i));
                } else {
                    //开始结算此右括号,并根据有无栈顶元素决定输出目标
                    LeftParenthese top = stack.pop();
                    //如果是相连的贡献为1,否则贡献子集的结果*2
                    int inc = i - top.pos == 1 ? 1 : top.value * 2;
                    if (stack.isEmpty()) {
                        result += inc;
                    } else {
                        stack.peek().incVal(inc);
                    }
                }
            }
            return result;
        }

        class LeftParenthese {
            private int value = 0;
            private final int pos;

            public LeftParenthese(int pos) {
                this.pos = pos;
            }

            public void incVal(int increment) {
                this.value += increment;
            }

        }

    }

    /**
     * 方法二: 数学思维 <br/>
     * (()(())) 分解就是 (1+1*2)*2 即 (1*2) + (1*2*2) 拆解到最后,一定是所有提供1的"()"对乘以 2^所在深度 为结果
     */
    static class MathConsider implements Function<String, Integer> {

        @Override
        public Integer apply(String s) {
            int ans = 0, depth = 0;
            for (int i = 0; i < s.length(); i++) {
                // 统计深度, 如果遇到'()',则根据深度统计贡献值
                if ('(' == s.charAt(i)) {
                    //如果紧跟着一个')',则结算贡献
                    if (')' == s.charAt(i + 1)) {
                        ans += 1 << depth;
                    }
                    depth++;
                } else if (')' == s.charAt(i)) {
                    depth--;
                }
            }
            return ans;
        }
    }

    /**
     * 方法一 : 暴力解 <br/>
     * 针对每一个 "(" 寻找到对应的 ")",分治中间的部分
     */
    static class BruteForce implements Function<String,Integer> {

        private String input;

        @Override
        public Integer apply(String s) {
            this.input = s;
            return calcValue(0, s.length());
        }

        public int calcValue(int left, int end) {
            int sum = 0;
            for (int i = left; i < end; ) {
                int tRight = getRelativeRight(input, i);
                if (tRight - i == 1) {
                    sum++;
                } else {
                    sum += calcValue(i + 1, tRight) * 2;
                }
                i = tRight + 1;
            }
            return sum;
        }

        /**
         * 寻找pos左括号对应的右括号位置,
         * 通过将 ( 记为 -1 , ) 记为1 的方式统计,当统计结果为0时,此右括号为目标值
         * @param s 串
         * @param pos (位置
         * @return )位置
         */
        private static int getRelativeRight(String s,int pos) {
            if (s.charAt(pos) == '(') {
                //寻找此括号对应的右括号
                int sum = 0;
                for (int i = pos; i < s.length(); i++) {
                    if (s.charAt(i) == '(') {
                        sum += -1;
                    } else {
                        sum += 1;
                    }
                    if (sum == 0) {
                        return i;
                    }
                }
                throw new RuntimeException("无法找到匹配的右括号");
            } else {
                throw new RuntimeException("无法计算')'");
            }
        }

    }


}

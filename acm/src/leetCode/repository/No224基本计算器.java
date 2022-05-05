package leetCode.repository;

import java.util.*;

/**
 *
 * @author jiang
 * @date 2022/5/4
 */
public class No224基本计算器 {

    public static void main(String[] args) {
        System.out.println(new No224基本计算器().calculate(" 2-1 + 2 "));
        System.out.println(new No224基本计算器().calculate("1-(-2)"));
        System.out.println(new No224基本计算器().calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(new No224基本计算器().calculate("(1+(5+5*2)/3)+(6+8)"));
    }

    public int calculate(String s) {
        return new 后缀表达式().calculate(s);
    }

    /**
     * 彻底参考后缀表达式的方案,先生成后缀表达式,再用后缀表达式计算
     */
    static class 后缀表达式 {

        public int calculate(String s) {
            //词法解析
            List<Token> tokens = lex(s);
            System.out.println("词法解析结果:" + tokens);
            //后缀表达式
            List<Token> suffixTokens = transferToSuffix(tokens);
            System.out.println("后缀表达式结果:" + suffixTokens);
            //计算结果
            return calcSuffix(suffixTokens);
        }

        /**
         * 运算后缀表达式
         *
         * @param suffixTokens 后缀表达式
         * @return 返回结果
         */
        private int calcSuffix(List<Token> suffixTokens) {
            Stack<Integer> calcStack = new Stack<>();
            for (Token token : suffixTokens) {
                if (token instanceof Num) {
                    //数字,压栈
                    calcStack.push(((Num) token).getValue());
                } else if (token instanceof Ops) {
                    //加或者减吧
                    int beOps = calcStack.pop();
                    int num = calcStack.pop();
                    char ops = ((Ops) token).getOps();
                    switch (ops) {
                        case '-' -> calcStack.push(num - beOps);
                        case '+' -> calcStack.push(num + beOps);
                        case '*' -> calcStack.push(num * beOps);
                        case '/' -> calcStack.push(num / beOps);
                        default -> throw new RuntimeException("unsupported operation");
                    }
                }
            }
            return calcStack.pop();
        }

        /**
         * 转化成后缀表达式
         *
         * @param tokens token列表
         * @return 后缀表达式
         */
        private List<Token> transferToSuffix(List<Token> tokens) {
            Stack<Token> result = new Stack<>();
            Stack<Ops> opsStack = new Stack<>();
            for (Token token : tokens) {
                if (token instanceof Num) {
                    result.push(token);
                } else if (token instanceof Ops) {
                    if (((Ops) token).isLeftPar()) {
                        // 左括号,直接push
                        opsStack.push((Ops) token);
                    } else if (((Ops) token).isRightPar()) {
                        // 右括号,出栈推入后缀表达式,直至左括号
                        while (!opsStack.peek().isLeftPar()) {
                            result.push(opsStack.pop());
                        }
                        opsStack.pop();
                    } else {
                        // 加减乘除运算符
                        while (!opsStack.isEmpty() && opsStack.peek().isNotPar()
                                && !((Ops) token).isHighPrioBefore(opsStack.peek())) {
                            // 如果新加入的运算符优先级不大于栈顶运算符优先级,推入后缀表达式
                            result.push(opsStack.pop());
                        }
                        opsStack.push((Ops) token);
                    }
                }
            }
            while (!opsStack.isEmpty()) {
                result.add(opsStack.pop());
            }
            return result.stream().toList();
        }

        /**
         * 词法分析
         * @param s 输入
         * @return token列表
         */
        private List<Token> lex(String s) {
            List<Token> tokenList = new ArrayList<>();
            char[] input = s.replace(" ", "").toCharArray();
            if (input[0] == '-') {
                // 如果负号开始,则给起始处添加前缀0
                tokenList.add(new Num(0));
            }
            for (int i = 0; i < input.length; i++) {
                if (Character.isDigit(input[i])) {
                    // 数字就要连读
                    int number = input[i] - '0';
                    while (++i < input.length && Character.isDigit(input[i])) {
                        number = number * 10 + (input[i] - '0');
                    }
                    i--;
                    tokenList.add(new Num(number));
                } else if (input[i] == '(' && input[i + 1] == '-') {
                    //  如果左括号后面跟负号,则在负号前追加0抵消负数解析.  1-(-2) --> 1-(0-2)
                    //  (此方法应对此题没问题,因为只有加减法,然而有更高优先级的算数符的时候就要考虑别的方案)
                    //  失败样例: 1-(-2/2) --> 1-(0-2/2)
                    tokenList.add(new Ops(input[i]));
                    tokenList.add(new Num(0));
                } else {
                    tokenList.add(new Ops(input[i]));
                }
            }
            return tokenList;
        }

        /**
         * 数字 token
         */
        class Num extends Token {

            private final int value;

            public Num(int value) {
                this.value = value;
            }

            public int getValue() {
                return this.value;
            }

            public String toString() {
                return String.valueOf(value);
            }
        }

        class Ops extends Token {

            private final char ops;

            public Ops(char ops) {
                this.ops = ops;
            }

            private char getOps() {
                return this.ops;
            }

            boolean isLeftPar() {
                return ops == '(';
            }

            boolean isRightPar() {
                return ops == ')';
            }

            boolean isNotPar() {
                return ops != '(' && ops != ')';
            }

            @Override
            public String toString() {
                return String.valueOf(ops);
            }

            int getPrio() {
                return switch (this.ops) {
                    case '+', '-' -> 1;
                    case '*', '/' -> 2;
                    default -> throw new RuntimeException("unknow token");
                };
            }

            /**
             * 是否有更高优先级
             *
             * @param ops 被比较对象
             * @return 有更高优先级返回true
             */
            boolean isHighPrioBefore(Ops ops) {
                return getPrio() > ops.getPrio();
            }

        }

        abstract class Token {

        }

    }

    /**
     * 第一反应凭感觉写的思路,参考了后缀表达式的双栈方案. <br/>
     * 单独的负号运算符对应处理方案是追加一个0,比如 (-1) 处理成 (0-1) <br/>
     * 所有负数入栈前处理成 负数入栈 , (0-1) 处理成 (0+(-1))
     */
    static class MyFirstWay {

        public int calculate(String s) {
            //去掉空格以及修改前缀-
            String input = s.replace(" ","");
            if (input.startsWith("-")) {
                input = "0" + input;
            }
            Stack<Integer> ns = new Stack<>();
            Stack<Character> cs = new Stack<>();
            for (int i = 0; i < input.length(); i++) {
                char t = input.charAt(i);
                if (Character.isDigit(t)) {
                    int number = t - '0';
                    //读取所有数字,组成一个算数
                    while (++i < input.length() && Character.isDigit(t = input.charAt(i))) {
                        number = number * 10 + (t - '0');
                    }
                    if (!cs.isEmpty() && cs.peek() == '-') {
                        cs.pop();
                        cs.push('+');
                        ns.push(-number);
                    } else {
                        ns.push(number);
                    }
                    i--;
                } else if (t == '+' || t == '-') {
                    //算数符,直接塞入
                    cs.push(t);
//                System.out.println("push:" + t);
                } else if (t == '(') {
                    if (input.charAt(i + 1) == '-') {
                        ns.push(0);
                    }
                    cs.push(t);
                } else if (t == ')') {
                    //开始运算,到最近一个左括号
                    char ops;
                    int number = ns.pop();
                    while ((ops = cs.pop()) != '(') {
                        if (ops == '+') {
                            number += ns.pop();
                        }
                    }
                    //括号表达式的运算结果存入栈
                    if (!cs.isEmpty() && cs.peek() == '-') {
                        ns.push(-number);
                        cs.pop();
                        cs.push('+');
                    } else {
                        ns.push(number);
                    }
                }
            }
            //计算结果
//        System.out.println(ns);
//        System.out.println(cs);
            char ops;
            int result = ns.pop();
            while (!ns.isEmpty() && !cs.isEmpty()) {
                ops = cs.pop();
                if (ops == '+') {
                    result += ns.pop();
                }
            }
            return result;
        }

    }

}

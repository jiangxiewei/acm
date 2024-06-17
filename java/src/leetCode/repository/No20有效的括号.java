package leetCode.repository;

import java.util.Stack;

public class No20有效的括号 {

    public static void main(String[] args) {
        System.out.println(new No20有效的括号().isValid("()") == true);
        System.out.println(new No20有效的括号().isValid("()[]{}") == true);
        System.out.println(new No20有效的括号().isValid("(]") == false);
        System.out.println(new No20有效的括号().isValid("([)]") == false);
        System.out.println(new No20有效的括号().isValid("{[]}") == true);
    }

    public boolean isValid(String s) {
        return new Stack().isValid(s);
    }

    static class Stack implements IsValid {

        @Override
        public boolean isValid(String s) {
            java.util.Stack<Character> stack = new java.util.Stack<>();
            char[] carr = s.toCharArray();
            for (int i = 0; i < carr.length; i++) {
                switch (carr[i]) {
                    case '(', '[', '{' -> stack.push(carr[i]);
                    case ')' -> {
                        if (stack.size() > 0 && stack.peek() == '(') {
                            stack.pop();
                        } else {
                            return false;
                        }
                    }
                    case ']' -> {
                        if (stack.size() > 0 && stack.peek() == '[') {
                            stack.pop();
                        } else {
                            return false;
                        }
                    }
                    case '}' -> {
                        if (stack.size() > 0 && stack.peek() == '{') {
                            stack.pop();
                        } else {
                            return false;
                        }
                    }
                }
            }
            return stack.size() == 0;
        }


    }


    interface IsValid {
        public boolean isValid(String s);
    }

}

package codeforce.EducationalCodeforcesRound47;

import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class BMinimumTernaryString {

    /**
     * 越看越像智障,丢人代码
     *
     * @param s 输入
     */
    public static void stackSimulation(String s) {
        Stack<Character> stack = new Stack<>();
        boolean twoFlag = false, xFlag = false;
        char[] newChar = new char[s.length()];
        int point = 0;
        char cur;
        for (int i = 0; i < s.length(); i++) {
            cur = s.charAt(i);
            if (cur == '2') {
                //打个标记,方便后面0来找2组合成'x'
                twoFlag = true;
            }
            if (stack.empty()) {
                stack.push(cur);
            } else if (cur == '0' && twoFlag) {
                // 遇到0且栈内有2的情况,组合成x(代表20)
                while (stack.peek() != '2') {
                    newChar[point++] = stack.pop();
                }
                stack.pop();
                stack.push('x');
                twoFlag = false;
                xFlag = true;
            } else if (cur == '1' && xFlag) {
                //对于有20的情况,1肯定是要跑到20前面去的,故先输出
                newChar[point++] = '1';
            } else if (cur > stack.peek() && !xFlag) {
                //遇到当前比栈顶元素大的时候(2>1,1>0,2>0),出栈至不大于为止,若是存在x,那么将只剩下2,x,0的组合,而这个组合只用最后输出即可
                while (!stack.isEmpty() && stack.peek() < cur) {
                    newChar[point++] = stack.pop();
                }
                stack.push(cur);
            } else {
                stack.push(cur);
            }
//            System.out.println(stack);//输出中间过程,更清晰debug.
        }
        if (!xFlag) {
            while (!stack.empty()) {
                newChar[point++] = stack.pop();
            }
        } else {
            Stack<Character> xStack = new Stack<>();
            while (!stack.isEmpty()) {
                xStack.push(stack.pop());
            }
            while (!xStack.isEmpty()) {
                char next = xStack.pop();
                if (next == 'x') {
                    newChar[point++] = '2';
                    newChar[point++] = '0';
                } else {
                    newChar[point++] = next;
                }
            }
        }
        System.out.println(new String(newChar));
    }

    /**
     * 2与2之后的0的相对位置是不变的,2之后的1会全部移到2前面.
     *
     * @param s 输入
     */
    public static void twoAsSpliter(String s) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> q = new LinkedBlockingQueue<>();
        char[] newChar = new char[s.length()];
        int p = 0;
        boolean afterTwoFlag = false;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == '2') {
                afterTwoFlag = true;
                q.add(cur);
            }
            if (cur == '0') {
                if (afterTwoFlag) {
                    q.add(cur);
                } else {
                    stack.push(cur);
                }
            }
            if (cur == '1') {
                if (!stack.isEmpty() && stack.peek() < cur) {
                    while (!stack.isEmpty() && stack.peek() == '0') {
                        newChar[p++] = stack.pop();
                    }

                }
                stack.push(cur);
            }
        }
        while (!stack.isEmpty()) {
            newChar[p++] = stack.pop();
        }
        while (!q.isEmpty()) {
            newChar[p++] = q.poll();
        }
        System.out.println(new String(newChar));
    }

    public static void countWay(String s) {
        int zeroBeforeTwo = 0;
        int oneNum = 0;
        boolean twoFlag = false;
        Queue<Character> q = new LinkedBlockingQueue<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                oneNum++;
                continue;
            } else if (c == '2') {
                twoFlag = true;
            }
            if (twoFlag) {
                q.add(c);
            } else if (c == '0') {
                zeroBeforeTwo++;
            }
        }
        //开始生成新字符
        char[] newChar = new char[s.length()];
        int p = 0;
        for (int i = 0; i < zeroBeforeTwo; i++) {
            newChar[p++] = '0';
        }
        for (int i = 0; i < oneNum; i++) {
            newChar[p++] = '1';
        }
        while (!q.isEmpty()) {
            newChar[p++] = q.poll();
        }
        System.out.println(new String(newChar));
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
//        stackSimulation(s);
//        twoAsSpliter(s);
        countWay(s);
    }
}

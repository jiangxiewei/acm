package leetCode.repository;

import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class No84 {

    /**
     * 遍历数组
     * 1.栈为空则入栈
     * 2.若h[i]>=栈顶元素,入栈
     * 3.若h[i]<栈顶元素,则开始出栈计算面积,并将最后出栈的h[x]值改为h[i]再重新入栈
     * @param heights 数组,本例会创建个新数组,在数组最后一个位置补个高度为0的图
     * @return 最大面积值
     */
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        Stack<Integer> s = new Stack<>();
        int[] h = new int[heights.length + 1];
        for (int i = 0; i < h.length; i++) {
            h[i] = i == heights.length ? 0 : heights[i];
            if (s.empty()) {
                //空栈直接入栈
                s.push(i);
                continue;
            }
            if (h[i] < h[s.peek()]) {
                //遇到小于栈顶的值,开始出栈,查找最大面积
                int top = 0;
                while (!s.empty() && h[s.peek()] > h[i]) {
                    top = s.pop();
                    max = Math.max((i - top) * h[top], max);
                }
                //将最后出栈的高度改为h[i]重新入栈
                h[top] = h[i];
                s.push(top);
                s.push(i);
            } else {
                s.push(i);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        No84 n84 = new No84();
        System.out.println(n84.largestRectangleArea(new int[]{1,2,3,2,1,5,6,2,3,2,2}));
    }
}

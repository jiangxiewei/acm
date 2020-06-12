package leetCode.repository;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.Function;

/**
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * @author jiang
 * @date 2020/6/11
 */

public class No739DailyTemperatures {

    public static void main(String[] args) {
        No739DailyTemperatures no = new No739DailyTemperatures();
        //[1, 1, 4, 2, 1, 1, 0, 0]
        System.out.println(Arrays.toString(no.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }

    public int[] dailyTemperatures(int[] t) {
        Function<int[], int[]> func = new Greedy();
        return func.apply(t);
    }

    /**
     * 贪心解法,从右往左遍历,对于t[i],先找t[i+1]是否大于,如果不大于,则找t[t[i+1]] (即查找下一个比t[i+1]大的温度值.)
     */
    public static class Greedy implements Function<int[], int[]> {

        @Override
        public int[] apply(int[] t) {
            int[] result = new int[t.length];
            for (int i = result.length - 1; i > 0; i--) {
                int pos = i;
                //迭代查找下一个更大的值,直到找到t[pos]比目标(t[i-1])大的值.
                //如果result[pos]==0说明没有下一个比t[pos]大的值了.
                while (result[pos] !=0 && t[pos] <= t[i - 1]) { //递增1
                    pos += result[pos];
                }
                if (t[pos] > t[i - 1]) {
                    result[i - 1] = pos - i + 1;
                }
            }
            return result;
        }
    }

    /**
     * 单调栈解法.
     */
    public static class MonotonicStack implements Function<int[], int[]> {

        @Override
        public int[] apply(int[] t) {
            int[] result = new int[t.length];
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < t.length; stack.push(i++)) {
                while (!stack.isEmpty() && t[stack.peek()] < t[i]) {
                    int pop = stack.pop();
                    result[pop] = i - pop;
                }
            }
            return result;
        }
    }

}

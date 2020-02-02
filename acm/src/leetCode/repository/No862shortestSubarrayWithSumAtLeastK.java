package leetCode.repository;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 和至少为 K 的最短子数组
 *
 * @author jiang
 * @date 2020/1/29
 */
public class No862shortestSubarrayWithSumAtLeastK {

    /**
     * 测试main方法
     */
    public static void main(String[] args) {
        No862shortestSubarrayWithSumAtLeastK test = new No862shortestSubarrayWithSumAtLeastK();
        Validate[] validates = {
                new Validate(new int[]{1}, 1, 1),
                new Validate(new int[]{1, 2}, 4, -1),
                new Validate(new int[]{2, -1, 2}, 3, 3),
                new Validate(new int[]{-36, 10, -28, -42, 17, 83, 87, 79, 51, -26, 33, 53, 63, 61, 76, 34, 57, 68, 1, -30}, 484, 9)
        };
        for (Validate validate : validates) {
            int result = test.shortestSubarray(validate.a, validate.k);
            if (result != validate.result) {
                System.out.printf("-------------\r\na:%s\r\npre:%s\r\nk:%d, logical result:%d, now:%d\r\n",
                        Arrays.toString(validate.a), Arrays.toString(getPreSumArray(validate.a)),
                        validate.k, validate.result, result);
            }
        }
    }

    public int shortestSubarray(int[] A, int K) {
        return monotonically(A, K);
    }

    /**
     * 在增加y的过程中保持数组的单调性并去掉无用比较来减少比较量,使后续的遍历次数减少
     * 目标 : pre[y]-pre[x] >= K 使得 y-x尽可能小
     * 推断1 :
     * 当 x1 < x2 时. 若存在pre[x1] > pre[x2]那么就无需考虑x1的情况.
     * 因为 pre[y]-pre[x] >= K的条件若x2不能满足,x1肯定无法满足,即便x2满足了,那更加不用考虑x1的情况了(y-x2<y-x1).
     * 因此 x1从此不再需要判断,后续的y也都可忽视x1. (x1挪出比较队列)
     * 推断2 :
     * 若y2 > y1 且存在 pre[y1]-pre[x] 时(假设x为y1下最优解),则无需再考虑y2
     * 行为 :
     * 1.每当我们增加y的时候(y++),可以将y前大于pre[y]的值全过滤掉,结果是使得比较队列中的pre[x]值(x为队列中的值)是单调递增的
     * 并且后续的y比较时将不用再比较这些无需比较的x.
     * 2.当我们为某个y1找到最优的x1时,可以将x1从比较队列中去除,因为任何后续的y++后的y(设为y2)都不能可能使 y2-x1 < y1-x1
     **/
    private int monotonically(int[] a, int k) {
        long[] pre = getPreSumArray(a);
        int min = Integer.MAX_VALUE;
        LinkedList<Integer> list = new LinkedList<>();
        for (int y = 0; y < pre.length; y++) {
            //行为2,进行单调性清洗.
            while (!list.isEmpty() && pre[y] < pre[list.peekLast()]) {
                list.removeLast();
            }
            //行为1,符合目标函数的x可以剔除了
            while (!list.isEmpty() && pre[y] - pre[list.peekFirst()] >= k) {
                min = Math.min(min, y - list.removeFirst());
            }
            list.addLast(y);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * 常规数组解决方案,简单的循环.
     * 但是超时.
     */
    private int original(int[] a, int k) {
        long[] pre = getPreSumArray(a);
        int min = Integer.MAX_VALUE;
        for (int y = 1; y < pre.length; y++) {
            for (int x = y - 1; x >= 0; x--) {
                if (y - x < min) {
                    if (pre[y] - pre[x] >= k) {
                        min = y - x;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * 返回前缀和数组pre[]
     * sum(x,y) = a[x] + a[x+1] + ...... + a[y]
     * = pre[y] - pre[x-1]
     * 第0个位置补0,即pre[0]=0. 序号从1开始,sum(1,x) = pre[x] - pre[0] = pre[x]
     */
    private static long[] getPreSumArray(int[] a) {
        long[] pre = new long[a.length + 1];
        pre[0] = 0;
        for (int i = 0; i < a.length; i++) {
            pre[i + 1] = pre[i] + (long) a[i];
        }
        return pre;
    }

    static class Validate {
        public int[] a;
        public int k;
        public int result;

        public Validate(int[] a, int k, int result) {
            this.a = a;
            this.k = k;
            this.result = result;
        }
    }

}

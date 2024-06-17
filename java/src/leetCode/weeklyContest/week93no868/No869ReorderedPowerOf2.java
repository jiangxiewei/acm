package leetCode.weeklyContest.week93no868;

import java.util.ArrayList;
import java.util.List;

/**
 * 从正整数 N 开始，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false
 * <p>
 * 1 <= N <= 10^9
 *
 * @author Administrator
 * @date 2019/3/27
 */
public class No869ReorderedPowerOf2 {

    private static List<int[]> list = new ArrayList<>(32);

    {
        //提前生成10^9内所有的2的幂值,并统计每个幂值所需的数字数
        for (int i = 1; i <= 1E9; i <<= 1) {
            list.add(statisNum(i));
        }
    }

    /**
     * 统计各个数字出现的次数
     *
     * @param n 一个整数
     * @return 返回数组int[10]
     */
    private int[] statisNum(int n) {
        int[] statis = new int[10];
        while (n > 0) {
            statis[n % 10]++;
            n /= 10;
        }
        return statis;
    }

    /**
     * 判断两个数组是否相等(内容)
     *
     * @param a 数组A
     * @param b 数组B
     * @return 相等与否
     */
    private boolean judgeEqual(int[] a, int[] b) {
        for (int i = 0; i < 10; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean reorderedPowerOf2(int N) {
        int[] statis = statisNum(N);
        for (int i = 0; i < list.size(); i++) {
            if (judgeEqual(statis, list.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new No869ReorderedPowerOf2().reorderedPowerOf2(46));
    }

}

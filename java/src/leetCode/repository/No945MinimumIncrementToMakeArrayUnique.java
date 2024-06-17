package leetCode.repository;

import java.util.Arrays;

/**
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 * <p>
 * demo:
 * 输入：[1,2,2]
 * 输出：1
 *
 * @author jiang
 * @date 2020/3/22
 */
public class No945MinimumIncrementToMakeArrayUnique {

    public static void main(String[] args) {
        No945MinimumIncrementToMakeArrayUnique unique = new No945MinimumIncrementToMakeArrayUnique();
        System.out.println(unique.minIncrementForUnique(new int[]{1, 2, 2}));
        System.out.println(unique.minIncrementForUnique(new int[]{3, 2, 1, 2, 1, 7}));
    }

    public int minIncrementForUnique(int[] a) {
        return nextVersion(a);
    }

    /**
     * 排序法
     */
    public int nextVersion(int[] a) {
        int result = 0;
        Arrays.sort(a);
        for (int i = 1; i < a.length; i++) {
            if (a[i] <= a[i - 1]) {
                result += a[i - 1] + 1 - a[i];
                a[i] = a[i - 1] + 1;
            }
        }
        return result;
    }

    /**
     * 小笼包版,本质排序法.
     */
    public int ckVersion(int[] a) {
        int inc = 0;
        int s = 0;
        int pre = -1;
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            int interval = a[i] + s - pre;
            if (interval <= 0) {
                s += interval + 1;
            } else {
                s = Math.max(s - interval + 1, 0);
            }
            pre = a[i] + s;
            inc += s;
        }
        return inc;
    }

}

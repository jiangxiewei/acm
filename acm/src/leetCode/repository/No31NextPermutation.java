package leetCode.repository;

import java.util.function.Consumer;

/**
 * 31. 下一个排列
 * <p>
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 *
 * @author jiang
 * @date 2020/7/7
 */
public class No31NextPermutation {

    public static void main(String[] args) {
        No31NextPermutation n31 = new No31NextPermutation();
        n31.nextPermutation(new int[]{1, 2, 3});
        n31.nextPermutation(new int[]{3, 2, 1});
        n31.nextPermutation(new int[]{1, 1, 5});
        n31.nextPermutation(new int[]{2, 3, 1});
        //2,1,3
        n31.nextPermutation(new int[]{1, 3, 2});
        //4,3,2,1
        n31.nextPermutation(new int[]{4, 3, 1, 2});
    }

    public void nextPermutation(int[] nums) {
        Consumer<int[]> consumer = new FirstWay();
        consumer.accept(nums);
//        System.out.println(Arrays.toString(nums));
    }

    public static class FirstWay implements Consumer<int[]> {
        @Override
        public void accept(int[] a) {
            for (int i = a.length - 2; i >= 0; i--) {
                if (a[i] < a[i + 1]) {
                    for (int j = a.length - 1; j > i; j--) {
                        if (a[j] > a[i]) {
                            swap(a, i, j);
                            reverse(a, i + 1, a.length - 1);
                            return;
                        }
                    }
                }
            }
            reverse(a, 0, a.length - 1);
        }

        public void reverse(int[] a, int s, int e) {
            int length = e - s + 1;
            for (int i = s; 2 * (i - s) < length - 1; i++) {
                swap(a, i, e - (i - s));
            }
        }

        public void swap(int[] a, int i, int j) {
            a[j] = a[j] ^ a[i];
            a[i] = a[j] ^ a[i];
            a[j] = a[j] ^ a[i];
        }

    }

}

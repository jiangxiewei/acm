package leetCode.repository;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.function.BiFunction;

/**
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * @author jiangxiewei
 * @since 2021/9/15
 */
public class No215KthLargestElementInAnArray {

    public static void main(String[] args) {
        //样例1
        int result = new No215KthLargestElementInAnArray().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
        System.out.println(result);
        assert result == 5;
        //样例2
        result = new No215KthLargestElementInAnArray().findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
        System.out.println(result);
        assert result == 4;
    }

    public int findKthLargest(int[] nums, int k) {
        return new PriorityQueueSort().apply(nums, k);
    }

    /**
     * 优先队列(堆)实现第k大的数据
     */
    static class PriorityQueueSort implements BiFunction<int[],Integer,Integer> {

        @Override
        public Integer apply(int[] nums, Integer k) {
            PriorityQueue<Integer> q = new PriorityQueue<>(k);
            for (int num : nums) {
                if (q.size() < k) {
                    q.offer(num);
                } else if (q.peek() < num) {
                    q.poll();
                    q.offer(num);
                }
            }
            return q.peek();
        }

    }

    /**
     * 快排方式寻找第k个最大值.
     */
    static class QuickSort implements BiFunction<int[],Integer,Integer> {

        private int[] nums;
        private static final Random RANDOM = new Random();

        @Override
        public Integer apply(int[] nums, Integer k) {
            this.nums = nums;
            return null;
        }

        private void qsort(int start, int end) {
            for (int l = start, r = end; l < r; ) {

            }
        }

        private void getNextRef(int l, int r) {

        }
    }

}

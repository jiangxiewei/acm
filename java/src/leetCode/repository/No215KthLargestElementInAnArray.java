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
        return new MaxHeapSort().apply(nums, k);
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
     * 大根堆实现,每次提取出大根,第k次提取就是需要的第k大的值
     */
    static class MaxHeapSort implements BiFunction<int[], Integer, Integer> {

        int[] heap ;


        @Override
        public Integer apply(int[] ints, Integer k) {
            this.heap = ints;
            // 构建大根堆
            for (int i = heap.length / 2; i >= 0; i--) {
                maxHeapify(i);
            }
//            System.out.println(Arrays.toString(heap));
            // 进行节点delete ,共k次
            int lastPolled = 0;
            for (int i = 0; i < k; i++) {
                lastPolled = heap[0];
                heap[0] = Integer.MIN_VALUE;
                maxHeapify(0);
            }
            return lastPolled;
        }

        /**
         * 递归调整大根堆节点
         * @param pos 需要调整的节点id
         */
        void maxHeapify(int pos) {
            int leftSon = pos * 2 + 1;
            int rightSon = pos * 2 + 2;
            if (leftSon < heap.length && heap[pos] < heap[leftSon]) {
                swap(pos, leftSon);
                maxHeapify(leftSon);
            }
            if (rightSon < heap.length && heap[pos] < heap[rightSon]) {
                swap(pos, rightSon);
                maxHeapify(rightSon);
            }
        }

        void swap(int a, int b) {
            heap[a] = heap[a] ^ heap[b];
            heap[b] = heap[a] ^ heap[b];
            heap[a] = heap[a] ^ heap[b];
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

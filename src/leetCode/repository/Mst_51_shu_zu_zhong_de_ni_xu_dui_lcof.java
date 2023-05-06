package leetCode.repository;

import java.util.*;

/**
 * @author jiang
 * @date 2020/3/30
 */
public class Mst_51_shu_zu_zhong_de_ni_xu_dui_lcof {

    public static void main(String[] args) {
        Mst_51_shu_zu_zhong_de_ni_xu_dui_lcof lcof = new Mst_51_shu_zu_zhong_de_ni_xu_dui_lcof();
        System.out.println(lcof.reversePairs(new int[]{7, 5, 6, 4})); //5
        System.out.println(lcof.reversePairs(new int[]{1, 3, 2, 3, 1}));//4
        System.out.println(lcof.reversePairs(new int[]{4, 5, 6, 7}));//0
    }

    public int reversePairs(int[] nums) {
//        return divideAndConquer(nums);
        return bitWay(nums);
    }

    /**
     * 树状数组法
     *
     * @param nums 输入数组
     * @return 结果
     */
    public int bitWay(int[] nums) {
        long result = 0;
        List<Map.Entry<Integer, Integer>> lis = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            lis.add(new HashMap.SimpleEntry<>(nums[i], i + 1));
        }
        lis.sort(Map.Entry.comparingByKey());
        BinaryIndexedTree tree = new BinaryIndexedTree(nums.length + 1);
        for (Map.Entry<Integer, Integer> li : lis) {
            result += tree.search(li.getValue());
            tree.update(li.getValue(), 1);
        }
        return (int) ((long)nums.length * (nums.length - 1) / 2 - result);
    }

    /**
     * 分治法,一边归并排序,一边统计逆序对数量.
     *
     * @param nums 数量
     * @return 统计结果
     */
    public int divideAndConquer(int[] nums) {
        return mergeSortWithCount(nums, 0, nums.length - 1);
    }

    /**
     * 归并排序的同时进行统计左右闭区间.
     *
     * @param nums 原始数组
     * @param l    从l开始
     * @param r    排序到r结束
     * @return 统计结果
     */
    public int mergeSortWithCount(int[] nums, int l, int r) {
        int reversedCount = 0;
        if (r <= l) {
            return reversedCount;
        }
        int mid = (l + r) / 2;
        //left sort
        reversedCount += mergeSortWithCount(nums, l, mid);
        //rigth sort
        reversedCount += mergeSortWithCount(nums, mid + 1, r);
        // sort l to r
        int order = 0;
        int[] result = new int[r - l + 1];
        for (int li = l, ri = mid + 1; li <= mid || ri <= r; ) {
            if (li > mid) {
                result[order++] = nums[ri];
                ri++;
                continue;
            } else if (ri > r) {
                result[order++] = nums[li];
                li++;
                continue;
            }
            if (nums[li] <= nums[ri]) {
                //left小于等于right,正常排序.无逆序对
                result[order++] = nums[li];
                li++;
            } else {
                //left大于right,left里剩下的所有都能跟ri此时组成逆序对
                result[order++] = nums[ri];
                reversedCount += (mid - li + 1);
                ri++;
            }
        }
        if (order > 0) {
            System.arraycopy(result, 0, nums, l, order);
        }
        return reversedCount;
    }


    /**
     * @author jiang
     * @date 2020/4/4
     */
    private class BinaryIndexedTree {

        private final ArrayList<Long> c;

        public BinaryIndexedTree() {
            this(1024 + 1);
        }

        public BinaryIndexedTree(int initialCap) {
            this.c = new ArrayList<>(initialCap);
            for (int i = 0; i < initialCap; i++) {
                c.add(0L);
            }
        }

        private long lowbit(long a) {
            return a & -a;
        }

        public void update(int index, long add) {
            if (index == 0) {
                throw new IllegalArgumentException(" index can not be 0 ");
            }
            for (int i = index; i < c.size(); i += lowbit(i)) {
                c.set(i, c.get(i) + add);
            }
        }

        private long search(int index) {
            int result = 0;
            for (int i = index; i > 0; i -= lowbit(i)) {
                result += c.get(i);
            }
            return result;
        }

        public long sum(int from, int to) {
            if (from == 0) {
                return search(to);
            } else {
                return search(to) - search(from);
            }
        }


    }

}
package leetCode.repository;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 315. 计算右侧小于当前元素的个数
 * <p>
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * <p>
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 *
 * @author jxw
 * @date 2020/7/11
 */
public class No315CountOfSmallerNumbersAfterSelf {

    public static void main(String[] args) {
        No315CountOfSmallerNumbersAfterSelf no = new No315CountOfSmallerNumbersAfterSelf();
        System.out.println(no.countSmaller(new int[]{5, 2, 6, 1}));
        System.out.println(no.countSmaller(new int[]{-1, -1}));
        System.out.println(no.countSmaller(new int[]{26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38, 7, 35, 23, 52, 22,
                83, 51, 98, 69, 81, 32, 78, 28, 94, 13, 2, 97, 3, 76, 99, 51, 9, 21, 84, 66, 65, 36, 100, 41}));
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>(0);
        }
        int[] result = new int[nums.length];
        //离散化
        Pair<Integer, Integer>[] pairs = new Pair[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            pairs[i] = new Pair<>(i, nums[i]);
        }
        //统计
        BinaryIndexedTree bit = new BinaryIndexedTree(nums.length);
        Arrays.sort(pairs, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        Integer prevalue = pairs[pairs.length - 1].getValue();
        int count = -1;
        for (int i = pairs.length - 1; i >= 0; i--) {
            if (pairs[i].getValue().equals(prevalue)) {
                count++;
            } else {
                count = 0;
            }
            result[pairs[i].getKey()] = (int) bit.sum(pairs[i].getKey() + 1, pairs.length - 1) - count;
            bit.update(pairs[i].getKey(), 1);
            prevalue = pairs[i].getValue();
        }
        List<Integer> resultList = new ArrayList<>(nums.length);
        for (int i = 0; i < result.length; i++) {
            resultList.add(result[i]);
        }
        return resultList;
    }

    private class BinaryIndexedTree {

        private final ArrayList<Long> c;

        public BinaryIndexedTree() {
            this(1024 + 1);
        }

        public BinaryIndexedTree(int initialCap) {
            initialCap++;
            this.c = new ArrayList<>(initialCap);
            for (int i = 0; i < initialCap; i++) {
                c.add(0L);
            }
        }

        private long lowbit(long a) {
            return a & -a;
        }

        /**
         * 更新index位置的统计值
         *
         * @param index 取值范围[-1,initialCap]
         * @param add   添加的值
         */
        public void update(int index, long add) {
            index++;
            if (index < 0) {
                throw new IllegalArgumentException(" index can not be less than -1 ");
            }
            for (int i = index; i < c.size(); i += lowbit(i)) {
                c.set(i, c.get(i) + add);
            }
        }

        private long search(int index) {
            index++;
            int result = 0;
            for (int i = index; i > 0; i -= lowbit(i)) {
                result += c.get(i);
            }
            return result;
        }

        /**
         * @param from 起始点
         * @param to   结束点
         * @return 返回[from, to]的结果值.
         */
        public long sum(int from, int to) {
            return search(to) - search(from - 1);
        }

    }

}

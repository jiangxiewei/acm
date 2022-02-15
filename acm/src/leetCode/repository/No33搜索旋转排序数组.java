package leetCode.repository;

import java.util.function.BiFunction;

/**
 *
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * @apiNote https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * @author jiangxiewei
 * @since 2022/2/10
 */
public class No33搜索旋转排序数组 {

    public static void main(String[] args) {
        No33搜索旋转排序数组 no33 = new No33搜索旋转排序数组();
        // result should be 2
        System.out.println(no33.search(new int[]{5, 1, 3}, 3));
        // result should be 0
        System.out.println(no33.search(new int[]{3, 5, 1}, 3));
        // result should be 0
        System.out.println(no33.search(new int[]{1}, 1));
        // result should be 4
        System.out.println(no33.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        // result should be -1
        System.out.println(no33.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        // result should be -1
        System.out.println(no33.search(new int[]{-1}, 0));
    }

    public int search(int[] nums, int target) {
        return new AnalyzeBinarySearch().apply(nums, target);
//        return new Divide2AndBinarySearch().apply(nums, target);
    }

    /**
     * 一次二分, 根据每次mid的落点,一定能保证 [l,mid] or [mid,r] 是一个单调区间,判断处于单调区间的部分是否包含target再进行二分搜索即可.
     * 当 l < r 的时候则说明剩下的部分已经是完全的升序区间了,反之亦然.
     */
    public static class AnalyzeBinarySearch implements BiFunction<int[], Integer, Integer> {

        @Override
        public Integer apply(int[] nums, Integer target) {
            int l = 0, r = nums.length - 1, mid = (l + r) / 2;
            while (l < r - 1) {
                //开始二分
                if (nums[mid] == target) {
                    return mid;
                }
                //左区间升序,否则右区间升序
                boolean isLeftSort = nums[l] <= nums[mid], isRightSort = !isLeftSort,
                        targetInLeft = nums[l] <= target && target <= nums[mid],
                        targetInRight = nums[mid] <= target && target <= nums[r];
                //是否选择左区间
                boolean selectLeftPart = (isLeftSort && targetInLeft) || (isRightSort && !targetInRight);
                if (selectLeftPart) {
                    //使用左区间
                    r = mid;
                } else {
                    //使用右区间
                    l = mid;
                }
                mid = (l + r) / 2;
            }
            if (nums[r] == target) {
                return r;
            } else if (nums[l] == target) {
                return l;
            }
            return -1;
        }
    }

    /**
     * 分两次二分,第一次二分搜索旋转点在何处,然后根据旋转点分成两part,接着根据两part的范围选择一个part在进行一次二分搜搜
     */
    public static class Divide2AndBinarySearch implements BiFunction<int[], Integer, Integer> {

        private int[] nums;

        @Override
        public Integer apply(int[] nums, Integer target) {
            this.nums = nums;
            if (nums.length == 1) {
                if (nums[0] == target) {
                    return 0;
                } else {
                    return -1;
                }
            }
            //第一步,搜索旋转点,将数组分为两个部分
            int midPoint = searchRotatedPoint();
            int result = binarySearch(0, midPoint - 1, target);
            if (result == -1) {
                result = binarySearch(midPoint, nums.length-1, target);
            }
            return result;
        }

        public int binarySearch(int from, int to, int target) {
            int mid = (from + to) / 2;
            while (from < to) {
                if (target < nums[mid]) {
                    to = mid;
                } else if (target > nums[mid]) {
                    from = mid + 1;
                } else {
                    return mid;
                }
                mid = (from + to) / 2;
            }
            if (nums[from] == target) {
                return from;
            } else if (nums[to] == target) {
                return to;
            }
            return -1;
        }

        /**
         * 搜索一个节点,使节点左右都是升序数组. 如果整个数组都是升序的,那么就选个中心点.
         *
         * @return 分割点
         */
        public int searchRotatedPoint() {
            int l = 0, r = nums.length - 1, mid = (l + r + 1) / 2;
            boolean isLeftSort;
            while (!(isLeftSort = nums[l] <= nums[mid - 1]) || !(nums[mid] <= nums[r])) {
                if (isLeftSort) {
                    l = mid;
                } else {
                    r = mid;
                }
                mid = (l + r + 1) / 2;
            }
            return mid;
        }
    }

}

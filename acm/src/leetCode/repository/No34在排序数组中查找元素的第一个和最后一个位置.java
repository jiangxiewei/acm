package leetCode.repository;

import java.util.function.BiFunction;

/**
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 *
 * 进阶：
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 *
 * @author jiangxiewei
 * @since 2022/3/30
 */
public class No34在排序数组中查找元素的第一个和最后一个位置 {

    /**
     * 二分搜索,通过搜索 目标值-1 和 目标值+1 的坐标,快速查找到最接近目标的两个位置
     *
     * @param nums   数组
     * @param target 目标值
     * @return 左右区间
     */
    public int[] searchRange(int[] nums, int target) {
        return new BinarySearch().apply(nums, target);
    }

    /**
     * 两次二分,二分方法上要有略微差异,使得找到的结果一个最靠左,一个最靠右
     */
    static class BinarySearch implements BiFunction<int[], Integer, int[]> {

        @Override
        public int[] apply(int[] nums, Integer target) {
            if (nums.length == 0) {
                return new int[]{-1, -1};
            }
            int left = binarySearch(nums, target, true);
            int right = binarySearch(nums, target, false);
            while (left < nums.length && nums[left] != target) {
                left++;
            }
            while (right > 0 && nums[right] != target) {
                right--;
            }
            if (left > right) {
                return new int[]{-1, -1};
            }
            return new int[]{left, right};
        }

        /**
         * 二分解法,不断趋近目标值的左侧和右侧(根据trendLeft为true or false)
         *
         * @param nums      数组
         * @param target    目标值
         * @param trendLeft 趋近左或右
         * @return 结果集
         */
        public int binarySearch(int[] nums, int target, boolean trendLeft) {
            int l = 0, r = nums.length - 1, mid = (l + r) / 2;
            while (l < r) {
                if (nums[mid] < target) {
                    l = mid + 1;
                } else if (nums[mid] > target) {
                    r = mid - 1;
                } else if (trendLeft) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
                mid = (l + r) / 2;
            }
            return l;
        }

    }

    /**
     * 老辣鸡代码,引以为戒.
     * 思路是找 target-1 和 target+1 的位置然后往target靠近
     * 但是赵狄后来跟我说,假如target+1的重复数也很大,实际上也会有同样的降级效果. 所以此方案成废案了. 赵狄还是个有想法的小伙.
     */
    static class OldJunk implements BiFunction<int[], Integer, int[]> {

        @Override
        public int[] apply(int[] nums, Integer target) {
            if (nums.length == 0 || nums[binarySearch(nums, target)] != target) {
                return new int[]{-1, -1};
            }
            int l = binarySearch(nums, target - 1);
            while (nums[l] != target) {
                l++;
            }
            int r = binarySearch(nums, target + 1);
            while (nums[r] != target) {
                r--;
            }
            return new int[]{l, r};
        }


        /**
         * 二分,但是不管有没有找到,都会返回最接近的pos
         * @param nums 升序数组
         * @param target 目标值
         * @return 目标值如果存在,返回目标值pos,否则返回最接近目标值的结果
         */
        public int binarySearch(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = (l + r) / 2;
                if (nums[mid] < target) {
                    l = mid + 1;
                } else if (nums[mid] > target) {
                    r = mid - 1;
                } else {
                    return mid;
                }
            }
            return l;
        }

    }


}

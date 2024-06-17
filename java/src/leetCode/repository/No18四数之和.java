package leetCode.repository;

import java.util.*;

public class No18四数之和 {

    public static void main(String[] args) {
        System.out.println(new No18四数之和().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0)); // [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        System.out.println(new No18四数之和().fourSum(new int[]{2, 2, 2, 2, 2}, 8));      // [[2,2,2,2]]
        System.out.println(new No18四数之和().fourSum(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296));      // []
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        return new 夹逼().fourSum(nums, target);
    }

        class 夹逼 implements 四数之和 {

        @Override
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 3; i++) {
                for (int j = i + 1; j < nums.length - 2; j++) {
                    for (int l = j + 1, r = nums.length - 1; l < r; ) {
                        // 夹逼
                        long sum = (long)nums[i] + nums[j] + nums[l] + nums[r];
                        if (sum > target) {
                            while (r > 0 && nums[r] == nums[r - 1]) {
                                r--;
                            }
                            r--;
                        } else if (sum <= target) {
                            if (sum == target) {
                                result.add(List.of(nums[i], nums[j], nums[l], nums[r]));
                            }
                            while (l + 1 < nums.length && nums[l] == nums[1 + l]) {
                                l++;
                            }
                            l++;
                        }
                    }
                    while (j <nums.length-2 && nums[j] == nums[j+1]) {
                        j++;
                    }
                }
                while (i <nums.length-3 && nums[i] == nums[i+1]) {
                    i++;
                }
            }
            return result;
        }

    }

    interface 四数之和 {
        List<List<Integer>> fourSum(int[] nums, int target);
    }

}

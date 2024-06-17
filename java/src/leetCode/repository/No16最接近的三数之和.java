package leetCode.repository;

import java.util.Arrays;

public class No16最接近的三数之和 {

    public static void main(String[] args) {
        System.out.println(new No16最接近的三数之和().threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }

    int threeSumClosest(int[] nums, int target){
        return new MyFirstDoublePointerSolution().threeSumClosest(nums, target);
    }

    class MyFirstDoublePointerSolution implements MySolution {

        @Override
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int mostClose = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                for (int l = i+1, r = nums.length - 1; l < r; ) {
                    int sum = nums[i] + nums[l] + nums[r];
//                    System.out.printf("i:%d,l:%d,r:%d,sum:%d\n", i, l, r, sum);
                    mostClose = Math.abs(mostClose - target) > Math.abs(sum - target) ? sum : mostClose;
                    if (sum > target) {
                        r--;
                    } else {
                        l++;
                    }
                }
            }
            return mostClose;
        }
    }

    interface MySolution {
        int threeSumClosest(int[] nums, int target);
    }

}

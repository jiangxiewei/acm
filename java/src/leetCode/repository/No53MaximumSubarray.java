package leetCode.repository;


/**
 * @author gouqi
 * @date 2019/5/19
 */
public class No53MaximumSubarray {

    public static void main(String[] args) {
        System.out.println(new No53MaximumSubarray().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6);
        System.out.println(new No53MaximumSubarray().maxSubArray(new int[]{-1, -2, -3, -4, -5}) == -1);
    }

    /**
     * 递推方程 : dp[i] = max(dp[i-1]+dp[i],dp[i])
     *
     * @param nums 入参数组
     * @return 结果最大值
     */
    public int maxSubArray(int[] nums) {
        int premax = 0, maxResult = -Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            premax = Math.max(premax + nums[i], nums[i]);
            maxResult = Math.max(premax, maxResult);
        }
        return maxResult;
    }

}

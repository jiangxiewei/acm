package leetCode.repository;

/**
 * 最大子数组和
 * @author jiangxiewei
 * @since 2022/3/31
 */
public class No53最大子数组和 {


    /**
     * @param nums 数组
     * @return 结果
     */
    public int maxSubArray(int[] nums) {
        return mathThinkAfterOptimum(nums);
    }

    /**
     * 解法一: 动态规划  <br/>
     * dp[i]表示以 nums[i] 为结尾的最大子序和 ,
     * 递推方程 : dp[i] = max( dp[i-1] + dp[i] , dp[i] )
     *
     * @param nums 入参数组
     * @return 结果最大值
     */
    public int dpThink(int[] nums) {
        int dpi = 0, maxResult = -Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            //计算dp[i]
            dpi = Math.max(dpi + nums[i], nums[i]);
            //记录dp[i]是不是历史最大
            maxResult = Math.max(dpi, maxResult);
        }
        return maxResult;
    }

    /**
     * 解法二: 问题拆分 <br/>
     * 求最大子数组和,  <br/>
     * 设pre[x] = nums[0]+....+nums[x]  <br/>
     * 设区间[a,b] 为 sum(a,b) = pre[b] - pre[a-1]  <br/>
     * 所以求最大的sum(a,b)就是求最大的 pre[b] - pre[a-1] 并且 b > a-1  <br/>
     * 问题转换为 pre[x] - pre[y] ( x>y ) 求最值  <br/>
     * 所以接下来遍历x,针对每个x搜索最小的pre[y] (此处可记录从x往左看的最小pre[y]数组). <br/>
     * 优化后的方案: [前缀和计算] 和 [x往左看最小和值记录] 聚合到一个for循环内. <br/>
     * 空间复杂度: O(1) <br/>
     * 时间复杂度: O(n) <br/>
     *
     * @param nums 入参数组
     * @return 结果
     */
    public int mathThinkAfterOptimum(int[] nums) {
        int leftMin = 0, preSum = 0, maxSubSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            //当前前缀和 preSum ,相当于优化前的 preSum[i]
            preSum += nums[i];
            //记录 子区间和 是否为历史最大
            maxSubSum = Math.max(maxSubSum, preSum - leftMin);
            //计算leftMin(当前往左看最小的前缀和),相当于优化前的 preSumMin[i]
            leftMin = Math.min(leftMin, Math.min(preSum, 0));
        }
        return maxSubSum;
    }

    /**
     * 优化前的方案,前缀和计算 与 x往左看的最小和值计算 单独列出
     * 空间复杂度: O(n)
     * 时间复杂度: O(n)
     *
     * @param nums 输入数组
     * @return 结果
     */
    public int mathThinkBeforeOptimum(int[] nums) {
        //前缀和计算
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        //生成i往左看的最小值表
        int[] preSumMin = new int[nums.length];
        for (int i = 0; i < preSum.length; i++) {
            preSumMin[i] = Math.min(i > 0 ? preSumMin[i - 1] : 0, Math.min(preSum[i], 0));
        }
        //最后遍历一次,前缀和减去最小值表,记录最大值
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < preSum.length; i++) {
            maxSum = Math.max(maxSum, preSum[i] - (i > 0 ? preSumMin[i - 1] : 0));
        }
        return maxSum;
    }


}

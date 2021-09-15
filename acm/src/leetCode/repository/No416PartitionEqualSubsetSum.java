package leetCode.repository;

import java.util.function.Function;

/**
 * leetcode 416题 (https://leetcode-cn.com/problems/partition-equal-subset-sum/)
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 *
 * @author jiangxiewei
 * @since 2021/9/14
 */
public class No416PartitionEqualSubsetSum {

    public static void main(String[] args) {
        No416PartitionEqualSubsetSum no416 = new No416PartitionEqualSubsetSum();
        assert no416.canPartition(new int[]{1, 5, 11, 5});
        assert !no416.canPartition(new int[]{1, 2, 3, 5});
        assert !no416.canPartition(new int[]{1, 2, 5});
        assert no416.canPartition(new int[]{1, 1});
    }

    public boolean canPartition(int[] nums) {
        return new ZeroOnePackDP().apply(nums);
    }

    /**
     * 01背包思路
     */
    class ZeroOnePackDP implements Function<int[],Boolean> {

        @Override
        public Boolean apply(int[] ints) {
            int length = ints.length;
            //统计
            int sum = 0;
            for (int i = 0; i < ints.length; i++) {
                sum += ints[i];
            }
            if ((sum & 1) > 0) {
                //奇数,无法出结果
                return false;
            }
            //计算中间目标值
            int midSize = sum / 2;
            //初始化 dp 数组.
            boolean[][] dp = new boolean[length+1][midSize+1];
            for (int i = 0; i < midSize + 1; i++) {
                dp[0][i] = false;
            }
            dp[0][midSize] = true;
            //开始迭代. 递推方程式: dp[i][v] = dp[i][v] 或 dp[i][v-V[i]] (物品有价值,只通过true或false进行传递代表可达性.)
            for (int i = 1; i < dp.length; i++) {
                for (int v = midSize; v >= 0; v--) {
                    dp[i][v] = dp[i - 1][v] | (v + ints[i - 1] <= midSize && dp[i - 1][v + ints[i - 1]]);
                }
            }
            return dp[length - 1][0];
        }
    }

}



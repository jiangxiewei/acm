package leetCode.repository;

/**
 * 面试题17.16.按摩师
 * the-masseuse-lcci
 * <p>
 * 描述:
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。
 * 在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
 * 给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-masseuse-lcci
 *
 * @author jiang
 * @date 2020/3/24
 */
public class Mst_17_16_TheMasseuseLcci {

    public static void main(String[] args) {
        Mst_17_16_TheMasseuseLcci ci = new Mst_17_16_TheMasseuseLcci();
        System.out.println(ci.massage(new int[]{1, 2, 3, 1}));
        System.out.println(ci.massage(new int[]{2, 7, 9, 3, 1}));
        System.out.println(ci.massage(new int[]{2, 1, 4, 5, 3, 1, 1, 3}));
        System.out.println(ci.massage(new int[]{2, 1, 1, 2}));
    }

    public int massage(int[] nums) {
        return mySecondDfsWay(nums);
    }

    /**
     * 从尾递归的结果上看可以发现我需要的是上一个值添加与否分别的最优值,然后将当前值添加与否的两个最优结果传递下去.
     * 但是先推出尾递归再回来写DP感觉适得其反?感觉尾递归还是更好点
     */
    private int myDPWay(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }

    private int mySecondDfsWay(int[] nums) {
        return nums.length == 0 ? 0 : dfs2(nums, 1, nums[0], 0);
    }

    private int myFirstDfsWay(int[] nums) {
        return nums.length == 0 ? 0 :
                Math.max(dfs1(nums, 0, true, 0),
                        dfs1(nums, 0, false, 0));
    }

    /**
     * 由dfs1进一步推出来递归,尾递归了?.
     */
    private int dfs2(int[] nums, int i, int preAdded, int preUnadd) {
        if (i == nums.length) {
            return Math.max(preAdded, preUnadd);
        }
        return dfs2(nums, i + 1, nums[i] + preUnadd, Math.max(preAdded, preUnadd));
    }

    /**
     * 先尝试使用深搜,最后推算DP转移方程
     * 根据可知,
     * 对于当前的i需要知道上一个是否添加过
     * 如果上一个添加过,当前没有选择,
     * 但是上一个如果没添加过,那么就会有两个选择
     * <p>
     * dp[i][1] = dp[i-1][0] + nums[i]
     * dp[i][0] = max( dp[i-1][1] , dp[i-1][0] )
     */
    private int dfs1(int[] nums, int i, boolean preAdd, int preResult) {
        if (i == nums.length - 1) {
            return preResult + (preAdd ? 0 : nums[i]);
        }
        if (preAdd) {
            return dfs1(nums, i + 1, false, preResult);
        } else {
            return Math.max(
                    dfs1(nums, i + 1, true, preResult + nums[i]),
                    dfs1(nums, i + 1, false, preResult)
            );
        }
    }

}

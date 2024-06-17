package leetCode.repository;

/**
 * 这题出题初衷是希望用二分来做的,不过刚好这题用滑动窗口也可以处理
 *
 * @author jiangxiewei
 * @since 2022/3/31
 */
public class No209长度最小的子数组 {

    public int minSubArrayLen(int target, int[] nums) {
        return windowOfBinarySearch(target, nums);
    }

    /**
     * 尝试使用二分发搜索右区间.
     *
     * @param target 目标值
     * @param nums   数组
     * @return 结果
     */
    public int windowOfBinarySearch(int target, int[] nums) {
        //计算前缀和
        int[] pre = new int[nums.length];
        pre[0] = nums[0];
        for (int i = 1; i < pre.length; i++) {
            pre[i] = nums[i] + pre[i - 1];
        }
        //开始针对每一个左开区间i搜索一个右闭区间.
        int minLength = Integer.MAX_VALUE;
        for (int i = -1; i < nums.length - 1; i++) {
            int l = i + 1, r = nums.length - 1, mid = (l + r) / 2;
            int leftSum = i == -1 ? 0 : pre[i];
            while (l < r) {
                int midValue = pre[mid] - leftSum;
                if (midValue < target) {
                    l = mid + 1;
                } else if (midValue > target) {
                    r = mid - 1;
                } else {
                    l = mid;
                    break;
                }
                mid = (l + r) / 2;
            }
            //搜索结果记录
            if (pre[l] - leftSum >= target) {
                minLength = Math.min(minLength, l - i);
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * 滑动窗口处理方案.
     *
     * @param target 目标值
     * @param nums   数组
     * @return 最小长度
     */
    public int slideWindow(int target, int[] nums) {
        int[] pre = new int[nums.length];
        pre[0] = nums[0];
        for (int i = 1; i < pre.length; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }
        int l = -1, r = 0, minLength = Integer.MAX_VALUE;
        //只要l没到头,还能继续玩
        while (l < pre.length - 1) {
            int sumLp1ToR = pre[r] - (l == -1 ? 0 : pre[l]);
            if (sumLp1ToR < target) {
                //如果[l+1,r]区间和结果小于我们目标值,继续扩增r
                r++;
                if (r >= pre.length) {
                    //如果r到头了,说明 [l,r+1] 都小于target,直接结束.
                    break;
                }
            } else if (sumLp1ToR >= target) {
                //达到题目要求了,计算是否为最小区间.
                minLength = Math.min(r - l, minLength);
                r = ++l + 1;
            }
        }
        return minLength != Integer.MAX_VALUE ? minLength : 0;
    }

}

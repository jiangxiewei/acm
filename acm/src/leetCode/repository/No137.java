package leetCode.repository;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * 题目要求：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 思路有点像是数字电路设计一个计数器.此题的情况就是为每一位二进制进行计数,计数三次归零,那么最后没有归零的数就是出现一次的那个数位
 *
 * @author gouqi
 * @date 2018/8/30
 */
public class No137 {

    /**
     * @param nums 数组
     * @return 结果
     */
    public static int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int c : nums) {
            int tempA = (~a & b & c) + (a & ~b & ~c);
            b = (~a & ~b & c) + (~a & b & ~c);
            a = tempA;
        }
        return b;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 1, 0, 1, 99};
        System.out.println(singleNumber(nums));
    }

}

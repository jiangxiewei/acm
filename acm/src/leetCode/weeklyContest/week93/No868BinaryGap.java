package leetCode.weeklyContest.week93;

/**
 * No868BinaryGap
 * 给定一个正整数 N，找到并返回 N 的二进制表示中两个连续的 1 之间的最长距离。
 * 如果没有两个连续的 1，返回 0 。
 *
 * @author Administrator
 * @date 2019/3/27
 */
public class No868BinaryGap {

    public static void main(String[] args) {
        int result = new No868BinaryGap().binaryGap(6);
        System.out.println(result);
    }

    public int binaryGap(int N) {
        int result = 0, prePos = -1, cur = 0;
        for (int i = 0; N > 0; N >>= 1, i++) {
            cur = N & 1;
            //当前位置是1,计算距离上一个1的距离
            if (cur == 1) {
                if (prePos != -1) {
                    result = Math.max(result, i - prePos);
                }
                prePos = i;
            }
        }
        return result;
    }

}

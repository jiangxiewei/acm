package leetCode.repository;

/**
 * 给你一个二进制字符串 s（仅由 '0' 和 '1' 组成的字符串）。
 * <p>
 * 返回所有字符都为 1 的子字符串的数目。
 * <p>
 * 由于答案可能很大，请你将它对 10^9 + 7 取模后返回。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "0110111"
 * 输出：9
 * 解释：共有 9 个子字符串仅由 '1' 组成
 * "1" -> 5 次
 * "11" -> 3 次
 * "111" -> 1 次
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "101"
 * 输出：2
 * 解释：子字符串 "1" 在 s 中共出现 2 次
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "111111"
 * 输出：21
 * 解释：每个子字符串都仅由 '1' 组成
 * <p>
 * 示例 4：
 * <p>
 * 输入：s = "000"
 * 输出：0
 *
 * @author jiang
 * @date 2020/7/13
 */
public class No1515BestPositionForAServiceCentre {

    public static void main(String[] args) {
        No1515BestPositionForAServiceCentre center = new No1515BestPositionForAServiceCentre();
        //4
        System.out.println(center.getMinDistSum(new int[][]{{0, 1}, {1, 0}, {1, 2}, {2, 1}}));
        //2.73205
        System.out.println(center.getMinDistSum(new int[][]{{1, 1}, {0, 0}, {2, 0}}));
    }

    private int[][] positions;

    public double getMinDistSum(int[][] positions) {
        this.positions = positions;
        double l = 0, r = 100, lastResult = 0;
        while (Math.abs(r - l) > 1e-6) {
            //对新假设的x求一个'最优'的y.
            double midx = (l + r) / 2, midmidx = (midx + r) / 2, midy = findY(midx), midmidy = findY(midmidx);
            double midCalc = dis(midx, midy), midmidCalc = dis(midmidx, midmidy);
            if (midCalc < midmidCalc) {
                r = midmidx;
                lastResult = midmidCalc;
            } else {
                l = midx;
                lastResult = midCalc;
            }
        }
        return lastResult;
    }

    //在x确定情况下,三分夹逼查找y.  即对每一个x求0~100中y的近似值
    private double findY(double x) {
        double l = 0, r = 100;
        while (Math.abs(r - l) > 1e-6) {
            double mid = (l + r) / 2, midmid = (mid + r) / 2;
            double midCalc = dis(x, mid), mmCalc = dis(x, midmid);
            if (midCalc < mmCalc) {
                r = midmid;
            } else {
                l = mid;
            }
        }
        return l;
    }

    //计算(x,y)到所有点的欧几里得距离总和.
    private double dis(double x, double y) {
        double result = 0;
        for (int[] position : positions) {
            result += Math.sqrt((x - position[0]) * (x - position[0]) + (y - position[1]) * (y - position[1]));
        }
        return result;
    }

}

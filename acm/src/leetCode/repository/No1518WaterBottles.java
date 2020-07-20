package leetCode.repository;

/**
 * 1518. 换酒问题
 * <p>
 * 小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。
 * <p>
 * 如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
 * <p>
 * 请你计算 最多 能喝到多少瓶酒。
 * <p>
 * 输入：numBottles = 9, numExchange = 3
 * 输出：13
 * 解释：你可以用 3 个空酒瓶兑换 1 瓶酒。
 * 所以最多能喝到 9 + 3 + 1 = 13 瓶酒。
 * <p>
 * 链接：https://leetcode-cn.com/problems/water-bottles
 *
 * @author jxw
 * @date 2020/7/20
 */
public class No1518WaterBottles {

    public static void main(String[] args) {
        No1518WaterBottles a = new No1518WaterBottles();
        //13
        System.out.println(a.numWaterBottles(9, 3));
        //19
        System.out.println(a.numWaterBottles(15, 4));
        //6
        System.out.println(a.numWaterBottles(5, 5));
    }

    public int numWaterBottles(int numBottles, int numExchange) {
        int drinked = numBottles, empty = numBottles;
        while (empty / numExchange > 0) {
            int rest = empty % numExchange;
            empty = empty / numExchange;
            drinked += empty;
            empty += rest;
        }
        return drinked;
    }

}

package leetCode.repository;

import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author gouqi
 * @date 2019/8/7
 */
public class No42TrappingRainWater {

    public static void main(String[] args) {
        int result = new No42TrappingRainWater().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        System.out.println(result);
    }

    public int trap(int[] height) {
        if (height == null || height.length < 1) {
            return 0;
        }
        return splitInTwoNew(height);
    }
    
    /**
     * 方法二,与方法一本质相同,但是代码精简了很多.
     * 与分割成两段的方法本质相同,但是把两次遍历压缩到一次遍历,最后减去了重复相加的部分(刚好为整个面积)
     * @param height 数组
     * @return 雨水面积
     */
    public int splitInTwoNew(int[] height) {
        int maxL = 0, maxR = 0, result = 0;
        for (int i = 0; i < height.length; i++) {
            maxL = Math.max(maxL, height[i]);
            maxR = Math.max(maxR, height[height.length - i - 1]);
            result += maxL + maxR - height[i];
        }
        result -= height.length * maxL;
        return result;
    }

    /**
     * 方法一,最容易想到的直观思路.
     * 通过最大值将数组分成两段,分别计算可接收雨水
     * @param height 高度数组
     * @return 雨水面积
     */
    public int splitInTwo(int[] height) {
        int result = 0;
        //查找最大值的下表
        int maxP = 0;
        for (int i = 0; i < height.length; i++) {
            maxP = height[maxP] > height[i] ? maxP : i;
        }
        //分别对左右数组进行计算
        int maxH = 0;
        //计算左半段
        for (int i = 0; i <= maxP; i++) {
            int diff = maxH - height[i];
            if (diff >= 0) {
                result += diff;
            } else {
                maxH = height[i];
            }
        }
        //计算右半段
        maxH = 0;
        for (int i = height.length - 1; i >= maxP; i--) {
            int diff = maxH - height[i];
            if (diff >= 0) {
                result += diff;
            } else {
                maxH = height[i];
            }
        }
        return result;
    }

}

package leetCode;

public class No4 {

    /**
     * 题解:https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/
     * 寻找两个数组的分割点,分割左数组与右数组的数量之和相同,i与j的关系为 i + j = (a.length+b.length)/2
     *
     * @param a 数组A
     * @param b 数组B
     * @return 结果值
     */
    public double findMedianSortedArrays(int[] a, int[] b) {
        double ans = 0;
        //保证a.length<b.length
        if (a.length > b.length) {
            int[] t = a;
            a = b;
            b = t;
        }
        int totalSize = a.length + b.length;
        boolean oddFlag = (totalSize & 1) == 0;
        int l = 0, r = a.length, i = 0, j = 0;
        while (l <= r) {
            i = (r + l) / 2;
            j = totalSize / 2 - i;
//            System.out.println("l:" + l + "  r:" + r + "  i:" + i + "  j:" + j);
            if (i < r && b[j - 1] > a[i]) {
                l = i + 1;
            } else if (i > l && a[i - 1] > b[j]) {
                r = i - 1;
            } else {
                if (i == a.length && !oddFlag) {
                    return b[j];
                }
                int leftMax = Math.max(i > 0 ? a[i - 1] : Integer.MIN_VALUE, j > 0 ? b[j - 1] : Integer.MIN_VALUE);
                int rightMin = Math.min(i < a.length ? a[i] : Integer.MAX_VALUE, j < b.length ? b[j] : Integer.MAX_VALUE);
//                System.out.println("left:" + leftMax + "  right:" + rightMin);
                if (oddFlag) {
                    return (leftMax + rightMin) / 2.0;
                } else {
                    return rightMin;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        No4 no4 = new No4();
        System.out.println(no4.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }
}

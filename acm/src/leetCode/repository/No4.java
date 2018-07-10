package leetCode.repository;

/**
 * leetCode 题库第四题
 */
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

    /**
     * 另一种方法,查找第k( (a.length+b.length+1)/2 即中位数 )大的数的位置,每次排查两个数组k/2的位置
     *
     * @param a      数组A
     * @param b      数组B
     * @param second
     * @return
     */
    public double findMedianSortedArrays(int[] a, int[] b, String second) {
        int size = a.length + b.length;
        return dfsToFindK(a, 0, b, 0, (size + 1) / 2);
    }

    public double dfsToFindK(int[] a, int la, int[] b, int lb, int k) {
        int midK = k / 2, midA = -1, midB = -1, pA, pB, nk = k;
        if (k == 0) {
            return Math.min(la < a.length ? a[la] : Integer.MAX_VALUE, lb < b.length ? b[lb] : Integer.MAX_VALUE);
        }
        //如果有一个数组被清空了,那么直接在剩下的数组里找结果
        if (la == a.length) {
            return ((a.length + b.length) & 1) == 1 ? b[k - 1 + lb] : (b[k - 1 + lb] + b[k + lb]) / 2.0;
        } else if (lb == b.length) {
            return ((a.length + b.length) & 1) == 1 ? a[k - 1 + la] : (a[k - 1 + la] + a[k + la]) / 2.0;
        }
        //如果k为1,说明下个就是结果
        if (k == 1) {
            if (((a.length + b.length) & 1) == 1) {
                //奇数
                return a[la] > b[lb] ? b[lb] : a[la];
            } else {
                //偶数
                if (a[la] < b[lb]) {
                    return (a[la] + dfsToFindK(a, la + 1, b, lb, k - 1)) / 2.0;
                } else {
                    return (b[lb] + dfsToFindK(a, la, b, lb + 1, k - 1)) / 2.0;
                }
            }
        }
        pA = la + midK - 1 >= a.length ? a.length - 1 : la + midK - 1;
        pB = lb + midK - 1 >= b.length ? b.length - 1 : lb + midK - 1;
        if (a[pA] > b[pB]) {
            nk -= pB - lb + 1;
            return dfsToFindK(a, la, b, pB + 1, nk);
        } else {
            nk -= pA - la + 1;
            return dfsToFindK(a, pA + 1, b, lb, nk);
        }

    }


    public static void main(String[] args) {
        No4 no4 = new No4();
//        System.out.println(no4.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println(no4.findMedianSortedArrays(new int[]{1}, new int[]{1}, "寻找第k大的数"));
    }
}

package leetCode.repository;

import java.util.Arrays;

public class No1775 {


    public int minOperations(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum();
        if (sum1 > sum2) {
            int[] t = nums1;
            nums1 = nums2;
            nums2 = t;
        }
        int target = Math.abs(sum1 - sum2);
        int times = 0;
        int n1p = 0, n2p = nums2.length - 1;
        while ((n1p < nums1.length || n2p >= 0) && target > 0) {
            int spare1 = n1p < nums1.length ? 6 - nums1[n1p] : 0;
            int spare2 = n2p >= 0 ? nums2[n2p] - 1 : 0;
            target -= Math.max(spare1, spare2);
            if (spare1 >= spare2 && n1p < nums1.length) {
                n1p++;
            } else if (spare2 >= spare1 && n2p >= 0) {
                n2p--;
            }
            times++;
        }
        return target > 0 ? -1 : times;
    }

    public static void main(String[] args) {
        No1775 no1775 = new No1775();
        System.out.println(no1775.minOperations(new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 1, 2, 2, 2, 2}) == 3);
        System.out.println(no1775.minOperations(new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{6}) == -1);
        System.out.println(no1775.minOperations(new int[]{6, 6}, new int[]{1}) == 3);
    }

}

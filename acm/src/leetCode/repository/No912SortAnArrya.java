package leetCode.repository;

import java.util.Arrays;

/**
 * 912. 排序数组
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * @author jiangxiewei
 * @since 2022/2/17
 */
public class No912SortAnArrya {

    public static void main(String[] args) throws InterruptedException {
        No912SortAnArrya sortAnArrya = new No912SortAnArrya();
        System.out.println(Arrays.toString(sortAnArrya.sortArray(new int[]{5, 2, 3, 1})));
        System.out.println(Arrays.toString(sortAnArrya.sortArray(new int[]{5, 1, 1, 2, 0, 0})));
        System.out.println(Arrays.toString(sortAnArrya.sortArray(new int[]{1, 2, 3, 4, 5})));
    }

    public int[] sortArray(int[] nums) {
        return new MergeSort().sort(nums);
    }


    /**
     * 归并算法实现排序数组
     * 性质：1、时间复杂度：O(nlogn)  2、空间复杂度：O(n)  3、稳定排序  4、非原地排序
     */
    public static class MergeSort extends AbstractSortAnArray {

        private int[] arr, tmp;

        @Override
        int[] sort(int[] arr) {
            this.arr = arr;
            this.tmp = new int[arr.length];
            sortPart(0, arr.length - 1);
            return this.arr;
        }

        void sortPart(int l, int r) {
            if (r > l) {
                //左右半边分别排序,并将结果归并
                int mid = (l + r) / 2;
                sortPart(l, mid);
                sortPart(mid + 1, r);
                reMerge(l, mid, mid + 1, r);
            }
        }

        void reMerge(int ls, int le, int rs, int re) {
            //归并[ls,le]和[rs,re]数组,重新赋值到[ls,re]中.
            //lp,rp分别为第一,二部分已排序数组范围
            int tmpPos = 0, start = ls, tmpResult;
            while (ls <= le || rs <= re) {
                if (ls > le) {
                    tmpResult = arr[rs++];
                } else if (rs > re) {
                    tmpResult = arr[ls++];
                } else if (arr[ls] > arr[rs]) {
                    tmpResult = arr[rs++];
                } else {
                    tmpResult = arr[ls++];
                }
                tmp[tmpPos++] = tmpResult;
            }
            if (tmpPos > 0) {
                //把归并后的tmp数组抽出来覆盖到arr上
                System.arraycopy(tmp, 0, arr, start, tmpPos);
            }
        }

    }

    public abstract static class AbstractSortAnArray {
        /**
         * 排序
         *
         * @param arr 排序前的数组
         * @return 返回排序后的数组
         */
        abstract int[] sort(int[] arr);
    }


}

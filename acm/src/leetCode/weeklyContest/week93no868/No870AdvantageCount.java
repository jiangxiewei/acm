package leetCode.weeklyContest.week93no868;


import java.util.Arrays;
import java.util.Comparator;

/**
 * 优势洗牌
 *
 * 给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
 * 返回 A 的任意排列，使其相对于 B 的优势最大化。
 *
 * 1 <= A.length = B.length <= 10000
 * 0 <= A[i] <= 10^9
 * 0 <= B[i] <= 10^9
 *
 * @author Administrator
 * @date 2019/3/27
 */
public class No870AdvantageCount {

    class PosNum {
        int pos;
        int val;
        boolean used;

        public PosNum(int pos, int val) {
            this.pos = pos;
            this.val = val;
            used = false;
        }

        public int getVal() {
            return val;
        }
    }

    /**
     * 将数组重新排序,并用PosNum(保留原先位置信息)存储
     * @param arr 输入数组
     * @return PosNum[]
     */
    public PosNum[] generatePosNumArray(int[] arr) {
        PosNum[] result = new PosNum[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = new PosNum(i, arr[i]);
        }
        Arrays.sort(result, Comparator.comparing(PosNum::getVal));
        return result;
    }

    public int[] advantageCount(int[] A, int[] B) {
        int[] resultA = new int[A.length];
        boolean[] vis = new boolean[A.length];
        PosNum[] a = generatePosNumArray(A), b = generatePosNumArray(B);
        int bPos = 0;
        //将最大化时的几个优势放入结果数组中
        for (int i = 0; i < a.length; i++) {
            if (a[i].val <= b[bPos].val) {
                //此值无法与b相比取得优势
                continue;
            }
            resultA[b[bPos].pos] = a[i].val;
            vis[b[bPos].pos] = true;
            a[i].used = true;
            bPos++;
        }
        //将未放入的数字放入(无法取得优势的数字塞入).
        int aPos = 0;
        for (int i = 0; i < vis.length; i++) {
            if (!vis[i]) {
                for (; a[aPos].used; aPos++) {
                }
                resultA[i] = a[aPos].val;
                a[aPos].used = true;
            }
        }
        return resultA;
    }

}

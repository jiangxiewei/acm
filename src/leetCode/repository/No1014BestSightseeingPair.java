package leetCode.repository;

/**
 * 输入：[8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 *
 * @author jiang
 * @date 2020/6/17
 */
public class No1014BestSightseeingPair {

    public static void main(String[] args) {
        No1014BestSightseeingPair pair = new No1014BestSightseeingPair();
        System.out.println(pair.maxScoreSightseeingPair(new int[]{8, 1, 5, 2, 6}));
    }

    /**
     * 思路:
     * 我们的目标是 f(i,j)=A[i] + A[j] + i - j , i<j
     * <br/>
     * 我们可以变形成 f(i,j) = A[i] + i + (A[j]-j) = g(i) + h(j)
     * <br/>
     * 然后(可以画图看看)发现,对于every j,只需要找到最大的g(i)即可.  故遍历j期间,记录下g(i)max即可
     */
    public int maxScoreSightseeingPair(int[] a) {
        int targetMax = Integer.MIN_VALUE, preMax;
        preMax = a[0];
        for (int i = 1; i < a.length; i++) {
            targetMax = Math.max(a[i] - i + preMax, targetMax);
            preMax = Math.max(a[i] + i, preMax);
        }
        return targetMax;
    }

}

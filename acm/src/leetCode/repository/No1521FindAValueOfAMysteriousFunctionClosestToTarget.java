package leetCode.repository;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * 1521. 找到最接近目标值的函数值
 * func即求[l,r]区间所有数字求与的结果
 * Winston 构造了一个如上所示的函数 func 。他有一个整数数组 arr 和一个整数 target ，他想找到让 |func(arr, l, r) - target| 最小的 l 和 r 。
 * <p>
 * 请你返回 |func(arr, l, r) - target| 的最小值。
 * <p>
 * 请注意， func 的输入参数 l 和 r 需要满足 0 <= l, r < arr.length 。
 * <p>
 * 输入：arr = [9,12,3,7,15], target = 5
 * 输出：2
 * <p>
 * 输入：arr = [1000000,1000000,1000000], target = 1
 * 输出：999999
 * <p>
 * 输入：arr = [1,2,4,8,16], target = 0
 * 输出：0
 * <p>
 * 链接：https://leetcode-cn.com/problems/find-a-value-of-a-mysterious-function-closest-to-target
 *
 * @author jxw
 * @date 2020/7/20
 */
public class No1521FindAValueOfAMysteriousFunctionClosestToTarget {

    public static void main(String[] args) {
        No1521FindAValueOfAMysteriousFunctionClosestToTarget no = new No1521FindAValueOfAMysteriousFunctionClosestToTarget();
        //2
        System.out.println(no.closestToTarget(new int[]{9, 12, 3, 7, 15}, 5));
        //999999
        System.out.println(no.closestToTarget(new int[]{1000000, 1000000, 1000000}, 1));
        //0
        System.out.println(no.closestToTarget(new int[]{1, 2, 4, 8, 16}, 0));
    }

    public int closestToTarget(int[] arr, int target) {
        BiFunction<int[], Integer, Integer> function = new OfficialWay();
        return function.apply(arr, target);
    }

    // Set[r]为所有[l,r]的与结果集(0<=l && l<=r1)
    // 则 Set[r+1]为 (arr[r+1]与Set[r]中所有值求与的新集合) U (arr[r+1])
    // 而结果集result则为 set[0] U ... U set[r-1] U set[r]
    static class OfficialWay implements BiFunction<int[], Integer, Integer> {

        @Override
        public Integer apply(int[] arr, Integer target) {

            Set<Integer> andResult = new HashSet<>(), preResult = new HashSet<>();
            for (int cur : arr) {
                preResult = preResult.stream().map(v -> v & cur).collect(Collectors.toSet());
                preResult.add(cur);
                andResult.addAll(preResult);
            }
            return andResult.stream().map(v -> Math.abs(v - target)).min(Integer::compareTo).get();
        }

    }

}

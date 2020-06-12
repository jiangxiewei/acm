package leetCode.repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 *
 * @author jiang
 * @date 2020/6/12
 */
public class No15_3sum {

    public static void main(String[] args) {
        No15_3sum no = new No15_3sum();
        //[-1, 0, 1], [-1, -1, 2]
        System.out.println(no.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        //[0,0,0]
        System.out.println(no.threeSum(new int[]{0, 0, 0}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Function<int[], List<List<Integer>>> func = new ThreePointer();
        return func.apply(nums);
    }

    /**
     * 第一个指针代表两数之和的target
     * 然后剩下两个指针来玩两数之和吧. 找到 t[j] + t[k] == t[i]就行.
     */
    public static class ThreePointer implements Function<int[], List<List<Integer>>> {

        private int[] arr;
        private List<List<Integer>> result;

        @Override
        public List<List<Integer>> apply(int[] arr) {
            Arrays.sort(arr);
            this.arr = arr;
            this.result = new LinkedList<>();
            //此处加上arr[i] <= 0 减少遍历次数
            for (int i = 0; i + 1 < arr.length - 1 && arr[i] <= 0; i++) {
                if (i > 0 && arr[i] == arr[i - 1]) {
                    continue;
                }
                twoSum(arr[i], i + 1, arr.length - 1);
            }
            return result;
        }

        public void twoSum(int target, int l, int r) {
            while (l < r) {
                if (arr[l] + arr[r] > -target) {
                    r--;
                } else if (arr[l] + arr[r] < -target) {
                    l++;
                } else {
                    result.add(Arrays.asList(target, arr[l], arr[r]));
                    while (l < r && arr[l + 1] == arr[l++]) {
                    }
                    while (l < r && arr[r - 1] == arr[r--]) {
                    }
                }
            }
        }
    }

}

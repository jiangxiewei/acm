package leetCode.repository;

import java.util.function.Function;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。
 * 请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * <p>
 * 0 <= num < 231
 *
 * @author jiang
 * @date 2020/6/9
 */
public class Mst_46_ba_shu_zi_fan_yi_cheng_zi_fu_chuan_lcof {

    public static void main(String[] args) {
        Mst_46_ba_shu_zi_fan_yi_cheng_zi_fu_chuan_lcof no = new Mst_46_ba_shu_zi_fan_yi_cheng_zi_fu_chuan_lcof();
        //5
        System.out.println(no.translateNum(12258));
        //1
        System.out.println(no.translateNum(506));
    }

    public int translateNum(int num) {
        Function<int[], Integer> solution = new Recursive();
        String s = String.valueOf(num);
        int[] arr = new int[s.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = s.charAt(i) - '0';
        }
        return solution.apply(arr);
    }

    /**
     * DP法.
     * f[i] = f[i-1] + (arr[i]与arr[i-1]能否组合) f[i-2]
     */
    public static class Recursive implements Function<int[], Integer> {

        @Override
        public Integer apply(int[] arr) {
            int f2 = 0, f1 = 1, count = 1;
            for (int i = 1; i < arr.length; i++, f2 = f1, f1 = count) {
                int twoComb = arr[i - 1] * 10 + arr[i];
                if (arr[i - 1] > 0 && twoComb < 26 && twoComb >= 0) {
                    count += i - 2 >= 0 ? f2 : 1;
                }
            }
            return count;
        }
    }

    /**
     * 搜索法,没DP爽
     */
    public static class Dfs implements Function<int[], Integer> {

        private int count = 0;
        private int[] arr;

        @Override
        public Integer apply(int[] num) {
            this.count = 0;
            this.arr = num;
            dfs(0);
            return count;
        }

        public void dfs(int position) {
            if (position >= arr.length) {
                count++;
                return;
            }
            dfs(position + 1);
            if (position + 1 < arr.length && arr[position] > 0) {
                int twoComb = arr[position] * 10 + arr[position + 1];
                if (0 <= twoComb && twoComb <= 25) {
                    dfs(position + 2);
                }
            }
        }

    }

}

package leetCode.repository;

import java.util.Random;

/**
 * @author jiangxiewei
 * @since 2022/4/4
 */
public class No528按权重随机选择 {


    class Solution {

        /**
         * 原始数组
         */
        private final int[] w;
        /**
         * 前缀和
         */
        private final int[] pre;
        private final Random random;

        public Solution(int[] w) {
            this.w = w;
            pre = new int[w.length];
            for (int i = 0; i < w.length; i++) {
                pre[i] = w[i] + (i > 0 ? pre[i - 1] : 0);
            }
            random = new Random();
        }

        public int pickIndex() {
            int seed = random.nextInt(pre[pre.length - 1]);
//        System.out.println(Arrays.toString(pre));
            int l = 0, r = pre.length - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
//            System.out.println("l:" + l + ",r:" + r + ",mid:" + mid + ",seed:" + seed);
                if (seed >= pre[mid] - w[mid] && seed < pre[mid]) {
                    return mid;
                } else if (seed >= pre[mid]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            throw new RuntimeException();
        }
    }

}

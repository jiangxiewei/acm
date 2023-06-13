package leetCode.repository;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.BiFunction;

/**
 * @see <a href="https://leetcode.cn/prob   lems/o8SXZn/"></a>
 */
public class LCP_33_蓄水 {

    public static void main(String[] args) {
        int result = new LCP_33_蓄水().storeWater(new int[]{1, 3}, new int[]{6, 8});
        System.out.println("result:" + result);
        assert result == 4;

        result = new LCP_33_蓄水().storeWater(new int[]{9, 0, 1}, new int[]{0, 2, 2});
        System.out.println("result:" + result);
        assert result == 3;

        result = new LCP_33_蓄水().storeWater(new int[]{16, 29, 42, 70, 42, 9}, new int[]{89, 44, 50, 90, 94, 91});
        System.out.println("result:" + result);
        assert result == 11;

        result = new LCP_33_蓄水().storeWater(new int[]{0}, new int[]{0});
        System.out.println("result:" + result);
        assert result == 0;

        result = new LCP_33_蓄水().storeWater(new int[]{3710, 6067, 2993, 70, 2340, 2748, 9385, 3027, 3456, 5246, 9739, 1220, 9539, 9074, 4729, 7051, 8462, 6908, 3649, 9996, 8890, 2980, 4350, 7350, 6344, 6759, 4420, 269, 9341, 648, 7737, 8133, 3717, 2766, 5807, 4338, 2077, 5775, 4905, 7262, 1258, 613, 3837, 3475, 437, 3739, 9814, 4790, 2075, 7722, 3290, 5685, 3499, 6992, 4421, 934, 6004, 5763, 3463, 6138, 8818, 445, 4778, 4979, 126, 3969, 2994, 87, 3739, 8582, 9559, 8326, 9132, 257, 8928, 9147, 1615, 4665, 9828, 3925, 6435, 5326, 836, 519, 298, 600, 5503, 273, 9580, 5383, 8966, 4810, 1386, 7207, 8060, 678, 8837, 6946, 1210, 945},
                new int[]{6304, 6509, 4276, 9645, 6455, 8167, 9667, 4385, 8872, 7889, 9936, 4413, 9922, 9894, 8065, 7627, 9225, 9907, 7055, 9996, 9439, 3351, 9317, 8363, 9383, 6850, 4621, 5389, 9508, 3391, 9650, 8363, 8719, 5594, 8770, 5403, 7107, 9941, 9254, 9355, 4614, 4640, 4896, 8759, 4397, 8441, 9870, 9906, 2396, 8092, 6939, 9432, 8182, 9090, 8029, 4930, 7772, 7066, 7279, 7778, 9529, 2947, 6552, 6930, 5260, 8470, 8478, 1371, 9453, 9767, 9888, 9964, 9960, 3990, 9391, 9377, 3063, 5374, 9880, 7684, 7864, 7078, 2622, 3754, 617, 9773, 9415, 8026, 9883, 5735, 9233, 6715, 9105, 7932, 9178, 1081, 9340, 7284, 6621, 196});
        System.out.println("result:" + result);
        assert result == 127;

        result = new LCP_33_蓄水().storeWater(
                new int[]{9988, 5017, 5130, 2445, 9896, 9151, 3625, 7801, 608, 3283, 1386, 979, 5209, 4182, 8234, 9870, 8714, 6435, 3800, 956, 4006, 5620, 7474, 1205, 6993, 3320, 1201, 7593, 905, 3816, 4522, 4560, 8027, 8219, 6686, 3779, 2141, 1240, 6504, 6612, 6921, 7329, 8145, 5745, 7652, 4340, 7933, 6246, 5157, 9447, 107, 9665, 3653, 2978, 9832, 4945, 4312, 2199, 449, 8432, 3230, 8163, 800, 6547, 1110, 1194, 9384, 632, 3275, 1229, 7230, 8643, 7613, 8256, 5043, 1288, 3088, 8997, 4554, 4755, 7433, 8146, 9722, 3469, 8863, 5831, 7816, 5058, 4316, 7946, 8402, 975, 2450, 4958, 9811, 9336, 21, 9309, 8999, 56},
                new int[]{9991, 6973, 7192, 9876, 9910, 9549, 3700, 8814, 1308, 9981, 9234, 7292, 7732, 8458, 8441, 9939, 9621, 7285, 7452, 2718, 6589, 7555, 8788, 3202, 7832, 4781, 8798, 9299, 2112, 9963, 8755, 7240, 9217, 8587, 6782, 9703, 8954, 3759, 6907, 7218, 7333, 8020, 8323, 5750, 9510, 8571, 8664, 8510, 9363, 9741, 8643, 9825, 4227, 8530, 9961, 8511, 8949, 7486, 9086, 9690, 5316, 9581, 9314, 8817, 7234, 8998, 9485, 5394, 7365, 1501, 7984, 9802, 9778, 8314, 7482, 7117, 5117, 9609, 8732, 9728, 9330, 8800, 9775, 6210, 8966, 7700, 8802, 7607, 8950, 9730, 9855, 1231, 5228, 5329, 9982, 9532, 3230, 9951, 9034, 8299});
        System.out.println("result:" + result);
        assert result == 138;

        result = new LCP_33_蓄水().storeWater(new int[]{63, 85}, new int[]{85, 96});
        System.out.println("result:" + result);
        assert result == 2;
    }

    public int storeWater(int[] bucket, int[] vat) {
        return new PriorityQueueSolution().apply(bucket, vat);
    }

    /**
     * 暴力枚举所有蓄水次数，对于每个缸，已知蓄水次数可以求得升级桶次数。
     */
    class Force implements BiFunction<int[], int[], Integer> {

        @Override
        public Integer apply(int[] bucket, int[] vat) {
            int maxStoreTime = 0;
            for (int i = 0; i < vat.length; i++) {
                int storeTime = devideAndCeil(vat[i], Math.max(bucket[i], 1));
                maxStoreTime = Math.max(maxStoreTime, storeTime);
            }
            // try to enumerate all the store action times
            int minStoreTotalTime = Integer.MAX_VALUE;
            for (int storeTime = 0; storeTime <= maxStoreTime; storeTime++) {
                int bucketIncTime = 0;
                for (int i = 0; i < vat.length; i++) {
                    if (storeTime == 0) {
                        if (vat[i] != 0) {
                            bucketIncTime = Integer.MAX_VALUE;
                            break;
                        }
                    } else {
                        int atleastBucket = devideAndCeil(vat[i], storeTime);
                        bucketIncTime += Math.max(0, atleastBucket - bucket[i]);
                    }
                }
                minStoreTotalTime = Math.min(minStoreTotalTime, bucketIncTime + storeTime);
            }
            return minStoreTotalTime;
        }
    }

    /**
     * 根据蓄水次数构造优先队列
     * 蓄水次数最多的缸需要进行桶升级然后重新计算蓄水次数并更新最小所需蓄水次数，直到无法提升蓄水次数为止。
     */
    class PriorityQueueSolution implements BiFunction<int[], int[], Integer> {

        @Override
        public Integer apply(int[] bucket, int[] vat) {
            Queue<Tupple> tuppleQueue = new PriorityQueue<>(vat.length, Comparator.comparingInt(Tupple::getCurrentStoreTime).reversed());

            int maxStoreTime = 0, optCount = 0;
            for (int i = 0; i < vat.length; i++) {
                if (vat[i] > 0) {
                    Tupple tupple = new Tupple(bucket[i], vat[i]);
                    if (tupple.getUpdateTime() > 0) {
                        optCount++;
                    }
                    maxStoreTime = Math.max(tupple.getCurrentStoreTime(), maxStoreTime);
                    tuppleQueue.offer(tupple);
                }
            }

            int result = maxStoreTime + optCount;
            while (!tuppleQueue.isEmpty()) {
                Tupple tupple = tuppleQueue.poll();
                maxStoreTime = Math.min(tupple.getCurrentStoreTime(), maxStoreTime);
                result = Math.min(maxStoreTime + optCount, result);
                if (tupple.getCurrentStoreTime() > tupple.getStoreTimeIfAdd()) {
                    tupple.addUpdateTime();
                    optCount++;
                    tuppleQueue.offer(tupple);
                } else {
                    tuppleQueue.offer(tupple);
                    break;
                }
            }

            return result;
        }

        class Tupple {
            private final int initialBucket;
            private int updateTime;
            private final int vat;

            public Tupple(int initialBucket, int vat) {
                this.initialBucket = initialBucket;
                this.vat = vat;
                if (vat > 0 && initialBucket == 0) {
                    updateTime = 1;
                }
            }

            public int getUpdateTime() {
                return updateTime;
            }

            public void addUpdateTime() {
                this.updateTime++;
            }

            public int getStoreTimeIfAdd() {
                return devideAndCeil(vat, initialBucket + updateTime + 1);
            }

            public int getCurrentStoreTime() {
                return devideAndCeil(vat, initialBucket + updateTime);
            }

            @Override
            public String toString() {
                return "Tupple{" +
                        "initialBucket=" + initialBucket +
                        ", updateTime=" + updateTime +
                        ", vat=" + vat +
                        '}';
            }
        }


    }

    private int devideAndCeil(int a, int b) {
        return a / b + (a % b > 0 ? 1 : 0);
    }

}

package leetCode.repository;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * information
 *
 * @author jxwww
 * @date 2018/8/26
 */
public class No765 {


    public static void main(String[] args) {
        int[] rows = new int[]{0, 2, 1, 4,3,5};
        System.out.println(new No765().minSwapsCouples(rows));
    }

    /**
     * 程序接口
     *
     * @param row 给我的数组
     * @return 交换次数
     */
    public int minSwapsCouples(int[] row) {
        int targetTimes = 0;

        UnionFindSets sets = new UnionFindSets(row.length);
        for (int i = 0; i < row.length; i += 2) {
            sets.union(row[i], row[i + 1]);
            sets.union(i, i + 1);
        }
        Map<Integer, Integer> countMap = new HashMap<>(sets.getSize());
        for (int i = 0; i < sets.getSize(); i++) {
            int findx = sets.find(i);
            Integer counted = countMap.get(findx);
            countMap.put(findx, counted == null ? 1 : counted + 1);
        }
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            targetTimes += entry.getValue() / 2 - 1;
        }

        return targetTimes;
    }

    /**
     * 检查x,y是否是pair
     *
     * @param x 序号x
     * @param y 序号y
     * @return 是否为pair
     */
    public boolean checkPair(int x, int y) {
        if (x - y == 1 || y - x == 1) {
            int odd, even;
            if ((x & 2) == 0) {
                even = x;
                odd = y;
            } else {
                even = y;
                odd = x;
            }
            if (even + 1 == odd) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    class UnionFindSets {

        /**
         * 并查集实例
         */
        private int[] sets;
        private int size;

        /**
         * 初始化并查集
         *
         * @param size 并查集大小
         */
        public UnionFindSets(int size) {
            sets = new int[size];
            this.size = size;
            Arrays.setAll(sets, operand -> operand);
//        System.out.println(Arrays.toString(sets));
        }

        /**
         * 查询根节点序号
         *
         * @param x 序号x
         * @return 序号x的根节点序号
         */
        public int find(int x) {
            if (sets[x] == x) {
                //指向自己,说明没有父节点了,返回本身
                return x;
            } else {
                //说明有父节点,递归查询下去直到找到根节点,并让所有节点指向根节点
                return sets[x] = find(sets[x]);
            }
        }

        /**
         * 检查x和y是否是相同集合
         *
         * @param x 集合序号x
         * @param y 集合序号y
         * @return 是否是相同集合
         */
        public boolean checkIfTheSame(int x, int y) {
            return find(x) == find(y);
        }

        /**
         * 合并操作,将x与y并成一个集合
         *
         * @param x 序号x
         * @param y 序号y
         * @return 弱已是一个集合, 则返回false, 否则返回true
         */
        public boolean union(int x, int y) {
            int fx = find(x), fy = find(y);
            if (fx == fy) {
                //已是相同集合
                return false;
            } else {
                sets[fx] = sets[fy] = Math.min(fx, fy);
                return true;
            }
        }

        /**
         * 对所有元素执行一次find(x)操作,让素有元素都指向其集合的根节点
         */
        public void findAll() {
            for (int i = 0; i < sets.length; i++) {
                find(i);
            }
        }

        public int getSize() {
            return size;
        }

        @Override
        public String toString() {
            return "UnionFindSets{" +
                    "sets=" + Arrays.toString(sets) +
                    '}';
        }

    }

}

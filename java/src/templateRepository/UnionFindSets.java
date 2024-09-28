package templateRepository;

import java.util.Arrays;

/**
 * UnionFindSets
 * 并查集模板
 *
 * @author jxwww
 * @date 2018/8/26
 */
public class UnionFindSets {

    /**
     * 并查集实例
     */
    private final int[] sets;

    /**
     * 初始化并查集
     *
     * @param size 并查集大小
     */
    public UnionFindSets(int size) {
        sets = new int[size];
        Arrays.setAll(sets, operand -> operand);
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
    public void redirectAll() {
        for (int i = 0; i < sets.length; i++) {
            find(i);
        }
    }

    public int getSize() {
        return sets.length;
    }

    @Override
    public String toString() {
        return "UnionFindSets{" +
                "sets=" + Arrays.toString(sets) +
                '}';
    }

}

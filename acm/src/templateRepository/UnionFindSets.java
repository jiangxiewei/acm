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
    private int[] sets;

    /**
     * 初始化并查集
     *
     * @param size 并查集大小
     */
    UnionFindSets(int size) {
        sets = new int[size];
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
            return x;
        } else {
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
     * 对所有元素执行一次find(x)操作,让素有元素的父节点都指向其根节点
     */
    public void findAll() {
        for (int i = 0; i < sets.length; i++) {
            find(i);
        }
    }

    @Override
    public String toString() {
        return "UnionFindSets{" +
                "sets=" + Arrays.toString(sets) +
                '}';
    }

    public static void main(String[] args) {
        UnionFindSets set = new UnionFindSets(20);
        set.union(1, 2);
        set.union(2, 3);
        set.union(0, 3);
        System.out.println(set.checkIfTheSame(0, 1));
        System.out.println(set.checkIfTheSame(2, 4));
        set.findAll();
        System.out.println(set);
    }

}

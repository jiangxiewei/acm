package leetCode.repository;


import java.util.Arrays;

/**
 * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 * <p>
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
 * <p>
 * 示例 1：
 * 输入：["a==b","b!=a"]
 * 输出：false
 * <p>
 * 示例 2：
 * 输出：["b==a","a==b"]
 * 输入：true
 * <p>
 * 示例 3：
 * 输入：["a==b","b==c","a==c"]
 * 输出：true
 * <p>
 * 示例 4：
 * 输入：["a==b","b!=c","c==a"]
 * 输出：false
 * <p>
 * 示例 5：
 * 输入：["c==c","b==d","x!=z"]
 * 输出：true
 *
 * @author jiang
 * @date 2020/6/8
 */
public class No990SatisfiabilityOfEqualityEquations {

    public static void main(String[] args) {
        No990SatisfiabilityOfEqualityEquations no = new No990SatisfiabilityOfEqualityEquations();
        //F
        System.out.println(no.equationsPossible(new String[]{"a==b", "b!=a"}));
        //T
        System.out.println(no.equationsPossible(new String[]{"b==a", "a==b"}));
        //T
        System.out.println(no.equationsPossible(new String[]{"a==b", "b==c", "a==c"}));
        //F
        System.out.println(no.equationsPossible(new String[]{"a==b", "b!=c", "c==a"}));
        //T
        System.out.println(no.equationsPossible(new String[]{"c==c", "b==d", "x!=z"}));
        //T
        System.out.println(no.equationsPossible(new String[]{"c==c", "f!=a", "f==b", "b==c"}));
        //T
        System.out.println(no.equationsPossible(new String[]{"e==d", "e==a", "f!=d", "b!=c", "a==b"}));
    }

    public boolean equationsPossible(String[] equations) {
        UnionFindSets sets = new UnionFindSets(26);
        for (String equation : equations) {
            String[] split = equation.split("(!=|==)");
            int x = split[0].charAt(0) - 'a', y = split[1].charAt(0) - 'a';
            if (equation.contains("!=")) {
                if (x == y || sets.find(x) == sets.find(y)) {
                    return false;
                }
            } else {
                sets.union(x, y);
            }
        }
        for (String equation : equations) {
            String[] split = equation.split("(!=|==)");
            int x = split[0].charAt(0) - 'a', y = split[1].charAt(0) - 'a';
            if (equation.contains("!=") && sets.find(x) == sets.find(y)) {
                return false;
            } else if (equation.contains("==") && sets.find(x) != sets.find(y)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 并查集模板
     */
    public class UnionFindSets {

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

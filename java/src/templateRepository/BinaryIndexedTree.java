package templateRepository;

import java.util.ArrayList;

/**
 * @author jiang
 * @date 2020/4/4
 */
public class BinaryIndexedTree {

    private final ArrayList<Long> c;

    public BinaryIndexedTree() {
        this(1024 + 1);
    }

    public BinaryIndexedTree(int initialCap) {
        initialCap++;
        this.c = new ArrayList<>(initialCap);
        for (int i = 0; i < initialCap; i++) {
            c.add(0L);
        }
    }

    private long lowbit(long a) {
        return a & -a;
    }

    /**
     * 更新index位置的统计值
     *
     * @param index 取值范围[-1,initialCap]
     * @param add   添加的值
     */
    public void update(int index, long add) {
        index++;
        if (index < 0) {
            throw new IllegalArgumentException(" index can not be less than -1 ");
        }
        for (int i = index; i < c.size(); i += lowbit(i)) {
            c.set(i, c.get(i) + add);
        }
    }

    private long search(int index) {
        index++;
        int result = 0;
        for (int i = index; i > 0; i -= lowbit(i)) {
            result += c.get(i);
        }
        return result;
    }

    /**
     * @param from 起始点
     * @param to   结束点
     * @return 返回[from, to]的结果值.
     */
    public long sum(int from, int to) {
        return search(to) - search(from - 1);
    }

}

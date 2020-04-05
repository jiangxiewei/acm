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
        this.c = new ArrayList<>(initialCap);
    }

    private long lowbit(long a) {
        return a & -a;
    }

    public void update(int index, long add) {
        if (index == 0) {
            throw new IllegalArgumentException(" index can not be 0 ");
        }
        for (int i = index; i < c.size(); index += lowbit(i)) {
            c.set(index, c.get(index) + add);
        }
    }

    private long search(int index) {
        int result = 0;
        for (int i = index; i > 0; i -= lowbit(i)) {
            result += c.get(i);
        }
        return result;
    }

    public long sum(int from, int to) {
        if (from == 0) {
            return search(to);
        } else {
            return search(to) - search(from);
        }
    }


}

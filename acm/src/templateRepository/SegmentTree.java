package templateRepository;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * information
 *
 * @author jxw
 * @date 2020/7/19
 */
public class SegmentTree<T extends Comparable<T>> {

    private TreeNode<T> rootNode;
    private BiFunction<T, T, T> midGetter;
    private Function<T, T> incrementer;

    public SegmentTree(T minValue, T maxValue) {
        this(minValue, maxValue,
                (t, t2) -> (T) t.getClass().cast(((((Number) t).longValue() + ((Number) t2).longValue()) >> 1)),
                t -> (T) t.getClass().cast(((Number) t).longValue() + 1)
        );
    }

    public SegmentTree(T minValue, T maxValue, BiFunction<T, T, T> midGetter, Function<T, T> incrementer) {
        this.midGetter = midGetter;
        this.incrementer = incrementer;
        this.rootNode = new TreeNode<>(minValue, maxValue, midGetter, incrementer);
    }

    public void updateTree(T l, T r, long value) {
        updateTree(rootNode, l, r, value);
    }

    private void updateTree(TreeNode<T> node, T l, T r, long value) {
        node.sum += value;
        T nodeMid = midGetter.apply(node.l, node.r);
        if (l.compareTo(node.l) == 0 && r.compareTo(node.r) == 0) {
            node.lazyValue += value;
        } else if (r.compareTo(nodeMid) <= 0) {
            // r < mid 更新左半区间
            updateTree(node.ls, l, r, value);
        } else if (l.compareTo(nodeMid) > 0) {
            // l > mid, 更新右半区间
            updateTree(node.rs, l, r, value);
        } else {
            //  l < mid && mid < r , 更新 [ l , mid ] + [ mid + 1 , r ]
            updateTree(node.ls, l, nodeMid, value);
            updateTree(node.rs, incrementer.apply(nodeMid), r, value);
        }
    }

    public long query(T l, T r) {
        //保证l<=r
        if (l.compareTo(r) > 0) {
            T t = l;
            l = r;
            r = t;
        }
        //保证查询在 [minValue,maxValue]之间
        if (l.compareTo(rootNode.l) < 0) {
            l = rootNode.l;
        }
        if (r.compareTo(rootNode.r) > 0) {
            r = rootNode.r;
        }
        return query(this.rootNode, l, r);
    }

    private long query(TreeNode<T> node, T l, T r) {
        T nodemid = midGetter.apply(node.l, node.r);
        if (node.lazyValue != 0) {
            pushLazyDown(node);
        }
        if ((node.l).compareTo(l) == 0 && r.compareTo(node.r) == 0) {
            //真子集
            return node.sum;
        } else if (r.compareTo(nodemid) <= 0) {
            // r < mid 查左半区间
            return query(node.ls, l, r);
        } else if (l.compareTo(nodemid) > 0) {
            // l > mid, 查右半区间
            return query(node.rs, l, r);
        } else {
            //  l < mid && mid < r , 查询 [ l , mid ] + [ mid + 1 , r ]
            return query(node.ls, l, nodemid) + query(node.rs, incrementer.apply(nodemid), r);
        }
    }

    private void pushLazyDown(TreeNode<T> node) {
        if (node.ls != null) {
            node.ls.sum += node.lazyValue;
        }
        if (node.rs != null) {
            node.rs.sum += node.lazyValue;
        }
        node.lazyValue = 0;
    }

    public static void main(String[] args) {
        SegmentTree<Integer> tree = new SegmentTree<>(1, 10, (a, b) -> (a + b) >> 1, a -> a + 1);
        tree.updateTree(1, 3, 5L);
        tree.updateTree(3, 4, 5L);
        System.out.println(tree.query(4, 5));
        System.out.println(tree.query(2, 3));
    }

    class TreeNode<T extends Comparable<T>> {

        private TreeNode<T> ls, rs;
        private T l, r;
        private long sum = 0, lazyValue = 0;

        TreeNode(T l, T r, BiFunction<T, T, T> midGetter, Function<T, T> incrementer) {
            this.l = l;
            this.r = r;
            if (l.compareTo(r) < 0) {
                T mid = midGetter.apply(l, r);
                this.ls = new TreeNode<>(l, mid, midGetter, incrementer);
                this.rs = new TreeNode<>(incrementer.apply(mid), r, midGetter, incrementer);
            } else if (l.compareTo(r) == 0) {
            } else {
                throw new IllegalArgumentException("the tree node l must less than r");
            }
        }
    }

}

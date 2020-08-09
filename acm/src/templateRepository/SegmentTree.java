package templateRepository;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * 未测试,慎用.
 *
 * @author jxw
 * @date 2020/7/19
 */
public class SegmentTree<RV extends Comparable<RV>> {

    private final TreeNode rootNode;
    private final Supplier<RV> valueInitializer;
    private final BiFunction<RV, RV, RV> valueSum;

    public SegmentTree(Long minValue, Long maxValue, RV initValue, BiFunction<RV, RV, RV> valueSum) {
        this(minValue, maxValue, () -> initValue, valueSum);
    }

    private SegmentTree(Long minValue, Long maxValue, Supplier<RV> valueInitializer,
                        BiFunction<RV, RV, RV> valueSum) {
        this.valueInitializer = valueInitializer;
        this.valueSum = (rv, rv2) -> {
            if (rv == null) {
                return rv2;
            } else if (rv2 == null) {
                return rv;
            } else {
                return valueSum.apply(rv, rv2);
            }
        };
        this.rootNode = new TreeNode(minValue, maxValue);
    }

    public void updateTree(Long l, Long r, RV value) {
        updateTree(rootNode, l, r, value);
    }

    private void updateTree(TreeNode node, Long l, Long r, RV value) {
        node.sum = valueSum.apply(node.sum, value);
        Long mid = (node.l + node.r) / 2;
        //真子集,更新至lazy标记上.
        if (l.compareTo(node.l) == 0 && r.compareTo(node.r) == 0) {
            if (node.lazyValue == null) {
                node.lazyValue = valueInitializer.get();
            }
            node.lazyValue = valueSum.apply(node.lazyValue, value);
        } else if (r.compareTo(mid) <= 0) {
            // r < mid 更新左半区间
            updateTree(node.ls, l, r, value);
        } else if (l.compareTo(mid) > 0) {
            // l > mid, 更新右半区间
            updateTree(node.rs, l, r, value);
        } else {
            //  l < mid && mid < r , 更新 [ l , mid ] + [ mid + 1 , r ]
            updateTree(node.ls, l, mid, value);
            updateTree(node.rs, mid + 1, r, value);
        }
    }

    public RV query(Long point) {
        return query(point, point);
    }

    public RV query(Long l, Long r) {
        //保证l<=r
        if (l.compareTo(r) > 0) {
            Long t = l;
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

    private RV query(TreeNode node, Long l, Long r) {
        Long mid = (node.l + node.r) / 2;
        if (node.lazyValue != null) {
            pushLazyDown(node);
        }
        if ((node.l).compareTo(l) == 0 && r.compareTo(node.r) == 0) {
            //真子集
            return node.sum;
        } else if (r.compareTo(mid) <= 0) {
            // r < mid 查左半区间
            return query(node.ls, l, r);
        } else if (l.compareTo(mid) > 0) {
            // l > mid, 查右半区间
            return query(node.rs, l, r);
        } else {
            //  l < mid && mid < r , 查询 [ l , mid ] + [ mid + 1 , r ]
            return valueSum.apply(query(node.ls, l, mid), query(node.rs, mid + 1, r));
        }
    }

    private void pushLazyDown(TreeNode node) {
        if (node.ls != null) {
            node.ls.sum = valueSum.apply(node.ls.sum, node.lazyValue);
        }
        if (node.rs != null) {
            node.rs.sum = valueSum.apply(node.rs.sum, node.lazyValue);
        }
        node.lazyValue = null;
    }

    class TreeNode {

        private TreeNode ls, rs;
        private Long l;
        private Long r;
        private RV sum = valueInitializer.get();
        private RV lazyValue = valueInitializer.get();

        TreeNode(Long l, Long r) {
            this.l = l;
            this.r = r;
            if (l.compareTo(r) < 0) {
                Long mid = (l + r) / 2;
                this.ls = new TreeNode(l, mid);
                this.rs = new TreeNode(mid + 1, r);
            } else if (l.compareTo(r) == 0) {
            } else {
                throw new IllegalArgumentException("the tree node l must less than r");
            }
        }

    }

}

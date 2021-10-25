package leetCode.repository;


import java.util.function.BiFunction;

/**
 * https://leetcode-cn.com/problems/add-two-numbers/submissions/
 */
public class No2 {

    /**
     * 默认数据结构
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        /**
         * 自己加的,打印用的
         */
        public void print(){
            System.out.print(val);
            if (next != null) {
                next.print();
            }
        }

    }

    /**
     * 测试用main方法
     * @param args
     */
    public static void main(String[] args) {
        test(new ListNode(2, new ListNode(4, new ListNode(3))),
                new ListNode(5, new ListNode(6, new ListNode(4))));
        test(new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))))))),
                new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))));
    }

    
    public static void test(ListNode l1, ListNode l2) {
        l1.print();
        System.out.print("+");
        l2.print();
        System.out.print("=");
        new No2().addTwoNumbers(l1, l2).print();
        System.out.println();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return new NewWay().apply(l1, l2);
    }

    /**
     * 新写的方法,看起来更优雅点
     */
    public static class NewWay implements BiFunction<ListNode, ListNode, ListNode> {

        @Override
        public ListNode apply(ListNode l1, ListNode l2) {
            return addTwoAndEnter(l1, l2, 0);
        }

        private ListNode addTwoAndEnter(ListNode l1, ListNode l2, int inc) {
            if (l1 == null && l2 == null && inc == 0) {
                return null;
            }
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            ListNode n1 = l1 != null ? l1.next : null;
            ListNode n2 = l2 != null ? l2.next : null;
            int newv = (v1 + v2 + inc);
            return new ListNode(newv % 10, addTwoAndEnter(n1, n2, newv / 10));
        }
    }
    
    /**
     * 很久以前写的逻辑. 归档至此
     */
    public static class OldWay implements BiFunction<ListNode,ListNode,ListNode> {

        @Override
        public ListNode apply(ListNode l1, ListNode l2) {
            ListNode targetHead = new ListNode(0), target = targetHead;
            boolean go = false;
            while (l1 != null || l2 != null) {
                int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + (go ? 1 : 0);
                go = false;
                if (sum >= 10) {
                    sum -= 10;
                    go = true;
                }
                target.next = new ListNode(sum);
                target = target.next;
                l1 = l1 != null ? l1.next : null;
                l2 = l2 != null ? l2.next : null;
            }
            if (go) {
                target.next = new ListNode(1);
            }

            return targetHead.next;
        }
    }

}

package leetCode.repository;

/**
 * 给你一个链表，
 * 两两交换其中相邻的节点，
 * 并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * @author jiang
 * @date 2023/7/5
 */
public class No24两两交换链表中的节点 {

    public ListNode swapPairs(ListNode head) {
        return new 递归一下试试().swapPairs(head);
    }

    class 官方题解的递归 implements 两两交换链表中的节点 {

        @Override
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode next = head.next;
            head.next = swapPairs(next.next);
            next.next = head;
            return next;
        }
    }

    class 递归一下试试 implements 两两交换链表中的节点 {

        @Override
        public ListNode swapPairs(ListNode head) {
            gogogo(head);
            return this.head;
        }

        private ListNode head;
        private ListNode pre;

        private void gogogo(ListNode node) {
            if (node == null) {
                return;
            }

            if (head == null) {
                if (node.next != null) {
                    head = node.next;
                } else {
                    head = node;
                }
            }
            // exchange
            if (node.next != null) {
                ListNode next = node.next;
                if (pre != null) {
                    pre.next = next;
                }
                node.next = next.next;
                next.next = node;
                pre = node;
                gogogo(node.next);
            }
        }

    }

    interface 两两交换链表中的节点 {
        public ListNode swapPairs(ListNode head);
    }

    // -----------------------------------------------------------------------------

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


}

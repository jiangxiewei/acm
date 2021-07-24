package leetCode.repository;


public class No2 {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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

    /**
     * 链表倒置
     *
     * @return 倒置后的新head节点
     */
    private ListNode reserveList(ListNode headNode) {
        ListNode cur = headNode, pre = null, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        int[] l1 = new int[]{2, 4, 3}, l2 = new int[]{5, 6, 4};
        ListNode head1 = new ListNode(l1[0]), pos = head1;
        for (int i = 1; i < l1.length; i++) {
            pos.next = new ListNode(l1[i]);
            pos = pos.next;
        }

        ListNode head2 = new ListNode(l2[0]);
        pos = head2;
        for (int i = 1; i < l2.length; i++) {
            pos.next = new ListNode(l2[i]);
            pos = pos.next;
        }

        System.out.println(listTostring(new No2().addTwoNumbers(head1, head2)));
    }

    public static String listTostring(ListNode listNode) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode node = listNode;
        while (node.next != null) {
            sb.append(node.val).append(",");
            node = node.next;
        }
        sb.append(node.val + "]");
        return sb.toString();
    }
}

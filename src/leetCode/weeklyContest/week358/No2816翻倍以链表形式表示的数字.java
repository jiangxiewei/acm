package leetCode.weeklyContest.week358;

public class No2816翻倍以链表形式表示的数字 {


    public ListNode doubleIt(ListNode head) {
        int inc = dfsToDouble(head);
        if (inc > 0) {
            return new ListNode(inc, head);
        } else {
            return head;
        }
    }

    private int dfsToDouble(ListNode node) {
        int inc = 0;
        if (node.next != null) {
            inc = dfsToDouble(node.next);
        }
        int newvalue = node.val * 2 + inc;
        node.val = newvalue % 10;
        return newvalue / 10;
    }

    private class ListNode {
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


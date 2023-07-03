package leetCode.repository;

public class No19删除链表的倒数第N个节点 {

    public static void main(String[] args) {
        System.out.println(new No19删除链表的倒数第N个节点().removeNthFromEnd(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null))))), 2));
        System.out.println(new No19删除链表的倒数第N个节点().removeNthFromEnd(new ListNode(1, null),1));
        System.out.println(new No19删除链表的倒数第N个节点().removeNthFromEnd(new ListNode(1, new ListNode(2)), 1));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        return new 双指针().removeNthFromEnd(head, n);
    }

    class 双指针 implements 删除倒数第N个节点 {

        @Override
        public ListNode removeNthFromEnd(ListNode head, int n) {
            int counter = 1;
            ListNode ptr1 = head, ptr2 = null, ptr2Pre = null;
            while (ptr1 != null) {
                if (counter >= n) {
                    if (ptr2 == null) {
                        ptr2 = head;
                    } else {
                        ptr2Pre = ptr2;
                        ptr2 = ptr2.next;
                    }
                }
                ptr1 = ptr1.next;
                counter++;
            }
            if (ptr2 == head) {
                return head.next;
            } else {
                ptr2Pre.next = ptr2.next;
                return head;
            }
        }
    }

    interface 删除倒数第N个节点 {
        public ListNode removeNthFromEnd(ListNode head, int n);
    }

    // ----------------- 题目需要,非解题内容 -----------------
    static class ListNode {
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(val);
            ListNode node = next;
            while (node != null) {
                sb.append(node.val);
                node = node.next;
            }
            return sb.toString();
        }

    }

}




package leetCode.repository;

public class No21合并两个有序链表 {

    public static void main(String[] args) {
        System.out.println(new No21合并两个有序链表().mergeTwoLists(new ListNode(1, new ListNode(2, new ListNode(4))), new ListNode(1, new ListNode(3, new ListNode(4)))).toString());
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        return new 简单题().mergeTwoLists(list1, list2);
    }

    class 简单题 implements 合并两个有序链表 {

        @Override
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode newHead = null, newPtr = null;
            while (list1 != null || list2 != null) {
                if (list1 == null || (list2 != null && list1.val >= list2.val)) {
                    // link to list2
                    if (newPtr == null) {
                        newHead = newPtr = list2;
                    } else {
                        newPtr.next = list2;
                        newPtr = newPtr.next;
                    }
                    list2 = list2.next;
                    newPtr.next = null;

                } else if (list2 == null || (list1 != null && list1.val < list2.val)) {
                    // link to list1
                    if (newPtr == null) {
                        newHead = newPtr = list1;
                    } else {
                        newPtr.next = list1;
                        newPtr = newPtr.next;
                    }
                    list1 = list1.next;
                    newPtr.next = null;
                } else {
                    throw new RuntimeException("fuck you");
                }
            }
            return newHead;
        }
    }

    interface 合并两个有序链表 {
        ListNode mergeTwoLists(ListNode list1, ListNode list2);
    }

    // ---------------
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
            ListNode t = next;
            while (t != null) {
                sb.append(t.val);
                t = t.next;
            }
            return sb.toString();
        }
    }

}

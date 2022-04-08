package leetCode.repository;

/**
 *
 * @author jiangxiewei
 * @since 2022/4/8
 */
public class No160相交链表 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        return superDoublePointer(headA, headB);
    }

    /**
     * 官方题解二的双指针, 通过指针Pa或Pb移动到头后循环到另一个链表的起始点上达成移动量一致
     *
     * @param headA 链表A的起始节点
     * @param headB 链表B的起始节点
     * @return 相交的链表
     */
    public ListNode superDoublePointer(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pa = headA, pb = headB;
        while (pa != pb) {
            pa = pa == null ? headB : pa.next;
            pb = pb == null ? headA : pb.next;
        }
        return pa;
    }

    /**
     * aSum = a链表求和, bSum = b链表求和
     * 然后开始移动a和b的指针Pa和Pb
     * 每移动一下,减少aSum或bSum的值,当相等时,一起移动两个指针. 直到移动到相同的节点上
     * 通过求和的方式来判断两侧指针偏移情况
     * @param headA a链表
     * @param headB b链表
     * @return 目标节点
     */
    private ListNode sumValueToMoveP(ListNode headA, ListNode headB) {
        int aSum = getListSum(headA);
        int bSum = getListSum(headB);
        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            if (aSum >= bSum) {
                aSum -= headA.val;
                headA = headA.next;
            }
            if (aSum <= bSum) {
                bSum -= headB.val;
                headB = headB.next;
            }
        }
        return null;
    }

    private int getListSum(ListNode node) {
        int sum = 0;
        while (node != null) {
            sum += node.val;
            node = node.next;
        }
        return sum;
    }

    /**
     * 题目提供的节点
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}

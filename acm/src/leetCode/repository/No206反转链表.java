package leetCode.repository;

/**
 * @author jiangxiewei
 * @since 2022/3/31
 */
public class No206反转链表 {

    public ListNode reverseList(ListNode head) {
        return reserve(head);
    }

    /**
     * 递归翻转链表
     * 初始: pre -> cur -> next
     * 然后递归让cur成为pre,
     * 直到状态进入最后一层:  pre -> null 的时候, 可以返回cur作为头结点.
     * 回到倒数第二层: 将 pre -> cur -> null 变成 cur -> pre -> null (pre的指针在回到上一级后来纠正)
     * 然后在上一级逻辑里处理 pre -> cur <- next 变成  ( next -> cur -> pre -> null)
     *
     * @param pre 前置节点.
     * @return 你没有体验的床新的头结点
     */
    public ListNode reserve(ListNode pre) {
        if (pre == null || pre.next == null) {
            return pre;
        }
        ListNode cur = pre.next, head;
        if (cur.next == null) {
            head = cur;
        } else {
            head = reserve(cur);
        }
        cur.next = pre;
        pre.next = null;
        return head;
    }


    /**
     * 题目给的结构
     */
    public class ListNode {
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

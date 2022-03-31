package leetCode.repository;

import java.util.Stack;
import java.util.function.BiFunction;

/**
 *
 *
 * @author jiangxiewei
 * @since 2022/3/30
 */
public class No445两数相加II {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return new StackWay().apply(l1, l2);
    }

    /**
     * 栈方案,一次stack处理
     */
    static class StackWay implements BiFunction<ListNode, ListNode, ListNode> {

        @Override
        public ListNode apply(ListNode l1, ListNode l2) {
            Stack<Integer> s1 = transferToStack(l1);
            Stack<Integer> s2 = transferToStack(l2);
            int inc = 0;
            ListNode newNode = null;
            while (!s1.isEmpty() || !s2.isEmpty() || inc > 0) {
                //进位或两栈有任一不为空,则继续
                if (newNode == null) {
                    //节点初始化
                    newNode = new ListNode();
                } else {
                    //生成前置节点
                    ListNode pre = new ListNode();
                    pre.next = newNode;
                    newNode = pre;
                }
                //计算
                int s1Val = s1.isEmpty() ? 0 : s1.pop();
                int s2Val = s2.isEmpty() ? 0 : s2.pop();
                int sum = s1Val + s2Val + inc;
                newNode.val = sum % 10;
                inc = sum / 10;
            }
            return newNode;
        }

        /**
         * 转换成栈
         *
         * @param node 节点
         * @return 栈
         */
        Stack<Integer> transferToStack(ListNode node) {
            Stack<Integer> s = new Stack<>();
            while (node != null) {
                s.push(node.val);
                node = node.next;
            }
            return s;
        }

    }

    /**
     * 引以为戒,写的什么拉胯玩意.
     * 先入栈,两栈相加后生成的链表再
     */
    class OldJunk implements BiFunction<ListNode,ListNode,ListNode> {

        @Override
        public ListNode apply(ListNode l1, ListNode l2) {
            Stack<Integer> s1 = transfer(l1);
            Stack<Integer> s2 = transfer(l2);
            ListNode newNode = null , head = null;
            int inc = 0;
            while (!s1.empty() || !s2.empty() || inc > 0) {
                // 如果为null,初始化节点. 不为null,生成下一个节点.
                if (newNode == null) {
                    head = newNode = new ListNode();
                } else {
                    newNode.next = new ListNode();
                    newNode = newNode.next;
                }
                // 计算
                int s1pop = s1.empty() ? 0 : s1.pop();
                int s2pop = s2.empty() ? 0 : s2.pop();
                int sum = s1pop + s2pop + inc;
                inc = sum / 10;
                newNode.val = sum % 10;
            }
            //链表倒置
            ListNode newHead = reverse(head);
            head.next = null;
            return newHead;
        }

        /**
         * 链表转换成栈
         *
         * @param node 链表头节点
         * @return 栈
         */
        Stack<Integer> transfer(ListNode node) {
            Stack<Integer> s = new Stack<>();
            while (node != null) {
                s.push(node.val);
                node = node.next;
            }
            return s;
        }

        /**
         * 倒置链表
         *
         * @param node 节点哟
         * @return 返回新的头结点
         */
        ListNode reverseNode(ListNode node) {
            ListNode reverse = reverse(node);
            node.next = null;
            return reverse;
        }

        ListNode reverse(ListNode pre) {
            ListNode cur = pre.next, head = null;
            if (cur == null) {
                return pre;
            } else if (cur.next == null) {
                head = cur;
            } else {
                head = reverse(cur);
            }
            cur.next = pre;
            return head;
        }

    }



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

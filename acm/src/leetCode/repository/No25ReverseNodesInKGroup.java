package leetCode.repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * K 个一组翻转链表
 * <a href="https://leetcode-cn.com/problems/reverse-nodes-in-k-group/">address</a>
 *
 * @author jiangxiewei
 * @since 2021/5/12
 */
public class No25ReverseNodesInKGroup {

    public static void main(String[] args) {
        new 自己想的递归模式().reverseKGroup(ListNode.packageList(1, 2, 3, 4, 5), 3).print();
        new 自己想的递归模式().reverseKGroup(ListNode.packageList(1, 2, 3, 4, 5), 1).print();
        new 自己想的递归模式().reverseKGroup(ListNode.packageList(1), 1).print();
        new 自己想的递归模式().reverseKGroup(ListNode.packageList(1,2,3,4,5), 2).print();
    }

    public ListNode reverseKGroup(ListNode head, int k){
        return new 自己想的递归模式().reverseKGroup(head, k);
    }

    /**
     * 解法接口
     */
    private interface ReverseKGroup {
        ListNode reverseKGroup(ListNode head, int k);
    }

    /**
     * 递归方式实现,个人感觉写的很不优雅("写的什么辣鸡玩意").
     */
    private static class 自己想的递归模式 implements ReverseKGroup {

        @Override
        public ListNode reverseKGroup(ListNode head, int k) {
            var cur = head;
            while (cur != null) {
                recursion(cur, k);
                if (isBreak) {
                    break;
                }
                cur.next = deepestRecursionTail;
                beforeRecursionHead = cur;
                cur = cur.next;
            }
            return firstResult == null ? head : firstResult;
        }

        private ListNode firstResult = null;
        private boolean isBreak = false;
        private ListNode beforeRecursionHead = null;
        private ListNode deepestRecursionTail = null;
        public ListNode recursion(ListNode node, int cur) {
            if (cur > 0 && node == null) {
                isBreak = true;
            } else if (cur == 1) {
                if (beforeRecursionHead != null) {
                    beforeRecursionHead.next = node;
                }
                if (firstResult == null) {
                    firstResult = node;
                }
                deepestRecursionTail = node.next;
            } else {
                recursion(node.next, cur - 1);
                if (isBreak) {
                    return node;
                }
                if (node.next != null) {
                    node.next.next = node;
                }
            }
            return node;
        }

    }

    /**
     * 莫赋值
     */
    private static class ListNode {
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

        public static ListNode packageList(int... values) {
            var head = new ListNode();
            ListNode cur = head;
            for (int i = 0; i < values.length; i++) {
                cur.val = values[i];
                if (i<values.length-1) {
                    cur.next = new ListNode();
                }
                cur = cur.next;
            }
            return head;
        }

        public void print() {
            List<Integer> linked = new LinkedList<>();
            ListNode cur = this;
            do {
                linked.add(cur.val);
            } while ((cur = cur.next) != null);
            System.out.println(Arrays.toString(linked.toArray()));
        }

    }

}
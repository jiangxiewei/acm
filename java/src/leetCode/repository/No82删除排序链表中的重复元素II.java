package leetCode.repository;

import java.util.HashMap;
import java.util.function.Function;

/**
 * 删除排序链表中的重复元素 II
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 返回同样按升序排列的结果链表。
 *
 * @author jiangxiewei
 * @since 2021/12/3
 */
public class No82删除排序链表中的重复元素II {

    public static void main(String[] args) {
        No82删除排序链表中的重复元素II no82 = new No82删除排序链表中的重复元素II();
        no82.deleteDuplicates(ListNode.from(1, 2, 3, 3, 4, 4, 5)).print();
    }

    public ListNode deleteDuplicates(ListNode head) {
        return new TwiceScan().apply(head);
    }

    /**
     * 两次扫描
     */
    static class TwiceScan implements Function<ListNode, ListNode> {
        @Override
        public ListNode apply(ListNode listNode) {
            if (listNode == null) {
                return listNode;
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            ListNode node = listNode, preNode = null;
            //第一次
            do {
                map.put(node.val, map.getOrDefault(node.val, 0) + 1);
            } while ((node = node.next) != null);
            //第二次
            ListNode newHead = node = listNode;
            do {
                if (map.get(node.val) > 1) {
                    if (preNode != null) {
                        preNode.next = node.next;
                    } else {
                        newHead = node.next;
                    }
                } else {
                    preNode = node;
                }
            } while ((node = node.next) != null);
            return newHead;
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

        public void print() {
            print(null);
        }

        private void print(StringBuilder sb) {
            sb = sb == null ? new StringBuilder() : sb;
            sb.append(val);
            if (next != null) {
                next.print(sb);
            } else {
                System.out.println(sb);
            }
        }

        public static ListNode from(Integer... args) {
            ListNode head = null, curNode = null;
            for (Integer arg : args) {
                if (curNode == null) {
                    head = curNode = new ListNode(arg);
                } else {
                    curNode = curNode.next = new ListNode(arg);
                }
            }
            return head;
        }

    }
}

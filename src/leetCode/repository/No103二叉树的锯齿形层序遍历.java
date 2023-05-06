package leetCode.repository;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

/**
 * 103. 二叉树的锯齿形层序遍历
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * 返回锯齿形层序遍历如下：
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * @author jiangxiewei
 * @since 2021/10/27
 */
public class No103二叉树的锯齿形层序遍历 {

    /**
     * 提供的数据结构
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        TreeNode(Integer... args) {
            this.val = args[0];
            Queue<TreeNode> queue = new LinkedBlockingQueue<>();
            queue.offer(this);
            for (int i = 1; i < args.length; i += 2) {
                TreeNode node = queue.poll();
                Integer left = args[i];
                Integer right = i + 1 < args.length ? args[i + 1] : null;
                if (left != null) {
                    node.left = new TreeNode(left);
                    queue.offer(node.left);
                }
                if (right != null) {
                    node.right = new TreeNode(right);
                    queue.offer(node.right);
                }
            }
        }

        public String print() {
            StringJoiner sj = new StringJoiner(",", "[", "]");
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(this);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    sj.add("null");
                    continue;
                }
                sj.add(String.valueOf(node.val));
                queue.offer(node.left);
                queue.offer(node.right);
            }
            return sj.toString();
        }

    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        new No103二叉树的锯齿形层序遍历().zigzagLevelOrder(new TreeNode(3, 9, 20, null, null, 15, 7));
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        return new 先层序遍历后倒置数组().apply(root);
    }

    public static class 先层序遍历后倒置数组 implements Function<TreeNode, List<List<Integer>>> {

        @Override
        public List<List<Integer>> apply(TreeNode treeNode) {
            //层序遍历
            List<List<Integer>> bfsResult = new ArrayList<>();
            Queue<Node> queue = new LinkedList<>();
            queue.offer(new Node(treeNode, 0));
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node.trueNode == null) {
                    continue;
                }
                //结果集
                while (bfsResult.size() < node.floor + 1) {
                    bfsResult.add(new LinkedList<>());
                }
                bfsResult.get(node.floor).add(node.trueNode.val);
                queue.offer(new Node(node.trueNode.left, node.floor + 1));
                queue.offer(new Node(node.trueNode.right, node.floor + 1));
            }
            //间隔倒置数组
            for (int i = 0; i < bfsResult.size(); i++) {
                if ((i & 1) == 1) {
                    Collections.reverse(bfsResult.get(i));
                }
            }
            return bfsResult;
        }

        public class Node {
            TreeNode trueNode;
            int floor;

            public Node(TreeNode trueNode, int floor) {
                this.trueNode = trueNode;
                this.floor = floor;
            }
        }

    }

}

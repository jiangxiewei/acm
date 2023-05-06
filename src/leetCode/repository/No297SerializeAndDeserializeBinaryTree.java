package leetCode.repository;

import java.util.Queue;
import java.util.StringJoiner;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉树的序列化与反序列化
 *
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 你可以将以下二叉树：
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 *
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 *
 * @author jiang
 * @date 2020/6/16
 */
public class No297SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {

        No297SerializeAndDeserializeBinaryTree no = new No297SerializeAndDeserializeBinaryTree();
        System.out.println(no.serialize(no.deserialize("[1,2,3,null,null,4,5]")));
        System.out.println(no.serialize(no.deserialize("[]")));
    }

    /**
     * 层序遍历(BFS)序列化
     * @param root 根节点
     * @return 序列化后字符串
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        StringJoiner sb = new StringJoiner(",", "[", "]");
        Queue<TreeNode> que = new LinkedBlockingQueue<>();
        que.add(root);
        sb.add(String.valueOf(root.val));
        while (!que.isEmpty()) {
            TreeNode poll = que.poll();
            if (poll.left != null) {
                que.add(poll.left);
                sb.add(String.valueOf(poll.left.val));
            } else {
                sb.add(null);
            }
            if (poll.right != null) {
                que.add(poll.right);
                sb.add(String.valueOf(poll.right.val));
            } else {
                sb.add(null);
            }
        }
        return sb.toString();
    }

    /**
     * 反序列化
     * @param data 序列化后的字符串
     * @return 反序列化的根
     */
    public TreeNode deserialize(String data) {
        String[] split = data.replaceAll("[\\[\\]]", "").split(",");
        if (split.length == 0 || "".equals(split[0])) {
            return null;
        }
        Queue<TreeNode> que = new LinkedBlockingQueue<>();
        int index = 0;
        TreeNode root = new TreeNode(getValueFromStr(split[index++]));
        que.add(root);
        Integer next;
        while (!que.isEmpty() && index < split.length) {
            TreeNode poll = que.poll();
            next = getValueFromStr(split[index++]);
            if (next != null) {
                poll.left = new TreeNode(next);
                que.add(poll.left);
            }
            next = getValueFromStr(split[index++]);
            if (next != null) {
                poll.right = new TreeNode(next);
                que.add(poll.right);
            }
        }
        return root;
    }

    private Integer getValueFromStr(String string) {
        if ("null".equals(string)) {
            return null;
        } else {
            return Integer.valueOf(string);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}


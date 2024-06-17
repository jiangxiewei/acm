package leetCode.repository;

/**
 * @author jiangxiewei
 * @since 2022/4/7
 */
public class No543二叉树的直径 {

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return maxResult;
    }

    /**
     * 计算每个节点左右子树最大深度,返回深度更大的一个.
     * 迭代过程中顺便记录最大的左右子树最大和.
     */
    private int maxResult = 0;
    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftMaxDep = dfs(node.left);
        int rightMaxDep = dfs(node.right);
        maxResult = Math.max(maxResult, leftMaxDep + rightMaxDep);
        return Math.max(leftMaxDep, rightMaxDep) + 1;
    }

    /**
     * 题目提供的结构
     */
    public class TreeNode {
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
    }
}

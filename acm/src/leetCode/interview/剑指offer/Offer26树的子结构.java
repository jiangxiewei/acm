package leetCode.interview.剑指offer;

/**
 * @author jiangxiewei
 * @since 2022/4/14
 */
public class Offer26树的子结构 {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return searchBRootFromA(A, B);
    }

    private boolean searchBRootFromA(TreeNode a, TreeNode b) {
        //递归搜索A节点中,与B的根值一样的节点
        if (a == null) {
            return false;
        } else if (a.val == b.val && gogogo(a, b)) {
            //相同,开始同步迭代两树,判断是否一致
            return true;
        } else {
            //继续搜索
            return searchBRootFromA(a.left, b) || searchBRootFromA(a.right, b);
        }
    }

    private boolean gogogo(TreeNode a, TreeNode b) {
        if (a != null && b != null && a.val == b.val) {
            //a,b值相等
            return gogogo(a.left, b.left) && gogogo(a.right, b.right);
        } else if (a == null && b == null) {
            //a,b都结束,true
            return true;
        } else if (a != null && b == null) {
            //a未结束,b结束
            return true;
        } else {
            return false;
        }
    }

    /**
     * 题目提供的节点结构
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}

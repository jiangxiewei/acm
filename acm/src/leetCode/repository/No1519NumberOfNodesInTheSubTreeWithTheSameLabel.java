package leetCode.repository;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 子树中标签相同的节点数
 * <p>
 * 给你一棵树（即，一个连通的无环无向图），这棵树由编号从 0  到 n - 1 的 n 个节点组成，且恰好有 n - 1 条 edges 。树的根节点为节点 0 ，树上的每一个节点都有一个标签，也就是字符串 labels 中的一个小写字符（编号为 i 的 节点的标签就是 labels[i] ）
 * <p>
 * 边数组 edges 以 edges[i] = [ai, bi] 的形式给出，该格式表示节点 ai 和 bi 之间存在一条边。
 * <p>
 * 返回一个大小为 n 的数组，其中 ans[i] 表示第 i 个节点的子树中与节点 i 标签相同的节点数。
 * <p>
 * 树 T 中的子树是由 T 中的某个节点及其所有后代节点组成的树。
 * <p>
 * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
 * 输出：[2,1,1,1,1,1,1]
 * 解释：节点 0 的标签为 'a' ，以 'a' 为根节点的子树中，节点 2 的标签也是 'a' ，因此答案为 2 。注意树中的每个节点都是这棵子树的一部分。
 * 节点 1 的标签为 'b' ，节点 1 的子树包含节点 1、4 和 5，但是节点 4、5 的标签与节点 1 不同，故而答案为 1（即，该节点本身）。
 * <p>
 * 输入：n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
 * 输出：[4,2,1,1]
 * 解释：节点 2 的子树中只有节点 2 ，所以答案为 1 。
 * 节点 3 的子树中只有节点 3 ，所以答案为 1 。
 * 节点 1 的子树中包含节点 1 和 2 ，标签都是 'b' ，因此答案为 2 。
 * 节点 0 的子树中包含节点 0、1、2 和 3，标签都是 'b'，因此答案为 4 。
 * <p>
 * 输入：n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = "aabab"
 * 输出：[3,2,1,1,1]
 * <p>
 * 链接：https://leetcode-cn.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label
 *
 * @author jxw
 * @date 2020/7/20
 */
public class No1519NumberOfNodesInTheSubTreeWithTheSameLabel {

    public static void main(String[] args) {
        No1519NumberOfNodesInTheSubTreeWithTheSameLabel no = new No1519NumberOfNodesInTheSubTreeWithTheSameLabel();
        //[2,1,1,1,1,1,1]
        System.out.println(Arrays.toString(no.countSubTrees(7,
                new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, "abaedcd")));
        //[4, 2, 1, 1]
        System.out.println(Arrays.toString(no.countSubTrees(4, new int[][]{{0, 1}, {1, 2}, {0, 3}}, "bbbb")));
        //[1,1,2,1]
        System.out.println(Arrays.toString(no.countSubTrees(4, new int[][]{{0, 2}, {0, 3}, {1, 2}}, "aeed")));
    }

    //邻接表
    LinkedList<Integer>[] adjacency;
    //每个节点子树的数据统计.
    int[][] count;
    //最终结果.
    int[] result;
    //节点n对应的字符
    char[] lableArr;

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        adjacency = new LinkedList[n];
        count = new int[n][26];
        result = new int[n];
        lableArr = labels.toCharArray();
        //建立邻接表.用于迭代
        for (int[] edge : edges) {
            adjacency[edge[0]] = adjacency[edge[0]] == null ? new LinkedList<>() : adjacency[edge[0]];
            adjacency[edge[1]] = adjacency[edge[1]] == null ? new LinkedList<>() : adjacency[edge[1]];
            adjacency[edge[0]].add(edge[1]);
            adjacency[edge[1]].add(edge[0]);
        }
        travNode(0, -1);
        return result;
    }

    //顺着邻接表迭代树
    private void travNode(int cur, int pre) {
        //节点拥有的字符对应统计值+1
        count[cur][lableArr[cur] - 'a']++;
        if (adjacency[cur] != null) {
            for (Integer next : adjacency[cur]) {
                //保证向下查询
                if (next == pre) {
                    continue;
                }
                //继续向下迭代
                travNode(next, cur);
                //把子树的所有字符数量统计加到当前节点上.
                for (int i = 0; i < 26; i++) {
                    count[cur][i] += count[next][i];
                }
            }
        }
        //此节点子集统计完成,将此节点对应字符统计值添加到结果上.
        result[cur] = count[cur][lableArr[cur] - 'a'];
    }

}

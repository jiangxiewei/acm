package codeforce.CodeforcesRound498Div3;

import java.util.*;

/**
 * E.Military Problem
 * 构建了个树,然后对于每次询问我居然真的每次都去对应的端点走了一次前序遍历.....
 * 即便加上了统计进行剪枝也依旧超时,标准的错误思路.
 */
public class EErrorCodeEMilitaryProblem {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), q = scan.nextInt();
        int[] father = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            father[i] = scan.nextInt();
        }
        //初始化军官树
        Officers officerTree = new Officers(father);
        int u, k;
        for (int i = 0; i < q; i++) {
            u = scan.nextInt();
            k = scan.nextInt();
            Officers.Node target = officerTree.dfsNoK(officerTree.getOfficer(u), new int[]{k});
            System.out.println((target == null ? -1 : target.index));
        }
    }

    /**
     * 军官树,存放一个数组维护所有节点.
     */
    static class Officers {

        private Node[] nodes;

        /**
         * 初始化,将树连通
         *
         * @param fatherArray 从2开始,fatherArray[i]对应军官i的上级
         */
        Officers(int[] fatherArray) {
            nodes = new Node[fatherArray.length];
            nodes[1] = new Node(1);
            Node node;
            for (int i = 2; i < fatherArray.length; i++) {
                Node father = getOfficer(fatherArray[i]);
                nodes[i] = node = new Node(i);
                father.addSon(node);
            }
            countNode(nodes[1]);
        }

        /**
         * 直接获取序号为index的军官节点
         *
         * @param index 序号
         * @return 节点
         */
        Node getOfficer(int index) {
            return nodes[index];
        }

        /**
         * 对每一个节点进行递归统计
         */
        public int countNode(Node nod) {
            int count = 1;
            for (Node node : nod.sons) {
                count += countNode(node);
            }
            nod.setTotalSons(count);
            return count;
        }

        /**
         * DFS 查找第k个遍历,对于每个提问(u,k),剪枝跳过所有统计数小于k-1的值.
         *
         * @return 查找到了则返回, 没查找到则返回null
         */
        Node dfsNoK(Node u, int[] k) {
//            System.out.println(u.index + " k:" + k[0]);
            if (k[0] == 1) {
                return u;
            }
            for (Node n : u.sons) {
                if (k[0] > n.totalSons + 1) {
                    k[0] -= n.totalSons;
                } else {
                    k[0]--;
                    return dfsNoK(n, k);
                }
            }
            return null;
        }

        /**
         * 节点
         */
        class Node {
            private int index;
            private List<Node> sons = new LinkedList<>();
            private int totalSons = 0;

            public Node(int index) {
                this.index = index;
            }

            public void addSon(Node son) {
                sons.add(son);
            }

            public void setTotalSons(int totalSons) {
                this.totalSons = totalSons;
            }
        }

        /**
         * debug用
         */
        public void print() {
            System.out.println("Tree:");
            printTree(nodes[1]);
        }

        /**
         * dfs遍历打印树内容 debug用
         */
        private void printTree(Node node) {
            System.out.print(node.index + ":" + node.totalSons + " ");
            for (Node next : node.sons) {
                printTree(next);
            }
        }
    }

}

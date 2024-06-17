package codeforce.CodeforcesRound498Div3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * E.Military Problem
 * 输入n个端点跟q次询问
 * 然后输入n-1个数,第i个数代表序号为(i+1)的端点的父节点
 * 接着输入q个询问,每个询问输入u跟k两个数,代表从u开始传播(前序遍历)的第k个数
 */
public class EMilitaryProblem {

    /**
     * 思路:
     * 1.维护一颗树,统计每个子树的数目
     * 2.生成一个从根节点开始前序遍历的序列,记录下每个节点在序列内的序号
     * 3.对于每一个询问(u,k)做如下处理:
     * (1)先判断k是否大于u节点的统计(第1步的统计)结果,若大于则输出-1
     * (2)再查找u在序列(第2步生成的序列)中的位置,那么序列的第u+k-1个数字就是要输出的结果
     *
     * @param args nothing
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), q = scan.nextInt();
        int[] father = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            father[i] = scan.nextInt();
        }
        //初始化军官树
        Officers officerTree = new Officers(father);
        //开始询问
        List<Integer> target = officerTree.getPreOrder();
        int u, k;
        for (int i = 0; i < q; i++) {
            u = scan.nextInt();
            k = scan.nextInt();
            if (officerTree.getOfficer(u).getCount() < k) {
                System.out.println("-1");
            } else {
                System.out.println(target.get(officerTree.getOfficer(u).getOrder() + k - 1));
            }

        }
    }

    /**
     * 存放军官的树,存放一个数组维护所有节点.
     */
    static class Officers {

        private Node[] nodes;
        private List<Integer> preOrder;

        /**
         * 初始化,将树连通,统计,生成前序遍历结果
         *
         * @param fatherArray 从2开始,fatherArray[i]对应军官i的上级
         */
        Officers(int[] fatherArray) {
            nodes = new Node[fatherArray.length];
            nodes[1] = new Node(1);
            for (int i = 2; i < fatherArray.length; i++) {
                nodes[fatherArray[i]].addSon(nodes[i] = new Node(i));
            }
            preOrder = new ArrayList<>(fatherArray.length - 1);
            countNodeAndGenerateOrder(nodes[1]);
        }

        /**
         * 对每一个节点进行递归统计,顺便生成前序遍历的结果
         */
        private int countNodeAndGenerateOrder(Node nod) {
            nod.setOrder(preOrder.size());
            preOrder.add(nod.getIndex());
            int count = 1;
            for (Node node : nod.sons) {
                count += countNodeAndGenerateOrder(node);
            }
            nod.setCount(count);
            return count;
        }

        public List<Integer> getPreOrder() {
            return preOrder;
        }

        public Node getOfficer(int index) {
            return nodes[index];
        }

        /**
         * 节点
         */
        class Node {
            private int index;
            private int count;
            private int order;
            private List<Node> sons = new LinkedList<>();

            Node(int index) {
                this.index = index;
            }

            public void addSon(Node son) {
                sons.add(son);
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getIndex() {
                return index;
            }

            public int getCount() {
                return count;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getOrder() {
                return order;
            }


        }
    }
}

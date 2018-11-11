package hdu;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 增广路模板题
 * <p>
 * 题目坑点: 题目虽然告诉你边数量会输入过量,但没有告知过量情况下该如何处理.此处处理方式: 输入重边相叠加
 * <p>
 * 默认1为源点,N为汇点
 *
 * @author jxwww
 * @date 2018/11/11
 */
public class No3549FlowProblem {

    /**
     * 邻接矩阵
     */
    private int[][] capMatrix;
    /**
     * 前置点
     */
    private int[] preVertex;
    /**
     * 顶点数量n
     * 顶点标号为1~n
     */
    private int n;

    /**
     * bfs用队列
     */
    private Queue<Integer> que = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        new No3549FlowProblem().getInputAndResolve();
    }

    /**
     * 使用EK法,查找最大流查找网络流
     *
     * @return 最大流
     */
    private int findMaxFlowWithEK() {
        int maxFlow = 0;
        int cur, flow = Integer.MAX_VALUE;
        while (bfsFindArgumentingPath()) {
//            System.out.println("找到增广路");
            //先查找到的增广路上的可改尽量
            cur = n;
            while (preVertex[cur] > 0) {
//                System.out.println("增广路经:c[" + preVertex[cur] + "][" + cur + "]=" + capMatrix[preVertex[cur]][cur] + ",flow=" + flow);
                flow = Math.min(capMatrix[preVertex[cur]][cur], flow);
                cur = preVertex[cur];
            }
            //更新流
            cur = n;
            while (preVertex[cur] > 0) {
                updateFlow(preVertex[cur], cur, flow);
                cur = preVertex[cur];
            }
            maxFlow += flow;
        }
        return maxFlow;
    }

    /**
     * 使用BFS查找增广路
     *
     * @return 存在增广路则返回true
     */
    private boolean bfsFindArgumentingPath() {
        //清空标记 与 前置顶点
        for (int i = 1; i <= n; i++) {
            preVertex[i] = 0;
        }
        que.add(1);
        preVertex[1] = -1;
        Integer cur;
        while (!que.isEmpty()) {
            cur = que.poll();
            if (cur == n) {
                //到达终点,清空队列
                while (!que.isEmpty()) {
                    que.poll();
                }
                return true;
            }
            for (int i = 1; i <= n; i++) {
                //查找增广路,方便起见此处使用邻接矩阵.
                if (preVertex[i] == 0 && capMatrix[cur][i] > 0) {
//                    System.out.println("bfs--c[" + cur + "][" + i + "]=" + capMatrix[cur][i]);
                    //未被搜索过且残余容量不为空
                    preVertex[i] = cur;
                    que.add(i);
                }
            }
        }
        return false;
    }

    /**
     * 更新流
     *
     * @param u    u
     * @param v    v
     * @param flow flow
     */
    public void updateFlow(int u, int v, int flow) {
//        System.out.println(String.format("update u:%d v:%d flow:%d", u, v, flow));
        if (capMatrix[u][v] >= flow && flow > 0) {
            capMatrix[u][v] -= flow;
            capMatrix[v][u] += flow;
        } else {
            throw new IllegalArgumentException("redu[u][v]=" + capMatrix[u][v] + ",flow=" + flow);
        }
    }

    /**
     * 提交hdu OJ时的形式
     */
    public void getInputAndResolve() {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt(), n, m, u, v, c;
        int[][] matrix;
        for (int seq = 1; seq <= t; seq++) {
            //获取点数量,边数量的输入值
            n = scan.nextInt() + 1;
            m = scan.nextInt();
            matrix = new int[n][n];
            //m条边
            for (int i = 0; i < m; i++) {
                //获取输入的 C(u,v) = c 的信息
                u = scan.nextInt();
                v = scan.nextInt();
                c = scan.nextInt();
                //此题坑点,会重复输入相同边,且要求边值相加.
                matrix[u][v] += c;
            }
            //将初始的容量矩阵信息存入此对象中
            this.n = n - 1;
            this.capMatrix = matrix;
            this.preVertex = new int[n];
            //执行对象的查找最大流方法并打印最大流结果
            System.out.println("Case " + seq + ": " + findMaxFlowWithEK());
        }
    }

}

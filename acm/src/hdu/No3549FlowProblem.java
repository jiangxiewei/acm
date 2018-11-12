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
     * ek中为: 前置点
     * dinic中为: 层次标记
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

    /**
     * 入口
     *
     * @param args 无视
     */
    public static void main(String[] args) {
        new No3549FlowProblem().getInputAndResolve();
    }

    /**
     * 使用Dinic法,查找最大流
     *
     * @return 最大流
     */
    private int findMaxFlowWithDinic() {
        int maxFlow = 0;
        while (bfsFindArgumentingPath(true)) {
            maxFlow += dfsToArgumentPath();
        }
        return maxFlow;
    }

    /**
     * dfs查找增广路并增广
     *
     * @return 可改进量
     */
    private int dfsToArgumentPath() {


        return 0;
    }

    /**
     * 使用EK法,查找最大流
     *
     * @return 最大流
     */
    private int findMaxFlowWithEK() {
        int maxFlow = 0;
        int cur, flow = Integer.MAX_VALUE;
        //不断BFS搜索增广路,找到了对路增广
        while (bfsFindArgumentingPath(false)) {
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
     * 使用BFS查找增广路,也可用于dinic算法的分层
     *
     * @param laminate 分层标记,为true时将只对图进行分层
     * @return 存在增广路则返回true
     */
    private boolean bfsFindArgumentingPath(boolean laminate) {
        //清空标记 与 前置顶点
        for (int i = 1; i <= n; i++) {
            preVertex[i] = -1;
        }
        que.add(1);
        preVertex[1] = 0;
        Integer cur;
        while (!que.isEmpty()) {
            cur = que.poll();
            if (cur == n) {
                //到达终点
                que.clear();
                return true;
            }
            for (int i = 1; i <= n; i++) {
                //查找增广路,方便起见此处使用邻接矩阵.
                if (preVertex[i] == -1 && capMatrix[cur][i] > 0) {
//                    System.out.println("bfs--c[" + cur + "][" + i + "]=" + capMatrix[cur][i]);
                    //未被搜索过且残余容量不为空
                    if (laminate) {
                        //对其分层
                        preVertex[i] = preVertex[cur] + 1;
                    } else {
                        preVertex[i] = cur;
                    }
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

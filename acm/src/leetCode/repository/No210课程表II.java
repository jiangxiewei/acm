package leetCode.repository;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author jiangxiewei
 * @since 2022/4/6
 */
public class No210课程表II {

    /**
     * 拓扑排序 + 广度优先方案
     * 通过入度统计判断节点是否可
     *
     * @param numCourses    课程数
     * @param prerequisites 边集合
     * @return 结果顺序
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //初始化 邻接表
        LinkedList<Integer>[] dependency = new LinkedList[numCourses];
        for (int i = 0; i < dependency.length; i++) {
            dependency[i] = new LinkedList<>();
        }
        //初始化 入度统计表 和 邻接表
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int pre = prerequisite[1], after = prerequisite[0];
            //记录邻接表
            dependency[pre].add(after);
            //统计入度
            inDegree[after]++;
        }
        //入度为0的节点入队列,开始广度优先gogogo
        Queue<Integer> queue = new LinkedBlockingQueue<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                //入度为0,入队列
                queue.offer(i);
            }
        }
        //初始节点有了,开始bfs迭代
        int pos = 0;
        int[] resultSort = new int[numCourses];
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            resultSort[pos++] = (cur);
            //查看可达节点,清空入度
            for (Integer element : dependency[cur]) {
                if ((--inDegree[element]) == 0) {
                    //入度为空了,下一个来清理
                    queue.offer(element);
                }
            }
        }
        //结果集数量对不上(可能有环),88
        if (pos != numCourses) {
            return new int[0];
        }
        return resultSort;
    }

}

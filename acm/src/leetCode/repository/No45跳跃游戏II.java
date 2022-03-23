package leetCode.repository;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

/**
 * @author jiangxiewei
 * @since 2022/3/23
 */
public class No45跳跃游戏II {

    public static void main(String[] args) {
        System.out.println(new No45跳跃游戏II().jump(new int[]{2, 3, 1, 1, 4}));
    }

    public int jump(int[] nums) {
        return new Bfs().apply(nums);
    }

    /**
     * 从另一个角度看,这玩意其实很像bfs
     * 把每个节点以及其可达节点都画出来,马上能发现是一张图,可以用bfs找最短路径.
     */
    static class Bfs implements Function<int[], Integer> {

        @Override
        public Integer apply(int[] ints) {
            Queue<Integer> queue = new LinkedBlockingQueue<>();
            queue.offer(0);
            Integer curNode;
            //记录到此节点所需的步数,默认为0
            int[] calc = new int[ints.length];
            while ((curNode = queue.poll()) != null) {
                //遍历此节点curNode可达所有节点nextNode,算好nextNode所需步数,然后nextNode入队列
                for (int i = 1; i <= ints[curNode] && curNode + i < ints.length; i++) {
                    int nextNode = curNode + i;
                    if (calc[nextNode] == 0) {
                        //如果nextNode未被遍历过,计算curNode到nextNode所需步数并记录至calc[nextNode]内.
                        calc[nextNode] = calc[curNode] + 1;
                        //进队列等待计算nextNode的可达节点所需步数
                        queue.offer(nextNode);
                    }
                }
            }
            return calc[ints.length - 1];
        }
    }

    /**
     * 尝试dp法求,结果发现成了贪心.
     */
    static class Greed implements Function<int[], Integer> {

        @Override
        public Integer apply(int[] ints) {
            int[] calc = new int[ints.length];
            Arrays.fill(calc, Integer.MAX_VALUE);
            calc[0] = 0;
            for (int i = 0; i < ints.length; i++) {
                for (int j = 1; j <= ints[i] && i + j < ints.length; j++) {
                    calc[i + j] = Math.min(calc[i + j], calc[i] + 1);
                }
            }
            return calc[ints.length - 1];
        }
    }

}

package leetCode.repository;

import java.util.*;

/**
 *
 * 你有 k 个服务器，编号为 0 到 k-1 ，它们可以同时处理多个请求组。每个服务器有无穷的计算能力但是 不能同时处理超过一个请求 。请求分配到服务器的规则如下：
 *
 * 第 i （序号从 0 开始）个请求到达。
 * 如果所有服务器都已被占据，那么该请求被舍弃（完全不处理）。
 * 如果第 (i % k) 个服务器空闲，那么对应服务器会处理该请求。
 * 否则，将请求安排给下一个空闲的服务器（服务器构成一个环，必要的话可能从第 0 个服务器开始继续找下一个空闲的服务器）。比方说，如果第 i 个服务器在忙，那么会查看第 (i+1) 个服务器，第 (i+2) 个服务器等等。
 * 给你一个 严格递增 的正整数数组 arrival ，表示第 i 个任务的到达时间，和另一个数组 load ，其中 load[i] 表示第 i 个请求的工作量（也就是服务器完成它所需要的时间）。你的任务是找到 最繁忙的服务器 。最繁忙定义为一个服务器处理的请求数是所有服务器里最多的。
 *
 * 请你返回包含所有 最繁忙服务器 序号的列表，你可以以任意顺序返回这个列表。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests
 *
 * @author jiangxiewei
 * @since 2022/3/30
 */
public class No1606找到处理最多请求的服务器 {

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] serverLastUsed = new int[k];
        //初始化最新的可用时间点
        for (int i = 0; i < serverLastUsed.length; i++) {
            serverLastUsed[i] = 1;
        }
        int[] serverRequestResolved = new int[k];
        //初始化available列表和busy列表 (减少每次遍历数)
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        //队列里塞入<server编号,最后处理时间>
        PriorityQueue<Integer> busyQueue = new PriorityQueue<>(Comparator.comparingInt(o -> serverLastUsed[o]));
        //遍历请求,更新处理数
        for (int i = 0; i < arrival.length; i++) {
            // arrival[i]代表时间流动到此,将busy中已经不busy的数据进行剔除
            while (!busyQueue.isEmpty() && serverLastUsed[busyQueue.peek()] <= arrival[i]) {
                available.add(busyQueue.poll());
            }
            //available为空,不玩了
            if (available.isEmpty()) {
                continue;
            }
            //使用红黑树的api查找大于等于目标值的最近序号
            Integer hash = available.ceiling(i % k);
            if (hash == null) {
                // 如果不存在,直接使用可行列表中最小的
                hash = available.first();
            }
            serverRequestResolved[hash]++;
            serverLastUsed[hash] = arrival[i] + load[i];
            //此server进入busy状态
            busyQueue.offer(hash);
            available.remove(hash);
//            System.out.println(busyQueue);
        }
        //查找最大值
        int max = 0;
        for (int i = 0; i < serverRequestResolved.length; i++) {
            max = serverRequestResolved[i] > max ? serverRequestResolved[i] : max;
        }
//        System.out.println(Arrays.toString(serverRequestResolved));
//        System.out.println(Arrays.toString(serverLastUsed));
        //查找和最大值一样的服务器列表
        List<Integer> resultServerList = new LinkedList<>();
        for (int i = 0; i < serverRequestResolved.length; i++) {
            if (serverRequestResolved[i] == max) {
                resultServerList.add(i);
            }
        }
        return resultServerList;
    }

}

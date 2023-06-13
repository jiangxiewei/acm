package leetCode.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class No2475数组中不等三元组的数目 {

    public static void main(String[] args) {

        // 3
        System.out.println(new No2475数组中不等三元组的数目().unequalTriplets(new int[]{4, 4, 2, 4, 3}));

    }

    // 根据数字进行统计，得到k=数字，v=出现次数。 再遍历数字，将数字分三组
    // 例：存在统计结果 ( a->3 , b->1 , c->1 , d->1 , e->1 ) (k->v)
    // 对c求包含c的所有结果为: a*c*d + a*c*e + b*c*d +b*c*e = (a+b) * c * (d+e) 同理推广至 b 和 d ， 然后求和包含b、c、d出现的所有结果得到最终结果。
    public int unequalTriplets(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        AtomicInteger result = new AtomicInteger(), leftCount = new AtomicInteger();
        countMap.values().forEach(v -> {
            if (leftCount.get() != 0) {
                result.addAndGet(v * leftCount.get() * (nums.length - leftCount.get() - v));
            }
            leftCount.addAndGet(v);
        });

        return result.get();
    }

}

package leetCode.weeklyContest.week358;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class No2815数组中的最大数对和 {


    public static void main(String[] args) {
        System.out.println(new No2815数组中的最大数对和().maxSum(new int[]{51, 71, 17, 24, 42}));
    }

    public int maxSum(int[] nums) {
        Map<Integer, List<Integer>> maxNumMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int maxNumInBit = getMaxNumInBit(nums[i]);
            List<Integer> list = maxNumMap.computeIfAbsent(maxNumInBit, key -> new ArrayList<>());
            list.add(nums[i]);
        }
        AtomicInteger maxValue = new AtomicInteger(-1);
        maxNumMap.forEach((key, value) -> {
            if (value.size() > 1) {
                value.sort(Comparator.comparing(Integer::intValue).reversed());
                maxValue.set(Math.max(value.get(0) + value.get(1), maxValue.get()));
            }
        });
        return maxValue.get();
    }

    private int getMaxNumInBit(int num) {
        int max = 0, t;
        while (num > 0) {
            t = num % 10;
            max = Math.max(max, t);
            num /= 10;
        }
        return max;
    }

}

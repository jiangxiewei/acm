package leetCode.weeklyContest.week358;

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class No2817限制条件下元素之间的最小绝对差 {

    public int minAbsoluteDifference(List<Integer> nums, int x) {
        return 第二次写的x处开始遍历(nums, x);
    }

    // 二分搜索平衡树的运用
    public int 第一次写的正向遍历(List<Integer> nums, int x) {
        // 初始化
        TreeMap<Integer, Integer> tm = new TreeMap<>(Integer::compareTo);
        for (int i = x; i < nums.size(); i++) {
            Integer exist = tm.get(nums.get(i));
            if (exist == null) {
                exist = 0;
            }
            tm.put(nums.get(i), exist + 1);
        }
        // 塞入极值，使返回不会为空
        tm.put(Integer.MAX_VALUE, 1);
        tm.put(Integer.MIN_VALUE, 1);
        // 边搜索边剔除不可行数据
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size() - x; i++) {
            Integer current = nums.get(i);
            ans = Math.min(ans, Math.min(Math.abs(tm.ceilingKey(current) - current), Math.abs(tm.floorKey(current) - current)));
            // 减少对应数字出现的统计
            Integer shouldBeDec = nums.get(i + x);
            Integer count = tm.get(shouldBeDec);
            if (count == 1) {
                tm.remove(shouldBeDec);
            } else {
                tm.put(shouldBeDec, count - 1);
            }
        }
        return ans;
    }

    // 基于上面优化，代码更简洁
    public int 第二次写的x处开始遍历(List<Integer> nums, int x) {
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(Integer.MAX_VALUE);
        ts.add(Integer.MIN_VALUE / 2);
        int ans = Integer.MAX_VALUE;
        for (int i = x; i < nums.size(); i++) {
            ts.add(nums.get(i - x));
            Integer iV = nums.get(i);
            ans = Math.min(ans, Math.min(ts.ceiling(iV) - iV, iV - ts.floor(iV)));
        }
        return ans;
    }

}

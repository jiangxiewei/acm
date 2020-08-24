package leetCode.repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 1497. 检查数组对是否可以被 k 整除
 * <p>
 * 给你一个整数数组 arr 和一个整数 k ，其中数组长度是偶数，值为 n 。
 * <p>
 * 现在需要把数组恰好分成 n / 2 对，以使每对数字的和都能够被 k 整除。
 * <p>
 * 如果存在这样的分法，请返回 True ；否则，返回 False 。
 *
 * @author jxw
 * @date 2020/8/24
 */
public class No1497CheckIfArrayPairsAreDivisibleByK {

    public static void main(String[] args) {
        No1497CheckIfArrayPairsAreDivisibleByK no = new No1497CheckIfArrayPairsAreDivisibleByK();
        //true
        System.out.println(no.canArrange(new int[]{1, 2, 3, 4, 5, 10, 6, 7, 8, 9}, 5));
        //true
        System.out.println(no.canArrange(new int[]{1, 2, 3, 4, 5, 6}, 7));
        //true
        System.out.println(no.canArrange(new int[]{5, 3, 10, 1, -7, 0, 33, -1, 10, 8, -3, 0, -10, 47, -9,
                -6, 38, 8, 5, 38, -4, 4, -5, -8, -4, 0, -8, 5, 7, 3, -3, 0, 6, 8, 47, 39, 35, 39, 4, 9}, 43));
        //true
        System.out.println(no.canArrange(new int[]{-1, 1, -2, 2, -3, 3, -4, 4}, 3));
    }

    /**
     * 重点1: 对于任何 (a+b)%k == 0 则 ((a%k) + (b%k))%k == 0
     * 重点2: a%k范围为 [1-k,k-1] 则可通过 (a%k+k)%k的方式得到[0,k-1]的整数并且带入重点1仍有效
     */
    public boolean canArrange(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        //建立哈希表索引
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] % k + k) % k;
            Integer count = map.computeIfAbsent(arr[i], integer -> 0);
            map.put(arr[i], count + 1);
        }
        //对所有key找(k,k-key)整数对,(0找0,此处通过-key方式找.)
        for (Integer key : map.keySet()) {
            int count = map.get(key);
            while (count > 0) {
                if (checkAndSet(map, -key) || checkAndSet(map, k - key)) {
                    count = map.get(key) - 1;
                    map.put(key, count);
                } else {
                    return false;
                }
            }
            //key自销,发现销过头,说明key数量为奇数,不足以自销.
            if (count < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAndSet(Map<Integer, Integer> map, int check) {
        Integer count = map.get(check);
        if (count == null || count == 0) {
            return false;
        } else {
            map.put(check, count - 1);
            return true;
        }
    }

}

package leetCode.repository;

import java.util.*;

public class No1 {

    public int[] twoSum(int[] nums, int target) {
        int[] targetArray = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        Set<Integer> multiple = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i])!=null){
                multiple.add(nums[i]);
            } else {
                map.put(nums[i], i);
            }
        }
        Integer x = null;
        for (int i = 0; i < nums.length; i++) {
            x = map.get(target - nums[i]);
            if (x==null){
                continue;
            }
            if (!x.equals(i)){
                targetArray[0] = i;
                targetArray[1] = x;
                return targetArray;
            }
        }
        return targetArray;
    }

    public static void main(String[] args) {
        No1 no = new No1();
        System.out.println(Arrays.toString(no.twoSum(new int[]{3,2,4}, 6)));
    }

}

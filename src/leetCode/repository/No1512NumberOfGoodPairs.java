package leetCode.repository;

/**
 * information
 *
 * @author jxw
 * @date 2020/7/12
 */
public class No1512NumberOfGoodPairs {

    public static void main(String[] args) {
        No1512NumberOfGoodPairs no = new No1512NumberOfGoodPairs();
        System.out.println(no.numIdenticalPairs(new int[]{1,2,3,1,1,3}));
        System.out.println(no.numIdenticalPairs(new int[]{1,1,1,1}));
    }

    public int numIdenticalPairs(int[] nums) {
        int[] map = new int[101];
        for (int i = 0; i < nums.length; i++) {
            map[nums[i]]++;
        }
        int result = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] > 1) {
                result += map[i] * (map[i] - 1) / 2;
            }
        }
        return result;
    }

}

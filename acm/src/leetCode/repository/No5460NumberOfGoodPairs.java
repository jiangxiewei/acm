package leetCode.repository;

/**
 * information
 *
 * @author jxw
 * @date 2020/7/12
 */
public class No5460NumberOfGoodPairs {

    public static void main(String[] args) {
        No5460NumberOfGoodPairs no = new No5460NumberOfGoodPairs();
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

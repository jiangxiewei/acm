package leetCode.repository;

public class No3289 {

    public int[] getSneakyNumbers(int[] nums) {
        int[] count = new int[101];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        int[] result = new int[2];
        int p = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 1) {
                result[p++] = i;
            }
        }
        return result;
    }

}

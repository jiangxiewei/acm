package leetCode.repository;

public class No3270 {


    public int generateKey(int num1, int num2, int num3) {
        int[] nums = new int[]{num1, num2, num3};
        int result = 0;
        for (int i = 4; i > 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int num : nums) {
                int n = (int) (num / Math.pow(10, i - 1) % 10);
                min = Math.min(n, min);
            }
            result += (int) Math.pow(10, i - 1) * min;
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(new No3270().generateKey(1, 10, 1000));
        System.out.println(new No3270().generateKey(987, 879, 798));
        System.out.println(new No3270().generateKey(1140, 1851, 2057));
    }

}


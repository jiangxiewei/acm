package leetCode.repository;

/**
 * @author jiang
 * @date 2023/6/26
 */
public class No11盛最多水的容器 {

    public static void main(String[] args) {
        System.out.println(new No11盛最多水的容器().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(new No11盛最多水的容器().maxArea(new int[]{1,1}));
    }

    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return max;
    }

}

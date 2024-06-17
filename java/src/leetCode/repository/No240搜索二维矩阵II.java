package leetCode.repository;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 *
 * @author jiangxiewei
 * @since 2022/3/29
 */
public class No240搜索二维矩阵II {

    /**
     * 从图左下开始往右上走,
     * 若 m[x][y] > target 则 m[x+n][y] 一定都 >target  (n为x + n <= 边界的任意值) 所以可从下网上遍历
     * 若 m[x][y] < target 则说明 m[x][y-n] 一定都 < target (n为 y-n >=0 边界的任意值) 所以可从左往右
     *
     * @param matrix 矩阵
     * @param target 目标值
     * @return 结果
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int n = matrix[0].length;
        int x = matrix.length - 1;
        int y = 0;
        while (x >= 0 && y < n) {
            if (matrix[x][y] > target) {
                x--;
            } else if (matrix[x][y] < target) {
                y++;
            } else {
                return true;
            }
        }
        return false;
    }

}

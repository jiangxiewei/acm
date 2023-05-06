package leetCode.repository;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jiangxiewei
 * @since 2022/4/8
 */
public class No54螺旋矩阵 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new LinkedList<>();
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] vis = new boolean[matrix.length][matrix[0].length];
        int x = 0, y = 0, dir = 0, nextX = 0, nextY = 0;
        endWhile:
        while (true) {
            vis[x][y] = true;
            result.add(matrix[x][y]);
            //获取下一个点
            for (int i = 0; i < direction.length; i++) {
                nextX = x + direction[(dir + i) % 4][0];
                nextY = y + direction[(dir + i) % 4][1];
                if (nextX >= 0 && nextX < matrix.length && nextY >= 0 && nextY < matrix[0].length && !vis[nextX][nextY]) {
                    dir = (dir + i) % 4;
                    break;
                } else if (i == direction.length - 1) {
                    break endWhile;
                }
            }
            x = nextX;
            y = nextY;
        }
        return result;
    }

}

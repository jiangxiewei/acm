package leetCode.repository;

/**
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * <p>
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
 * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 *
 * @author jiang
 * @date 2020/5/9
 */
public class No695MaxAreaOfIsland {
    public static void main(String[] args) {
        No695MaxAreaOfIsland island = new No695MaxAreaOfIsland();
        // 0
        System.out.println(island.maxAreaOfIsland(new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}}
        ));
    }

    public int maxAreaOfIsland(int[][] grid) {
        return firstDfsWay(grid);
    }

    /**
     * 第一种搜索式
     * @param grid 图
     * @return result
     */
    public int firstDfsWay(int[][] grid) {
        //生成 vis 数组. 用于判断是否为已检查过的岛屿.
        boolean[][] vis = new boolean[grid.length][];
        for (int i = 0; i < vis.length; i++) {
            vis[i] = new boolean[grid[i].length];
        }
        //开始对每个点进行岛屿大小探查
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!vis[i][j]) {
                    max = Math.max(dfs(grid, i, j, vis), max);
                }
            }
        }
        return max;
    }

    public int dfs(int[][] grid, int x, int y, boolean[][] vis) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length
                || vis[x][y]) {
            return 0;
        }
        if (grid[x][y] == 1) {
            vis[x][y] = true;
            int c = 1;
            c += dfs(grid, x + 1, y, vis);
            c += dfs(grid, x - 1, y, vis);
            c += dfs(grid, x, y + 1, vis);
            c += dfs(grid, x, y - 1, vis);
            return c;
        }
        return 0;
    }

}

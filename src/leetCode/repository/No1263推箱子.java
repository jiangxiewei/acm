package leetCode.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

/**
 * 未完待续
 * 还没AC
 * @author jiang
 * @date 2023/5/8
 */
public class No1263推箱子 {

    public static void main(String[] args) {
        System.out.println(new No1263推箱子().minPushBox(new char[][]{
                {'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '#', '#', '#', '#'},
                {'#', '.', '.', 'B', '.', '#'},
                {'#', '.', '#', '#', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}}) == 3);
        System.out.println(new No1263推箱子().minPushBox(new char[][]{{'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '#', '#', '#', '#'},
                {'#', '.', '.', 'B', '.', '#'},
                {'#', '#', '#', '#', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}}
        ) == -1);
        System.out.println(new No1263推箱子().minPushBox(new char[][]{{'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '.', '.', '#', '#'},
                {'#', '.', '#', 'B', '.', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}}
        ) == 5);
    }

    public int minPushBox(char[][] grid) {
        return new BFS().apply(grid);
    }

    class BFS implements Function<char[][], Integer> {

        private char[][] grid;

        @Override
        public Integer apply(char[][] chars) {
            this.grid = chars;

            int[] boxPos = findBoxPos();
            if (boxPos == null) {
                throw new RuntimeException("there is no box in map");
            }

            Queue<Pos> bfsQue = new LinkedBlockingQueue<>();
            bfsQue.offer(new Pos(boxPos[0], boxPos[1], 0));
            while (!bfsQue.isEmpty()) {
                var pos = bfsQue.poll();
                if (grid[pos.x][pos.y] == 'T') {
                    return pos.step;
                }
                List<Pos> posCanGo = findPosCanGo(pos);
                for (Pos canGo : posCanGo) {
                    bfsQue.offer(canGo);
                }
            }
            return -1;
        }


        private List<Pos> findPosCanGo(Pos step) {
            var nextPos = new LinkedList<Pos>();
            int[][] nextAcc = {{-1, 0, 1, 0}, {0, -1, 0, 1}};
            for (int[] twoDir : nextAcc) {
                Pos a = new Pos(step.x + twoDir[0], step.y + twoDir[1], step.step + 1);
                Pos b = new Pos(step.x + twoDir[2], step.y + twoDir[3], step.step + 1);
                if (canPass(a) && canPass(b)) {
                    nextPos.offer(a);
                    nextPos.offer(b);
                }
            }
            return nextPos;
        }

        private boolean canPass(Pos pos) {
            var t = grid[pos.x][pos.y];
            return (t == '.' || t == 'S' || t == 'T' || t == 'B');
        }

        private int[] findBoxPos() {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 'B') {
                        return new int[]{i, j};
                    }
                }
            }
            return null;
        }

        private int[] findPersonPos() {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 'S') {
                        return new int[]{i, j};
                    }
                }
            }
            return null;
        }

        class Pos {

            public Pos(int x, int y, int step) {
                this.x = x;
                this.y = y;
                this.step = step;
            }

            public int x;
            public int y;
            public int step;
        }

    }

}
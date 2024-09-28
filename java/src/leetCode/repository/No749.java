package leetCode.repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class No749 {

    UnionFindSets unionSets;
    Set<Integer> virGroupSets = new HashSet<>();
    int[][] isInfected;
    Set<Integer> blockedSets = new HashSet<>();

    public int containVirus(int[][] isInfected) {
        int result = 0;
        this.isInfected = isInfected;
        this.unionSets = new UnionFindSets(isInfected.length * isInfected[0].length);
        initUnionSets();
        refreshVirGroup();
//        printAll(result);
        while (!isAllBloecked() && !isAllAffected()) {
            int mostInfluence = findMostInfluence();
            result += calculateBlock(mostInfluence, new boolean[isInfected.length][isInfected[0].length]);
            this.blockedSets.add(mostInfluence);
            spreadVirs();
            refreshVirGroup();
//            printAll(result);
        }
        return result;
    }


    public int calculateBlock(int blockId, boolean[][] vis) {
        int[] xy = getOriginXY(blockId);
        int x = xy[0];
        int y = xy[1];
        if (!borderCheck(x, y)) {
            return 0;
        }
        if (vis[x][y]) {
            return 0;
        }
        if (isInfected[x][y] == 0) {
            return 1;
        }
        vis[x][y] = true;
        int result = 0;
        int[] dir = new int[]{0, -1, 1};
        for (int i : dir) {
            for (int j : dir) {
                if (i != 0 && j != 0) continue;
                if (!borderCheck(x+i, y+j)) continue;
                int nbid = getBlockId(x + i, y + j);
                if (isInfected[x + i][y + j] == 1 && !unionSets.isSame(blockId, nbid)) {
                    continue;
                }
                result += calculateBlock(nbid, vis);
            }
        }
        return result;
    }

    public void spreadVirs() {
        boolean[][] vis = new boolean[isInfected.length][isInfected[0].length];
        for (Integer g : virGroupSets) {
            if (blockedSets.contains(g)) continue;
            int[] xy = getOriginXY(g);
            int x = xy[0];
            int y = xy[1];
            spread(x, y, vis);
        }
    }

    public void spread(int x, int y, boolean[][] vis) {
        if (!borderCheck(x, y)) {
            return;
        }
        if (vis[x][y]){
            return;
        }
        vis[x][y] = true;
        int[] dir = new int[]{0, -1, 1};
        for (int i : dir) {
            for (int j : dir) {
                if ((i == 0 && j == 0) || (i != 0 && j != 0)) continue;
                if (!borderCheck(x + i, y + j)) continue;
                if (isInfected[x + i][y + j] == 1 && unionSets.isSame(getBlockId(x,y), getBlockId(x+i,y+j))) {
                    spread(x + i, y + j, vis);
                } else {
                    vis[x + i][y + j] = true;
                    isInfected[x + i][y + j] = 1;
                    unionSets.union(getBlockId(x, y), getBlockId(x + i, y + j));
                    checkMerge(x+i, y+j);
                }
            }
        }

    }

    public void checkMerge(int x, int y) {
        int[] dir = new int[]{0, -1, 1};
        int blockId = getBlockId(x, y);
        for (int i : dir) {
            for (int j : dir) {
                if ((i == 0 && j == 0) || (i != 0 && j != 0)) continue;
                if (!borderCheck(x + i, y + j)) continue;
                if (isInfected[x + i][y + j] == 1) {
                    int nb = getBlockId(x + i, y + j);
                    unionSets.union(blockId, nb);
                }
            }
        }
    }

    public int findMostInfluence() {
        int max = -1, maxg = -1;
        for (Integer g : virGroupSets) {
            int[] originXY = getOriginXY(g);
            int x = originXY[0];
            int y = originXY[1];
            int influenceNum = checkInfluenceNum(x, y, new boolean[isInfected.length][isInfected[0].length]);
            if (max < influenceNum) {
                max = influenceNum;
                maxg = g;
            }
        }
        return maxg;
    }

    public boolean borderCheck(int x, int y) {
        if (x < 0 || x >= isInfected.length || y < 0 || y >= isInfected[x].length) {
            return false;
        }
        return !blockedSets.contains(unionSets.find(getBlockId(x, y)));
    }

    public int checkInfluenceNum(int x, int y, boolean[][] vis) {
        if (!borderCheck(x, y)) {
            return 0;
        }
        if (vis[x][y]){
            return 0;
        }
        if (this.isInfected[x][y] == 0) {
            vis[x][y] = true;
            return 1;
        }
        vis[x][y] = true;
        int result = 0;
        int[] dir = new int[]{0, -1, 1};
        for (int i : dir) {
            for (int j : dir) {
                if ((i == 0 && j == 0) || (i != 0 && j != 0)) continue;
                result += checkInfluenceNum(x + i, y + j, vis);
            }
        }
        return result;
    }

    public void initUnionSets() {
        boolean [][] vis = new boolean[isInfected.length][isInfected[0].length];
        for (int i = 0; i < this.isInfected.length; i++) {
            for (int j = 0; j < this.isInfected[i].length; j++) {
                int blockId = getBlockId(i, j);
                if (this.isInfected[i][j] == 1 && !vis[i][j]) {
                    spreadAndSetUnionSets(i, j, blockId, vis);
                }
            }
        }
    }

    public boolean isAllBloecked() {
        return virGroupSets.isEmpty();
    }

    public boolean isAllAffected() {
        for (int[] ints : this.isInfected) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    return false;
                }
            }
        }
        return true;
    }


    public void spreadAndSetUnionSets(int x, int y, int parentBlockId, boolean[][] vis) {
        if (x < 0 || x >= isInfected.length || y < 0 || y >= isInfected[x].length) {
            return;
        }
        if (isInfected[x][y] == 0 || vis[x][y]) {
            return;
        }
        vis[x][y] = true;
        int blockId = getBlockId(x, y);
        unionSets.union(blockId, parentBlockId);
        int[] dir = new int[]{0, -1, 1};
        for (int i : dir) {
            for (int j : dir) {
                if (i != 0 && j != 0) continue;
                spreadAndSetUnionSets(x + i, y + j, parentBlockId, vis);
            }
        }
    }

    public void refreshVirGroup() {
        virGroupSets.clear();
        for (int i = 0; i < this.isInfected.length; i++) {
            for (int j = 0; j < this.isInfected[i].length; j++) {
                int blockId = getBlockId(i, j);
                int root = unionSets.find(blockId);
                if (this.isInfected[i][j] == 1 && !blockedSets.contains(root)) {
                    virGroupSets.add(root);
                }
            }
        }
    }

    public int getBlockId(int x,int y) {
        return x * this.isInfected[0].length + y;
    }

    public int[] getOriginXY(int blockId) {
        int x = blockId / this.isInfected[0].length;
        int y = blockId % this.isInfected[0].length;
        return new int[]{x, y};
    }

    public static class UnionFindSets {

        int[] sets ;

        public UnionFindSets(int size) {
            sets = new int[size];
            Arrays.setAll(sets, i -> i);
        }

        public int find(int i) {
            if (sets[i] == i) return i;
            return sets[i] = find(sets[i]);
        }

        public int union(int i, int j) {
            int a = find(i);
            int b = find(j);
            if (a < b) {
                return sets[b] = a;
            } else {
                return sets[a] = b;
            }
        }

        public boolean isSame(int i, int j) {
            return find(i) == find(j);
        }

    }


    public void printAll(int result) {
        System.out.println("=========================================");
        System.out.println("---------print union group-----------");
        for (int i = 0; i < this.isInfected.length; i++) {
            for (int j = 0; j < this.isInfected[i].length; j++) {
                System.out.print("\t\t" + unionSets.find(getBlockId(i, j)));
            }
            System.out.println();
        }
        System.out.println("---------print map group------------");
        for (int[] ints : this.isInfected) {
            for (int anInt : ints) {
                System.out.print("\t\t" + anInt);
            }
            System.out.println();
        }
        System.out.println("------- block_ids ----------");
        System.out.println(blockedSets.toString());
        System.out.println("------- vir group ----------");
        System.out.println(virGroupSets.toString());
        System.out.println("-------- result count --------");
        System.out.println(result);
    }

    public static void main(String[] args) {
        System.out.println(new No749().containVirus(new int[][]{{0, 1, 0, 0, 0, 0, 0, 1}, {0, 1, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}}) == 10);
        System.out.println(new No749().containVirus(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}) == 4);
        System.out.println(new No749().containVirus(new int[][]{{1, 1, 1, 0, 0, 0, 0, 0, 0}, {1, 0, 1, 0, 1, 1, 1, 1, 1}, {1, 1, 1, 0, 0, 0, 0, 0, 0}}) == 13);
        System.out.println(new No749().containVirus(new int[][]{{0, 1, 0, 0, 0, 0, 0, 1}, {0, 1, 0, 1, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 1}}) == 16);
        System.out.println(new No749().containVirus(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 1, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}})==56);
        System.out.println(new No749().containVirus(new int[][]{{0,1,0,1,1,1,1,1,1,0},{0,0,0,1,0,0,0,0,0,0},{0,0,1,1,1,0,0,0,1,0},{0,0,0,1,1,0,0,1,1,0},{0,1,0,0,1,0,1,1,0,1},{0,0,0,1,0,1,0,1,1,1},{0,1,0,0,1,0,0,1,1,0},{0,1,0,1,0,0,0,1,1,0},{0,1,1,0,0,1,1,0,0,1},{1,0,1,1,0,1,0,1,0,1}}) == 38);
    }
}



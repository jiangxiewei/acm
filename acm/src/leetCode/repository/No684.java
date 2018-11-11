package leetCode.repository;

import templateRepository.UnionFindSets;

import java.util.Arrays;


/**
 * information
 *
 * @author jxwww
 * @date 2018/8/26
 */
public class No684 {

    public int[] findRedundantConnection(int[][] edges) {
        int capacity = 0;
        for (int i = 0; i < edges.length; i++) {
            capacity = Math.max(capacity, edges[i][0] > edges[i][1] ? edges[i][0] : edges[i][1]);
        }
        capacity += 1;
        UnionFindSets sets = new UnionFindSets(capacity);
        int targetPos = 0;
        for (int i = 0; i < edges.length; i++) {
            if (sets.union(edges[i][0], edges[i][1])) {
            } else {
                targetPos = i;
            }
        }
        return edges[targetPos];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new No684().findRedundantConnection(new int[][]{{2, 1}, {3, 1}, {4, 2}, {1, 4}})));
    }


}

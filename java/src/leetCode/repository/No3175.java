package leetCode.repository;

public class No3175 {

    public int findWinningPlayer(int[] skills, int k) {
        int maxI = 0;
        int win = -1;
        for (int i = 0; i < skills.length && win < k; i++) {
            if (skills[i] > skills[maxI]) { // 大于
                maxI = i;
                win = 0;
            }
            win++;
        }
        return maxI;
    }

    public static void main(String[] args) {
        No3175 no3175 = new No3175();
        System.out.println(no3175.findWinningPlayer(new int[]{4, 2, 6, 3, 9}, 2));
        System.out.println(no3175.findWinningPlayer(new int[]{2, 5, 4}, 3));
    }

}

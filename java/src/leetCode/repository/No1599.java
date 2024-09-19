package leetCode.repository;

public class No1599 {

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {

        int max = 0;
        int maxI = -1;
        int earn = 0;
        int people = 0;
        for (int i = 0; i < 5000000; i++) {
            if (i < customers.length) {
                people += customers[i];
            } else if (people == 0) {
                break;
            }
            int boardMan = Math.min(4, people);
            people -= boardMan;
            earn += boardingCost * boardMan - runningCost;
//            System.out.println("boardMan:" + boardMan + " earn:" + earn + " people:" + people);
            if (earn > max) {
                max = earn;
                maxI = i;
            }
        }
        return maxI >= 0 ? maxI + 1 : -1;

    }

    public static void main(String[] args) {
        No1599 fuck = new No1599();
        System.out.println(fuck.minOperationsMaxProfit(new int[]{8, 3}, 5, 6));
        System.out.println(fuck.minOperationsMaxProfit(new int[]{10, 9, 6}, 6, 4));
        System.out.println(fuck.minOperationsMaxProfit(new int[]{3, 4, 0, 5, 1}, 1, 92));
    }

}

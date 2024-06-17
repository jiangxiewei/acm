package leetCode.repository;

import java.util.Arrays;

public class No475供暖器 {

    public static void main(String[] args) {
        int radius = new No475供暖器().findRadius(new int[]{1, 2, 3}, new int[]{2});
        System.out.println(radius);
        System.out.println(radius == 1);
        radius = new No475供暖器().findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4});
        System.out.println(radius);
        System.out.println(radius == 1);
        radius = new No475供暖器().findRadius(new int[]{1, 5}, new int[]{2});
        System.out.println(radius);
        System.out.println(radius == 3);
        radius = new No475供暖器().findRadius(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        System.out.println(radius);
        System.out.println(radius == 0);

        radius = new No475供暖器().findRadius(new int[]{1, 5}, new int[]{10});
        System.out.println(radius);
        System.out.println(radius == 9);

        radius = new No475供暖器().findRadius(new int[]{282475249, 622650073, 984943658, 144108930, 470211272, 101027544, 457850878, 458777923}, new int[]{823564440, 115438165, 784484492, 74243042, 114807987, 137522503, 441282327, 16531729, 823378840, 143542612});
        System.out.println(radius);
        System.out.println(radius == 161834419);

    }

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int hpos = 0;
        long needMinRadius = 0;
        for (int i = 0; i < houses.length; i++) {
            while (hpos < heaters.length - 1 && heaters[hpos] <= houses[i] && heaters[hpos + 1] <= houses[i]) {
                hpos++;
            }
            long toLeft = Math.abs(houses[i] - heaters[hpos]);
            long toRight = Integer.MAX_VALUE;
            if (hpos + 1 < heaters.length) {
                toRight = Math.abs(heaters[hpos + 1] - houses[i]);
            }
            needMinRadius = Math.max(needMinRadius, Math.min(toLeft, toRight));
        }
        return (int) needMinRadius;
    }

}

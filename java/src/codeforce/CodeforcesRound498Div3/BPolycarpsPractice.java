package codeforce.CodeforcesRound498Div3;

import java.util.*;

public class BPolycarpsPractice {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n, k, x;
        n = scan.nextInt();
        k = scan.nextInt();
        Integer[] arr = new Integer[k];
        Map.Entry<Integer, Integer>[] pairs = new Map.Entry[n];
        for (int i = 0; i < n; i++) {
            x = scan.nextInt();
            pairs[i] = new AbstractMap.SimpleEntry<>(x, i);
        }
        Arrays.sort(pairs, (o1, o2) -> o1.getKey() > o2.getKey() ? -1 : o1.getKey().equals(o2.getKey()) ? 0 : 1);
        int max = 0;
        for (int i = 0; i < k; i++) {
            max += pairs[i].getKey();
            arr[i] = pairs[i].getValue();
        }
        System.out.println(max);
        if (k==1){
            System.out.println(n);
            return;
        }
        Arrays.sort(arr, (o1, o2) -> o1 < o2 ? -1 : o1.equals(o2) ? 0 : 1);
//        System.out.println("arr:" + Arrays.toString(arr));
        int former = 0;
        for (int i = 1; i < arr.length; i++) {
            System.out.print(arr[i] - former + " ");
            former = arr[i];
        }
        System.out.println(n-arr[k-1]);
    }
}

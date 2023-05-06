package codeforce.CodeforcesRound498Div3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DTwoStringsSwaps {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String sa = scan.next(), sb = scan.next();
        char[] a = sa.toCharArray();
        char[] b = sb.toCharArray();
//        System.out.println(sa + "," + sb);
        int ans = 0;
        boolean oddFlag = (n & 1) == 1;
        Map<Character, Integer> map = new HashMap<>(8);
        for (int l = (n - 1) / 2, r = n / 2; l >= 0 && r < n; l--, r++) {
            putIntoMap(a[l], map);
            putIntoMap(a[r], map);
            putIntoMap(b[l], map);
            putIntoMap(b[r], map);
//            System.out.println(String.format("l:%d,r:%d,map.size=%d, al:%c,ar:%c,bl:%c,br:%c", l, r, map.size(), a[l], a[r], b[l], b[r]));
            if (map.size() == 4) {
                ans += 2;
            } else if (map.size() == 3) {
                if (a[l] == a[r]) {
                    ans += 2;
                } else {
                    ans++;
                }
            } else if (map.size() == 2) {
                if (oddFlag && l == (n - 1) / 2) {
                    ans++;
                } else {
                    for (Character c : map.keySet()) {
                        if (map.get(c) == 1) {
                            ans++;
                            break;
                        }
                    }
                }
            }
            map.clear();
        }
        System.out.println(ans);
    }

    public static void putIntoMap(Character x, Map<Character, Integer> map) {
        Integer t = map.get(x);
        if (t == null) {
            map.put(x, 1);
        } else {
            map.put(x, t + 1);
        }
    }

}

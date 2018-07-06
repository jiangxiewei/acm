package codeforce.EducationalCodeforcesRound42;

import java.util.*;

public class BStudentsInRailwayCarriage {

    public static void main(String[] args) {
        int n, a, b;
        String str;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        a = scan.nextInt();
        b = scan.nextInt();
        scan.nextLine();
        str = scan.nextLine();
//        System.out.println(n+","+a+","+b+","+str);
        List<Integer> list = new ArrayList<>();
        int formerAster = -1;
        for (int i = 0; i < n; i++) {
            char set = str.charAt(i);
            if (set == '*') {
                if (i > formerAster + 1) {
                    list.add(i - formerAster - 1);
                }
                formerAster = i;
            }
        }
        if (n > formerAster + 1) {
            list.add(n - formerAster - 1);
        }
        int count = 0;
        list.sort((o1, o2) -> (o2.compareTo(o1)));

        for (int t : list) {
            if (a < b) {
                a = a ^ b;
                b = a ^ b;
                a = a ^ b;
            }
//            System.out.println(String.format("a:%d,b:%d,count:%d,t:%d", a, b, count, t));
            if (t == 1) {
                if (a>0) {
                    a--;
                    count++;
                }
                continue;
            }
            if ((t / 2 + (t & 1)) > a) {
                count += a;
                a = 0;
            } else {
                count += (t / 2 + (t & 1));
                a -= (t / 2 + (t & 1));
            }
            if ((t / 2) > b) {
                count += b;
                b = 0;
            } else {
                count += (t / 2);
                b -= (t / 2);
            }
        }
        System.out.println(count);
    }

}

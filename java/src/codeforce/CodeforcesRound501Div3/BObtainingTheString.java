package codeforce.CodeforcesRound501Div3;

import java.util.Scanner;

/**
 * swap bi 跟 b(i+1)
 * 使两个字符串相等
 */
public class BObtainingTheString {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int[] am = new int[26], bm = new int[26];
        int n = scan.nextInt();
        scan.nextLine();
        char[] a = scan.nextLine().toCharArray(), b = scan.nextLine().toCharArray();
        for (int i = 0; i < n; i++) {
            am[a[i] - 'a']++;
            bm[b[i] - 'a']++;
        }
        //先判断两字符串是否字母数量相同
        for (int i = 0; i < am.length; i++) {
            if (am[i] != bm[i]) {
                System.out.println("-1");
                return;
            }
        }
        int[] target = new int[n * n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            //若两个不相同,开始查找最接近的一个相同的,往前移到当前位置
            if (b[i] != a[i]) {
                int t = 0;
                for (int j = i + 1; j < n; j++) {
                    if (a[j] == b[i]) {
                        t = j;
                        break;
                    }
                }
                for (int j = t; j > i; j--) {
                    a[j] = a[j - 1];
                    target[count++] = j;
                }
                a[i] = b[i];
//                System.out.println(Arrays.toString(a));
            }
        }
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            System.out.print(target[i] + " ");
        }
    }

}

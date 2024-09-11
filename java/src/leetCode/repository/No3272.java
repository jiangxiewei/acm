package leetCode.repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * 枚举目标回文串：  <br/>
 *      因为目标是回文串，所以只需要1~5位数字就能枚举所有回文串，然后再筛选能被k整除的.
 * 排列组合计算回文串的所有构成因子: <br/>
 *      1.拿到的回文串防止重复计算，对串排序得到构成因子进行去重。 (比如 123 和  213 和 321 排序后都是 123 , 他们构成因子一样，只需计算一次) <br/>
 *      2.排列组合计算公式  S = (长度n - C(0)) * (n-1)! / ( C(0)!*C(1)!*C(2)!*C(3)!*C(4)!*C(5)!*C(6)!*C(7)!*C(8)!*C(9)! ) <br/>
 *      C(x) = 数字x在回文串中出现的次数
 *
 */
public class No3272 {


    private static final int[] jiecheng = new int[]{1,1,2,6,24,120,720,5040,40320,362880,3628800};

    public long countGoodIntegers(int n, int k) {
        int nn = (n + 1) / 2;
        int start = ((int) Math.pow(10, nn - 1));
        int end = ((int) Math.pow(10, nn));
        int result = 0;
        Set<String> visited = new HashSet<>();
        for (int i = start; i < end; i++) {
            long ox = originX(i, n);
            if (ox % k != 0) {
                continue;
            }
            String hash = hash(ox);
            if (visited.contains(hash)) continue;
            visited.add(hash);
            int[] count = count(ox);
            int r = (n-count[0])*jiecheng[n-1];
            int divid = 1;
            for (int j = 0; j < count.length; j++) {
                if (count[j] > 1) {
                    divid *= jiecheng[count[j]];
                }
            }
//            System.out.println("calc:" + i + ",ox:" + ox + ",r:" + r + ",divid:" + divid);
            result += r / divid;
        }
        return result;
    }

    private static long originX(int x, int n) {
        if (n==1) return x;
        char[] arr = String.valueOf(x).toCharArray();
        //reserve arr
        char t;
        for (int i = 0; i < arr.length/2; i++) {
            t = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = t;
        }
        return Long.parseLong(x + String.copyValueOf(arr, n & 1, arr.length - (n & 1)));
    }

    private String hash(long x) {
        char[] a = String.valueOf(x).toCharArray();
        Arrays.sort(a);
        return String.valueOf(a);
    }

    private int[] count(long x) {
        int[] counter = new int[10];
        while (x > 0) {
            counter[(int) (x % 10)]++;
            x /= 10;
        }
        return counter;
    }

    public static void main(String[] args) {
        System.out.println(new No3272().countGoodIntegers(2, 1)==9);
        System.out.println(new No3272().countGoodIntegers(3, 5)==27);
        System.out.println(new No3272().countGoodIntegers(1,4)==2);
        System.out.println(new No3272().countGoodIntegers(5, 6) == 2468);
        System.out.println(new No3272().countGoodIntegers(7, 2) == 509248);
    }

}

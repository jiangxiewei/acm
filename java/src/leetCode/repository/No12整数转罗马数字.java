package leetCode.repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiang
 * @date 2023/6/26
 */


public class No12整数转罗马数字 {

    public static void main(String[] args) {
        System.out.println(new No12整数转罗马数字().intToRoman(58).equals("LVIII"));
        System.out.println(new No12整数转罗马数字().intToRoman(1994).equals("MCMXCIV"));
    }

    public String intToRoman(int num) {
        return new Solution1().intToRoman(num);
    }

    class Solution1 implements IntToRoman {

        private static int[] table = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        private static String[] roma = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

        @Override
        public String intToRoman(int num) {
            StringBuilder sb = new StringBuilder();
            int pos = table.length - 1;
            while (num > 0 && pos >= 0) {
                for (int i = 0; i < num / table[pos] ; i++) {
                    sb.append(roma[pos]);
                }
                num %= table[pos];
                pos--;
            }
            return sb.toString();
        }
    }

    interface IntToRoman {
        String intToRoman(int num);
    }

}
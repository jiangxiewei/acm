package leetCode.repository;

public class No13罗马数字转整数 {

    public static void main(String[] args) {
        System.out.println(new No13罗马数字转整数().romanToInt("III") == 3);
        System.out.println(new No13罗马数字转整数().romanToInt("IV") == 4);
        System.out.println(new No13罗马数字转整数().romanToInt("IX") == 9);
        System.out.println(new No13罗马数字转整数().romanToInt("MCMXCIV") == 1994);
    }

    public int romanToInt(String s) {
        return new Solution1().romanToInt(s);
    }

    static class Solution1 implements RomanToInt {

        private static int[] table = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        private static String[] roma = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

        @Override
        public int romanToInt(String s) {
            int sum = 0;
            while (s.length() > 0) {
                for (int i = roma.length - 1; i >= 0; i--) {
                    if (s.startsWith(roma[i])) {
                        s = s.substring(roma[i].length());
                        sum += table[i];
//                        System.out.printf("talbe[%d]=%d,s=\"%s\"\n", i, table[i], s);
                        break;
                    }
                }
            }
            return sum;
        }
    }


    interface RomanToInt {
        int romanToInt(String s);
    }

}

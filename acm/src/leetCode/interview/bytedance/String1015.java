package leetCode.interview.bytedance;

/**
 * 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * <p>
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * <p>
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author jiang
 * @date 2020/4/29
 */
public class String1015 {

    public static void main(String[] args) {
        String1015 str = new String1015();
        //6
        System.out.println(str.multiply("2", "3"));
        //56088
        System.out.println(str.multiply("123", "456"));
        //0
        System.out.println(str.multiply("0", "0"));
    }

    public String multiply(String num1, String num2) {
        return firstWay(num1, num2);
    }

    public static final char BASE_CH = '0';

    public String firstWay(String num1, String num2) {
        int[] base = new int[300];
        char[] ch1 = num1.toCharArray();
        char[] ch2 = num2.toCharArray();

        for (int i = ch1.length - 1; i >= 0; i--) {
            for (int j = ch2.length - 1; j >= 0; j--) {
                int mult = (ch1[i] - BASE_CH) * (ch2[j] - BASE_CH);
                int baseIndex = (ch1.length - 1 - i) + (ch2.length - 1 - j);
                base[baseIndex] += mult % 10;
                base[baseIndex + 1] += mult / 10 + base[baseIndex] / 10;
                base[baseIndex] %= 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean prefixZero = true;
        for (int i = base.length - 1; i >= 0; i--) {
            if (prefixZero) {
                prefixZero = base[i] == 0;
            }
            if (!prefixZero) {
                sb.append(base[i]);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

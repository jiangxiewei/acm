package leetCode.repository;

public class No28找出字符串中第一个匹配项的下标 {

    public static void main(String[] args) {

        System.out.println(new No28找出字符串中第一个匹配项的下标().strStr("sadbutsad", "sad"));

    }

    public int strStr(String haystack, String needle) {
        return solutionKMP(haystack, needle);
    }

    public int solutionKMP(String haystack, String needle) {
        // 预运算前缀数组，用于快速从后缀匹配转变为前缀匹配。
        int[] pre = new int[needle.length()];
        for (int j = 0, i = 1; i < needle.length(); i++) {
            while (j > 0 && needle.charAt(j) != needle.charAt(i)) {
                j = pre[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pre[i] = j;
        }
        // 算法本质是把needle和haystack拼接，不过后半部分不需要保存结果，所以可以不用数组存储。
        for (int j = 0, i = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = pre[j - 1];
            }
            if (needle.charAt(j) == haystack.charAt(i)) {
                j++;
            }
            if (j == needle.length()) {
                return i - needle.length() + 1;
            }
        }
        return -1;
    }

}

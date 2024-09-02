package leetCode.repository;

public class No3271 {

    public String stringHash(String s, int k) {

        var sb = new StringBuilder();
        int i = 0, sum = 0;
        for (char c : s.toCharArray()) {
            sum += c-'a';
            if (i++ >= k - 1) {
                sb.append((char) (sum % 26 + 'a'));
                sum = i = 0;
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(new No3271().stringHash("abcd", 2));
        System.out.println(new No3271().stringHash("mxz", 3));
    }

}

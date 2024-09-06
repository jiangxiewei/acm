package leetCode.repository;

public class No3274 {


    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        char[] a = coordinate1.toCharArray();
        char[] b = coordinate2.toCharArray();
        boolean is_a_black = ((a[0] - 'a') & 1) == (a[1] & 1);
        boolean is_b_black = ((b[0] - 'a') & 1) == (b[1] & 1);
        return is_a_black == is_b_black;
    }


    public static void main(String[] args) {
        System.out.println(new No3274().checkTwoChessboards("a1", "c3"));
        System.out.println(new No3274().checkTwoChessboards("a1", "h3"));
    }
}

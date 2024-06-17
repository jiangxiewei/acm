package leetCode.repository;

public class No9回文数 {


    public static void main(String[] args) {
        System.out.println(new No9回文数().isPalindrome(121));
        System.out.println(!new No9回文数().isPalindrome(-121));
        System.out.println(!new No9回文数().isPalindrome(10));
        System.out.println(!new No9回文数().isPalindrome(21120));
    }

    public boolean isPalindrome(int x) {

        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        } else if (x % 10 == 0) {
            return false;
        } else {
            // 快慢指针判断已经读到一半的位置，翻转低位部分的数字。
            int mod100x = x, reserved = 0;
            do {
                reserved = reserved * 10 + x % 10;
                x /= 10;
                mod100x /= 100;
            } while (mod100x > 0);
            System.out.println("x=" + x + ",reserved=" + reserved);
            return x == reserved || (x == reserved / 10);
        }

    }

}

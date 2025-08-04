/**
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 */
struct Solution;

impl Solution {
    // build a reversed number and compare to the original one.
    pub fn is_palindrome(x: i32) -> bool {
        if x < 0 {
            return false;
        }
        // create reverse copy
        let mut x_copy = x;
        let mut reverse = 0;
        while x_copy > 0 {
            reverse = reverse * 10 + x_copy % 10;
            x_copy /= 10;
        }
        reverse == x
    }
}

#[test]
fn test() {
    assert_eq!(Solution::is_palindrome(10), false);
    assert_eq!(Solution::is_palindrome(121), true);
    assert_eq!(Solution::is_palindrome(-121), false);
    assert_eq!(Solution::is_palindrome(1000000001), true);
}

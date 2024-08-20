struct Solution {}

impl Solution {
    pub fn divide(dividend: i32, divisor: i32) -> i32 {
        let is_positive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        let dividend2 = (dividend as i64).saturating_abs();
        let divisor2 = (divisor as i64).saturating_abs();
        let mut remain = 0;
        let mut result = 0_i64;
        for i in (0..32).rev() {
            let cur = dividend2 >> i & 1;
            remain = (remain << 1) + cur;
            result = result << 1;
            if remain >= divisor2 {
                remain -= divisor2;
                result = result + 1;
            }
        }
        if is_positive {
            if result > i32::MAX as i64 {
                i32::MAX
            } else {
                result as i32
            }
        } else {
            -result as i32
        }
    }
}

#[test]
fn test() {
    assert_eq!(dbg!(Solution::divide(i32::MIN, 1)), i32::MIN);
    assert_eq!(dbg!(Solution::divide(i32::MIN, -1)), i32::MAX);
    assert_eq!(dbg!(Solution::divide(10, 3)), 3);
    assert_eq!(dbg!(Solution::divide(7, -3)), -2);
}

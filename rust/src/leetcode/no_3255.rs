
struct Solution {}

impl Solution {
    pub fn results_array(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let mut new_vec = Vec::<i32>::with_capacity(nums.len() - k as usize + 1);
        for _ in 0..new_vec.capacity() {
            new_vec.push(-1);
        }
        let mut r = nums.len() - 1;
        for (i, &v) in nums.iter().enumerate().rev() {
            unsafe {
                if i + 1 < nums.len() && v + 1 != *nums.get_unchecked(i + 1) {
                    r = i;
                };
            }
            if r - i + 1 >= k as usize {
                new_vec[i] = nums[k as usize + i - 1];
            };
        }
        new_vec
    }
}

#[test]
fn test() {
    assert_eq!(dbg!(Solution::results_array(vec![1,3,4], 2)), vec![-1,4]);
    assert_eq!(dbg!(Solution::results_array(vec![1], 1)), vec![1]);
    assert_eq!(dbg!(Solution::results_array(vec![3,2,3,2,3,2], 2)), vec![-1,3,-1,3,-1]);
    assert_eq!(dbg!(Solution::results_array(vec![2, 2, 2, 2, 2], 4)), vec![-1, -1]);
}
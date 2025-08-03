struct Solution;
impl Solution {
    pub fn min_removal(nums: Vec<i32>, k: i32) -> i32 {
        let mut max_count = 0;
        let mut l = 0usize;
        let mut r = 0usize;
        let k = k as i64;
        let mut nums = nums.iter().map(|x| { *x as i64 }).collect::<Vec<_>>();
        nums.sort();
        // println!("{nums:?}");
        while r < nums.len() {
            if l == r || nums[l] * k >= nums[r] {
                // println!("(l:{l:?},r:{r:?}) go r");
                max_count = max_count.max(r - l + 1);
                r += 1;
            } else if nums[l] * k < nums[r] { // unbalanced
                // println!("(l:{l:?},r:{r:?}) go l");
                l += 1;
            }
        }
        (nums.len() - max_count) as i32
    }
}

#[test]
fn test() {
    assert_eq!(Solution::min_removal(vec![2, 1, 5], 2), 1);
}

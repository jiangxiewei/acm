struct Solution {}

impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut sorted: Vec<(i32, i32)> = nums.to_vec().iter().enumerate()
            .map(|x| (*x.1, x.0 as i32))
            .collect();
        drop(nums);
        sorted.sort();

        let (mut l, mut r) = (0, sorted.len() - 1);
        while l < r {
            let sum = sorted[l].0 + sorted[r].0;
            if sum > target {
                r -= 1;
            } else if sum < target {
                l += 1;
            } else {
                return vec![sorted[l].1, sorted[r].1];
            }
        }
        vec![0, 0]
    }
}

#[test]
fn test() {
    assert_eq!(dbg!(Solution::two_sum(vec![3, 2, 4], 6)), vec![1, 2]);
    assert_eq!(dbg!(Solution::two_sum(vec![2, 7, 11, 15], 9)), vec![0, 1]);
}

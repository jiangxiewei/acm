
struct Solution {}

impl Solution {
    pub fn next_greater_elements(nums: Vec<i32>) -> Vec<i32> {
        let mut stack: Vec<usize> = vec![];
        let mut vector: Vec<i32> = vec![-1; nums.len()];
        // push the first element
        stack.push(0);

        for i in 1..nums.len() * 2 {
            let current_value = nums[i % nums.len()];
            while !stack.is_empty() && current_value > nums[*stack.last().unwrap()] {
                let t = stack.pop().unwrap();
                vector[t] = current_value;
            };
            if i < nums.len() {
                stack.push(i);
            }
        }

        vector
    }
}

#[test]
fn test() {
    assert_eq!(Solution::next_greater_elements(vec![]), vec![]);
    assert_eq!(Solution::next_greater_elements(vec![1]), vec![-1]);
    assert_eq!(Solution::next_greater_elements(vec![1, 2, 1]), vec![2, -1, 2]);
    assert_eq!(Solution::next_greater_elements(vec![1, 2, 3, 4, 3]), vec![2, 3, 4, -1, 4]);
}

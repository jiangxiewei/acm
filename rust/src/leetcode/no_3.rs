use std::collections::HashMap;

/**
    Given a string s, find the length of the longest substring without duplicate characters.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/
struct Solution;

impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let mut c_cnt = HashMap::<char, usize>::new();

        let vec_c: Vec<char> = s.chars().collect();

        let mut max_length = 0;
        let mut last_index = 0;
        for (i, c) in vec_c.iter().enumerate() {
            if c_cnt.contains_key(c) {
                let new_l = c_cnt.get(c).unwrap().clone();
                for j in last_index..=new_l {
                    c_cnt.remove(vec_c.get(j).unwrap());
                }
                last_index = new_l + 1;
            } else {
                max_length = max_length.max(i - last_index + 1);
            }
            c_cnt.insert(*c, i);
        }
        max_length as i32
    }
}

#[test]
fn test() {
    assert_eq!(0, Solution::length_of_longest_substring("".to_string()));
    assert_eq!(1, Solution::length_of_longest_substring(" ".to_string()));
    assert_eq!(
        3,
        Solution::length_of_longest_substring("abcabcbb".to_string())
    );
    assert_eq!(
        1,
        Solution::length_of_longest_substring("bbbbb".to_string())
    );
    assert_eq!(
        3,
        Solution::length_of_longest_substring("pwwkew".to_string())
    );
}

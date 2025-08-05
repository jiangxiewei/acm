
struct Solution {}

/** 
给你一个字符串 s，找到 s 中最长的回文子串 
 **/
impl Solution {
    // 最长回文子串
    pub fn longest_palindrome(s: String) -> String {
        Self::spread_from_center(s)
    }
    
    
    /**
        **动态规划法**
        dp\[i\]\[j\] = dp\[i+1\]\[j-1\] && s\[i\] == s\[j\]
     */
    #[allow(dead_code)]
    fn dynamic_programing(str: String) -> String {
        let arr = str.as_bytes();
        let arr_len: usize = arr.len();
        let mut dp = vec![vec![false; arr_len]; arr_len];

        let (mut ts, mut te, mut max) = (0usize, 0usize, 1);

        for i in 0..arr_len {
            dp[i][i] = true;
            if i+1 < arr_len {
                dp[i][i + 1] = arr[i] == arr[i + 1];
                if dp[i][i + 1] && 2 > max { (ts, te, max) = (i, i + 1, 2) };
            }
        }

        for i in 0..arr_len-1 {
            let (mut s, mut e) = (0, 2 + i);
            while s < arr_len - 1 && e < arr_len {
                if dp[s + 1][e - 1] && arr[s] == arr[e] { dp[s][e] = true; };
                if dp[s][e] && e - s + 1 > max { (ts, te, max) = (s, e, e - s + 1); };
                (s, e) = (s + 1, e + 1);
            };
        }
        str[ts..=te].to_string()
    }
    
    /**
        中心扩散法
    **/
    fn spread_from_center(s: String) -> String {
        let arr = s.into_bytes();
        let mut result: &[u8] = &[arr[0]];
        for i in 0..arr.len()-1 {
            let (sub1, sub2) = (
                Self::spread_from(&arr, (i, i)),
                Self::spread_from(&arr, (i, (i + 1)))
            );
            result = if sub1.len() <= result.len() && sub2.len() <= result.len() {
                result
            } else if sub1.len() > sub2.len() {
                sub1
            } else {
                sub2
            };
        };
        unsafe {
            String::from_utf8_unchecked(result.into())
        }
    }

    fn spread_from(arr: &Vec<u8>, (mut tl,mut tr): (usize,usize)) -> &[u8] {
        while tl < arr.len() && tr < arr.len() && arr[tl] == arr[tr] {
            tl = tl.wrapping_add_signed(-1);
            tr += 1;
        };
        if (tl.wrapping_add_signed(1)) <= (tr - 1) {
            &arr[tl.wrapping_add_signed(1)..=tr - 1]
        } else {
            &arr[tl..tl]
        }
    }

}


#[test]
fn test() {
    assert_eq!("bab", dbg!(Solution::longest_palindrome("babad".to_string())));
    assert_eq!("bb", dbg!(Solution::longest_palindrome("cbbd".to_string())));
    assert_eq!("ccc", dbg!(Solution::longest_palindrome("ccc".to_string())));
    assert_eq!("a", dbg!(Solution::longest_palindrome("a".to_string())));
    assert_eq!("aaaa", dbg!(Solution::longest_palindrome("aaaa".to_string())));
    assert_eq!("aca", dbg!(Solution::longest_palindrome("aacabdkacaa".to_string())));

}

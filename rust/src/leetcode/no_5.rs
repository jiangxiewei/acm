struct Solution {}

/** 
给你一个字符串 s，找到 s 中最长的回文子串 
 **/
impl Solution {
    // 最长回文子串
    pub fn longest_palindrome(s: String) -> String {
        Self::dynamic_programing(s)
    }
    
    
    /**
        动态规划法
     **/
    fn dynamic_programing(s: String) -> String {
        let arr = s.as_bytes();
        let arr_len: usize = arr.len();
        let mut dp = vec![vec![0u32; arr_len]; arr_len];
        for i in 0..arr_len {
            dp[i][i] = 1;
        }
        
        // todo 
        
        
        "".to_string()
    }
    
    /**
        中心扩散法
    **/
    fn spread_from_center(s: String) -> String {
        let arr = s.as_bytes();
        let (mut cl, mut cr, mut max_length) = (0usize, 0usize, 0usize);
        for i in 0..arr.len() {
            let (l, r) = Self::spread_from(arr, (i, i));
            (cl, cr, max_length) = if r - l + 1 > max_length { (l, r, r - l + 1) } else { (cl, cr, max_length) };
            if i + 1 < arr.len() {
                let (l, r) = Self::spread_from(arr, (i, i + 1));
                (cl, cr, max_length) = if r - l + 1 > max_length { (l, r, r - l + 1) } else { (cl, cr, max_length) };
            }
        }
        s[cl..=cr].to_string()
    }

    fn spread_from(arr: &[u8], (l, r): (usize, usize)) -> (usize, usize) {
        let (mut tl, mut tr) = (l as i32, r as i32);
        while tl >= 0 && tr < arr.len() as i32 && arr[tl as usize] == arr[tr as usize] {
            tl -= 1;
            tr += 1;
        };
        if (tl + 1) <= (tr - 1) {
            ((tl + 1) as usize, (tr - 1) as usize)
        } else {
            (tl as usize, tl as usize)
        }
    }
}


#[test]
fn test() {
    assert_eq!("bab", dbg!(Solution::longest_palindrome("babad".to_string())));
    assert_eq!("bb", dbg!(Solution::longest_palindrome("cbbd".to_string())));
    assert_eq!("ccc", dbg!(Solution::longest_palindrome("ccc".to_string())));
    assert_eq!("a", dbg!(Solution::longest_palindrome("a".to_string())));
}

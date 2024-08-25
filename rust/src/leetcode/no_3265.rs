
impl Solution {
    pub fn count_pairs(nums: Vec<i32>) -> i32 {
        let mut count = 0;
        for i in 0..nums.len() {
            for j in i + 1..nums.len() {
                if Self::is_similar(nums[i], nums[j]) {
                    count += 1;
                }
            }
        }
        count
    }

    fn is_similar(mut a: i32, mut b: i32) -> bool {
        let mut aa: [u8; 6] = [0; 6];
        let mut ba: [u8; 6] = [0; 6];
        for i in 0..6 {
            aa[i] = (a % 10) as u8;
            ba[i] = (b % 10) as u8;
            a /= 10;
            b /= 10;
        }
        let mut pre_diff: i32 = -1;
        let mut diff_count = 0;
        for i in 0..6 {
            if aa[i] != ba[i] {
                if pre_diff != -1 {
                    if aa[pre_diff as usize] == ba[i] && aa[i] == ba[pre_diff as usize] {
                        diff_count += 1;
                    } else {
                        return false;
                    }
                } else {
                    diff_count += 1;
                    pre_diff = i as i32;
                }
            }
        }
        if diff_count == 0 || diff_count == 2 {
            true
        } else {
            false
        }
    }
}

#[test]
fn test() {
    assert_eq!(dbg!(Solution::count_pairs(vec![3, 12, 30, 17, 21])), 2);
    assert_eq!(dbg!(Solution::count_pairs(vec![1, 1, 1, 1, 1])), 10);
    assert_eq!(
        dbg!(Solution::count_pairs(vec![
            226726, 387862, 880512, 611522, 343461, 420841, 334461, 10813, 226726, 334461, 80113,
            314364, 10813, 163067, 134364, 332548, 413463, 343416, 236429, 164332, 566432, 226726,
            334164, 343461, 143463, 163229, 667555, 667555, 343461, 657565, 343461, 770521, 285866,
            930657, 236429, 502387, 313446, 334461, 12219, 163760, 144363, 227626
        ])),
        53
    );
}

struct Solution {}

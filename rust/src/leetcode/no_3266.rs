use std::cmp::Ordering;
use std::collections::BinaryHeap;

const MOD: u128 = 1000000007;

impl Solution {
    pub fn get_final_state(nums: Vec<i32>, k: i32, multiplier: i32) -> Vec<i32> {
        if multiplier==1 {
            return nums;
        }
        let mut max_heap: BinaryHeap<Point> = BinaryHeap::new();

        let mut maxp = 0;
        for i in 0..nums.len() {
            if nums[i] > nums[maxp] { maxp = i; }
            max_heap.push(Point(i, nums[i] as u128));
        }
        let mut times = k;
        while times > 0 {
            let mut v = max_heap.pop().unwrap();
            let p = v.0;
            v.1 = v.1 * multiplier as u128;
            max_heap.push(v);
            times -= 1;
            if p==maxp { break }
        }
        let remain_opt = times as usize % nums.len();
        times = times / nums.len() as i32;
        let multiplier_1 = Self::quick_pow(multiplier as i128, times as i128, MOD as i128) as u128;
        let multiplier_2 = Self::quick_pow(multiplier as i128, times as i128 + 1, MOD as i128) as u128;
        let mut vec = Vec::from(nums);
        for i in 0..vec.len() {
            let x = max_heap.pop().unwrap();
            let m = if i < remain_opt {
                multiplier_2
            } else {
                multiplier_1
            };
            vec[x.0] = ((x.1 % MOD * m) % MOD) as i32;
        }

        vec
    }

    fn quick_pow(a: i128, b: i128, modd: i128) -> i128 {
        if a == 0 { return 0; };
        if b == 0 { return 1; };
        let mut result = 1;
        let mut base = a % modd;
        let mut x = b;
        while x != 0 {
            if x & 1 != 0 {
                result = (result * base) % modd;
            }
            base = (base * base) % modd;
            x = x >> 1;
        }
        return result;
    }
}

#[derive(Eq, PartialEq, Debug)]
struct Point(usize, u128);

impl PartialOrd for Point {
    fn partial_cmp(&self, other: &Self) -> Option<Ordering> {
        Some(Point::cmp(self, other))
    }
}

impl Ord for Point {
    fn cmp(&self, other: &Self) -> Ordering {
        self.1
            .cmp(&other.1)
            .reverse()
            .then_with(|| self.0.cmp(&other.0).reverse())
    }
}

#[test]
fn test() {
    assert_eq!(
        dbg!(Solution::get_final_state(vec![2, 1, 3, 5, 6], 5, 2)),
        vec![8, 4, 6, 5, 6]
    );
    assert_eq!(
        dbg!(Solution::get_final_state(vec![2, 2, 2], 2, 2)),
        vec![4, 4, 2]
    );
    assert_eq!(
        dbg!(Solution::get_final_state(vec![100000, 2000], 2, 1000000)),
        vec![999999307, 999999993]
    );
    assert_eq!(
        Solution::get_final_state(
            vec![66307295, 441787703, 589039035, 322281864], 900900704, 641725),
        vec![664480092, 763599523, 886046925, 47878852]
    );
}

struct Solution {}

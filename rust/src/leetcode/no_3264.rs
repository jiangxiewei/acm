use std::cmp::Ordering;
use std::collections::BinaryHeap;

impl Solution {
    pub fn get_final_state(nums: Vec<i32>, k: i32, multiplier: i32) -> Vec<i32> {
        let mut bh: BinaryHeap<Point> = BinaryHeap::new();
        
        for (i, v) in nums.iter().enumerate() {
            bh.push(Point(i, *v));
        }

        let mut  times = k;
        while times > 0 {
            let mut p = bh.pop().unwrap();
            p.1 = p.1 * multiplier;
            bh.push(p);
            times -= 1;
        }
        let mut r = Vec::<i32>::from(nums);

        for x in bh.iter() {
            r[x.0] = x.1;
        }
        r
    }
}

#[derive(Eq,PartialEq)]
struct Point(usize, i32);

impl Ord for Point {
    fn cmp(&self, other: &Self) -> Ordering {
        self.1.cmp(&other.1).reverse().then_with(|| { self.0.cmp(&other.0).reverse()})
    }
}

impl PartialOrd for Point {
    fn partial_cmp(&self, other: &Self) -> Option<Ordering> {
        Some(self.1.cmp(&other.1).reverse().then_with(|| self.0.cmp(&other.0).reverse()))
    }
}

#[test]
fn test() {
    assert_eq!(dbg!(Solution::get_final_state(vec![2, 1, 3, 5, 6], 5, 2)), vec![8, 4, 6, 5, 6]);
    assert_eq!(dbg!(Solution::get_final_state(vec![2, 2, 2], 2, 2)), vec![4, 4, 2]);
    
}


struct Solution {}

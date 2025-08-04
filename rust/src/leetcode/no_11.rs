/**
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 */
struct Solution;

impl Solution {
    pub fn max_area(height: Vec<i32>) -> i32 {
        Self::collapse_from_edge(height)
    }

    fn collapse_from_edge(height: Vec<i32>) -> i32 {
        let mut max_area = 0;
        let mut l = 0;
        let mut r = height.len() - 1;
        while l < r {
            max_area = max_area.max((r - l) as i32 * height[l].min(height[r]));
            if height[r] > height[l] {
                l += 1;
            } else {
                r -= 1;
            }
        }
        max_area
    }

    // waisting memory .
    #[allow(dead_code)]
    fn spread_from_center(height: Vec<i32>) -> i32 {
        let mut max = 0;
        let mut right_sight_max = Vec::<i32>::with_capacity(height.len());
        let mut left_sight_max = Vec::<i32>::with_capacity(height.len());
        for x in height.iter() {
            if max.lt(x) {
                max = x.clone();
            }
            right_sight_max.push(max);
        }
        max = 0;
        for x in height.iter().rev() {
            if max.lt(x) {
                max = x.clone();
            }
            left_sight_max.push(max);
        }
        left_sight_max.reverse();

        let (max_p, _) = height
            .iter()
            .enumerate()
            .reduce(|a, b| if a.1.lt(b.1) { b } else { a })
            .unwrap();

        let mut max_area = 0i32;
        let mut l = max_p;
        let mut r = max_p;
        while l > 0 || (r + 1 < height.len()) {
            if l < 1 {
                r += 1;
            } else if r + 1 >= height.len() || right_sight_max[l - 1] > left_sight_max[r + 1] {
                l -= 1;
            } else {
                r += 1;
            }
            max_area = max_area.max((r - l) as i32 * height[r].min(height[l]));
        }
        max_area
    }
}

#[test]
fn test() {
    assert_eq!(Solution::max_area(vec![1, 8, 6, 2, 5, 4, 8, 3, 7]), 49);
    assert_eq!(Solution::max_area(vec![1, 1]), 1);
}

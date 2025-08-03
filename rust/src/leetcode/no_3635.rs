/// cn: 100748 , 100750
///
/// https://leetcode.com/problems/earliest-finish-time-for-land-and-water-rides-i/description/
/// https://leetcode.com/problems/earliest-finish-time-for-land-and-water-rides-ii/description/
///
struct Solution;

impl Solution {
    pub fn earliest_finish_time(
        land_start_time: Vec<i32>,
        land_duration: Vec<i32>,
        water_start_time: Vec<i32>,
        water_duration: Vec<i32>,
    ) -> i32 {
        let mut land_infos = land_start_time
            .iter()
            .enumerate()
            .map(|(i, s)| (*s, land_duration[i]))
            .collect::<Vec<_>>();
        let mut water_infos = water_start_time
            .iter()
            .enumerate()
            .map(|(i, s)| (*s, water_duration[i]))
            .collect::<Vec<_>>();
        Self::find_right_from_left(&mut land_infos, &mut water_infos).min(
            Self::find_right_from_left(&mut water_infos, &mut land_infos),
        )
    }

    fn find_right_from_left(a: &mut Vec<(i32, i32)>, b: &mut Vec<(i32, i32)>) -> i32 {
        a.sort_by_key(|(start, dur)| start + dur);
        b.sort_by_key(|x| x.0);

        let mut before_min_duration = Vec::with_capacity(b.len());

        for i in 0..b.len() {
            let b_item = &b[i];
            let last_duration = before_min_duration.last().unwrap_or(&0);
            if i == 0 || b_item.1 < *last_duration {
                before_min_duration.push(b_item.1);
            } else {
                before_min_duration.push(*last_duration);
            };
        }

        let mut after_min_end_time = Vec::with_capacity(b.len());
        for i in (0..b.len()).rev() {
            let current_end_time = (b[i].0) + (b[i].1);
            let last_min_end_time = *after_min_end_time.last().unwrap_or(&0);
            if i == b.len() - 1 || current_end_time < last_min_end_time {
                after_min_end_time.push(current_end_time);
            } else {
                after_min_end_time.push(last_min_end_time);
            }
        }
        after_min_end_time.reverse();
        let mut min_end_time = i32::MAX;
        for (s, d) in a {
            let mut i = b
                .binary_search_by_key(&(*s + *d), |(x1, x2)| *x1)
                .unwrap_or_else(|r| r);
            if i == b.len() {
                min_end_time = min_end_time.min(*s + *d + before_min_duration[i - 1]);
            } else if i == 0 {
                min_end_time = min_end_time.min(after_min_end_time[i]);
            } else {
                min_end_time = min_end_time.min(after_min_end_time[i]);
                min_end_time = min_end_time.min(*s + *d + before_min_duration[i - 1]);
            }
        }
        min_end_time
    }
}

#[test]
fn test() {
    assert_eq!(
        Solution::earliest_finish_time(vec![2, 8], vec![4, 1], vec![6], vec![3]),
        9
    );
    assert_eq!(
        Solution::earliest_finish_time(vec![5], vec![3], vec![1], vec![10]),
        14
    );
    assert_eq!(
        Solution::earliest_finish_time(
            vec![32, 6, 55],
            vec![2, 22, 28],
            vec![45, 66, 43],
            vec![86, 7, 35]
        ),
        73
    );
    assert_eq!(
        Solution::earliest_finish_time(
            vec![32, 9, 56, 67, 30],
            vec![76, 86, 93, 93, 43],
            vec![81, 17],
            vec![57, 62]
        ),
        122
    );
}

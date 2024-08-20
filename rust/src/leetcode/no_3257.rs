struct Solution {
}

impl Solution {

    
    pub fn maximum_value_sum(board: Vec<Vec<i32>>) -> i64 {
        let mut selected_point = Vec::<(usize, usize, i64)>::new();
        for (xi,x) in board.iter().enumerate() {
            let mut arr = x.iter().enumerate()
                .map(|(i, v)| { (i, *v) })
                .collect::<Vec<(usize, i32)>>();
            arr.sort_by(|&a, &b| { a.1.cmp(&b.1).reverse() });
            selected_point.append(arr[0..3].iter().map(|(p, v)| { (xi, *p, *v as i64) }).collect::<Vec::<(usize, usize, i64)>>().as_mut());
        }
        selected_point.sort_by(|x1, x2| { x1.2.cmp(&x2.2).reverse()});
        let mut max = i64::MIN;
        for i in 0..selected_point.len() {
            for j in i+1..selected_point.len() {
                for k in j+1..selected_point.len() {
                    unsafe {
                        let a = selected_point.get_unchecked(i);
                        let b = selected_point.get_unchecked(j);
                        let c = selected_point.get_unchecked(k);
                        if a.1 != b.1 && b.1 != c.1 && a.1 != c.1 && a.0 != b.0 && b.0 != c.0 && a.0 != c.0 {
                            max = i64::max(max, a.2 + b.2 + c.2);
                        };
                    }
                }
            }
        }
        max
    }

}

#[test]
fn test() {
    assert_eq!(dbg!(Solution::maximum_value_sum(vec![vec![-46,-9,28], vec![34,-74,-25], vec![-62,-73,-68]])), -11);
    assert_eq!(dbg!(Solution::maximum_value_sum(vec![vec![1, 2, 3], vec![4, 5, 6], vec![7, 8, 9]])), 15);
    assert_eq!(dbg!(Solution::maximum_value_sum(vec![vec![70, -16, 31], vec![2, 75, 37], vec![67, -95, -44]])), 173);
    assert_eq!(dbg!(Solution::maximum_value_sum(vec![vec![1, 2, 3], vec![4, 5, 6], vec![7, 8, 9]])), 15);
    assert_eq!(dbg!(Solution::maximum_value_sum(vec![vec![-3, 1, 1, 1], vec![-3, 1, -3, 1], vec![-3, 2, 1, 1]])), 4);
    assert_eq!(dbg!(Solution::maximum_value_sum(vec![vec![1, 1, 1], vec![1, 1, 1], vec![1, 1, 1]])), 3);
}

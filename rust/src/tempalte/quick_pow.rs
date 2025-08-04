pub fn quick_pow(a: i128, b: i128, modd: i128) -> i128 {
    if a == 0 {
        return 0;
    };
    if b == 0 {
        return 1;
    };
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

fn normal(a: i128, b: i128, modd: i128) -> i128 {
    let mut result = 1;
    for _ in 0..b {
        result = (result * a) % modd;
    }
    result
}

#[test]
fn test() {
    assert_eq!(quick_pow(2, 3, 100000), 8);
    assert_eq!(
        quick_pow(641725, 225225174, 1e9 as i128 + 7),
        normal(641725, 225225174, 1e9 as i128 + 7)
    );
    assert_eq!(
        quick_pow(641725, 225225174 + 1, 1e9 as i128 + 7),
        normal(641725, 225225174 + 1, 1e9 as i128 + 7)
    );
}

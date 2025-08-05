
impl Solution {
    pub fn add_two_numbers(l1: Option<Box<ListNode>>, l2: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        Self::add(l1, l2, 0)
    }
    
    pub fn add(l1: Option<Box<ListNode>>, l2: Option<Box<ListNode>>, inc: i32) -> Option<Box<ListNode>> {
        if l1==None && l2==None && inc==0 { 
            None
        } else {
            let sum = inc +
                if l1 == None { 0 } else { l1.as_ref().unwrap().val } +
                if l2 == None { 0 } else { l2.as_ref().unwrap().val };
            Some(Box::new(ListNode {
                val: sum % 10,
                next: Self::add(
                    if l1 != None { l1.unwrap().next } else { None },
                    if l2 != None { l2.unwrap().next } else { None },
                    sum / 10,
                ),
            }))
        }

    }
    
}


#[test]
fn test() {
    assert_eq!(dbg!(Solution::add_two_numbers(ListNode::try_from(vec![2,4,3]),
                                              ListNode::try_from(vec![5,6,4]))),
               ListNode::try_from(vec![7,0,8]));

    assert_eq!(dbg!(Solution::add_two_numbers(ListNode::try_from(vec![0]),
                                              ListNode::try_from(vec![0]))),
               ListNode::try_from(vec![0]));

    assert_eq!(dbg!(Solution::add_two_numbers(ListNode::try_from(vec![9, 9, 9, 9, 9, 9, 9]),
                                              ListNode::try_from(vec![9, 9, 9, 9]))),
               ListNode::try_from(vec![8, 9, 9, 9, 0, 0, 0, 1]));
}


// Definition for singly-linked list.
#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

impl ListNode {
    #[allow(dead_code)]
    #[inline]
    fn new(val: i32) -> Self {
        ListNode { next: None, val }
    }

    fn try_from(vec: Vec<i32>) -> Option<Box<Self>> {
        if vec.len() == 0 {
            return None;
        };
        let root: Box<ListNode> = Box::new(Self {
            val: vec[0],
            next: Self::try_from(vec.iter().skip(1).map(|x| *x).collect()),
        });
        Some(root)
    }
}

struct Solution {}

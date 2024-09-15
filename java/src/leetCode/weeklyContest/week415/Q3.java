package leetCode.weeklyContest.week415;

import java.util.Arrays;

public class Q3 {

    public int minValidStrings(String[] words, String target) {
        Node root = new Node('0');
        for (String word : words) {
            buildNode(word, root);
        }
        int[] dp = new int[target.length()];
        Arrays.fill(dp, Integer.MAX_VALUE);
        char[] arr = target.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            Node t = root;
            for (int j = i; j < arr.length && t.next[arr[j] - 'a'] != null; j++) {
                if (i > 0 && dp[i - 1] == Integer.MAX_VALUE) {
                    break;
                }
                dp[j] = Math.min(dp[j], (i == 0 ? 0 : dp[i-1]) + 1);
                t = t.next[arr[j] - 'a'];
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[dp.length - 1] == Integer.MAX_VALUE ? -1 : dp[dp.length - 1];
    }

    private void buildNode(String word, Node root) {
        char[] arr = word.toCharArray();
        Node t = root;
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] - 'a';
            if (t.next[index]==null) {
                t.next[index] = new Node(arr[i]);
            }
            t = t.next[index];
        }
    }

    public static class Node {
        public char v;
        public Node[] next = new Node[26];

        public Node(char v) {
            this.v = v;
        }

    }


    public static void main(String[] args) {
        Q3 q = new Q3();
//        System.out.println(q.minValidStrings(new String[]{"abc","aaaaa","bcdef"}, "aabcdabc")==3);
//        System.out.println(q.minValidStrings(new String[]{"abababab","ab"}, "ababaababa")==2);
//        System.out.println(q.minValidStrings(new String[]{"abcdef"}, "xyz")==-1);
//        System.out.println(q.minValidStrings(new String[]{"ecaaedbcadbceaecdcadacbacdeeadebbcdeaebb", "cb"}, "eeabd") == -1);
//        System.out.println(q.minValidStrings(new String[]{"b", "ccacc", "a"}, "cccaaaacba") == 8);
//        System.out.println(q.minValidStrings(new String[]{"abbbbbbabbbbbaaaaababaabbbbababbbbabbabbabaaabbbabbababababbbbbabbaaaabaaaaabb","aaaabaabaab","abbbbaabaaaabbabbbabaababbababbaabbaaaabbbbabb","abbabbabababaabaaaabbaaabbbaaabaaababbbbabbaababbbabbbabbbaabaaaaaaabbbbbbbbaabbbbbbabbbaaabaabbbabbbaababaaaabbabbabaaaaabaaaababbbaaabbbabbbababbaabbbabbbababaaaababbababbaaabaaabaaabbbbbbbbabbabaaabababbbaabababbbbbaaaabaaabaaabbaaaabbbaababaababaababbbbaabaaaababbbbababaaababaababbaababbaaaaabbaababbbbbbbbababbbbbabaababbbbabbaababbbbbbaabaaabbabbabababbaaaabaaaabaaabaaaabaaaabaabbaaababbbbbbabaaababbaabbabbabababaaaaabaabaababaaababbbabbabaaaabbabbbaabbaabbbbbbbabbbabbabbbaabbbbbaabbbbabaabaabbbaaaabaababaabaabaabbbaabaaabbbaabbabbbbbaaabbabaabbbabbbbbabbaabaababbaabbaaaabaaaabbababbabbababaabbbaaaabbaaaaaaabaaabb","aabbbbabaaaaababbababbabbbbaabbaaabbaabbbaabbabababaabaaabbabbaaaaaaababaaaaabbabbbaababab","aaaaaabbbbbbaaab","bbaabaa","bbbbbbaabbaaaabbaaaabbbabbbbaaaabbabbb","aaaaabbbabaaabbababaabbaaaaabababaaaabaabbabbbbbabaababbaabababbbbbaaabbbbaaabaabbabaabababbbb","babbababbbaabbbababbababbbaaa"}, "aabbbbabaabbabbbabbaaaaaaaaabbabbbaaaaaabbbaabbbbb"));
//        System.out.println(q.minValidStrings(new String[]{"adaeabcabdcaabbeceeadeaebcdddeadcbceeeadddabdc", "a"}, "beeea") == -1);
    }

}

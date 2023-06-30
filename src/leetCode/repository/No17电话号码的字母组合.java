package leetCode.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class No17电话号码的字母组合 {


    public static void main(String[] args) {
        //["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println(new No17电话号码的字母组合().letterCombinations("23"));
        System.out.println(new No17电话号码的字母组合().letterCombinations(""));
        //["a","b","c"]
        System.out.println(new No17电话号码的字母组合().letterCombinations("2"));

    }

    public List<String> letterCombinations(String digits) {
        return new DFS().letterCombinations(digits);
    }

    class DFS implements 电话号码的字母组合 {

        static char[][] mapping = new char[][]{{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        private final char[] chars = new char[5];

        private String input;
        private final List<String> combinations = new ArrayList<>();

        @Override
        public List<String> letterCombinations(String digits) {
            this.input = digits;
            dfs(0);
            return combinations;
        }

        private void dfs(int i) {
            if (input.length() == 0) {
                return;
            }
            if (i >= input.length()) {
                combinations.add(String.valueOf(chars, 0, i));
                return;
            }
            char[] alph = mapping[input.charAt(i)-'0'];
            for (char c : alph) {
                chars[i] = c;
                dfs(i + 1);
            }

        }

    }



    interface 电话号码的字母组合 {
        List<String> letterCombinations(String digits);
    }

}

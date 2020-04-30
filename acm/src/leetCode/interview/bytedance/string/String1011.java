package leetCode.interview.bytedance.string;

import java.util.LinkedList;
import java.util.StringJoiner;

/**
 * 翻转字符串里的单词<br/>
 * 给定一个字符串，逐个翻转字符串中的每个单词。<br/>
 * 说明：
 * <li/> 无空格字符构成一个单词。
 * <li/> 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * <li/> 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <br/>
 * 示例 1：
 * <p>
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * <br/>
 * 示例 2：
 * <p>
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * <br/>
 * 示例 3：
 * <p>
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <br/>
 *
 * @author jiang
 * @date 2020/4/30
 */
public class String1011 {

    public static void main(String[] args) {
        String1011 str = new String1011();
        System.out.println(str.reverseWords("the sky is blue"));//"blue is sky the"
        System.out.println(str.reverseWords("  hello world!  "));//"world! hello"
        System.out.println(str.reverseWords("a good   example"));//"example good a"
    }

    public String reverseWords(String s) {
        return firstWay(s);
    }

    public String firstWay(String s) {
        LinkedList<String> linkedList = new LinkedList<>();
        for (String word : s.split("[ ]+")) {
            if (!"".equals(word)) {
                linkedList.addFirst(word);
            }
        }
        StringJoiner result = new StringJoiner(" ");
        for (String word : linkedList) {
            result.add(word);
        }
        return result.toString();
    }

}

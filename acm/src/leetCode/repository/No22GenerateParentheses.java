package leetCode.repository;

import java.util.*;

/**
 * {@link "https://leetcode-cn.com/problems/generate-parentheses/submissions"}
 *
 * @author jxw
 * @date 2019/8/15
 */
public class No22GenerateParentheses {

    private int left = 0, right = 0;
    private char[] str = null;
    private List<String> result = new LinkedList<>();

    public List<String> generateParenthesis(int n) {
        str = new char[n * 2];
        right = left = n;
        left--;
        dfs(0, 0);
        return result;
    }

    /**
     * 深度搜索
     *
     * @param dep  深度
     * @param type 0代表左括号,1代表右括号
     */
    private void dfs(int dep, int type) {
        //赋值
        str[dep] = type == 0 ? '(' : ')';
        //到头了
        if (dep == str.length - 1) {
            result.add(new String(str));
            return;
        }
        //判断左括号是否还够用
        if (left > 0) {
            left--;
            //继续递归
            dfs(dep + 1, 0);
            left++;
        }
        //判断右括号是否还够用并判断左括号是否耗尽
        if (right > 0 && right > left) {
            right--;
            //继续递归
            dfs(dep + 1, 1);
            right++;
        }
    }

    public static void main(String[] args) {
        No22GenerateParentheses ins = new No22GenerateParentheses();
        System.out.println(Arrays.toString(ins.generateParenthesis(3).toArray(new String[0])));
    }

}

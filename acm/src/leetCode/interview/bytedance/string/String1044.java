package leetCode.interview.bytedance.string;

import java.util.LinkedList;
import java.util.List;

/**
 * 复原IP地址<br/>
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。<br/>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 * @author jiang
 * @date 2020/4/30
 */
public class String1044 {

    public static void main(String[] args) {
        String1044 str = new String1044();
        System.out.println(str.restoreIpAddresses("25525511135"));//[255.255.11.135, 255.255.111.35]
        System.out.println(str.restoreIpAddresses("010010"));//[0.10.0.10, 0.100.1.0]
        System.out.println(str.restoreIpAddresses("255255255255"));//[255.255.255.255]
    }

    private List<String> resultSet;

    public List<String> restoreIpAddresses(String s) {
        resultSet = new LinkedList<>();
        dfs(s.toCharArray(), 0, 4);
        return resultSet;
    }

    private LinkedList<Character> current = new LinkedList<>();

    public void dfs(char[] origin, int cp, int remainPointNum) {
        if (cp == origin.length && remainPointNum == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < current.size(); i++) {
                sb.append(current.get(i));
            }
            sb.deleteCharAt(sb.length() - 1);
            resultSet.add(sb.toString());
        } else if (remainPointNum <= 0) {
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (cp + i >= origin.length || (i > 0 && origin[cp] == '0')) {
                return;
            }
            //i决定取几位数字
            int parsed = 0;
            for (int j = 0; j <= i; j++) {
                parsed += (origin[cp + j] - '0') * Math.pow(10, (i - j));
            }
            if (parsed > 255) continue;
            for (int j = 0; j <= i; j++) {
                current.add(origin[cp + j]);
            }
            current.add('.');
            dfs(origin, cp + i + 1, remainPointNum - 1);
            current.removeLast();
            for (int j = 0; j <= i; j++) {
                current.removeLast();
            }
        }
    }

}

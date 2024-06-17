package leetCode.repository;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串
 *
 * @author gouqi
 * @date 2018/9/10
 */
public class No10 {

    private String s, p;
    private static final char ANY = '.';
    private static final char ZERO_OR_ANY = '*';

    public boolean isMatch(String s, String p) {
        this.s = s;
        this.p = p;
        return matchDFS(0, 0);
    }

    public boolean matchDFS(int sPos, int pPos) {

        //当两个都走完,说明刚好匹配完毕
        if (pPos == p.length() && sPos == s.length()) {
            return true;
        } else if (pPos >= p.length()) {
            //这是只匹配到了子串
            return false;
        }

        if (p.charAt(pPos + 1 < p.length() ? pPos + 1 : p.length() - 1) != ZERO_OR_ANY) {
            //下一位不为*
            if (isTwoCharMatch(sPos, pPos)) {
                //两字符相同,两边都走
                return matchDFS(sPos + 1, pPos + 1);
            } else {
                //凉凉
                return false;
            }
        } else {
            //下一位为*,那么就用*匹配0-n个
            boolean overFlag = false;
            for (int i = sPos; i <= s.length() && !overFlag; i++) {
                if (i == s.length() || !isTwoCharMatch(i, pPos)) {
                    //若s已经匹配完,或遇到一个第一个不与?*相匹配的字符时,打上结束标志
                    overFlag = true;
                }
                //进入递归,表明i之后不被?*消费,正则进入下一步
                if (matchDFS(i, pPos + 2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断两个字符是否等价
     *
     * @param sPos 字符串所在位置
     * @param pPos 正则字符串所在位置
     * @return 是否等价
     */
    public boolean isTwoCharMatch(int sPos, int pPos) {
        if (sPos >= s.length()) {
            return false;
        }
        if (p.charAt(pPos) == s.charAt(sPos) || p.charAt(pPos) == ANY) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String a = "ab", b = ".*";
        System.out.println(new No10().isMatch(a, b));
    }

}

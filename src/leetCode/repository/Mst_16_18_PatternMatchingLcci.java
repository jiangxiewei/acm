package leetCode.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 面试题 16.18 模式匹配
 * @author jiang
 * @date 2020/6/22
 */
public class Mst_16_18_PatternMatchingLcci {

    public static void main(String[] args) {
        Mst_16_18_PatternMatchingLcci mst = new Mst_16_18_PatternMatchingLcci();
        //true
        System.out.println(mst.patternMatching("abba", "dogcatcatdog"));
        //false
        System.out.println(mst.patternMatching("abba", "dogcatcatfish"));
        //false
        System.out.println(mst.patternMatching("aaaa", "dogcatcatdog"));
        //true
        System.out.println(mst.patternMatching("abba", "dogdogdogdog"));
        //true
        System.out.println(mst.patternMatching("a", ""));
        //false
        System.out.println(mst.patternMatching("ab", ""));
        //true
        System.out.println(mst.patternMatching("aa", ""));
    }

    private Map<Character, String> patMap;
    private Set<String> regPat;

    public boolean patternMatching(String pattern, String value) {
        if (pattern.length() == 1) {
            return true;
        }
        patMap = new HashMap<>();
        regPat = new HashSet<>();
        return patDfs(pattern, 0, value, 0);
    }

    /**
     * dfs法一遍扫描一遍构造 pattern对应的字符与str的映射关系.
     */
    private boolean patDfs(String pattern, int pi, String value, int vi) {
        if (pi >= pattern.length() && vi >= value.length()) {
            return true;
        } else if (pi >= pattern.length() || vi > value.length()) {
            return false;
        }
        Character c = pattern.charAt(pi);
        String cmap = patMap.get(c);
        if (cmap != null) {
            //已匹配过的pattern
            return value.startsWith(cmap, vi) && patDfs(pattern, pi + 1, value, vi + cmap.length());
        } else {
            //开始架设当前pattern内容
            for (int i = value.length() - vi; i >= 0; i--) {
                //取接下来的i位作为内容
                String patStr = value.substring(vi, vi + i);
                if (regPat.contains(patStr)) {
                    continue;
                }
                regPat.add(patStr);
                patMap.put(c, patStr);
                if (patDfs(pattern, pi + 1, value, vi + i)) {
                    return true;
                }
                regPat.remove(patStr);
                patMap.remove(c);
            }
        }
        return false;
    }

}

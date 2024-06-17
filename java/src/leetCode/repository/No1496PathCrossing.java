package leetCode.repository;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 给你一个字符串 path，其中 path[i] 的值可以是 'N'、'S'、'E' 或者 'W'，分别表示向北、向南、向东、向西移动一个单位。
 *
 * 机器人从二维平面上的原点 (0, 0) 处开始出发，按 path 所指示的路径行走。
 *
 * 如果路径在任何位置上出现相交的情况，也就是走到之前已经走过的位置，请返回 True ；否则，返回 False
 *
 *
 * 输入：path = "NES"
 * 输出：false
 * 解释：该路径没有在任何位置相交。
 *
 * 输入：path = "NESWW"
 * 输出：true
 * 解释：该路径经过原点两次。
 *
 * @author jxw
 * @date 2020/8/24
 */
public class No1496PathCrossing {

    public static void main(String[] args) {
        No1496PathCrossing no = new No1496PathCrossing();
        //false
        System.out.println(no.isPathCrossing("NES"));
        //true
        System.out.println(no.isPathCrossing("NESWW"));
    }

    public boolean isPathCrossing(String path) {
        Map<Character, Map.Entry<Integer,Integer>> dirTrans = new HashMap<>();
        dirTrans.put('N', new AbstractMap.SimpleEntry(0, +1));
        dirTrans.put('E', new AbstractMap.SimpleEntry(+1, 0));
        dirTrans.put('S', new AbstractMap.SimpleEntry(0, -1));
        dirTrans.put('W', new AbstractMap.SimpleEntry(-1, 0));

        int x = 0, y = 0;
        HashSet<Map.Entry<Integer, Integer>> set = new HashSet<>();
        set.add(new AbstractMap.SimpleEntry<>(x, y));
        for (char c : path.toCharArray()) {
            Map.Entry<Integer, Integer> entry = dirTrans.get(c);
            x += entry.getKey();
            y += entry.getValue();
            AbstractMap.SimpleEntry<Integer, Integer> simpleEntry = new AbstractMap.SimpleEntry<>(x, y);
            if (set.contains(simpleEntry)) {
                return true;
            }
            set.add(simpleEntry);
        }
        return false;

    }

}

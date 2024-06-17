package codeforce.EducationalCodeforcesRound42;

import java.util.*;

public class DMergeEquals {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Long[] input = new Long[n];
        Map<Long, Integer> positionMap = new HashMap<>(n);
        for (Integer i = 0; i < n; i++) {
            long number = scan.nextLong();
            putIntoMap(number, i, positionMap);
        }
        //存入排序结构
        TreeMap<Integer, Long> sortedMap = new TreeMap<>();
        for (Long num : positionMap.keySet()) {
            sortedMap.put(positionMap.get(num), num);
        }
        //根据最终下标输出num
        System.out.println(sortedMap.size());
        Iterator<Integer> iterator = sortedMap.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.print(sortedMap.get(iterator.next()));
            if (iterator.hasNext()) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    private static void putIntoMap(Long num, Integer i, Map<Long, Integer> positionMap) {
        if (positionMap.containsKey(num)) {
            positionMap.remove(num);
            putIntoMap(num + num, i, positionMap);
        } else {
            positionMap.put(num, i);
        }
    }

    public static void myLowBWay() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Long[] input = new Long[n];
        TreeMap<Long, PriorityQueue<Integer>> positionMap = new TreeMap<>();
        for (Integer i = 0; i < n; i++) {
            input[i] = scan.nextLong();
            PriorityQueue<Integer> position = positionMap.computeIfAbsent(input[i], aLong -> new PriorityQueue<>());
            position.add(i);
        }

        TreeMap<Integer, Long> sortedMap = new TreeMap<>();
        //每次重新获取迭代器.
        for (Iterator<Long> iter = positionMap.keySet().iterator(); iter.hasNext(); iter = positionMap.keySet().iterator()) {
            //数字
            Long key = iter.next();
//            System.out.println(key + "=====");
            //将相同数据进行合并存入Map,新key为(key+key)
            PriorityQueue<Integer> position = positionMap.get(key);
            //如果大于1,进行合并操作
            if (position.size() > 1) {
                PriorityQueue<Integer> upgradePos = positionMap.get(key + key);
                if (upgradePos == null) {
                    upgradePos = new PriorityQueue<>();
                    positionMap.put(key + key, upgradePos);
                }
                //是否升级到key+key
                boolean upFlag = false;
                //是否需要保留最后一个数(奇数保留)
                boolean remainFlag = (position.size() & 1) == 1;
                while (!position.isEmpty()) {
                    //数字位置
                    Integer pos = position.poll();
                    if (upFlag) {
                        upgradePos.add(pos);
                    }
                    if (remainFlag && position.size() == 1) {
                        break;
                    }
                    upFlag = !upFlag;
                }
            }
            //当前key处理完,存入结果集,将key从原map中移除
            if (!position.isEmpty()) {
                sortedMap.put(position.poll(), key);
            }
            positionMap.remove(key);
        }
        //根据最终下标输出num
        System.out.println(sortedMap.size());
        Iterator<Integer> iterator = sortedMap.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.print(sortedMap.get(iterator.next()));
            if (iterator.hasNext()) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}


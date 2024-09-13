package leetCode.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 消灭 A 需要攻击的次数为 c(a) , 同理可得消灭 B 需要的攻击次数，记作 c(b)
 * 如果先消灭 A，再消灭 B，那么受到的伤害总和为 S(a,b) = c(a) * damage(a) + ( c(a) + c(b) ) * damage(b)
 * 如果先消灭 B，再消灭 A，那么受到的伤害总和为 S(b,a) = c(b) * damage(b) + ( c(a) + c(b) ) * damage(a)
 * 如果先消灭 A，再消灭 B 更好，那么有 S(a,b) < S(b,a) ,进行化简可得 :  c(a) * damage(b) < c(b) * damage(a)
 * 所以只要 damage(x) / c(x) 分值越高，则越是优先处理
 */
public class No3273 {



    public long minDamage(int power, int[] damage, int[] health) {
        long totalDamage = 0;
        List<Enermy> list = new ArrayList<>();
        for (int i = 0; i < damage.length; i++) {
            Enermy e = new Enermy();
            totalDamage += damage[i];
            e.damage = damage[i];
            e.health = health[i];
            e.cost = e.health / power + (e.health % power > 0 ? 1 : 0);
            e.score = e.cost * 1.0 / e.damage;
            list.add(e);
        }
        list.sort(Comparator.comparingDouble(o -> o.score));
        long suffer = 0;
        for (Enermy e : list) {
//            System.out.println(e);
            suffer += e.cost * totalDamage;
            totalDamage -= e.damage;
        }
//        System.out.println(suffer);
        return suffer;
    }

    public static class Enermy {
        public int damage;
        public int health;
        public int cost;
        public double score;

        @Override
        public String toString() {
            return "Enermy{" +
                    "damage=" + damage +
                    ", health=" + health +
                    ", cost=" + cost +
                    ", score=" + score +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(new No3273().minDamage(4, new int[]{1, 2, 3, 4}, new int[]{4, 5, 6, 8}) == 39);
        System.out.println(new No3273().minDamage(1, new int[]{1, 1, 1, 1}, new int[]{1, 2, 3, 4}) == 20);
        System.out.println(new No3273().minDamage(8, new int[]{40}, new int[]{59}) == 320);
    }

}

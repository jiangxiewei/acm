package leetCode.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LCP_80 {

    public String evolutionaryRecord(int[] parents) {

        Map<Integer, Node> shitMap = new HashMap<>();
        Node root = null;
        for (int i = 0; i < parents.length; i++) {
            Node cur = shitMap.computeIfAbsent(i, Node::new);
            if (parents[i] != -1) {
                Node p = shitMap.computeIfAbsent(parents[i], Node::new);
                p.add(cur);
            } else {
                root = cur;
            }
        }
        String result = root.deepestFirst();
        result = result.endsWith("1") ? result.substring(0, result.lastIndexOf("0") + 1) : result;
        return result;
    }

    public static class Node {

        private final List<Node> childrens = new ArrayList<>();

        public int id;

        public Node(int id) {
            this.id = id;
        }

        public void add(Node n) {
            childrens.add(n);
        }

        public String deepestFirst() {
            StringBuilder sb = new StringBuilder();
            childrens.stream().map(Node::deepestFirst).map(s -> 0 + s + 1).sorted().forEach(sb::append);
            return sb.toString();
        }

    }


    public static void main(String[] args) {
        LCP_80 fuck = new LCP_80();
        System.out.println("00110".equals(fuck.evolutionaryRecord(new int[]{-1, 0, 0, 2})));
        System.out.println("00101100".equals(fuck.evolutionaryRecord(new int[]{-1, 0, 0, 1, 2, 2})));
        System.out.println("00001101100011100101101011001100".equals(fuck.evolutionaryRecord(new int[]{-1, 0, 1, 1, 3, 0, 1, 1, 6, 0, 9, 6, 7, 4, 12, 1, 3, 5})));
        System.out.println("0000110101110000110110010110101100110".equals(fuck.evolutionaryRecord(new int[]{-1, 0, 1, 0, 3, 1, 5, 1, 4, 1, 2, 0, 0, 11, 5, 4, 4, 6, 16, 2})));

    }

}

package leetCode.repository;

import java.util.*;

/**
 * @author jiangxiewei
 * @since 2021/8/5
 */
public class No146LRUCache {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        assert lruCache.get(1) == 1;
        lruCache.put(3, 3);
        assert lruCache.getSize() == 2;
        assert lruCache.get(2) == -1;
        lruCache.put(4, 4);
        assert lruCache.getSize() == 2;
        assert lruCache.get(1) == -1;
        assert lruCache.get(3) == 3;
        assert lruCache.get(4) == 4;
    }

    static class LRUCache {

        private final Map<Integer, Node> cache;
        private Node head= new Node(-1, -1), tail = new Node(-1, -1);
        private final Integer capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>(capacity * 2);
            head.nextNode = tail;
            tail.preNode = head;
        }

        public int get(int key) {
            Node node = getNode(key);
            if (node == null) {
                return -1;
            }
            return node.value;
        }

        private Node getNode(int key) {
            var node = cache.get(key);
            if (node == null) {
                return null;
            }
            node.removeSelf();
            head.insertNext(node);
            return node;
        }

        public void put(int key, int value) {
            var select = getNode(key);
            if (select != null) {
                select.value = value;
                return;
            }
            var node = new Node(key, value);
            head.insertNext(node);
            if (cache.size() >= capacity) {
                Node shouldBeRemoved = tail.preNode;
                shouldBeRemoved.removeSelf();
                cache.remove(shouldBeRemoved.key);
            }
            cache.put(node.key, node);
        }

        public int getSize() {
            return cache.size();
        }

        static class Node {
            int key;
            int value;
            Node preNode;
            Node nextNode;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public void insertNext(Node node) {
                nextNode.preNode = node;
                node.nextNode = nextNode;
                nextNode = node;
                node.preNode = this;
            }

            public void removeSelf() {
                if (preNode != null) {
                    preNode.nextNode = nextNode;
                }
                if (nextNode != null) {
                    nextNode.preNode = preNode;
                }
                preNode = null;
                nextNode = null;
            }

        }

    }

}

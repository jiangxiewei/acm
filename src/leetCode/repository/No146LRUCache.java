package leetCode.repository;

import java.util.*;

/**
 * @author jiangxiewei
 * @since 2021/8/5
 */
public class No146LRUCache {

    public static void main(String[] args) {
        //第一套测试
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
        //第二套测试
        lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(1, 1);
        lruCache.put(2, 3);
        lruCache.put(4, 1);
        assert lruCache.get(1) == -1;
        assert lruCache.get(2) == 3;
        //第三套测试
        lruCache = new LRUCache(1);
        lruCache.put(2, 1);
        assert lruCache.get(2) == 1;
        lruCache.put(3, 2);
        assert lruCache.get(2) == -1;
        assert lruCache.get(3) == 2;
    }

    public static class LRUCache {

        private final Map<Integer, Node> map;
        private final int capacity;
        private final Node head = new Node(-1, -1), tail = new Node(-1, -1);

        LRUCache(int cap) {
            map = new HashMap<>(cap + 1);
            this.capacity = cap;
            head.next = tail;
            tail.pre = head;
        }

        void put(int key, int val) {
            //先查询是否存在,存在就要修改val,不存在记得塞进容器内,最后记得放到链表头.
            Node node = map.get(key);
            node = node == null ? new Node(key, val) : node;
            node.val = val;
            node.insertBefore(head.next);
            map.put(key, node);
            //如果大于额定值,进行清理.
            if (map.size() > capacity) {
                map.remove(tail.pre.key);
                tail.pre.free();
            }
        }

        int get(int key) {
            Node node = map.get(key);
            if (node != null) {
                //如果存在节点,则移动到最新
                node.insertBefore(head.next);
                return node.val;
            } else {
                return -1;
            }
        }

        int getSize() {
            return map.size();
        }

        /**
         * 构造双向链表的节点.
         * 定义连接/游离的行为,方便进行节点迁移.
         */
        static class Node {
            final int key;
            int val;
            Node pre, next;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }

            /**
             * 这个节点免费了.
             */
            public void free() {
                if (pre != null) {
                    pre.next = next;
                }
                if (next != null) {
                    next.pre = pre;
                }
                pre = null;
                next = null;
            }

            /**
             * 插在某个节点前面
             *
             * @param node 节点
             */
            public void insertBefore(Node node) {
                //注意: 防止自己插自己
                if (this == node) {
                    return;
                }
                //注意: 重新连接前先将节点游离,防止之前连接没清干净
                this.free();
                pre = null;
                //连接node上一个节点至此节点
                if (node.pre != null) {
                    pre = node.pre;
                    node.pre.next = this;
                }
                //连接此节点至node
                next = node;
                node.pre = this;
            }

        }

    }


    /**
     * 通过linkedHashmap的accessOrder实现访问移动节点至链表头处.
     * 通过重写removeEldestEntry方法加上删除最老节点的时机
     */
    static class LRUCacheByLinkedHashMap extends LinkedHashMap<Integer,Integer> {

        private final int capacity;

        LRUCacheByLinkedHashMap(int capacity) {
            super(capacity, 1, true);
            this.capacity = capacity;
        }

        int get(int key){
            return super.getOrDefault(key, -1);
        }

        void put(int key, int value){
            super.put(key, value);
        }

        /**
         * jdk8~11(只用过这些)的默认实现都是直接返回false,即无论如何都不会删除最老节点. 需要用户重写来加入清除节点的时机.
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > this.capacity;
        }

        int getSize() {
            return size();
        }
    }

}

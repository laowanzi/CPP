import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    int capacity;
    Map<Integer, Node> map;
    NodeList list;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        list = new NodeList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        return map.get(key).val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            list.removeNode(map.get(key));
        } else if (map.size() == capacity) {
            Node last = list.removeLast();
            int k = last.key;
            map.remove(k);
        }
        Node node = new Node(key, value);
        list.addFirst(node);
        map.put(key, node);
    }
    static class Node {
        Node prev;
        Node next;
        int val;
        int key;

        public Node(int key, int val) {
            this.prev = null;
            this.next = null;
            this.key = key;
            this.val = val;
        }
    }
    static class NodeList {
        Node head;
        Node tail;

        public NodeList() {
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node node) {
            Node next = head.next;
            head.next = node;
            node.prev = head;
            node.next = next;
            next.prev = node;
        }
        void removeNode(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }
        Node removeLast() {
            Node node = tail.prev;
            removeNode(node);
            return node;
        }

    }
}

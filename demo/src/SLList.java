public class SLList {
    private IntNode first;
    private int size;
    public SLList(int x) {
        first = new IntNode(x, null);
        size = 1;
    }
    public SLList() {
        first = null;
        size = 0;
    }
    public void addFirst(int x) {
        first = new IntNode(x, first);
        size++;
    }
    public int getFirst() {
        return first.item;
    }
    public void addLast(int x) {
        IntNode p = first;
        while (p != null) {
            p = p.next;
        }
        p = new IntNode(x, null);
        size++;
    }
    public static int size(IntNode first) {
        if (first.next == null) {
            return 1;
        }
        return 1 + size(first.next);
    }
    public int size() {
        return size;
    }

}

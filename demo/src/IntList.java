public class IntList {
    public int first;
    public IntList rest;
    public IntList(int f, IntList r) {
        this.first = f;
        this.rest = r;
    }
    public int size() {
        if (this.rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    public int iterativeSize() {
        IntList p = this;
        int len = 1;
        while (p.rest != null) {
            len++;
            p = p.rest;
        }
        return len;
    }

    public int get(int i) {
        if (i <= 0) {
            return 0;
        }
        IntList p = this;
        for (int j = 0; j < i; j++) {
            p = p.rest;
        }
        return p.first;
    }
}

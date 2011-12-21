package adt.datastruct;

class Node<T> {
    private Node<T> ref; 
    private T value;

    public Node(T value) {
        this.ref = null;
        this.value = value;
    }

    public T value() {
        return value;
    }

    public Node<T> ref() {
        return ref;
    }

    public void setRef(Node<T> ref) {
        this.ref = ref;
    }
}

public class UnionFind<T> {
    public UnionFind() {
    }

    public Node<T> newNode(T t) {
        return new Node<T>(t);
    }

    public void unify(Node<T> a, Node<T> b) {
        a = find(a);
        b = find(b);

        if (a == b)
            return;

        a.setRef(b);
    }

    public Node<T> find(Node<T> a) {
        if (a.ref() == null)
            return a;

        Node<T> s = find(a.ref());
        a.setRef(s);

        return s;
    }
}

package adt.datastruct;

import java.util.HashMap;
import java.util.Map;

class UFNode<T> {
    private UFNode<T> ref; 
    private T value;

    public UFNode(T value) {
        this.ref = null;
        this.value = value;
    }

    public T value() {
        return value;
    }

    public UFNode<T> ref() {
        return ref;
    }

    public void setRef(UFNode<T> ref) {
        this.ref = ref;
    }
}

public class UnionFind<T> {
    private Map<T, UFNode<T>> nodes;
        
    public UnionFind() {
        nodes = new HashMap<T, UFNode<T>>();
    }

    public void addNode(T t) {
        if (nodes.containsKey(t))
            return;
        
        nodes.put(t, new UFNode<T>(t));
    }

    public void unify(T a, T b) {
        unify(nodes.get(a), nodes.get(b));
    }
    
    private void unify(UFNode<T> a, UFNode<T> b) {
        a = find(a);
        b = find(b);

        if (a == b)
            return;

        a.setRef(b);
    }
    
    public boolean isSame(T a, T b) {
        return find(nodes.get(a)) == find(nodes.get(b));
    }

    private UFNode<T> find(UFNode<T> a) {
        if (a.ref() == null)
            return a;

        UFNode<T> s = find(a.ref());
        a.setRef(s);

        return s;
    }
}

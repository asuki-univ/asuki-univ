package adt.datastruct;

abstract class TreeNode<T> {
    protected int value;
    protected T left;
    protected T right;
    
    protected TreeNode(int value) {
        this.value = value;
    }
    
    public int value() {
        return value;
    }
    
    public T left() {
        return left;
    }
    
    public void setLeft(T left) {
        this.left = left;
    }
    
    public T right() {
        return right;
    }
    
    public void setRight(T right) {
        this.right = right;
    }
}

public abstract class AbstractBalancedTree {
    public abstract int[] values();
    
    public abstract void insert(int v);
    public abstract void remove(int v);
    public abstract boolean contains(int v);
    
    public abstract boolean check();
}

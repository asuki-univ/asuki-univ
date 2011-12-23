package adt.datastruct;

public abstract class TreeNode<T> {
    private int value;
    private T left;
    private T right;
    
    protected TreeNode(int value) {
        this.value = value;
    }
    
    public int value() {
        return value;
    }
    
    public void setValue(int v) {
        this.value = v;
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
    
    public abstract String toNodeString();  
}


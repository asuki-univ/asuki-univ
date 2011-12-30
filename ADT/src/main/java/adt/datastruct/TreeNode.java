package adt.datastruct;

// ノード。後に拡張ができるようにジェネリクスを使っておく。
public abstract class TreeNode<T> {
    public int value;
    public T left;
    public T right;
    
    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
    
    // デバグ用
    public abstract String toNodeString();  
}


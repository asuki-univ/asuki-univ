package adt.datastruct;

import java.util.ArrayList;

public abstract class AbstractBinaryTree<T extends TreeNode<T>> {
    protected T root = null;
    protected int size = 0;
    
    public abstract void insert(int v);
    public abstract void remove(int v);
    public abstract boolean check();
    
    public void clear() {
        this.root = null;
        this.size = 0;
    }
    
    public int size() {
        return size;
    }

    public int[] values() {
        ArrayList<Integer> vs = new ArrayList<Integer>();
        
        values(root, vs);
        
        int[] result = new int[vs.size()];
        for (int i = 0; i < vs.size(); ++i)
            result[i] = vs.get(i);
        
        return result;
    }
    
    private void values(T node, ArrayList<Integer> vs) {
        if (node == null)
            return;
        
        values(node.left(), vs);
        vs.add(node.value());
        values(node.right(), vs);
    }
    
    public boolean contains(int v) {
        return contains(root, v);
    }    

    private boolean contains(TreeNode<T> node, int v) {
        if (node == null)
            return false;
        
        if (node.value() == v)
            return true;
        
        if (v < node.value())
            return contains(node.left(), v);
        else
            return contains(node.right(), v);
    }
    
    protected void replace(T parent, T oldNode, T newNode) {
        if (parent == null) {
            this.root = newNode;
            return;
        }
        
        if (parent.left() == oldNode)
            parent.setLeft(newNode);
        else
            parent.setRight(newNode);
    }
    
    protected void printTree() {
        printTree(0, root);
    }
    
    private void printTree(int indent, T node) {
        printIndent(indent);
        if (node == null) {
            System.out.println("LEAF");
            return;
        }
        
        System.out.println(node.toNodeString());
        printTree(indent + 1, node.left());
        printTree(indent + 1, node.right());
    }
    
    private void printIndent(int indent) {
        for (int i = 0; i < indent; ++i)
            System.out.print("    ");        
    }
}

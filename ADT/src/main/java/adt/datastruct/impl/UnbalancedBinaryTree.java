package adt.datastruct.impl;

import adt.datastruct.AbstractBinaryTree;
import adt.datastruct.TreeNode;

class UBTreeNode extends TreeNode<UBTreeNode> {
    public UBTreeNode(int v) {
        super(v);
    }
    
    @Override
    public String toNodeString() {
        return String.valueOf(value);
    }
}

public class UnbalancedBinaryTree extends AbstractBinaryTree<UBTreeNode> {
    
    @Override
    public UBTreeNode createNode(UBTreeNode parent, int v) {
        return new UBTreeNode(v);
    }
    
    @Override
    public void insert(int v) {
        if (this.root == null) {
            this.root = createNode(null, v);
            ++size;
            return;
        }
        
        UBTreeNode node = this.root;
        
        while (true) {
            if (v <= node.value) {
                if (node.left != null)
                    node = node.left;
                else {
                    node.left = createNode(node, v);
                    ++size;
                    return;
                }
            } else {
                if (node.right != null)
                    node = node.right;
                else {
                    node.right = createNode(node, v);
                    ++size;
                    return;
                }
            }
        }        
    }

    @Override
    public void remove(int v) {
        if (root == null)
            return;
        
        remove(null, root, v);
    }
    
    private void remove(UBTreeNode parent, UBTreeNode node, int v) {
        if (node == null)
            return;
        
        if (v < node.value) {
            remove(node, node.left, v);
            return;
        }
        if (node.value < v) {
            remove(node, node.right, v);
            return;
        }
        
        assert v == node.value;
        --size;
        
        UBTreeNode maxLeft = findMaxAndRemove(node, node.left);
        if (maxLeft != null) {
            node.value = maxLeft.value;
            return;
        }
        
        UBTreeNode minRight = findMinAndRemove(node, node.right);
        if (minRight != null) {
            node.value = minRight.value;
            return;
        }
        
        replace(parent, node, null);        
    }
    
    protected UBTreeNode findMaxAndRemove(UBTreeNode parent, UBTreeNode node) {
        if (node == null)
            return null;
        
        while (true) {
            if (node.right == null)
                break;
            parent = node;
            node = node.right;
        }
        
        replace(parent, node, node.left);
        return node;
    }
    
    protected UBTreeNode findMinAndRemove(UBTreeNode parent, UBTreeNode node) {
        if (node == null)
            return null;
        
        while (true) {
            if (node.left == null)
                break;
            parent = node;
            node = node.left;
        }
        
        replace(parent, node, node.right);
        return node;
    }

    @Override
    public boolean check() {
        return check(root);
    }

    private boolean check(UBTreeNode node) {
        if (node == null)
            return true;
        
        if (node.left != null && node.value <= node.left.value)
            return false;
        if (node.right != null && node.right.value < node.value)
            return false;
        
        return true;
    }
}

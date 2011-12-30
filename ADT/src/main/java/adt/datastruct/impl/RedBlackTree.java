package adt.datastruct.impl;

import adt.datastruct.AbstractBinaryTree;
import adt.datastruct.TreeNode;

class RedBlackTreeNode extends TreeNode<RedBlackTreeNode> {
    private RedBlackTreeNode parent;
    private boolean red;
    private final boolean leaf;
    
    public RedBlackTreeNode(RedBlackTreeNode parent, int v) {
        super(v);
        this.parent = parent;
        this.red = true;
        this.leaf = false;
        this.left = new RedBlackTreeNode(this);
        this.right = new RedBlackTreeNode(this);
    }
    
    // Create Leaf
    private RedBlackTreeNode(RedBlackTreeNode parent) {
        super(-1);
        this.parent = parent;
        this.red = false;
        this.leaf = true;
    }
    
    public RedBlackTreeNode parent() {
        return parent;
    }
    
    public void setParent(RedBlackTreeNode parent) {
        this.parent = parent;
    }
    
    public RedBlackTreeNode grandParent() {
        if (parent() != null)
            return parent().parent();
        return null;
    }

    public RedBlackTreeNode uncle() {
        RedBlackTreeNode grandParent = grandParent();
        if (grandParent == null)
            return null;
        if (grandParent.left == parent())
            return grandParent.right;
        else
            return grandParent.left;
    }
    
    public RedBlackTreeNode sibling() {
        if (this == parent().left)
            return parent.right;
        else
            return parent.left;
    }
    
    public boolean isRed() {
        return red;
    }
    
    public boolean isBlack() {
        return !red;
    }
    
    void setRed(boolean red) {
        this.red = red;
    }
    
    void setRed() {
        this.red = true;
    }
    
    void setBlack() {
        this.red = false;
    }
    
    boolean isLeaf() {
        return leaf;
    }

    @Override
    public String toNodeString() {
        return String.valueOf(value) + (isRed() ? " R" : " B");
    }
}

class RedBlackTreeNodeLeaf extends RedBlackTreeNode {
    public RedBlackTreeNodeLeaf(RedBlackTreeNode parent) {
        super(parent, -1);
    }    
    
    @Override
    boolean isLeaf() {
        return true;
    }
}

public class RedBlackTree extends AbstractBinaryTree<RedBlackTreeNode> {

    @Override
    public void insert(int v) {
        ++size;
        
        if (root == null) {
            root = new RedBlackTreeNode(null, v);
            root.setBlack();
            return;
        }

        // 通常通りの二分探索を行い、要素を挿入する。
        RedBlackTreeNode node = root;
        while (true) {
            if (v <= node.value) { // 左へ挿入
                if (node.left.isLeaf()) {
                    node.left = new RedBlackTreeNode(node, v);
                    node = node.left;
                    break;
                } else {
                    node = node.left;
                }
            } else { // 右へ挿入
                if (node.right.isLeaf()) {
                    node.right = new RedBlackTreeNode(node, v);
                    node = node.right;
                    break;
                } else {
                    node = node.right;
                }
            }
        }
        
        // その後、色の付け替えを行う。
        adjustColorForInsertion(node);
    }

    private void adjustColorForInsertion(RedBlackTreeNode node) {
        assert node.isRed();
        
        // 親が null の場合は root だという意味なので、色を黒とする
        if (node.parent() == null) {
            node.setBlack();
            return;
        }
                
        // 親が黒の場合、自分はそのまま赤とすればよい。
        if (node.parent().isBlack())
            return;
        
        // 親が赤かつ伯父が赤の場合、祖父は黒である。
        // 祖父を赤とし、親と伯父を黒とする。
        RedBlackTreeNode grand = node.grandParent();
        RedBlackTreeNode uncle = node.uncle();
        if (uncle != null && uncle.isRed()) {
            node.parent().setBlack();
            uncle.setBlack();
            grand.setRed();
            adjustColorForInsertion(grand);
            return;
        }

        // 以下、伯父が黒の場合
        // 祖父から見て、左の右　もしくは　右の左の場合、左回転もしくは右回転を行っておく。
        if (node == node.parent().right && node.parent() == grand.left) {
            rotateLeft(node.parent());
            node = node.left;
        } else if (node == node.parent().left && node.parent() == grand.right) {
            rotateRight(node.parent());
            node = node.right;
        }

        // 祖父を右回転もしくは左回転し、色の付け替えを行う。
        grand = node.grandParent();
        node.parent().setBlack();
        grand.setRed();
        if (node == node.parent().left && node.parent() == grand.left) {
            rotateRight(grand);
        } else if (node == node.parent().right && node.parent() == grand.right) {
            rotateLeft(grand);
        }
    }

    @Override
    public void remove(int v) {
        remove(this.root, v); 
    }
    
    private void remove(RedBlackTreeNode node, int v) {
        if (node == null)
            return;
        
        if (v < node.value)
            remove(node.left, v);
        else if (node.value < v)
            remove(node.right, v);
        else {
            assert node.value == v;
            if (!node.left.isLeaf()) {
                RedBlackTreeNode maxNode = findMaximumNode(node.left);
                node.value = maxNode.value;
                adjustColorAndRemove(maxNode);
            } else if (!node.right.isLeaf()) {
                RedBlackTreeNode minNode = findMinimumNode(node.right);
                node.value = minNode.value;
                adjustColorAndRemove(minNode);
            } else {
                adjustColorAndRemove(node);
            }
        }
    }
    
    private void adjustColorAndRemove(RedBlackTreeNode node) {
        // node は多くとも１つの子供のみを持つ。
        assert (node.left.isLeaf() || node.right.isLeaf());
        
        RedBlackTreeNode child = node.left.isLeaf() ? node.right : node.left;
        RedBlackTreeNode parent = node.parent();
        --size;
        replaceWithParent(parent, node, child);

        // Node が赤であれば、これ以上調整する必要はない。
        if (node.isRed())
            return;
        
        // 子供が赤であれば、それを黒とすれば良い
        if (child.isRed()) {
            child.setBlack();
            return;
        }

        // 自分が黒かつ子供も黒だった場合、黒の個数の調整が必要
        adjustColor(child);
    }
    
    private void adjustColor(RedBlackTreeNode node) {
        // node が root であれば、左右のパスにおいて、両方共黒の個数が１つ減るだけなので、調整の必要はない。
        if (node.parent() == null)
            return;

        // 兄弟が赤の場合、ローテーションを行なっておく。
        RedBlackTreeNode sibling = node.sibling();
        if (sibling != null && sibling.isRed()) {
            node.parent().setRed();
            sibling.setBlack();
            node.setBlack();
            if (node == node.parent().left)
                rotateLeft(node.parent());
            else
                rotateRight(node.parent());
        } 
        
        sibling = node.sibling();
        assert sibling != null;
        if (node.parent().isBlack() && sibling.isBlack() &&
                (sibling.left == null || sibling.left.isBlack()) &&
                (sibling.right == null || sibling.right.isBlack())) {
            sibling.setRed();
            adjustColor(node.parent());
            return;
        }

        if (node.parent().isRed() && sibling.isBlack() &&
            (sibling.left == null || sibling.left.isBlack()) &&
            (sibling.right == null || sibling.right.isBlack())) {
                sibling.setRed();
                node.parent().setBlack();
                return;
        }

        if (sibling.isBlack()) {
            if (node == node.parent().left && sibling.right.isBlack() && sibling.left.isRed()) { /* this last test is trivial too due to cases 2-4. */
                sibling.setRed();
                sibling.left.setBlack();
                rotateRight(sibling);
            } else if (node == node.parent().right && sibling.left.isBlack() && sibling.right.isRed()) {
                sibling.setRed();
                sibling.right.setBlack();
                rotateLeft(sibling);
            }
            
        }
        
        sibling = node.sibling();
        sibling.setRed(node.parent().isRed());
        node.parent().setBlack();

        if (node == node.parent().left) {
            sibling.right.setBlack();
            rotateLeft(node.parent());
        } else {
            sibling.left.setBlack();
            rotateRight(node.parent());
        }
    }
    
    private RedBlackTreeNode findMaximumNode(RedBlackTreeNode node) {
        if (!node.right.isLeaf())
            return findMaximumNode(node.right);
        
        return node;
    }
    
    private RedBlackTreeNode findMinimumNode(RedBlackTreeNode node) {
        if (!node.left.isLeaf())
            return findMinimumNode(node.left);
        return node;
    }

    private void replaceWithParent(RedBlackTreeNode parent, RedBlackTreeNode oldNode, RedBlackTreeNode newNode) {
        replace(parent, oldNode, newNode);
        if (newNode != null)
            newNode.setParent(parent);
    }
    
    @Override
    public boolean check() {
        return checkNumBlack(null, root) >= 0;
    }
    
    private int checkNumBlack(RedBlackTreeNode parent, RedBlackTreeNode node) {
        // 葉は黒
        if (node == null)
            return 1;
        
        int leftBlack = checkNumBlack(node, node.left);
        int rightBlack = checkNumBlack(node, node.right);
        
        if (leftBlack < 0 || rightBlack < 0)
            return -1;

        if (leftBlack != rightBlack)
            return -1;
        
        if (parent == null) {
            // 根は黒
            if (node.isRed())
                return -1;
        } else {
            // 赤の子は黒
            if (parent.isRed() && node.isRed())
                return -1;
        }
        
        return leftBlack + (node.isBlack() ? 1 : 0);        
    }

    private void rotateLeft(RedBlackTreeNode B) {
        RedBlackTreeNode P = B.parent();
        RedBlackTreeNode D = B.right;
        
        replace(P, B, D);
        D.setParent(P);
        
        B.right = D.left;
        if (D.left != null)
            D.left.setParent(B);
        
        D.left = B;
        B.setParent(D);
    }
    
    private void rotateRight(RedBlackTreeNode D) {
        RedBlackTreeNode P = D.parent();
        RedBlackTreeNode B = D.left;
        
        replace(P, D, B);
        B.setParent(D.parent());
        
        D.left = B.right;
        if (B.right != null)
            B.right.setParent(D);
        
        B.right = D;
        D.setParent(B);
    }
}

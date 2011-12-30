package adt.datastruct.impl;

import adt.datastruct.AbstractBinaryTree;
import adt.datastruct.TreeNode;


class AVLTreeNode extends TreeNode<AVLTreeNode> {
    // バランス。右の子の高さが n 高ければ n となる。マイナスは、左の子の方が高いことを表す。
    private int balance;

    AVLTreeNode(int value) {
        super(value);
        this.balance = 0;
    }
    
    public int balance() {
        return balance;
    }
    
    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public void addBalance(int balance) {
        this.balance += balance;
    }
    
    @Override
    public String toNodeString() {
        return String.format("%s (%d)", String.valueOf(value), Integer.valueOf(balance()));
    }
}

public class AVLTree extends AbstractBinaryTree<AVLTreeNode> {
    public AVLTree() {
    }

    @Override
    public void insert(int v) {
        ++size;
        
        // root がなければ、root に追加すればよい。
        if (root == null) {
            root = new AVLTreeNode(v);            
            return;
        }
        
        // root があれば、root の下に追加を行う。
        insert(null, this.root, v);
    }

    // v を node に挿入して高さの変化を返す。
    private int insert(AVLTreeNode parent, AVLTreeNode node, int v) {
        assert node != null;
        
        if (v <= node.value) {
            // 左へ挿入。
            int diff = 0;
            if (node.left == null) {
                // 左の子がなければ追加。このとき、左の子は追加分だけ１つ高くなる。
                node.left = new AVLTreeNode(v);
                diff = 1;
            } else {
                diff = insert(node, node.left, v);                
            }
            
            // 挿入後、バランスを取る。
            return achieveBalance(parent, node, diff, 0);
        } else {
            // 右へ挿入。左の場合とやっていることは同様。
            int diff = 0;
            if (node.right == null) {
                node.right = new AVLTreeNode(v);
                diff = 1;
            } else {
                diff = insert(node, node.right, v);
            }
            
            return achieveBalance(parent, node, 0, diff);
        }
    }
    
    @Override
    public void remove(int v) {
        remove(null, root, v);
    }
    
    // node から v を取り除き、変更された高さを返す。
    private int remove(AVLTreeNode parent, AVLTreeNode node, int v) {
        if (node == null)
            return 0;
        
        if (v < node.value) { // 左から取り除く場合
            // 左からデータを取り除き、バランスを取る。
            int diff = remove(node, node.left, v);
            return achieveBalance(parent, node, diff, 0);
        } else if (node.value < v) {
            // 右の場合も左と同様。
            int diff = remove(node, node.right, v);
            return achieveBalance(parent, node, 0, diff);
        } else {
            assert v == node.value;
            --size;

            if (node.left != null) {
                int diff = findMaxAndRemove(node, node.left, node);
                return achieveBalance(parent, node, diff, 0);
            } else if (node.right != null) {
                int diff = findMinAndRemove(node, node.right, node);
                return achieveBalance(parent, node, 0, diff);
            } else {                
                replace(parent, node, null);
                return -1;
            }
        }
    }
    
    // node から最大の要素を取り除き、高さの変化を返す。
    private int findMaxAndRemove(AVLTreeNode parent, AVLTreeNode node, AVLTreeNode nodeReplace) {
        assert parent != null;
        
        if (node.right != null) {
            int diff = findMaxAndRemove(node, node.right, nodeReplace);
            return achieveBalance(parent, node, 0, diff);            
        } else {
            if (nodeReplace != null)
                nodeReplace.value = node.value;
            
            replace(parent, node, node.left);         
            return -1;
        }
    }

    // node から最小の要素を取り除き、高さの変化を返す。
    private int findMinAndRemove(AVLTreeNode parent, AVLTreeNode node, AVLTreeNode nodeReplace) {
        assert parent != null;

        if (node.left != null) {
            int diff = findMinAndRemove(node, node.left, nodeReplace);
            return achieveBalance(parent, node, diff, 0);
        } else {           
            if (nodeReplace != null)
                nodeReplace.value = node.value;
            
            replace(parent, node, node.right);
            return -1;
        }
    }

    // node を中心に回転させる。高さの変化を返す。
    private int achieveBalance(AVLTreeNode parent, AVLTreeNode node, int leftHeightDiff, int rightHeightDiff) {
        assert (-2 <= node.balance() && node.balance() <= 2);
        
        if (leftHeightDiff == 0 && rightHeightDiff == 0)
            return 0;
        
        // leftHeightDiff か rightHeightDiff のどちらかのみが 0 でない。
        assert ((leftHeightDiff != 0) ^ (rightHeightDiff != 0));
        
        int heightDiff = 0;
        if ((leftHeightDiff > 0 && node.balance() <= 0) || (rightHeightDiff > 0 && node.balance() >= 0))
            ++heightDiff;
        if ((leftHeightDiff < 0 && node.balance() < 0) || (rightHeightDiff < 0 && node.balance() > 0))
            --heightDiff;
        
        node.addBalance(-leftHeightDiff + rightHeightDiff);        
        if (node.balance() == -2) {
            if (node.left.balance() != 0)
                --heightDiff;
            
            if (node.left.balance() == 1)
                replace(node, node.left, rotateLeft(node.left));            
            replace(parent, node, rotateRight(node));
        } else if (node.balance() == 2) {
            if (node.right.balance() != 0)
                --heightDiff;
            
            if (node.right.balance() == -1)
                replace(node, node.right, rotateRight(node.right));
            replace(parent, node, rotateLeft(node));
        }
        
        return heightDiff;
    }
    
    @Override
    public boolean check() {
        return checkHeight(root) >= 0;
    }

    private int checkHeight(AVLTreeNode node) {
        if (node == null)
            return 0;
        
        if (node.balance() < -1 || 1 < node.balance())
            return -1;
        
        int leftHeight = checkHeight(node.left);
        if (leftHeight < 0)
            return -1;
        int rightHeight = checkHeight(node.right);
        if (rightHeight < 0)
            return -1;

        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // ----------------------------------------------------------------------

    private static AVLTreeNode rotateLeft(AVLTreeNode B) {
        AVLTreeNode D = B.right;

        int heightC = 0;
        int heightE = heightC + D.balance();
        int heightA = (Math.max(heightC, heightE) + 1) - B.balance();

        B.right = D.left;
        D.left = B;

        B.setBalance(heightC - heightA);
        D.setBalance(heightE - (Math.max(heightA, heightC) + 1));
        
        assert (-2 <= B.balance() && B.balance() <= 2);
        assert (-2 <= D.balance() && D.balance() <= 2);
        
        return D;
    }
    
    private static AVLTreeNode rotateRight(AVLTreeNode D) {
        AVLTreeNode B = D.left;
        
        int heightC = 0;
        int heightA = heightC - B.balance();
        int heightE = (Math.max(heightA, heightC) + 1) + D.balance();
        
        D.left = B.right;
        B.right = D;
        
        D.setBalance(heightE - heightC);
        B.setBalance((Math.max(heightC, heightE) + 1) - heightA);

        assert (-2 <= B.balance() && B.balance() <= 2);
        assert (-2 <= D.balance() && D.balance() <= 2);

        return B;
    }
}


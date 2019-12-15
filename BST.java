/******************************************************************************
 *  Compilation:  javac BST.java
 *  Execution:    java BST
 *  Dependencies: StdIn.java StdOut.java Queue.java
 *  Data files:   https://algs4.cs.princeton.edu/32bst/tinyST.txt  
 *
 *  A symbol table implemented with a binary search tree.
 * 
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *  
 *  % java BST < tinyST.txt
 *  A 8
 *  C 4
 *  E 12
 *  H 5
 *  L 11
 *  M 9
 *  P 10
 *  R 3
 *  S 0
 *  X 7
 *
 ******************************************************************************/

import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BST <Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int count;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        //concise but tricky, read carefully
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);

        if      (cmp > 0) x.right = put(x.right, key, val);
        else if (cmp < 0) x.left  = put(x.left, key, val);
        else              x.val = val;
        x.count = 1 + size(x.left) + size(x.right);

        return x;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.count;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp > 0) x = x.right;
            else if (cmp < 0) x = x.left;
            else              return x.val;
        }

        return null;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);

        if      (cmp == 0) return x;
        
        if      (cmp < 0)  return floor(x.right, key);

        Node t = floor(x.right, key);
        if (t != null) return t;
        else           return x;
    }

    public int rank(Key key) {  
        return rank(key, root);  
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);

        if      (cmp == 0) return size(x.left);
        else if (cmp > 0)  return 1 + size(x.left) + rank(key, x.right); //tricky
        else               return rank(key, x.left); 
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);

        if      (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);

        //if cmp == 0, find the smallest at the right Node, exch that with deleted key
        else {
            if (x.right == null) return x.left;
            if (x.left == null)  return x.right;

            Node t = x;
            x = min(x.right);
            x.right = deleteMin(x.right); //TODO: delete and return the min key in right side
            x.left = t.left;
        }
        x.count = 1 + size(x.right) + size(x.left);
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
                            return min(x.left);
    }

    public void deleteMin() {
        // if(isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys () {
        Queue<Key> q = new Queue<Key>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node x, Queue<Key> q) {
        if (x == null) return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }

    public static void main(String[] args) { 
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            if ((st.size() > 1) && (st.floor(key) != st.floor2(key)))
                throw new RuntimeException("floor() function inconsistent");
            st.put(key, i);
        }

        for (String s : st.levelOrder())
            StdOut.println(s + " " + st.get(s));

        StdOut.println();

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
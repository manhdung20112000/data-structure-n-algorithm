/******************************************************************************
 *  Compilation:  javac RedBlackBST.java
 *  Execution:    java RedBlackBST < input.txt
 *  Dependencies: StdIn.java StdOut.java  
 *  Data files:   https://algs4.cs.princeton.edu/33balanced/tinyST.txt  
 *    
 *  A symbol table implemented using a left-leaning red-black BST.
 *  This is the 2-3 version.
 *
 *  Note: commented out assertions because DrJava now enables assertions
 *        by default.
 *
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *  
 *  % java RedBlackBST < tinyST.txt
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

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;

public class RedBlackBST <Key extends Comparable<Key>, Value>{
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int count;
        private boolean color;

        public Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    /***************************************************************************
     * Red-Black BST insertion
     ***************************************************************************/

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK; //the root color is always BLACK, this code prevents miss understanding
    }

    private Node put(Node x, Key key, Value val) {
        if(x == null) return new Node(key, val, RED);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val = val;

        if (isRed(x.right) && !isRed(x.left))    x = rotateLeft(x);            //lean left
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);        //balance 4-node
        if (isRed(x.left) && isRed(x.right))         flipColor(x);             //split 4-node
        
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }
 
    /***************************************************************************
     * Red-Black BST deletion
     ***************************************************************************/

    public void delete(Key key) {
        //TODO: if both children of root are black, set root to red (?)
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
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
            x.right = deleteMin(x.right); //delete and return the min key in right side
            x.left = t.left;
        }
        x.count = 1 + size(x.right) + size(x.left);
        return balance(x);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    /***************************************************************************
    *  Standard BST search.
    ***************************************************************************/


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

    /***************************************************************************
    *  Red-black tree helper functions.
    ***************************************************************************/

    private boolean isRed(Node x) {
        if (x == null) return BLACK;    //null link are black
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        assert isRed(h.right);
        Node x = h.right;        
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x; //this is set h = h.right
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColor(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    
    public boolean isEmpty() {
        return root == null;
    }

    private Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    private Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls nax() with empty symbol table");
        return max(root).key;
    }
    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    private Node balance(Node x) {
        if (isRed(x.right) && !isRed(x.left))    x = rotateLeft(x);            //lean left
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);        //balance 4-node
        if (isRed(x.left) && isRed(x.right))     flipColor(x);             //split 4-node

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

    
    /***************************************************************************
    *  Range count and range search.
    ***************************************************************************/
    
    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<Key>();
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue queue, Key lo, Key hi) {
        //TODO: (?)
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys(x.left, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
        if (cmphi > 0) keys(x.right, queue, lo, hi); 
    }


    public static void main(String[] args) { 
        RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();
    }
}
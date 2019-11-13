import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BST <Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(Key key, Value value){
        root = put(root, key, value);
    }

    public Node put(Node x, Key key, Value value){
        if (x == null) return new Node(key, value);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) put(x.left, key, value);
        else if (cmp > 0) put(x.right, key, value);
        else    x.val = value;
        return x;
    }

    public Value get(Key key){
        Node x = root; //what is the root? the top of the tree
        while (x != null){
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else    return x.val;
        }
        return null;
    }

    public void delete(Key key){

    }

    public Iterable<Key> iterator(){

    }

    public static void main(String[] args) {
        ST<String, Integer> st = new ST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}

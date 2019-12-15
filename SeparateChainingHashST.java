/******************************************************************************
 *  Compilation:  javac SeparateChainingHashST.java
 *  Execution:    java SeparateChainingHashST < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/34hash/tinyST.txt
 *
 *  A symbol table implemented with a separate-chaining hash table.
 * 
 ******************************************************************************/

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SeparateChainingHashST <Key, Value> {
    private int M = 97;
    private Node[] st = new Node[M];

    private static class Node {
        private Object key;             // <- no generic array creation!
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public void put(Key key, Value val) {
        int i = hash(key);
        for (Node index = st[i]; index != null; index = index.next){
            if(key.equals(index.key)) {index.val = val; return;}
        }
        st[i] = new Node(key, val, st[i]);
    }

    public Value get(Key key) {
        int i = hash(key);
        for (Node index = st[i]; index != null; index = index.next){
            if(key.equals(index.key)) return (Value) index.val;             //urly cast?
        }
        return null;
    }

    /************************************************************************
     * Hash function
     ************************************************************************/

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Iterable<Key> keys () {
        Queue<Key> queue = new Queue<Key> ();
        for (int i=0; i<M; i++) {
            if (st[i] != null) {
                for (Node index = st[i]; index != null; index = index.next) queue.enqueue((Key) index.key);
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print keys
        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s)); 
    }
}
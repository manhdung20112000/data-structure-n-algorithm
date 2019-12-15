/******************************************************************************
 *  Compilation:  javac LinearProbingHashST.java
 *  Execution:    java LinearProbingHashST < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/34hash/tinyST.txt
 *  
 *  Symbol-table implementation with linear-probing hash table.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinearProbingHashST <Key, Value> {
    private int M = 300001;
    private Value[] vals = (Value[]) new Object[M];
    private Key[] keys = (Key[]) new Object[M];

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value value) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M){
            if (keys[i].equals(key))                                    //Note!
                break;
        }
        keys[i] = key;
        vals[i] = value;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (key.equals(keys[i]))                                    //Note!
                return vals[i];
        }
        return null;
    }

    public Iterable<Key> keys () {
        Queue<Key> queue = new Queue<Key> ();
        for (int i=0; i<M; i++) {
            if (keys[i] != null) queue.enqueue(keys[i]);
        }
        return queue;
    }

    public static void main(String[] args) { 
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print keys
        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s)); 
    }

}
/******************************************************************************
 *  Compilation:  javac MaxPQ.java
 *  Execution:    java MaxPQ < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/24pq/tinyPQ.txt
 *  
 *  Generic max priority queue implementation with a binary heap.
 *  Can be used with a comparator instead of the natural order,
 *  but the generic Key type must still be Comparable.
 *
 *  % java PriorityQueue < tinyPQ.txt 
 *  Q X P (6 left on pq)
 *
 *  We use a one-based array to simplify parent and child calculations.
 *
 *  Can be optimized by replacing full exchanges with half exchanges
 *  (ala insertion sort).
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;

public class PriorityQueue <Key extends Comparable<Key>> {
    private Key[] PQ;
    private int N = 0;

    public PriorityQueue (int capacity) {
        PQ = (Key[]) new Comparable[capacity];
    }

    public void insert (Key v) {
        if (N == PQ.length - 1) resize (PQ.length*2);
        PQ[++N] = v;
        swim(N);
    }

    public void resize (int capacity) {
        Key[] copy = (Key[]) new Comparable[capacity];
        for (int i=1; i<=N; i++){
            copy[i] = PQ[i];
        }
        PQ = copy;
    }

    public Key delMax () {
        if (isEmpty()) throw new NoSuchElementException("Priority Queue underflow!");
        Key max = PQ[1];
        exch(1, N--);
        sink(1);
        PQ[N+1] = null;                         //prevent loitering
        if (N>0 && N==(PQ.length - 1) / 4) resize (PQ.length/2);
        return max;
    }

    private void swim (int k) {
        while (k>1 && less(k/2, k)) {           //k/2 is the k's root
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink (int k) {
        while (2*k<=N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public int size () {
        return N;
    }

    public boolean isEmpty (){
        return N==0;
    }

    public Key max () {
        return PQ[1];
    }

    private boolean less(int a, int b) {
        return PQ[a].compareTo(PQ[b]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = PQ[i];
        PQ[i] = PQ[j];
        PQ[j] = temp;
    }

    //test Clients
    public static void main(String[] args) {
        PriorityQueue<String> pq = new PriorityQueue(10);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else StdOut.print(pq.delMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }
}
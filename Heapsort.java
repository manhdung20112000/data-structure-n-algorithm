/******************************************************************************
 *  Compilation:  javac Heapsort.java
 *  Execution:    java Heapsort < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/24pq/tiny.txt
 *                https://algs4.cs.princeton.edu/24pq/words3.txt
 *  
 *  Sorts a sequence of strings from standard input using heapsort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Heapsort < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Heapsort < words3.txt
 *  all bad bed bug dad ... yes yet zoo   [ one string per line ]
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Heapsort {
    public static void sort (Comparable[] a) {
        int N = a.length;

        for (int k = N/2; k>=1; k--){
            sink(a, k, N);              //the first time insert
        }

        while (N>1){                    //the next time
            exch(a, 1, N);
            sink(a, 1, --N);
        }
    }

    private static void sink (Comparable[] a, int k, int N) {
        while (2*k<=N) {
            int j = 2*k;
            if (j < N && less(a, j, j+1)) j++;
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    /**
     * Carefull at the index! //a[i-1] //we must use from 1 to N, but in the array, data store in index from 0 to N-1
     */

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = temp;
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i-1].compareTo(a[j-1]) < 0;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Heapsort.sort(a);
        show(a);
    }
}
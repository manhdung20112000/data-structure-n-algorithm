import edu.princeton.cs.algs4.*;

public class HeapSort {

    public static void sort(Comparable[] pq){
        int n = pq.length;
        for (int k=n/2; k>=1; k--) sink(pq, k);
        while (n>=1){
            exch(pq, 1, n--);
            sink(pq, 1);
        }
    }

   /***************************************************************************
    * Helper functions for comparisons and swaps.
    * Indices are "off-by-one" to support 1-based indexing.
    ***************************************************************************/
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }
    private static void swim(Comparable[] pq, int k){
        while (k>1 && less(pq, k/2, k)){
            exch(pq, k/2, k);
            k = k/2;
        }
    }

    private static void sink(Comparable[] pq, int k){
        int N = pq.length;
        while (2*k <= N){
            int j = 2*k;
            if (j<N && less(pq, j, j+1)) j++;
            if(!less(pq, k, j)) break;
            exch(pq, k, j);
        }
    }
    
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Heap.sort(a);
        for (String str:a){
            StdOut.print(str + ' ');
        }
    }
}
/******************************************************************************
 *  Compilation:  javac Shellsort.java
 *  Execution:    java Shellsort < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/21elementary/tiny.txt
 *                https://algs4.cs.princeton.edu/21elementary/words3.txt
 *  Example:      https://www.coursera.org/learn/algorithms-part1/supplement/erHuw/lecture-slides 
 *  (page 40)
 *   
 *  Sorts a sequence of strings from standard input using shellsort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Shellsort < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *    
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *  
 *  % java Shellsort < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Shellsort {
    public static void sort(Comparable[] a) {
        int n = a.length;

        int h = 1;
        while (h < n/3) h = 3*h + 1; //1, 4, 13, 40, 121, 364, ...
        
        while (h>=1){
            for (int i=h; i<n; i++){
                for (int j=i; j>=h; j-=h){
                    if(less(a[j], a[j-h])) exch(a, j, j-h);
                    else break; //the key to reduce the running time
                }
            }
            h/=3;
        }
        
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Shellsort.sort(a);
        show(a);
    }
}
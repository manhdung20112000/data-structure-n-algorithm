/*
Test case:
=============
    6
    to
    be
    or
    not
    to
    be
=============
Result:
=============
    be
    be
    not
    or
    to
    to
=============
*/

import java.util.Scanner;
import edu.princeton.cs.algs4.StdRandom;

public class Quicksort {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j + 1, hi);

        //Solve the duplicate items
        /**
            int lt = lo, gt = hi;
            Comparable v = a[lo];
            int i = lo;
            while(i<=gt){
                if      ( less(a[i], v))    exch (a, lt++, i++);
                else if (!less(a[i], v))    exch (a, gt--, i);
                else                        i++;
            }
            sort(a, lo, lt - 1);
            sort(a, gt + 1, hi);
         */

        assert isSorted(a, lo, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo])) { // find item on the left (not greater) to swap
                if (i == hi)
                    break;
            }

            while (less(a[lo], a[--j])) { // find item on the right (not less) to swap
                if (j == lo)
                    break;
            }

            if (i >= j)
                break; // check if pointers cross
            exch(a, i, j); // swap
        }

        exch(a, lo, j); // must be j
        return j;
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
        if (a==b) return false;
        return a.compareTo(b) < 0;
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String s[] = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = sc.next();
        }

        Quicksort.sort(s);

        for (int i = 0; i < n; i++) {
            System.out.println(s[i]);
        }
        sc.close();

        // String[] a = StdIn.readAllStrings();
        // Selection.sort(a);
        // show(a);
    }
}
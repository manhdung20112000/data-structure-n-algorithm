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

public class Mergesort {
    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if(hi<=lo) return; //break recursion
        int mid = lo + (hi-lo)/2; //why don't we just "(lo + hi)/2" (?)
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        if(less(a[mid], a[mid+1])) return; // tricky code, reduce the time in some cases
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        assert isSorted(a, lo, mid);  // precondition: a[lo..mid]   sorted
        assert isSorted(a, mid+1, hi);  // precondition: a[mid+1..hi]   sorted

        //Copy data from a to aux
        for (int k=lo; k<=hi; k++){
            aux[k] = a[k];
        }

        //merge, copy back from aux to a
        int i = lo, j = mid+1;
        for (int k = lo; k<=hi; k++){
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else                           a[k] = aux[j++];
        }

        //precondition: a[lo..hi] sorted
        assert isSorted(a, lo, hi);

    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

    public static boolean isSorted(Comparable[] a){
        return isSorted(a, 0, a.length-1);
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        String s[] = new String[n];
        for (int i=0; i<n; i++){
            s[i] = sc.next();
        }

        Mergesort.sort(s);

        for (int i=0; i<n; i++){
            System.out.println(s[i]);
        }
        sc.close();

        // String[] a = StdIn.readAllStrings();
        // Selection.sort(a);
        // show(a);
    }
}
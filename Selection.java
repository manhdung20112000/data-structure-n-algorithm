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
//import edu.princeton.cs.algs4.StdIn;

public class Selection {
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++)
                if (less(a[j], a[min]))
                    min = j;
            exch(a, i, min);
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        String s[] = new String[n];
        for (int i=0; i<n; i++){
            s[i] = sc.next();
        }

        Selection.sort(s);

        for (int i=0; i<n; i++){
            System.out.println(s[i]);
        }
        sc.close();

        // String[] a = StdIn.readAllStrings();
        // Selection.sort(a);
        // show(a);
    }
}
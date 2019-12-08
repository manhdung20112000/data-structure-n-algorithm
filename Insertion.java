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

public class Insertion {
    public static void sort (Comparable[] a){
        int n = a.length;
        for (int i=0; i<n; i++){
            for (int j=i; j>0; j--){
                if(less(a[j], a[j-1])) exch(a, j, j-1);
                else break; //the key to reduce the running time
            }
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

        Insertion.sort(s);

        for (int i=0; i<n; i++){
            System.out.println(s[i]);
        }
        sc.close();
    }
}
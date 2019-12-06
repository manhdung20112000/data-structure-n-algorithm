import java.util.Scanner;


public class Selection {
    public static void sort (Comparable[] a){
        int n = a.length;
        for (int i=0; i<n; i++){
            int min = i;
            for (int j=i; j<n; j++){
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }

    private static void exch(Comparable[]a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public static boolean isSorted(Comparable[]a){
        for (int i=0; i<a.length-1; i++){
            if (less(a[i], a[i+1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Date[] str = new Date[n];

        for (int i=0; i<n; i++){
            int d = sc.nextInt();
            int m = sc.nextInt();
            int y = sc.nextInt();
            str[i] = new Date(); //java.lang.NullPointerException
            str[i].data(d, m, y);
        }

        sort(str);

        for (int i = 0; i < str.length; i++) str[i].print();
        sc.close();
    }
}


import java.util.Scanner;

public class QuickMedian {

    private class Array {
        private int[] pq;
        private int N;

        public class Array (int size){
            pq = new int[size];
            N = 0;
        }
    }

    public QuickMedian(int capacity){
        Array left = new Array(capacity/2);
        Array right = new Array(capacity/2);
    }

    public void insert(int s) {
        if (size == 1 || size == 0) //to be continues
        else if (s>=getMedian(right, left)){
            right[++N_right] = s;
            swim(right, N_right);
        }
        else {left[++N_left] = s; swim(left, N_left);}
    } 

    private void exch(int[] pq, int a, int b){
        int temp = pq[a];
        pq[a] = pq[b];
        pq[b] = temp;
    }

    private boolean less(int[] pq, int a, int b){
        return pq[a].compareTo(pq[b]) < 0;
    }

    private void swim(int[] pq, int k){
        while (k>1 && less(pq, k/2, k)){
            exch (pq, k, k/2);
            k = k/2;
        }

    }

    private void sink(int[] pq, int k){
        while (2*k<=N){
            int j = 2*k;
            if (j< N && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    public int delMedian(int[] pq){
        int max = pq[1];
        exch(pq, 1, N--); //Exchange first, then N-=1 that mean del the pq[N], N = N-1 now
        sink(pq, 1);
        pq[N+1] = null; //prevent loitering?
        return max;
    }

    public int getMedian(int[] right, int[] left){
        int median;

        return median;
    }

    public boolean isEmpty(int[] pq){
        return pq.length == 0;
    }

    public static void main(String[] args) {
        Scanner sc = sc.nextInt();

    }
}
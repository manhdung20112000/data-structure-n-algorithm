public class HeapSort {
    private Heap(){}

    public static void sort(String[] pq){

    }

    private void insert(String[] pq, String s){
        pq[++N] = s;
        swim(N);
    }

    private void exch(String[] pq, int a, int b){
        String temp = pq[a];
        pq[a] = pq[b];
        pq[b] = temp;
    }

    private boolean less(String[] pq, int a, int b){
        return pq[a].compareTo(pq[b]) < 0;
    }

    private void swim(String[] pq, int k){
        while (k>1 && less(k/2, k)){
            exch(pq, k/2, k);
            k = k/2;
        }
    }

    private void sink(String[] pq, int k){
        while (){
            int j = 2*k;
            if (j<=N && less(j, j+1)) j++;
            if(!less(k, ))
        }
    }
}
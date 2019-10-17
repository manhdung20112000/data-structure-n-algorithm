public class PriorityQueue {
    private String[] pq;
    private int N;

    public PriorityQueue(int capacity){
        pq = new String [capacity];
        N = 0;
    }

    public void insert(String s) {
        pq[++N] = s;
        swim(N);
    } 

    private void exch(int a, int b){
        String temp = pq[a];
        pq[a] = pq[b];
        pq[b] = temp;
    }

    private boolean less(int a, int b){
        return pq[a].compareTo(pq[b]) < 0;
    }

    private void swim(int k){
        while (k>1 && less(k/2, k)){
            exch (k, k/2);
            k = k/2;
        }

    }

    private void sink(int k){
        while (2*k<=N){
            int j = 2*k;
            if (j< N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public String delMax(){
        String max = pq[1];
        exch(1, N--); //Exchange first, then N-=1 that mean del the pq[N], N = N-1 now
        sink(1);
        pq[N+1] = null; //prevent loitering?
        return max;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue (10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty()) 
            System.out.println(pq.delMax());
    }

}
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

    private void exch(String a, String b){
        String temp = a;
        a = b;
        b = temp;
    }

    private boolean less(String a, String b){
        return a.compareTo(b) < 0;
    }

    private void swim(int k){
        while (k>1 && less(pq[k], pq[k/2]){
            exch (pq[k], pq[k/2]);
            k = k/2;
        }

    }

    private void sink(int k){
        while (2*k<=N){
            int j = 2*k;
            if (less(pq[j], pq[j+1])) j++;
            if (!less(pq[k], pq[j])) break;
            exch(a, b);
            
        }
    }

    public String delMax(){
        String max = pq[1];
        exch(pq[1], pq[N--]); //Exchange first, then N-=1 that mean del the pq[N], N = N-1 now
        sink(1);
        pq[N+1] = null; //prevent loitering?
        return max;
    }

    public boolean isEmpty(){
        return length == 0;
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
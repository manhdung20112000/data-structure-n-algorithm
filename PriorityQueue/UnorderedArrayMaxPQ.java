/*********************************************
*  Unordered priority queue implementation   *
**********************************************/

public class UnorderedArrayMaxPQ {
    private String[] pq;      // elements
    private int n;         // number of elements

    // set inititial size of heap to hold size elements
    public UnorderedArrayMaxPQ(int capacity) {
        pq = new String[capacity];
        n = 0;
    }

    public boolean isEmpty()   { return n == 0; }

    public int size()          { return n;      }

    public void insert(String x)  { pq[n++] = x;   }

    public String delMax() {
        int max = 0;
        for (int i = 1; i < n; i++)
            if (less(max, i)) max = i;
        exch(max, n-1);

        return pq[--n];
    }


   /***************************************************************************
    * Helper functions.
    ***************************************************************************/
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        String swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }


   /***************************************************************************
    * Test routine.
    ***************************************************************************/
    public static void main(String[] args) {
        UnorderedArrayMaxPQ pq = new UnorderedArrayMaxPQ (10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty()) 
            System.out.println(pq.delMax());
    }

}
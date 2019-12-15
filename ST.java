/**
 * Symbol Table, API
 */

public class ST <Key extends Comparable<Key>, Value>{
    private int N = 0;
    private Key[] keys;
    private Value[] values;

    public ST(){
        keys = (Key[]) new Comparable[2];
        values = (Value[]) new Comparable[2];
    }

    public void push(Key x) {

    }

    public Value get(Key x) {
        if (isEmpty()) return null;
        int i = rank(x);
        if (i<N && keys[i].compareTo(key) == 0) return values[i];   //double check
        return null;
    }

    private int rank(Key x) {
        int lo = 0, hi = N-1;

        while (lo<=hi){
            int mid = lo + (hi - lo)/2;
            int cmp = x.compareTo(keys[mid]);
            if      (cmp > 0) lo = mid+1;
            else if (cmp < 0) hi = mid+1;
            else              return mid;
        }

        return lo;
    }

    private void resize(int n){
        Item[] copy = (Item[]) new Object[n];
        for (int i=0; i<N; i++){
            copy[i] = items[i];
        }
        items = copy;
    }

    public void delete(Key x) {

    }

    public boolean contains(Key x) {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }
}
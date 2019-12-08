/**
 * Implement with resizing array
 */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayStack<Item>{
    private int N = 0;
    private Item[]items;

    public ResizingArrayStack(int capacity){
        items = (Item[]) new Object[capacity]; //urly cast? 
    }

    public boolean isEmpty(){
        return N==0;
    }

    public void push(Item item){
        if (N == items.length) resize(items.length*2);
        items[N++] = item; //use the value of index N, then increment N to N+1
    }

    public Item pop(){
        //return items[--N]; this is loitering, garbage colletor can't reclaim memory 'cause still have outstanding references
        Item old = items[--N];
        items[N] = null;
        if(N == items.length/4 && N>0) resize(items.length/2);
        return old;
    }

    public Item peek(){
        return items[N-1];
    }

    private void resize(int n){
        Item[] copy = (Item[]) new Object[n];
        for (int i=0; i<N; i++){
            copy[i] = items[i];
        }
        items = copy;
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack(8);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop() + ' ');
            else
                stack.push(s);
        }
    }
}
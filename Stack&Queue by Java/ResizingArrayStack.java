import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class ResizingArrayStack <Item> {
    private Item[] arr;
    private int N = 0;

    public void FixCapacityOfStack(int capacity){
        arr = new Item[capacity]; 
    }

    public boolean isEmpty(){
        return arr.length==0;
    }

    public void push(Item item){
        if (N == arr.length) resize(arr.length*2);
        arr[N++] = item;
    }

    public Item pop(){
        /*  loitering: Hoding a reference to an obj when it's no longer needed */
        Item item =  arr[--N];
        arr[N] = null;
        if(N == arr.length/4 && N>0) resize(N/2);
        return item;
    }

    private void resize(int n){
        Item[] copy = new Item[n];
        for (int i=0; i<arr.length; i++){
            copy[i] = arr[i];
        }

        arr = copy;
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop());
            else
                stack.push(s);
        }
    }
}
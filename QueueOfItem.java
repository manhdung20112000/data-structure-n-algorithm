import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class QueueOfItem <Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node{
        Node next = null;
        Item item;
    }
    
    public boolean isEmpty(){
        return first == null;
    }

    public void enqueue(Item item){
        Node oldTail = last;
        last = new Node();
        last.item = item;
        if(isEmpty()) {first = last; return;}
        oldTail.next = last;
    }

    public Item dequeue(){
        Node oldHead = first;
        first = first.next;
        if(isEmpty()) last = null;
        return oldHead.item;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        QueueOfItem<String> queue = new QueueOfItem<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(queue.dequeue() + ' ');
            else
                queue.enqueue(s);
        }
    }
}
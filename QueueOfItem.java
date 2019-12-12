/******************************************************************************
 *  Compilation:  javac Queue.java
 *  Execution:    java Queue < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/13stacks/tobe.txt  
 *
 *  A generic queue, implemented using a linked list.
 *
 *  % java Queue < tobe.txt 
 *  to be or not to be (2 left on queue)
 *
 ******************************************************************************/

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
        size ++;
        last = new Node();
        last.item = item;
        if(isEmpty()) {first = last; return;}
        oldTail.next = last;
    }

    public Item dequeue(){
        Node oldHead = first;
        first = first.next;
        size--;
        if(isEmpty()) last = null;
        return oldHead.item;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        QueueOfItem<String> queue = new QueueOfItem<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
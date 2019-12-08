/**
 * Implement with linked list
 */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class Stack <Item> {
    private Node first;
    private int size = 0;
    
    private class Node{
        Item data;
        Node next = null;
    }

    public void push(Item data){
        Node newNode = new Node();
        if(!isEmpty()) newNode.next = first;
        first = newNode;
        size++;
    }

    public Item pop(){
        Node oldFirst = first;
        first = first.next;
        size--;
        return oldFirst.data;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public Item peek(){
        return first.data;
    }

    public static void main(String[] args) {
        Stack<String>stack = new Stack();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop() + ' ');
            else
                stack.push(s);
        }
    }
}
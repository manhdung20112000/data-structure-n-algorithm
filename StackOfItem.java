import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackOfItem<Item> {
    private Node first = null;
    private int size = 0;

    private class Node {
        Item item;
        Node nextNode;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.nextNode = oldFirst;
        size++;
    }

    public Item pop() {
        Node oldFirst = first;
        first = first.nextNode;
        size--;
        return oldFirst.item;
    }

    public Item peek() {
        return first.item;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        StackOfItem<String> stack = new StackOfItem<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop() + ' ');
            else
                stack.push(s);
        }
    }
}
import java.util.Iterator;
import edu;

public class Stack<Item> implements Iterable<Item> {
    public Iterator <Item> iterator() {
        return new ListIterator();
        
    }
}
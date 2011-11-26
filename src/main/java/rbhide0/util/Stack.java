package rbhide0.util;

import java.util.LinkedList;
import java.util.List;

/**
 * Java.util's Stack implementation is based on Vector
 * whose methods are all synchronized. Our implementation's
 * underlying data structure is non-synchronized.
 *
 * @param <T> Element type to be stored in the stack.
 */
public class Stack<T> {
    private List<T> _list;

    public Stack() {
        _list = new LinkedList<T>();
    }

    public void push(T element) {
        _list.add(element);
    }

    public T pop() {
        return _list.remove(_list.size() - 1);
    }

    public T peek() {
        return _list.get(_list.size() - 1);
    }

    public int size() {
        return _list.size();
    }
}

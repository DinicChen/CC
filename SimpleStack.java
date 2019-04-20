import java.util.Deque;
import java.util.LinkedList;

class SimpleStack<E> {
    private final Deque<E> deque = new LinkedList<E>();

    public E pop() {
        return deque.pop();
    }

    public E peek() {
        return deque.peek();
    }

    public void push(E e) {
        deque.push(e);
    }

    public int size() {
        return deque.size();
    }

    public boolean isEmpty() {
        return deque.size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("top[");

        for(E e : deque)
            sb.append(e.toString() + ".");

        sb.append("]bottom");

        return sb.toString();
    }
}

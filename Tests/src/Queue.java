import java.util.Arrays;
import java.util.Objects;

public class Queue<T> {

    private T[] elements;
    private int first;
    private int last;
    private final int CAPACITY = 10;
    private final int COEFFICIENT = 2;

    public Queue() {
        elements = (T[]) new Object[this.CAPACITY];
        first = -1;
        last = -1;
    }

    public Queue(T[] array) throws NullPointerException {
        if (array != null) {
            first = 0;
            last = array.length - 1;
            elements = (T[]) new Object[array.length];
            System.arraycopy(array, 0, this.elements, 0, array.length);
        }
        else {
            throw new NullPointerException("Exception: argument points to null");
        }
    }

    public Queue(Queue<T> queue) throws NullPointerException {
        if (queue != null) {
            first = 0;
            last = queue.last - queue.first;
            elements = (T[]) new Object[last + 1];
            System.arraycopy(queue.elements, queue.first, this.elements, 0, elements.length);
        }
        else {
            throw new NullPointerException("Exception: argument points to null");
        }
    }

    public void add(T element) {
        last++;
        if (last >= elements.length) {
            if (first != 0) {
                if (last - first >= 0) {
                    System.arraycopy(elements, first, elements, 0, last - first);
                }
                last = last - first;
                first = 0;
                elements[last] = element;
            }
            else {
                T[] newElements = (T[]) new Object[elements.length * COEFFICIENT];
                System.arraycopy(elements, 0, newElements, 0, last);
                newElements[last] = element;
                elements = newElements;
            }
        }
        else {
            if (first == -1) {
                first = 0;
            }
            elements[last] = element;
        }
    }

    public T pop() {
        if (last == -1) {
            return null;
        }
        int index = first;
        first++;
        if (first > last) {
            first = -1;
            last = -1;
        }
        return elements[index];
    }

    public T peek() {
        if (last == -1) {
            return null;
        }
        return elements[first];
    }

    public int size() {
        if (last == -1) {
            return 0;
        }
        return last - first + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queue<?> queue = (Queue<?>) o;
        if (last - first != queue.last - queue.first) {
            return false;
        }
        if (last == -1 && queue.last == -1) {
            return true;
        }
        int j = queue.first;
        for (int i = first; i <= last; i++) {
            if (!elements[i].equals(queue.elements[j])) {
                return false;
            }
            j++;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(first, last);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        if (last == -1) {
            str.append("}");
        }
        else {
            for (int i = first; i < last; i++) {
                str.append(elements[i].toString()).append(", ");
            }
            str.append(elements[last].toString()).append("}");
        }
        return new String(str);
    }

}
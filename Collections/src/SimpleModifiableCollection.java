import java.util.*;

public class SimpleModifiableCollection<T> extends AbstractCollection<T> {

    protected T[] data;
    protected int size;
    protected final int COEFFICIENT = 2;

    public SimpleModifiableCollection() {
        data = (T[]) new Object[0];
        size = 0;
    }

    public SimpleModifiableCollection(Collection<? extends T> collection) {
        if (collection != null) {
            this.size = collection.size();
            data = (T[]) new Object[size];
            int i = 0;
            Iterator<T> it = (Iterator<T>) collection.iterator();
            while (it.hasNext()) {
                data[i] = it.next();
                i++;
            }
        }
        else {
            throw new NullPointerException();
        }
    }

    @Override
    public boolean add(T t) throws NullPointerException {
        if (t != null) {
            size++;
            if (size > data.length) {
                T[] newData = (T[]) new Object[data.length * COEFFICIENT];
                System.arraycopy(data, 0, newData, 0, data.length);
                newData[size - 1] = t;
                data = newData;
            } else {
                data[size - 1] = t;
            }
            return true;
        }
        else {
            throw new NullPointerException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor;
            private boolean flag;
            public boolean hasNext() {
                return data.length > cursor;
            }
            public T next() throws NoSuchElementException {
                try {
                    T el = data[cursor];
                    cursor++;
                    flag = true;
                    return el;
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    throw new NoSuchElementException();
                }
            }
            @Override
            public void remove() throws IllegalStateException{
                if (flag) {
                    data[cursor - 1] = data[size - 1];
                    data[size - 1] = null;
                    size--;
                    cursor--;
                }
                else {
                    throw new IllegalStateException();
                }
                flag = false;
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleModifiableCollection<?> that = (SimpleModifiableCollection<?>) o;
        if (size == that.size) {
            boolean[] flags = new boolean[size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (data[i].equals(that.data[j]) && !flags[j]) {
                        flags[j] = true;
                        break;
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                if (!flags[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, COEFFICIENT);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}

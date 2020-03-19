import java.util.*;

public class SimpleUnmodifiableCollection<T> extends AbstractCollection<T> {

    protected T[] data;
    protected int size;

    private class SimpleIterator implements Iterator<T> {

        private int cursor;

        public boolean hasNext() {
            return data.length > cursor;
        }

        public T next() throws NoSuchElementException {
            try {
                T el = data[cursor];
                cursor++;
                return el;
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                throw new NoSuchElementException();
            }
        }
    }

    public SimpleUnmodifiableCollection() {
        data = (T[]) new Object[0];
        size = 0;
    }

    public SimpleUnmodifiableCollection(Collection<? extends T> collection) {
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
    public Iterator<T> iterator() {
        return new SimpleIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleUnmodifiableCollection<?> that = (SimpleUnmodifiableCollection<?>) o;
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
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}

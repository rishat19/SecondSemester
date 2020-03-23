import java.util.*;

public class MyMap<K, V> extends AbstractMap<K, V> {

    protected List<Entry<K, V>> entries;

    protected class MyEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;
        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public K getKey() {
            return key;
        }
        @Override
        public V getValue() {
            return value;
        }
        @Override
        public V setValue(V value) {
            V prevValue = this.value;
            this.value = value;
            return prevValue;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyEntry<?, ?> myEntry = (MyEntry<?, ?>) o;
            return Objects.equals(key, myEntry.key) &&
                    Objects.equals(value, myEntry.value);
        }
        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    public MyMap() {
        entries = new ArrayList<>();
    }

    public MyMap(Map<? extends K, ? extends V> map) {
        if (map != null) {
            entries = new ArrayList<>();
            map.forEach(this::put);
        }
        else {
            throw new NullPointerException();
        }
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    @Override
    public boolean containsValue(Object value) throws NullPointerException {
        if (value != null) {
            for (Entry<K, V> entry : entries) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
            return false;
        }
        else {
            throw new NullPointerException();
        }
    }

    @Override
    public boolean containsKey(Object key) throws NullPointerException {
        if (key != null) {
            for (Entry<K, V> entry : entries) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
            return false;
        }
        else {
            throw new NullPointerException();
        }
    }

    @Override
    public V get(Object key) throws NullPointerException {
        if (key != null) {
            for (Entry<K, V> entry : entries) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
            return null;
        }
        else {
            throw new NullPointerException();
        }
    }

    @Override
    public V put(K key, V value) throws NullPointerException {
        if (key != null && value != null) {
            for (Entry<K, V> entry : entries) {
                if (entry.getKey().equals(key)) {
                    V prevValue = entry.getValue();
                    entry.setValue(value);
                    return prevValue;
                }
            }
            entries.add(new MyEntry(key, value));
            return null;
        }
        else {
            throw new NullPointerException();
        }
    }

    @Override
    public V putIfAbsent(K key, V value) throws NullPointerException {
        if (key != null && value != null) {
            for (Entry<K, V> entry : entries) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
            entries.add(new MyEntry(key, value));
            return null;
        }
        else {
            throw new NullPointerException();
        }
    }

    @Override
    public V remove(Object key) throws NullPointerException {
        if (key != null) {
            Iterator<Entry<K, V>> i = entries.iterator();
            while (i.hasNext()) {
                Entry<K, V> entry = i.next();
                if (entry.getKey().equals(key)) {
                    V value = entry.getValue();
                    i.remove();
                    return value;
                }
            }
            return null;
        }
        else {
            throw new NullPointerException();
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) throws NullPointerException {
        if (map != null) {
            map.forEach(this::put);
        }
        else {
            throw new NullPointerException();
        }
    }

    @Override
    public void clear() {
        entries = new ArrayList<>();
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (Entry<K, V> entry : entries) {
            set.add(entry.getKey());
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> v = new HashSet<>();
        for (Entry<K, V> entry : entries) {
            v.add(entry.getValue());
        }
        return v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MyMap<?, ?> myMap = (MyMap<?, ?>) o;
        return Objects.equals(entries, myMap.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), entries);
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for (Entry<K, V> entry : entries) {
            str.append(entry.getKey()).append(": ").append(entry.getValue()).append('\n');
        }
        return new String(str);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>(entries);
    }
}

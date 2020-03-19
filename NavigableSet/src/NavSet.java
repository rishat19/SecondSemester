import java.util.*;

public class NavSet<T> extends AbstractSet<T> implements NavigableSet<T> {

    protected List<T> data;
    protected Comparator<? super T> comparator;
    protected boolean descendingSetFlag;

    /**
     * Constructs a new, empty set, sorted according to the
     * natural ordering of its elements.  All elements inserted into
     * the set must implement the {@link Comparable} interface.
     * Furthermore, all such elements must be <i>mutually
     * comparable</i>: {@code e1.compareTo(e2)} must not throw a
     * {@code ClassCastException} for any elements {@code e1} and
     * {@code e2} in the set.  If the user attempts to add an element
     * to the set that violates this constraint (for example, the user
     * attempts to add a string element to a set whose elements are
     * integers), the {@code add} call will throw a
     * {@code ClassCastException}.
     */
    public NavSet() {
        data = new ArrayList<>();
        comparator = null;
    }

    /**
     * Constructs a new empty set, sorted according to the specified
     * comparator.  All elements inserted into the set must be <i>mutually
     * comparable</i> by the specified comparator: {@code comparator.compare(e1,
     * e2)} must not throw a {@code ClassCastException} for any elements
     * {@code e1} and {@code e2} in the set.  If the user attempts to add
     * an element to the set that violates this constraint, the
     * {@code add} call will throw a {@code ClassCastException}.
     *
     * @param comparator the comparator that will be used to order this set.
     *                   If {@code null}, the {@linkplain Comparable natural
     *                   ordering} of the elements will be used.
     */
    public NavSet(Comparator<? super T> comparator) {
        data = new ArrayList<>();
        this.comparator = comparator;
    }

    /**
     * Constructs a new set containing the elements in the specified
     * collection, sorted according to the <i>natural ordering</i> of its
     * elements.  All elements inserted into the set must implement the
     * {@link Comparable} interface.  Furthermore, all such elements must be
     * <i>mutually comparable</i>: {@code e1.compareTo(e2)} must not throw a
     * {@code ClassCastException} for any elements {@code e1} and
     * {@code e2} in the set.
     *
     * @param collection collection whose elements will comprise the new set
     * @throws ClassCastException   if the elements in {@code c} are
     *                              not {@link Comparable}, or are not mutually comparable
     * @throws NullPointerException if the specified collection is null
     */
    public NavSet(Collection<? extends T> collection) {
        if (collection != null) {
            data = new ArrayList<>();
            this.comparator = null;
            this.addAll(collection);
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Constructs a new set containing the elements in the
     * specified collection, sorted according to the specified
     * comparator.  All elements inserted into the set must be <i>mutually
     * comparable</i> by the specified comparator: {@code comparator.compare(e1,
     * e2)} must not throw a {@code ClassCastException} for any elements
     * {@code e1} and {@code e2} in the set.  If the user attempts to add
     * an element to the set that violates this constraint, the
     * {@code add} call will throw a {@code ClassCastException}.
     *
     * @param collection collection whose elements will comprise the new set
     * @param comparator the comparator that will be used to order this set.
     *                   If {@code null}, the {@linkplain Comparable natural
     *                   ordering} of the elements will be used.
     * @throws ClassCastException   if the elements in {@code c} are
     *                              not {@link Comparable}, or are not mutually comparable
     * @throws NullPointerException if the specified collection is null
     */
    public NavSet(Collection<? extends T> collection, Comparator<? super T> comparator) {
        if (collection != null) {
            data = new ArrayList<>();
            this.comparator = comparator;
            this.addAll(collection);
        }
        else {
            throw new NullPointerException();
        }
    }

    protected int compare(T e1, T e2) {
        int c;
        try {
            if (comparator != null) {
                c = comparator.compare(e1, e2);
            }
            else {
                Comparable c1 = (Comparable) e1;
                Comparable c2 = (Comparable) e2;
                c = c1.compareTo(c2);
                if (descendingSetFlag) {
                    c *= -1;
                }
            }
            return c;
        }
        catch (Exception e) {
            throw new ClassCastException();
        }
    }

    /**
     * Returns an iterator over the elements contained in this collection.
     *
     * @return an iterator over the elements contained in this collection
     */
    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }

    /**
     * Returns the number of elements in this set (its cardinality).
     *
     * @return the number of elements in this set (its cardinality)
     */
    @Override
    public int size() {
        return data.size();
    }

    /**
     * Returns the greatest element in this set strictly less than the
     * given element, or {@code null} if there is no such element.
     *
     * @param t the value to match
     * @return the greatest element less than {@code e},
     * or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    @Override
    public T lower(T t) throws ClassCastException, NullPointerException {
        if (t != null) {
            T element = null;
            for (T nextElement : data) {
                if (compare(nextElement, t) < 0) {
                    element = nextElement;
                }
                else {
                    break;
                }
            }
            return element;
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Returns the greatest element in this set less than or equal to
     * the given element, or {@code null} if there is no such element.
     *
     * @param t the value to match
     * @return the greatest element less than or equal to {@code e},
     * or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    @Override
    public T floor(T t) throws ClassCastException, NullPointerException {
        if (t != null) {
            T element = null;
            for (T nextElement : data) {
                if (compare(nextElement, t) <= 0) {
                    element = nextElement;
                }
                else {
                    break;
                }
            }
            return element;
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Returns the least element in this set greater than or equal to
     * the given element, or {@code null} if there is no such element.
     *
     * @param t the value to match
     * @return the least element greater than or equal to {@code e},
     * or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    @Override
    public T ceiling(T t) throws ClassCastException, NullPointerException {
        if (t != null) {
            for (T nextElement : data) {
                if (compare(nextElement, t) >= 0) {
                    return nextElement;
                }
            }
            return null;
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Returns the least element in this set strictly greater than the
     * given element, or {@code null} if there is no such element.
     *
     * @param t the value to match
     * @return the least element greater than {@code e},
     * or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    @Override
    public T higher(T t) throws ClassCastException, NullPointerException {
        if (t != null) {
            for (T nextElement : data) {
                if (compare(nextElement, t) > 0) {
                    return nextElement;
                }
            }
            return null;
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Retrieves and removes the first (lowest) element,
     * or returns {@code null} if this set is empty.
     *
     * @return the first element, or {@code null} if this set is empty
     */
    @Override
    public T pollFirst() {
        if (data.size() == 0) {
            return null;
        }
        else {
            Iterator<T> i = this.iterator();
            T element = i.next();
            i.remove();
            return element;
        }
    }

    /**
     * Retrieves and removes the last (highest) element,
     * or returns {@code null} if this set is empty.
     *
     * @return the last element, or {@code null} if this set is empty
     */
    @Override
    public T pollLast() {
        return data.remove(data.size() - 1);
    }

    /**
     * Returns a reverse order view of the elements contained in this set.
     * If either set is modified while an iteration over either set is in progress
     * (except through the iterator's own {@code remove} operation), the results of
     * the iteration are undefined.
     *
     * <p>The returned set has an ordering equivalent to
     * <tt>{@link Collections#reverseOrder(Comparator) Collections.reverseOrder}(comparator())</tt>.
     * The expression {@code s.descendingSet().descendingSet()} returns a
     * view of {@code s} essentially equivalent to {@code s}.
     *
     * @return a reverse order view of this set
     */
    @Override
    public NavigableSet<T> descendingSet() {
        NavSet<T> set = new NavSet<>();
        set.descendingSetFlag = true;
        set.addAll(data);
        return set;
    }

    /**
     * Returns an iterator over the elements in this set, in descending order.
     * Equivalent in effect to {@code descendingSet().iterator()}.
     *
     * @return an iterator over the elements in this set, in descending order
     */
    @Override
    public Iterator<T> descendingIterator() {
        return new NavSetDescendingIterator();
    }

    private class NavSetDescendingIterator implements Iterator<T> {
        private int cursor;
        private boolean flag;
        public NavSetDescendingIterator() {
            cursor = data.size() - 1;
        }
        @Override
        public boolean hasNext() {
            return cursor >= 0;
        }
        @Override
        public T next() throws NoSuchElementException {
            try {
                T element = data.get(cursor);
                cursor--;
                flag = true;
                return element;
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                throw new NoSuchElementException();
            }
        }
        @Override
        public void remove() throws IllegalStateException {
            if (flag) {
                data.remove(cursor + 1);
            }
            else {
                throw new IllegalStateException();
            }
            flag = false;
        }
    }

    /**
     * Returns a view of the portion of this set whose elements range from
     * {@code fromElement} to {@code toElement}.  If {@code fromElement} and
     * {@code toElement} are equal, the returned set is empty unless {@code
     * fromInclusive} and {@code toInclusive} are both true. The returned
     * set supports all optional set operations that this set supports.
     *
     * <p>The returned set will throw an {@code IllegalArgumentException}
     * on an attempt to insert an element outside its range.
     *
     * @param fromElement   low endpoint of the returned set
     * @param fromInclusive {@code true} if the low endpoint
     *                      is to be included in the returned view
     * @param toElement     high endpoint of the returned set
     * @param toInclusive   {@code true} if the high endpoint
     *                      is to be included in the returned view
     * @return a view of the portion of this set whose elements range from
     * {@code fromElement}, inclusive, to {@code toElement}, exclusive
     * @throws ClassCastException       if {@code fromElement} and
     *                                  {@code toElement} cannot be compared to one another using this
     *                                  set's comparator (or, if the set has no comparator, using
     *                                  natural ordering).  Implementations may, but are not required
     *                                  to, throw this exception if {@code fromElement} or
     *                                  {@code toElement} cannot be compared to elements currently in
     *                                  the set.
     * @throws NullPointerException     if {@code fromElement} or
     *                                  {@code toElement} is null and this set does
     *                                  not permit null elements
     * @throws IllegalArgumentException if {@code fromElement} is
     *                                  greater than {@code toElement};
     */
    @Override
    public NavigableSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive)
            throws ClassCastException, NullPointerException, IllegalArgumentException {
        if (fromElement != null && toElement != null) {
            if (this.compare(fromElement, toElement) <= 0) {
                T l = this.lower(fromElement);
                T h = this.higher(toElement);
                int fromIndex;
                int toIndex;
                if (data.size() > 0) {
                    if (fromInclusive || compare(fromElement, data.get(data.indexOf(l) + 1)) != 0) {
                        fromIndex = data.indexOf(l) + 1;
                    }
                    else {
                        fromIndex = data.indexOf(l) + 2;
                    }
                    if (toInclusive) {
                        if (h != null) {
                            toIndex = data.indexOf(h);
                        }
                        else {
                            toIndex = data.size();
                        }
                    }
                    else {
                        if (h != null) {
                            if (compare(toElement, data.get(data.indexOf(h) - 1)) != 0) {
                                toIndex = data.indexOf(h);
                            }
                            else {
                                toIndex = data.indexOf(h) - 1;
                            }
                        }
                        else {
                            if (compare(toElement, data.get(data.size() - 1)) != 0) {
                                toIndex = data.size();
                            }
                            else {
                                toIndex = data.size() - 1;
                            }
                        }
                    }
                    if (fromIndex > toIndex) {
                        fromIndex = toIndex;
                    }
                    return new NavSet<T>(data.subList(fromIndex, toIndex), comparator);
                }
                else {
                    return new NavSet<T>(comparator);
                }
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Returns a view of the portion of this set whose elements are less than
     * (or equal to, if {@code inclusive} is true) {@code toElement}. The returned
     * set supports all optional set operations that this set supports.
     *
     * <p>The returned set will throw an {@code IllegalArgumentException}
     * on an attempt to insert an element outside its range.
     *
     * @param toElement high endpoint of the returned set
     * @param inclusive {@code true} if the high endpoint
     *                  is to be included in the returned view
     * @return a view of the portion of this set whose elements are less than
     * (or equal to, if {@code inclusive} is true) {@code toElement}
     * @throws ClassCastException       if {@code toElement} is not compatible
     *                                  with this set's comparator (or, if the set has no comparator,
     *                                  if {@code toElement} does not implement {@link Comparable}).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code toElement} cannot be compared to elements
     *                                  currently in the set.
     * @throws NullPointerException     if {@code toElement} is null and
     *                                  this set does not permit null elements
     */
    @Override
    public NavigableSet<T> headSet(T toElement, boolean inclusive) throws ClassCastException, NullPointerException {
        if (toElement != null) {
            if (data.size() > 0) {
                return this.subSet(data.get(0), true, toElement, inclusive);
            }
            else {
                return new NavSet<T>(comparator);
            }
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Returns a view of the portion of this set whose elements are greater
     * than (or equal to, if {@code inclusive} is true) {@code fromElement}.
     * The returned set supports all optional set operations
     * that this set supports.
     *
     * <p>The returned set will throw an {@code IllegalArgumentException}
     * on an attempt to insert an element outside its range.
     *
     * @param fromElement low endpoint of the returned set
     * @param inclusive   {@code true} if the low endpoint
     *                    is to be included in the returned view
     * @return a view of the portion of this set whose elements are greater
     * than or equal to {@code fromElement}
     * @throws ClassCastException       if {@code fromElement} is not compatible
     *                                  with this set's comparator (or, if the set has no comparator,
     *                                  if {@code fromElement} does not implement {@link Comparable}).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code fromElement} cannot be compared to elements
     *                                  currently in the set.
     * @throws NullPointerException     if {@code fromElement} is null
     *                                  and this set does not permit null elements
     */
    @Override
    public NavigableSet<T> tailSet(T fromElement, boolean inclusive) throws ClassCastException, NullPointerException {
        if (fromElement != null) {
            if (data.size() > 0) {
                return this.subSet(fromElement, inclusive, data.get(data.size() - 1), true);
            }
            else {
                return new NavSet<T>(comparator);
            }
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Equivalent to {@code subSet(fromElement, true, toElement, false)}.
     *
     * @param fromElement
     * @param toElement
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) throws ClassCastException, NullPointerException,
            IllegalArgumentException {
        return this.subSet(fromElement, true, toElement, false);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Equivalent to {@code headSet(toElement, false)}.
     *
     * @param toElement
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     */
    @Override
    public SortedSet<T> headSet(T toElement) throws ClassCastException, NullPointerException {
        return this.headSet(toElement, false);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Equivalent to {@code tailSet(fromElement, true)}.
     *
     * @param fromElement
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     */
    @Override
    public SortedSet<T> tailSet(T fromElement) throws ClassCastException, NullPointerException {
        return this.tailSet(fromElement, true);
    }

    /**
     * Returns the comparator used to order the elements in this set,
     * or <tt>null</tt> if this set uses the {@linkplain Comparable
     * natural ordering} of its elements.
     *
     * @return the comparator used to order the elements in this set,
     * or <tt>null</tt> if this set uses the natural ordering
     * of its elements
     */
    @Override
    public Comparator<? super T> comparator() {
        return this.comparator;
    }

    /**
     * Returns the first (lowest) element currently in this set.
     *
     * @return the first (lowest) element currently in this set
     * @throws NoSuchElementException if this set is empty
     */
    @Override
    public T first() throws NoSuchElementException {
        if (data.size() > 0) {
            return data.get(0);
        }
        else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Returns the last (highest) element currently in this set.
     *
     * @return the last (highest) element currently in this set
     * @throws NoSuchElementException if this set is empty
     */
    @Override
    public T last() throws NoSuchElementException {
        if (data.size() > 0) {
            return data.get(data.size() - 1);
        }
        else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Compares the specified object with this set for equality.  Returns
     * <tt>true</tt> if the given object is also a set, the two sets have
     * the same size, and every member of the given set is contained in
     * this set.  This ensures that the <tt>equals</tt> method works
     * properly across different implementations of the <tt>Set</tt>
     * interface.<p>
     * <p>
     * This implementation first checks if the specified object is this
     * set; if so it returns <tt>true</tt>.  Then, it checks if the
     * specified object is a set whose size is identical to the size of
     * this set; if not, it returns false.  If so, it returns
     * <tt>containsAll((Collection) o)</tt>.
     *
     * @param o object to be compared for equality with this set
     * @return <tt>true</tt> if the specified object is equal to this set
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NavSet<?> navSet = (NavSet<?>) o;
        if (descendingSetFlag == navSet.descendingSetFlag &&
                Objects.equals(comparator, navSet.comparator) &&
                data.size() == navSet.data.size()) {
            Iterator<T> i = this.iterator();
            Iterator<T> j = (Iterator<T>) navSet.iterator();
            while (i.hasNext()) {
                if (!i.next().equals(j.next())) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the hash code value for this set.  The hash code of a set is
     * defined to be the sum of the hash codes of the elements in the set,
     * where the hash code of a <tt>null</tt> element is defined to be zero.
     * This ensures that <tt>s1.equals(s2)</tt> implies that
     * <tt>s1.hashCode()==s2.hashCode()</tt> for any two sets <tt>s1</tt>
     * and <tt>s2</tt>, as required by the general contract of
     * {@link Object#hashCode}.
     *
     * <p>This implementation iterates over the set, calling the
     * <tt>hashCode</tt> method on each element in the set, and adding up
     * the results.
     *
     * @return the hash code value for this set
     * @see Object#equals(Object)
     * @see Set#equals(Object)
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data, comparator, descendingSetFlag);
    }

    /**
     * Returns {@code true} if this set contains no elements.
     *
     * @return {@code true} if this set contains no elements
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Returns {@code true} if this set contains the specified element.
     * More formally, returns {@code true} if and only if this set
     * contains an element {@code e} such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o object to be checked for containment in this set
     * @return {@code true} if this set contains the specified element
     * @throws NullPointerException if the specified element is null
     *                              and this set uses natural ordering, or its comparator
     *                              does not permit null elements
     */
    @Override
    public boolean contains(Object o) throws NullPointerException {
        if (o != null) {
            return data.contains(o);
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>This implementation returns an array containing all the elements
     * returned by this collection's iterator, in the same order, stored in
     * consecutive elements of the array, starting with index {@code 0}.
     * The length of the returned array is equal to the number of elements
     * returned by the iterator, even if the size of this collection changes
     * during iteration, as might happen if the collection permits
     * concurrent modification during iteration.  The {@code size} method is
     * called only as an optimization hint; the correct result is returned
     * even if the iterator returns a different number of elements.
     *
     * <p>This method is equivalent to:
     *
     * <pre> {@code
     * List<E> list = new ArrayList<E>(size());
     * for (E e : this)
     *     list.add(e);
     * return list.toArray();
     * }</pre>
     */
    @Override
    public Object[] toArray() {
        return data.toArray();
    }

    /**
     * Adds the specified element to this set if it is not already present.
     * More formally, adds the specified element {@code e} to this set if
     * the set contains no element {@code e2} such that
     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
     * If this set already contains the element, the call leaves the set
     * unchanged and returns {@code false}.
     *
     * @param t element to be added to this set
     * @return {@code true} if this set did not already contain the specified
     * element
     * @throws ClassCastException   if the specified object cannot be compared
     *                              with the elements currently in this set
     * @throws NullPointerException if the specified element is null
     *                              and this set uses natural ordering, or its comparator
     *                              does not permit null elements
     */
    @Override
    public boolean add(T t) throws ClassCastException, NullPointerException {
        if (t != null) {
            if (data.contains(t)) {
                return false;
            }
            int i = 0;
            if (i == data.size()) {
                data.add(t);
                return true;
            }
            while (compare(t, data.get(i)) > 0) {
                i++;
                if (i == data.size()) {
                    data.add(t);
                    return true;
                }
            }
            data.add(i, t);
            return true;
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Removes the specified element from this set if it is present.
     * More formally, removes an element {@code e} such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>,
     * if this set contains such an element.  Returns {@code true} if
     * this set contained the element (or equivalently, if this set
     * changed as a result of the call).  (This set will not contain the
     * element once the call returns.)
     *
     * @param o object to be removed from this set, if present
     * @return {@code true} if this set contained the specified element
     * @throws NullPointerException if the specified element is null
     *                              and this set uses natural ordering, or its comparator
     *                              does not permit null elements
     */
    @Override
    public boolean remove(Object o) {
        if (o != null) {
            return data.remove(o);
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>This implementation iterates over the specified collection,
     * checking each element returned by the iterator in turn to see
     * if it's contained in this collection.  If all elements are so
     * contained <tt>true</tt> is returned, otherwise <tt>false</tt>.
     *
     * @param c
     * @throws NullPointerException {@inheritDoc}
     * @see #contains(Object)
     */
    @Override
    public boolean containsAll(Collection<?> c) throws NullPointerException {
        if (c != null) {
            for (Object o : c) {
                if (!this.contains(o)) {
                    return false;
                }
            }
            return true;
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Adds all of the elements in the specified collection to this set.
     *
     * @param c collection containing elements to be added to this set
     * @return {@code true} if this set changed as a result of the call
     * @throws ClassCastException   if the elements provided cannot be compared
     *                              with the elements currently in the set
     * @throws NullPointerException if the specified collection is null or
     *                              if any element is null and this set uses natural ordering, or
     *                              its comparator does not permit null elements
     */
    @Override
    public boolean addAll(Collection<? extends T> c) throws ClassCastException, NullPointerException {
        if (c != null) {
            boolean flag = false;
            for (T element : c) {
                if (element != null) {
                    if (this.add(element)) {
                        flag = true;
                    }
                } else {
                    throw new NullPointerException();
                }
            }
            return flag;
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Removes all of the elements from this set.
     * The set will be empty after this call returns.
     */
    @Override
    public void clear() {
        data = new ArrayList<T>();
    }

    /**
     * Returns a string representation of this collection.  The string
     * representation consists of a list of the collection's elements in the
     * order they are returned by its iterator, enclosed in square brackets
     * (<tt>"[]"</tt>).  Adjacent elements are separated by the characters
     * <tt>", "</tt> (comma and space).  Elements are converted to strings as
     * by {@link String#valueOf(Object)}.
     *
     * @return a string representation of this collection
     */
    @Override
    public String toString() {
        return super.toString();
    }
}

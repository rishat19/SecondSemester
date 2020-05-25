import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleModifiableCollectionTest {

    private SimpleModifiableCollection<Integer> collection;

    @Before
    public void setUp() {
        collection = new SimpleModifiableCollection<>();
    }

    @Test
    public void testConstructorWithParameter() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(7, 8));
        collection = new SimpleModifiableCollection<>(list);
        Iterator<Integer> it = collection.iterator();
        int a = it.next();
        int b = it.next();
        Assert.assertTrue(((a == 7 && b == 8) || (a == 8 && b == 7)) && collection.size() == 2);
    }

    @Test
    public void testAdd() {
        collection.add(19);
        Iterator<Integer> it = collection.iterator();
        int a = it.next();
        Assert.assertEquals(a, 19);
    }

    @Test
    public void testSize() {
        int a = collection.size();
        collection.add(1);
        collection.add(2);
        int b = collection.size();
        collection.add(3);
        collection.add(4);
        collection.add(5);
        int c = collection.size();
        Assert.assertTrue(a == 0 && b == 2 && c == 5);
    }

    @Test
    public void testRemove() {
        collection.add(19);
        collection.add(30);
        collection.add(0);
        Iterator<Integer> it = collection.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        Assert.assertEquals(collection.size(), 0);
    }

    @Test
    public void testSizeIncreaseWhenAdding() {
        collection.add(1);
        collection.add(2);
        collection.add(3);
        Assert.assertEquals(collection.size(), 3);
    }

    @Test
    public void testSizeDecreaseWhenRemoving() {
        collection = new SimpleModifiableCollection<>(Arrays.asList(1, 2, 3, 4));
        int a = collection.size();
        Iterator<Integer> it = collection.iterator();
        it.next();
        it.remove();
        Assert.assertTrue(a == 4 && collection.size() == 3);
    }

    @Test
    public void testIsIterator() {
        collection.add(1);
        Iterator<Integer> it = collection.iterator();
        Assert.assertTrue(it instanceof Iterator);
    }

    @Test
    public void testHasNext1() {
        collection.add(1);
        Iterator<Integer> it = collection.iterator();
        Assert.assertTrue(it.hasNext());
    }

    @Test
    public void testHasNext2() {
        collection.add(1);
        Iterator<Integer> it = collection.iterator();
        it.next();
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testHasNextandNext() {
        collection = new SimpleModifiableCollection<>(Arrays.asList(1, 2, 3, 4));
        Iterator<Integer> it = collection.iterator();
        int count = 0;
        while (it.hasNext()) {
            count++;
            it.next();
        }
        Assert.assertEquals(count, 4);
    }

    @Test
    public void testEquals() {
        collection.add(1);
        collection.add(2);
        collection.add(3);
        SimpleModifiableCollection<Integer> secondCollection = new SimpleModifiableCollection<>();
        secondCollection.add(2);
        secondCollection.add(1);
        secondCollection.add(3);
        Assert.assertEquals(collection, secondCollection);
    }

    @Test
    public void testHashCode() {
        collection.add(1);
        SimpleModifiableCollection<Integer> secondCollection = new SimpleModifiableCollection<>();
        secondCollection.add(1);
        Assert.assertEquals(collection.hashCode(), secondCollection.hashCode());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorForException() {
        collection = new SimpleModifiableCollection<>(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddForException() {
        collection.add(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextForException() {
        Iterator<Integer> it = collection.iterator();
        it.next();
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveForException() {
        Iterator<Integer> it = collection.iterator();
        it.remove();
    }

}
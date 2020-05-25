import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueueTest {

    private Queue<Integer> queue;

    @Before
    public void setUp() {
        Integer[] array = new Integer[] {4, 7, 13, 12, 11, 3, 72, 9, 19};
        queue = new Queue<>(array);
    }

    @Test
    public void testConstructorWithArrayParameter() {
        Assert.assertEquals("{4, 7, 13, 12, 11, 3, 72, 9, 19}", queue.toString());
    }

    @Test
    public void testConstructorWithQueueParameter() {
        Queue<Integer> secondQueue = new Queue<>(queue);
        Assert.assertEquals("{4, 7, 13, 12, 11, 3, 72, 9, 19}", secondQueue.toString());
    }

    @Test
    public void testAdd() {
        queue.add(93);
        queue.add(30);
        Assert.assertEquals("{4, 7, 13, 12, 11, 3, 72, 9, 19, 93, 30}", queue.toString());
    }

    @Test
    public void testPop1() {
        queue.pop();
        queue.pop();
        queue.pop();
        Assert.assertEquals("{12, 11, 3, 72, 9, 19}", queue.toString());
    }

    @Test
    public void testPop2() {
        queue.pop();
        queue.pop();
        Assert.assertEquals((int) queue.pop(), 13);
    }

    @Test
    public void testPop3() {
        for (int i = 0; i < 9; i++) {
            queue.pop();
        }
        Assert.assertNull(queue.pop());
    }

    @Test
    public void testAddAndPop() {
        queue.pop();
        queue.pop();
        queue.add(30);
        queue.pop();
        queue.add(93);
        queue.pop();
        queue.pop();
        Assert.assertEquals("{3, 72, 9, 19, 30, 93}", queue.toString());
    }

    @Test
    public void testPeek1() {
        Assert.assertEquals((int) queue.peek(), 4);
    }

    @Test
    public void testPeek2() {
        queue.pop();
        queue.pop();
        Assert.assertEquals((int) queue.peek(), 13);
    }

    @Test
    public void testPeek3() {
        for (int i = 0; i < 9; i++) {
            queue.pop();
        }
        Assert.assertNull(queue.peek());
    }

    @Test
    public void testSize1() {
        Assert.assertEquals(queue.size(), 9);
    }

    @Test
    public void testSize2() {
        queue.pop();
        queue.pop();
        queue.add(1);
        queue.pop();
        Assert.assertEquals(queue.size(), 7);
    }

    @Test
    public void testSize3() {
        queue = new Queue<>();
        Assert.assertEquals(queue.size(), 0);
    }

    @Test
    public void testEquals1() {
        Queue<Integer> secondQueue = new Queue<>(queue);
        Assert.assertEquals(queue, secondQueue);
    }

    @Test
    public void testEquals2() {
        queue.pop();
        queue.pop();
        queue.pop();
        Integer[] array = new Integer[] {12, 11, 3, 72, 9, 19};
        Queue<Integer> secondQueue = new Queue<>(array);
        Assert.assertEquals(queue, secondQueue);
    }

    @Test
    public void testHashCode() {
        Queue<Integer> secondQueue = new Queue<>(queue);
        Assert.assertEquals(queue.hashCode(), secondQueue.hashCode());
    }

    @Test
    public void testToString1() {
        Assert.assertEquals("{4, 7, 13, 12, 11, 3, 72, 9, 19}", queue.toString());
    }

    @Test
    public void testToString2() {
        queue = new Queue<>();
        Assert.assertEquals("{}", queue.toString());
    }

    @Test(expected = NullPointerException.class)
    public void testArrayConstructorForException() {
        Integer[] array = null;
        queue = new Queue<>(array);
    }

    @Test(expected = NullPointerException.class)
    public void testQueueConstructorForException() {
        Queue<Integer> secondQueue = null;
        queue = new Queue<>(secondQueue);
    }

}
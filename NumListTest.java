import org.junit.Assert;
import org.junit.Test;

public class NumListTest {

    NumArrayList list = new NumArrayList();

    /**
     * Testing independence of <i>size</> and <i>capacity</i>, <i>add</i>
     */
    @Test
    public void testAdd() {
        // testing size and capacity independence
        Assert.assertEquals(0, list.size());
        Assert.assertEquals(0, list.capacity());

        list = new NumArrayList(5);

        Assert.assertEquals(0, list.size());
        Assert.assertEquals(5, list.capacity());

        // testing add method
        /** test one */
        list = new NumArrayList();
        list.add(10);

        Assert.assertEquals(10, list.lookup(0), 0.001);
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(1, list.capacity());

        /** test many: checking for no errors when expanding list capacity and correct positioning of new elements */
        list.add(20);
        list.add(30);
        list.add(40);

        Assert.assertEquals(10, list.lookup(0), 0.001);
        Assert.assertEquals(20, list.lookup(1), 0.001);
        Assert.assertEquals(40, list.lookup(3), 0.001);
    }

    @Test
    public void testContainsLookup() {
        // testing contain and lookup with empty list
        Assert.assertEquals(false, list.contains(10));

        try {
            list.lookup(0);
            Assert.assertFalse(true);
        } catch (IndexOutOfBoundsException e) {
            Assert.assertTrue(true);
        }

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        // check for proper size
        Assert.assertEquals(4, list.size());

        // test first
        Assert.assertEquals(true, list.contains(10));

        // test middle
        Assert.assertEquals(true, list.contains(30));

        // test last
        Assert.assertEquals(true, list.contains(40));

        // invalid element
        Assert.assertEquals(false, list.contains(1000));

        // test first
        Assert.assertEquals(10, list.lookup(0), 0.01);

        // test middle
        Assert.assertEquals(30, list.lookup(2), 0.01);

        // test last
        Assert.assertEquals(40, list.lookup(3), 0.01);

        // invalid index
        try {
            list.lookup(4);
            Assert.assertFalse(true);
        } catch (IndexOutOfBoundsException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testInsert() {
        // insert with empty list
        list.insert(0, 1);

        Assert.assertEquals(1, list.lookup(0), 0.001);
        Assert.assertEquals(1, list.size());

        // index is greater than size
        list.insert(2, 30);

        Assert.assertEquals(30, list.lookup(1), 0.001);

        // testing with list with more elements (1, 30, 9, 2, 5, 8)
        list.add(9);
        list.add(2);
        list.add(5);
        list.add(8);

        // test first
        list.insert(0, 37);   // (37, 1, 30, 9, 2, 5, 8)
        Assert.assertEquals(37, list.lookup(0), 0.001);

        // test middle
        list.insert(3, 22);   // (37, 1, 30, 22, 9, 2, 5, 8)
        Assert.assertEquals(22, list.lookup(3), 0.001);

        // test last
        list.insert(7, 18);   // (37, 1, 30, 22, 9, 2, 5, 18, 8)
        Assert.assertEquals(18, list.lookup(7), 0.001);
    }

    @Test
    public void testRemove() {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        // test first
        list.remove(0);
        Assert.assertEquals(1, list.lookup(0), 0.01);
        Assert.assertEquals(false, list.contains(0));
        Assert.assertEquals(4, list.size(), 0.01);
        Assert.assertEquals(5, list.capacity(), 0.01);

        // test last
        list.remove(3);
        Assert.assertEquals(false, list.contains(4));
        Assert.assertEquals(3, list.size(), 0.01);
        Assert.assertEquals(5, list.capacity(), 0.01);

    }
}

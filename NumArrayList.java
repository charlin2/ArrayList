/**
 * An array list of floating point numbers.
 * @author <em> Charlie Lin </em>
 */
public class NumArrayList implements NumList {
    /** the array storing the numbers */
    private double[] list;

    /** the number of elements in the array */
    private int size = 0;

    /**
     * Creates a new empty NumArrayList
     */
    public NumArrayList() {
        list = new double[0];
    }

    /**
     * Creates a new NumArrayList with <i>capacity</i> storage space
     * @param capacity the capacity of the NumArrayList
     */
    public NumArrayList(int capacity) {
        list = new double[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return list.length;
    }

    @Override
    public void add(double value) {
        try {
            list[size] = value;
            size++;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            double[] resizedList = new double[size()+1];
            // copies all values in list into resized list
            for (int i = 0; i < list.length; i++) {
                resizedList[i] = list[i];
            }
            resizedList[size] = value;
            size++;
            list = resizedList;
        }
    }

    @Override
    public void insert(int i, double value) {
        if (i >= size() || i < 0) {
            add(value);
        }
        // array does not need to be resized
        else if (i < size() && size() < capacity()) {
            double current = value;   // saves the current value at i 
            double next = list[i];    // saves the next value in the list
            for (int index = i; index < size()-1; index++) {
                list[index] = current;
                current = next;
                next = list[index+1];
            }
        }
        // array needs to be resized
        else if (i < size() && size() == capacity()) {
            double[] resizedList = new double[size()+1];
            int listIndex = 0;  // Iterates through original list
            for (int resizedIndex = 0; resizedIndex < resizedList.length; resizedIndex++) {
                if (resizedIndex == i) {
                    resizedList[resizedIndex] = value;
                } else {
                    resizedList[resizedIndex] = list[listIndex];
                    listIndex++;
                }
            }
            size++;
            list = resizedList;
        }
    }

    @Override
    public void remove(int i) {
        if (i >= size() || i < 0) {
            // do nothing
        } else {
            for (int index = i; index < size()-1; index++) {
                list[index] = list[index+1];
            }
            size--;
        }
    }

    @Override
    public boolean contains(double value) {
        for (int i = 0; i < size(); i++) {
            if (list[i] == value) return true;
        }
        return false;
    }

    @Override
    public double lookup(int i) throws IndexOutOfBoundsException {
        if (i >= size()) throw new IndexOutOfBoundsException();
        return list[i];
    }

    @Override
    public boolean equals(NumList otherList) {
        if (size() != otherList.size()) return false;
        else if (size() == 0 && otherList.size() == 0) return true;
        else {
            int otherListCounter = 0;
            for (int i = 0; i < size(); i++) {
                if (list[i] != otherList.lookup(otherListCounter)) return false;
                otherListCounter++;
            }
        }
        return true;
    }
    
    @Override
    public void removeDuplicates() {
        // each unique element only needs to be checked once
        for (int i = 0; i < size()-1; i++) {
            // removes all instances of element after the first occurrence
            for (int compare = i + 1; compare < size(); compare++) {
                if (list[i] == list[compare]) {
                    remove(compare);
                    compare--;        // after list resizing, "next" element stays in place 
                }
            }
        }
    }

    @Override
    public String toString() {
        if (size() == 0) return "";
        StringBuilder arrayString = new StringBuilder();
        for (int i = 0; i < size() - 1; i++) {
            arrayString.append(list[i]);
            arrayString.append(' ');
        }
        arrayString.append(list[size()-1]);
        return arrayString.toString();
    }

    public static void main(String[] args) {
        NumArrayList demoList = new NumArrayList();
        System.out.println("NumArrayList demoList = new NumArrayList()");
        System.out.println("Empty list will return an empty list.\n" + "Current list: " + demoList.toString());
        System.out.println("Current list size: " + demoList.size());
        System.out.println("Current list capacity: " + demoList.capacity());
        System.out.println("\ndemoList.add(10)");
        demoList.add(10);
        System.out.println("Current list: " + demoList.toString());
        System.out.println("Current list size: " + demoList.size());
        System.out.println("Current list capacity: " + demoList.capacity());
        System.out.println("\ndemoList.add(20)");
        System.out.println("demoList.add(30)");
        System.out.println("demoList.add(50)");
        demoList.add(20);
        demoList.add(30);
        demoList.add(50);
        System.out.println("Current list: " + demoList.toString());
        System.out.println("Current list size: " + demoList.size());
        System.out.println("Current list capacity: " + demoList.capacity());
        System.out.println("\ndemoList.insert(3, 40)");
        demoList.insert(3, 40);
        System.out.println("Current list: " + demoList.toString());
        System.out.println("Current list size: " + demoList.size());
        System.out.println("Current list capacity: " + demoList.capacity());
        System.out.println("\ndemoList.remove(1)");
        demoList.remove(1);
        System.out.println("Current list: " + demoList.toString());
        System.out.println("Current list size: " + demoList.size());
        System.out.println("Current list capacity: " + demoList.capacity());
        System.out.println("\ndemoList.contains(20) \n" + demoList.contains(20));
        System.out.println("\ndemoList.lookup(1) \n" + demoList.lookup(1));
        System.out.println("\ndemoList.add(10)");
        System.out.println("demoList.add(10)");
        System.out.println("demoList.add(40)");
        System.out.println("demoList.add(30)");
        demoList.add(10);
        demoList.add(10);
        demoList.add(40);
        demoList.add(30);
        System.out.println("demoList.removeDuplicates()");
        demoList.removeDuplicates();
        System.out.println("Current list: " + demoList.toString());
        System.out.println("Current list size: " + demoList.size());
        System.out.println("Current list capacity: " + demoList.capacity());
        System.out.println("\nNumArrayList compareList = new NumArrayList(30)");
        NumArrayList compareList = new NumArrayList(30);
        System.out.println("compareList.add(10)");
        System.out.println("compareList.add(30)");
        System.out.println("compareList.add(40)");
        System.out.println("compareList.add(50)");
        compareList.add(10);
        compareList.add(30);
        compareList.add(40);
        compareList.add(50);
        System.out.println("demoList.equals(compareList)\n" + demoList.equals(compareList));
    }
}

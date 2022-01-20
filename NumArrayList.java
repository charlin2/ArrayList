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
        if (i >= size()) {
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
        if (i >= size()) {
            // do nothing
        } else if (i == size()-1) {
            size--;
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
            for (double d : list) {
                if (d != otherList.lookup(otherListCounter)) return false;
                otherListCounter++;
            }
        }
        return true;
    }
    
    @Override
    public void removeDuplicates() {
        for (int i = 0; i < size()-1; i++) {
            for (int compare = i + 1; compare < size(); compare++) {
                if (list[i] == list[compare]) {
                    remove(compare);
                    compare--;
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

}

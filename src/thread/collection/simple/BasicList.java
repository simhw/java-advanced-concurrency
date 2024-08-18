package thread.collection.simple;

import java.util.Arrays;

import static util.ThreadUtils.sleep;

public class BasicList implements SimpleList {

    private static final int DEFAULT_CAPACITY = 5;

    private final Object[] elements;
    private int size = 0;

    public BasicList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    // 원자적이지 않은 연산
    @Override
    public void add(Object e) {
        elements[size] = e;
        sleep(100);
        size++;
    }

    @Override
    public Object get(int index) {
        return elements[index];
    }

    @Override
    public String toString() {
        return "elements = " + Arrays.toString(Arrays.copyOf(elements, size))
                + ", size = " + size + ", capacity = " + DEFAULT_CAPACITY;
    }
}

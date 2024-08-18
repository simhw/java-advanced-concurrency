package thread.collection.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedListMain {
    public static void main(String[] args) {
        // synchronized를 추가하는 프록시 역할
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        list.add("A");
        list.add("B");
        list.add("C");

        System.out.println(list.getClass());
        System.out.println("list = " + list);
    }
}

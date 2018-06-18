package multithreading.producerConsumer;

import java.util.ArrayList;

public class TaskData {
    ArrayList<Integer> store = new ArrayList<Integer>();
    boolean work = true;
    boolean valueSet = false;

    ArrayList<Integer> get() {
        while (!valueSet) {
            Thread.yield();
        }
        System.out.println("Полученно значение = " + store.get(store.size()-1));
        valueSet = false;
        return store;
    }

    void put(int store) {
        while (valueSet) {
            Thread.yield();
        }
        this.store.add(store);

        //Выводим элемент последовательности
        System.out.println("Внесенно значение: " + store);
        valueSet = true;
    }
}

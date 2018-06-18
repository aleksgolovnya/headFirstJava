package multithreading.producerConsumer;

import java.util.ArrayList;

public class Consumer implements Runnable {
    TaskData taskData;
    Consumer(TaskData taskData) {
        this.taskData = taskData;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        while (taskData.work) {
            ArrayList<Integer> t = taskData.get();
            try {
                if (t.get(t.size() - 1) > t.get(t.size() - 2))  //сравниваем пердпоследний и последний элементы
                    System.out.println("Максимальное = " + t.get(t.size() - 1));
                else
                    System.out.println("Максимальное = " + t.get(t.size() - 2));
            } catch(Exception ex) {}
        }
    }
}

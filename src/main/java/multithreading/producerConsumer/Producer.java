package multithreading.producerConsumer;

import java.util.Random;

//Генерация последовательности случайный чисел
public class Producer implements Runnable {
    TaskData taskData;

    public Producer(TaskData taskData) {
        this.taskData = taskData;
        new Thread(this, "Producer").start();
    }

    public void run() {
        taskData.put(new Random().nextInt(10000));
        taskData.put(new Random().nextInt(10000));
        //сначала записываем два элемента
        while (taskData.work) {
            taskData.put(new Random().nextInt(10000));
        }
    }
}

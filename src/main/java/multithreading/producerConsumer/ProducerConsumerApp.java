package multithreading.producerConsumer;

public class ProducerConsumerApp {
    public static void main(String [] args) {
        TaskData taskData = new TaskData();
        new Producer(taskData);
        new Consumer(taskData);
        System.out.println("!");

        try {
            Thread.sleep(5000);
        }
        catch(InterruptedException e) {}
        taskData.work = false;
        System.out.println("Все потоки завершили работу");
    }
}

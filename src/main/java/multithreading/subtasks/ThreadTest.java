package multithreading.subtasks;

import java.util.Random;

public class ThreadTest implements Runnable {

    ThreadTest() {
        Thread t1 = new Thread(this, "Thread 1");
        t1.setPriority(Thread.NORM_PRIORITY + 2);
        System.out.println("Thread created: " + t1);
        t1.start();

        Thread t2 = new Thread(this, "Thread 2");
        t2.setPriority(Thread.NORM_PRIORITY + 1);
        System.out.println("Thread created: " + t2);
        t2.start();

        try {
            t1.join();
            t2.join();
        }

        catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        System.out.println("Exiting main thread");
    }

    public void run() {
        Thread t = Thread.currentThread();

        for (int i = 1; i <= 10; i++) {
            int r = randomArray();
            int avgFib = avgFib();
            t.yield();
            System.out.println(t.getName()+ " " + r + " " + avgFib + " " + factorial(8));
        }

        System.out.println("Exiting "+ t.getName());
    }

    public static int[] generateArray() {
        Random random = new Random();
        int [] a = new int[10];
        for(int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(100);
        }
        return a;
    }

    public static int avgArray(int[] a) {
        int result = 0;
        for(int i:a) {
            result += i;
        }
        return result / a.length;
    }

    public static int randomArray() {
        int [] a = generateArray();
//        for(int i: a) {
//            System.out.println(i);
//        }
        int result = avgArray(a);
        return result;
    }

    public static int f(int index) {
        if (index <= 0) {
            return 0;
        } else if (index == 1) {
            return 1;
        } else if (index == 2) {
            return 1;
        } else {
            return f(index - 1) + f(index - 2);
        }
    }

    public static int avgFib() {
        int n = 10;
        int result = 0;
        for(int i = 1; i <= n; i++) {
            result += f(i);
        }
        return result / n;
    }

    public static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n-1);
    }

    public static void main(String args[]) {
        new ThreadTest();
    }
}

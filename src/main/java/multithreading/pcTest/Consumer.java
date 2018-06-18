package multithreading.pcTest;

public class Consumer implements Runnable
{
    Q q;
    int n;
    int positive = 0;
    int negative = 0;
    Consumer(Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }
    public void run() {
        while (q.work) {
            n = q.get();
            if(n > 0) { positive++; }
            if(n < 0) { negative++; }

            System.out.println("Полученно число " + n);
            System.out.println("Положительных чисел " + positive);
            System.out.println("Отрицательных чисел " + negative);
        }
    }
}


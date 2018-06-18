package multithreading.pcTest;

class Producer implements Runnable
{
    Q q;
    int n;
    final int minRandom = -100;
    final int maxRandom = 100;

    Producer(Q q) {
        this.q = q; new Thread(this, "Producer").start();
    }

    public void run() {
        while(q.work) {
            n = (rnd(minRandom, maxRandom));
            q.put(n);
        }
    }

    public static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}

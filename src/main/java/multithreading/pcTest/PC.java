package multithreading.pcTest;

class PC
{
    public static void main(String args[])
    {
        Q q = new Q();

        new Producer(q);
        new Consumer(q);

        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {};

        q.work = false;
        System.out.println("Все потоки завершили работу");
    }
}

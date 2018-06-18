package multithreading.pcTest;

class Q
{
    int n;
    int dataWritten = 0;
    boolean valueSet = false;
    boolean work = true;
    synchronized int get()
    {
        if (!valueSet) try { wait(); }
        catch(InterruptedException e) {}
        //System.out.println("Got: " + n);
        valueSet = false;
        notify();
        return n;
    }
    synchronized void put(int n)
    {
        if (valueSet) try { wait(); }
        catch(InterruptedException e) {}
        if(dataWritten <= 50) {
            this.n = n;
            //System.out.println("Put: " + n);
            dataWritten++;
            valueSet = true;
            notify();
        }
    }
}
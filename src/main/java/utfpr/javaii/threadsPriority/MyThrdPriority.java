package utfpr.javaii.threadsPriority;

public class MyThrdPriority extends Thread {

    public int count;
    static boolean stop = false;

    MyThrdPriority(String name) {
        super(name);
        count = 0;
    }

    public void run() {
        System.out.println(getName() + " starting the race!!");
        do {
            count++;
        } while (stop == false && count < 10000000);
        stop = true;

        System.out.println(getName() + " terminating!");
    }
}

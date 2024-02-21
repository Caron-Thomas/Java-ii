package utfpr.javaii.threads;

public class MyThrd extends Thread {

    MyThrd(String name) {
        super(name);
        start();
    }

    public void run() {
        System.out.println(getName() + " starting!!");
        try {
            for (int i = 0; i < 10 ; i++) {
                Thread.sleep(400);
                System.out.println("in " + getName() + "count is " + i);
            }

        } catch (InterruptedException ie) {
            System.out.println(getName() + " was interrupted!");
        }
        System.out.println(getName() + " terminating!");
    }
}

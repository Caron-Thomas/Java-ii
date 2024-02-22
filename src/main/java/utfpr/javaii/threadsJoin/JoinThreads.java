package utfpr.javaii.threadsJoin;

public class JoinThreads {

    public static void main(String[] args) {
        System.out.println("Main thread is starting:");
        System.out.println("===========================");
        System.out.println("Calling for child threads");

        MyThrd mt1 = new MyThrd("Child #1");
        MyThrd mt2 = new MyThrd("Child #2");
        MyThrd mt3 = new MyThrd("Child #3");

        try {
            mt1.join();
            mt2.join();
            mt3.join();

        } catch (InterruptedException e) {
            System.out.println("Something went wrong! Threads were interrupted: ");
        }
        System.out.println("Main thread has ended:\n===========================");
    }
}

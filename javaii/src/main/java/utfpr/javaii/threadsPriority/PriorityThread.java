package utfpr.javaii.threadsPriority;

public class PriorityThread {

    public static void main(String[] args) {

        System.out.println("Main thread is starting:");
        System.out.println("===========================");
        System.out.println("Calling for child threads");

        MyThrdPriority mt1 = new MyThrdPriority("Low Priority Thread:");
        MyThrdPriority mt2 = new MyThrdPriority("Normal Priority Thread:");
        MyThrdPriority mt3 = new MyThrdPriority("High Priority Thread:");

        mt1.setPriority(Thread.NORM_PRIORITY-3);
        mt2.setPriority(Thread.NORM_PRIORITY);
        mt3.setPriority(Thread.NORM_PRIORITY+3);

        mt1.start();
        mt2.start();
        mt3.start();

        try {
            mt1.join();
            mt2.join();
            mt3.join();
        } catch (InterruptedException e) {
            System.out.println("Something went wrong! Threads were interrupted: ");
        }

        System.out.println("Low Priority Thread reach up to:....." + mt1.count);
        System.out.println("Normal Priority Thread reach up to:.." + mt2.count);
        System.out.println("High Priority Thread reach up to:...." + mt3.count);

        System.out.println("Main thread has ended:\n===========================");

    }
}

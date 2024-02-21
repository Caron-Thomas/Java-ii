package utfpr.javaii.race;

import java.util.TreeMap;

public class MotorBikeRace implements Runnable {

    Thread thrd;

    static boolean hasWin = false;

    public static void main(String[] args) {

        MotorBikeRace bike1 = new MotorBikeRace("Bike 1");
        MotorBikeRace bike2 = new MotorBikeRace("Bike 2");
        MotorBikeRace bike3 = new MotorBikeRace("Bike 3");

        try {
            bike1.thrd.join();
            bike2.thrd.join();
            bike3.thrd.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ponto bike 1 = " + bike1.race()); // TODO CORRIGIR
        System.out.println("ponto bike 2 = " + bike2.race());
        System.out.println("ponto bike 3 = " + bike3.race());
    }




    MotorBikeRace(String name) {
        thrd = new Thread(this, name);
        thrd.start();
    }

    @Override
    public void run() {
        race();
    }

    private int race() {
        int place = 0;
        do {
           place++;
        } while (hasWin && place < 10);
        hasWin = true;

        return place;
    }
}

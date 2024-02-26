package utfpr.javaii.race;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotorcycleRideRaceSuperStar {

    public static void main(String[] args) {
        new MotorcycleRideRaceSuperStar();
    }

    private static final int TOTAL_RIDERS = 10;

    MotorcycleRideRaceSuperStar() {

        final var championship = new Championship();

        for (int i = 0; i < TOTAL_RIDERS; i++) {
            final var rider = new Rider("Competidor #%s ".formatted(i + 1), championship);
            final var thrd = new Thread(rider);
            thrd.start();
        }

//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException ie) {
//            System.out.println("[ERROR] - " + ie.getMessage());
//        }

        championship.championshipResult();
    }

    private class Rider implements Runnable {

        private final String nome;
        private final Championship championship;

        private Rider(String nome, Championship championship) {
            this.nome = nome;
            this.championship = championship;
        }

        @Override
        public void run() {
                championship.addRider(nome);
        }
    }

    private static class Championship {

        final Map<String, Integer> riders = new HashMap<>();

        public void addRider(String nome) {
                riders.put(nome, 0);
        }

        public void championshipResult() {
            riders.entrySet().forEach(System.out::println);
        }
    }


}

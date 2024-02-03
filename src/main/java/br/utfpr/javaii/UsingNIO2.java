package br.utfpr.javaii;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UsingNIO2 {

    private static final String MY_FILE = "d:" + File.separator + "file-using-nio2.txt";
    public UsingNIO2() {
        try {
            writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("================================================================");

        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile() throws IOException {

        final Path path = Paths.get(MY_FILE);
        Files.readAllLines(path)
                .forEach(System.out::println);
    }

    private void writeFile() throws IOException {

        final Path path = Paths.get(MY_FILE);
        Files.writeString(path, "Olá Mundo NIO2");

        System.out.println("Arquivo gravado com sucesso!");
    }

    public static void main(String[] args) {
        new UsingNIO2();
    }
}

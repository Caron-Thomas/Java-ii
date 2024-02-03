package br.utfpr.javaii;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class UsingNIO {

    private static final String MY_FILE = "d:" + File.separator + "file-using-nio.txt";

    public UsingNIO() {
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

        final RandomAccessFile file = new RandomAccessFile(MY_FILE, "rw");
        final FileChannel channel = file.getChannel();

        final long fileSize = channel.size();

        final ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);

        channel.read(buffer);
        buffer.flip();

        for (int i = 0; i < fileSize; i++) {
            System.out.print(buffer.get() + " ");
        }

        buffer.flip();
        System.out.println();
        for (int i = 0; i < fileSize; i++) {
            System.out.print((char) buffer.get() + " " );
        }System.out.println();

        channel.close();
        file.close();
    }

    private void writeFile() throws IOException {

        final RandomAccessFile file = new RandomAccessFile(MY_FILE, "rw");
        final FileChannel channel = file.getChannel();

        channel.write(ByteBuffer.wrap("Ola mundo NIO".getBytes(StandardCharsets.UTF_8)));
        channel.close();
        file.close();

        System.out.println("Dados gravados no arquivo:");

    }

    public static void main(String[] args) {
        new UsingNIO();
    }
}

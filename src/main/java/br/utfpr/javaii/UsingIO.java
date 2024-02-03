package br.utfpr.javaii;

import java.io.*;

public class UsingIO {

    private static final String MY_FILE = "d:" + File.separator + "file-using-io.txt";

    public UsingIO() {

        /* //Exemplo simples de criação de documentos:
        try {
            final File file = new File(MY_FILE);

            if(!file.exists()){
                file.createNewFile();
                System.out.println(file.exists());

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // finalizando a logica de criação de arquivo ou pasta */


        try {
            writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        readFile();
    }

    private void writeFile() throws IOException {

        // codigo que escreve uma linha num arquivo texto. TODO -> Como observação, todo script comentado é feito internamente no FileOutputStream
        /*final File file = new File(MY_FILE);

        boolean fileIsCreated = false;

        if(!file.exists()){
            fileIsCreated = file.createNewFile();
        }

        if(fileIsCreated || file.exists()){ */
        final OutputStream output = new FileOutputStream(MY_FILE);
        final String content = "Olá Mundo IO\nola mundo io";

        output.write(content.getBytes("UTF-8"));
        output.close();
        System.out.println("Dados gravados no arquivo.");
        //}
    }

    private void readFile() {
        //Imput Stream le bytes. Então tem que converter em texto.
        // 1 byte        = 8 bits
        // 1 char UTF-8  = 1 byte
        // 1 char UTF-16 = 2 bytes
        //Usando input stream:
        try (final InputStream input = new FileInputStream(MY_FILE)) {

            int content;
            while((content = input.read()) != -1) {
                //System.out.print(content + " ");
                System.out.print((char)content);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n++++++++++++++++++++++++++++++++++++");

        //usando File Reader
        try (final FileReader file = new FileReader(MY_FILE) ) {

            int numberValue = file.read();
            while (numberValue != -1) {
                System.out.print((char)numberValue);
                numberValue = file.read();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UsingIO();
    }
}

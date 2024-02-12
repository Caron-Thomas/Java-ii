package br.edu.utfpr.sistemarquivos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.stream.Stream;

public enum Command {

    LIST() {
        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("LIST") || commands[0].startsWith("list");
        }

        @Override
        Path execute(Path path) throws IOException {

           try(final Stream<Path> dir = Files.list(path)) {
               dir.forEach(d-> System.out.println(d.getFileName()));
           }

           return path;
        }
    },
    SHOW() {
        private String[] parameters = new String[]{};

        @Override
        void setParameters(String[] parameters) {
            this.parameters = parameters;
        }

        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("SHOW") || commands[0].startsWith("show");
        }

        @Override
        Path execute(Path path) {

            String filePath = path.toString();
            final String concat;
            boolean match;
            final Path file;
            final FileReader fileReader = new FileReader();

            if(Arrays.stream(parameters).count() > 2)
                throw new UnsupportedOperationException("Verify file name...");
            try{
                String fileName = Arrays.stream(parameters).skip(1)
                        .findFirst()
                        .orElse(null);

                match = fileName.contains(".txt");

                if(!match)
                    throw new UnsupportedOperationException("You have permission to open only .txt type documents... Sorry :(");

                concat = filePath.concat(File.separator).concat(fileName.toLowerCase());

                file = Paths.get(concat);


                fileReader.read(file);
            } catch(Exception e) {
                throw new UnsupportedOperationException("Verify file name...");
            }


            return path;
        }
    },
    BACK() {

        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("BACK") || commands[0].startsWith("back");
        }

        @Override
        Path execute(Path path) {

            Path directory;

            boolean match = path.endsWith("hd");
            directory = path.getParent();

            if(!match)
                return directory;

            throw new UnsupportedOperationException("You have no permission to go any further... Sorry :( ");
        }
    },
    OPEN() {
        private String[] parameters = new String[]{};

        @Override
        void setParameters(String[] parameters) {
            this.parameters = parameters;
        }

        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("OPEN") || commands[0].startsWith("open");
        }

        @Override
        Path execute(Path path) {

            String directory;
            final String concat;
            boolean match;
            directory = path.toString();

            if(Arrays.stream(parameters).count() > 2)
                throw new UnsupportedOperationException("Verify directory name...");

            String dir = Arrays.stream(parameters).skip(1)
                                                  .findFirst()
                                                  .orElse(null);
            if(dir.contains("."))
                throw new UnsupportedOperationException("You've tried to open a file with a navigate command." +
                        " Please, use the [ SHOW ] command. And always, have a nice day!");

            try(final Stream<Path> addPath = Files.list(path)) {
                concat = directory.concat(File.separator).concat(dir.toLowerCase());
                match = addPath.anyMatch(l -> l.equals(Path.of(concat)));

                if(match)
                    return Path.of(concat);

            }
            catch(Exception e) {
                throw new UnsupportedOperationException("Enter a directory name...");
            }
            throw new UnsupportedOperationException("Verify directory name...");
        }
    },
    DETAIL() {
        private String[] parameters = new String[]{};

        @Override
        void setParameters(String[] parameters) {
            this.parameters = parameters;
        }

        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("DETAIL") || commands[0].startsWith("detail");
        }

        @Override
        Path execute(Path path) {

            // TODO implementar conforme enunciado - tem que mandar o Path certin mano!
            String pathStr = path.toString();
            final String concat;
            final Path file;

            if(Arrays.stream(parameters).count() > 2)
                throw new UnsupportedOperationException("Verify file name...");
            try{
                String fileName = Arrays.stream(parameters).skip(1)
                        .findFirst()
                        .orElse(null);

                concat = pathStr.concat(File.separator).concat(fileName.toLowerCase());

                file = Paths.get(concat);
                BasicFileAttributeView attributeView = Files.getFileAttributeView(file, BasicFileAttributeView.class);

                if(attributeView.readAttributes().isDirectory())
                    System.out.println("this is a directory;");

                if(attributeView.readAttributes().isRegularFile())
                    System.out.println("this is a file;");

                if(attributeView.readAttributes().isOther())
                    System.out.println("actually, we don't know exactly what this is...");

                System.out.println("the size of file or directory is: " + attributeView.readAttributes().size());
                System.out.println("creation date: " + attributeView.readAttributes().creationTime());
                System.out.println("lst edited at: " + attributeView.readAttributes().lastModifiedTime());

            } catch(Exception e) {
                throw new UnsupportedOperationException("Verify file name...");
            }
            return path;
        }
    },
    EXIT() {
        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("EXIT") || commands[0].startsWith("exit");
        }

        @Override
        Path execute(Path path) {
            System.out.print("Saindo...");
            return path;
        }

        @Override
        boolean shouldStop() {
            return true;
        }
    };

    abstract Path execute(Path path) throws IOException;

    abstract boolean accept(String command);

    void setParameters(String[] parameters) {
    }

    boolean shouldStop() {
        return false;
    }

    public static Command parseCommand(String commandToParse) {

        if (commandToParse.isBlank()) {
            throw new UnsupportedOperationException("Type something...");
        }

        final var possibleCommands = values();

        for (Command possibleCommand : possibleCommands) {
            if (possibleCommand.accept(commandToParse)) {
                possibleCommand.setParameters(commandToParse.split(" "));
                return possibleCommand;
            }
        }

        throw new UnsupportedOperationException("Can't parse command [%s]".formatted(commandToParse));
    }
}

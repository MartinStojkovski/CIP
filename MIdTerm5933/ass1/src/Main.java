import java.io.*;
import java.nio.file.*;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Path path = Paths.get("todo.txt");

        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Enter command:");

                String input = reader.readLine();

                if (input.startsWith("add")) {

                    if (input.length() > 4) {
                        String task = input.substring(4).trim(); // Trim any extra spaces

                        try {
                            Files.write(path, (task + "\n").getBytes(), StandardOpenOption.APPEND);
                            System.out.println("Task added.");
                        } catch (IOException e) {
                            System.out.println("Error writing to file: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid task format. Use: add <task>");
                    }

                } else if (input.equals("view")) {
                    try (Stream<String> lines = Files.lines(path)) {
                        long[] lineCount = {0};

                        lines.forEach(line -> {
                            lineCount[0]++;
                            System.out.println(lineCount[0] + ". " + line);
                        });

                        if (lineCount[0] == 0) {
                            System.out.println("No tasks found.");
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading from file: " + e.getMessage());
                    }

                } else if (input.equals("exit")) {
                    break;
                } else {
                    System.out.println("Invalid command");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }
}

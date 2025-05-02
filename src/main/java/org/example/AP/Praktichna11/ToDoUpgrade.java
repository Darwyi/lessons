package org.example.AP.Praktichna11;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class ToDoUpgrade {
    private static final int MAX_ENTRIES = 50;
    private static final Scanner s = new Scanner(System.in);
    private static final DateTimeFormatter datetime_format = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static Path filePath = Paths.get("src//main//resources//File.txt");

    public static void main(String[] args) {

        while (true) {
            System.out.print("Load existing ToDosFile or create new?[new/load]: ");
            String extdf = s.nextLine();
            if (extdf.equalsIgnoreCase("new")) {
                CreateToDosFile();
                break;
            } else if (extdf.equalsIgnoreCase("load")) {
                LoadToDosFile();
                break;
            } else {
                System.out.println("Wrong input!");
            }
        }


        try {
            System.out.print("""
                    Enter your date format or choose standart (example, yyyy-MM-dd HH:mm)
                    (or you can use standart format ISO_LOCAL_DATE_TIME) [y/n]:\s""");
            String formatInput = s.nextLine();
            if (formatInput.equalsIgnoreCase("y")) {
                formatInput = s.nextLine();
                DateTimeFormatter.ofPattern(formatInput);
            } else if (formatInput.equalsIgnoreCase("n")) {
                while (true) {
                    System.out.println("""
                                    ToDos
                    AddNew Todo(1)          DeleteToDo(2)
                    ViewAllTodos(3)         Exit(4)
                    """);
                    String choose = s.nextLine();

                    switch (choose) {
                        case "1":
                            AddNewTodo();
                            break;
                        case "2":
                            DeleteTodo();
                            break;
                        case "3":
                            ViewAllToDos();
                            break;
                        case "4":
                            Exit();
                            break;
                        default:
                            System.out.println("Enter valid choose!");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void Exit() {
        System.out.print("Would you like to save your file? [y/n]: ");
        String saveChoice = s.nextLine();
        if (saveChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter absolute path where to save your file: (example: C:\\Users\\User\\Desktop\\diary.txt): ");
            String savePath = s.nextLine();

            try {
                BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(filePath.getFileName())));
                BufferedWriter writer = new BufferedWriter(new FileWriter(savePath));
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
                reader.close();
                writer.close();
                s.close();
                System.out.println("Todo successful saved to: " + savePath);
            } catch (IOException e) {
                System.out.println("Error saving file. Try review you filepath");
            }
        }
    }

    private static void LoadToDosFile() {
        System.out.print ("Input filepath of existing ToDos file: ");
        try {
            filePath = Path.of(s.nextLine());
        } catch (InvalidPathException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void CreateToDosFile() {
        while (true) {
            System.out.print("Enter FileName: ");
            String filename = s.nextLine();
            File file = new File(filename);
            filePath = Path.of(file.getPath());
            return;
        }
    }

    private static void AddNewTodo() {
        try {
            System.out.print("Enter date and time by you chosen format: ");
            String input = s.nextLine();
            LocalDateTime date = LocalDateTime.from(datetime_format.parse(input));

            System.out.println("Enter todo (end with word 'stop'):");
            StringBuilder entryText = new StringBuilder();
            while (true) {
                String line = s.nextLine();
                if (line.equalsIgnoreCase("stop")) break;
                entryText.append(line).append(System.lineSeparator());
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(filePath.getFileName()), true));
            writer.write(String.valueOf(date));
            writer.newLine();
            writer.write(entryText.toString());
            writer.newLine();
            writer.close();
            System.out.println("Todo edded!");
        } catch (DateTimeException e) {
            System.out.println("Wrong date format." + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error during writing to file occured." + e.getMessage());
        }
    }

    private static void ViewAllToDos() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(filePath.getFileName())));
            String line;
            int entryNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                System.out.println("\nTodo №" + entryNumber++);
                System.out.println("Date: " + line);
                while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                    System.out.println(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("While reading file error occurred.");
        }
    }

    private static void DeleteTodo() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(filePath.getFileName())));
            String[][] entries = new String[MAX_ENTRIES][];
            int count = 0;
            String line;
            while ((line = reader.readLine()) != null && count < MAX_ENTRIES) {
                if (line.trim().isEmpty()) continue;
                String[] entry = new String[102];
                int lineCount = 0;
                entry[lineCount++] = line; // дата
                while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                    entry[lineCount++] = line;
                }
                String[] finalEntry = new String[lineCount];
                for (int i = 0; i < lineCount; i++) {
                    finalEntry[i] = entry[i];
                }
                entries[count++] = finalEntry;
            }
            reader.close();

            if (count == 0) {
                System.out.println("Your file hasn't todos.");
                return;
            }

            for (int i = 0; i < count; i++) {
                System.out.println("\nTodo №" + (i + 1));
                for (int j = 0; j < entries[i].length; j++) {
                    System.out.println(entries[i][j]);
                }
            }

            System.out.print("Enter todo num to delete: ");
            int index = Integer.parseInt(s.nextLine()) - 1;
            if (index < 0 || index >= count) {
                System.out.println("Wrong todo num.");
                return;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(filePath.getFileName())));
            for (int i = 0; i < count; i++) {
                if (i == index) continue;
                for (int j = 0; j < entries[i].length; j++) {
                    writer.write(entries[i][j]);
                    writer.newLine();
                }
                writer.newLine(); // пустий рядок між записами
            }
            writer.close();
            System.out.println("Todo deleted.");
        } catch (IOException e) {
            System.out.println("Error occurred while editing file.");
        } catch (NumberFormatException e) {
            System.out.println("Wrong num todo.");
        }
    }
}

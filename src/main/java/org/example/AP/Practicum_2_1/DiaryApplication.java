package org.example.AP.Practicum_2_1;

import org.example.AP.Practicum_2_1.utils.FilesystemUtilities;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DiaryApplication {
    private final Scanner s = new Scanner(System.in);
    private File file;
    private DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private boolean running = true;

    public void run() {
        while (true) {
            System.out.print("Load existing ToDosFile or create new?[new/load]: ");
            String choice = s.nextLine();
            if (choice.equalsIgnoreCase("new")) {
                file = FilesystemUtilities.createFile(s);
                programm();
                break;
            } else if (choice.equalsIgnoreCase("load")) {
                file = FilesystemUtilities.loadFile(s);
                programm();
                break;
            } else {
                System.out.println("Wrong input!");
            }
        }
    }

    private void programm() {
        while (running) {
            System.out.println("""
                                ToDos
                    AddNew Todo(1)          DeleteToDo(2)
                    ViewAllTodos(3)         Exit(4)
                    """);
            String choose = s.nextLine();
            switch (choose) {
                case "1" -> addNewTodo();
                case "2" -> deleteTodo();
                case "3" -> viewAllToDos();
                case "4" -> exit();
                default -> System.out.println("Enter valid choose!");
            }
        }
    }

    private void addNewTodo() {
        try {
            System.out.print("Enter date and time in ISO format: ");
            String input = s.nextLine();
            LocalDateTime date = LocalDateTime.parse(input, format);

            System.out.println("Enter todo (end with word 'stop'):");
            String entryText = "";
            while (true) {
                String line = s.nextLine();
                if (line.equalsIgnoreCase("stop")) break;
                entryText += line + System.lineSeparator();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(date.toString());
            writer.newLine();
            writer.write(entryText);
            writer.newLine();
            writer.close();
            System.out.println("Todo added!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllToDos() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
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

    private void deleteTodo() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String[][] entries = new String[50][];
            int count = 0;
            String line;
            while ((line = reader.readLine()) != null && count < 50) {
                if (line.trim().isEmpty()) continue;
                String[] entry = new String[100];
                int lineCount = 0;
                entry[lineCount++] = line;
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

            for (int i = 0; i < count; i++) {
                System.out.println("\nTodo №" + (i + 1));
                for (String l : entries[i]) {
                    System.out.println(l);
                }
            }

            System.out.print("Enter todo num to delete: ");
            int index = Integer.parseInt(s.nextLine()) - 1;
            if (index < 0 || index >= count) {
                System.out.println("Wrong todo num.");
                return;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < count; i++) {
                if (i == index) continue;
                for (String l : entries[i]) {
                    writer.write(l);
                    writer.newLine();
                }
                writer.newLine();
            }
            writer.close();
            System.out.println("Todo deleted.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    private void exit() {
        System.out.print("Would you like to save your file? [y/n]: ");
        String saveChoice = s.nextLine();
        if (saveChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter full path to save file: ");
            String savePath = s.nextLine();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                BufferedWriter writer = new BufferedWriter(new FileWriter(savePath));
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
                reader.close();
                writer.close();
                running = false;
                System.out.println("Saved to: " + savePath);
            } catch (IOException e) {
                System.out.println("Saving error.");
            }
        } else {
            running = false;
        }
    }
}

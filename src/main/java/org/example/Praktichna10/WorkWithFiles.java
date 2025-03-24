package org.example.Praktichna10;

import java.io.*;
import java.util.Scanner;

public class WorkWithFiles {
    public static Scanner s = new Scanner(System.in);
    public static FileReader fr;
    public static FileWriter fw;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("""
                    Write to the .txt file (1)       Read all .txt file (2)
                                            Exit program (3)""");

            switch (s.nextLine()) {
                case "1":
                    WriteFile();
                    break;
                case "2":
                    ReadFile();
                    break;
                case "3":
                    running = false;
                    break;
            }
        }
    }

    public static void ReadFile() {
            try {
                fr = new FileReader("File.txt");

                String FileContent = "";

                while (fr.ready()) {
                    FileContent += (char) fr.read();
                }

                System.out.println(FileContent);
            } catch (FileNotFoundException e) {
                System.out.println("File not found! " + e.getMessage());
            } catch (IOException e) {
                System.out.println("File couldn't read normally!");
            } finally {
                try {
                    if (fr != null) {
                        fr.close();
                    }
                    if (fw != null) {
                        fw.close();
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while closing file!");
                }
            }
    }

    public static void WriteFile() {
            try {
                fw = new FileWriter("File.txt", true);

                System.out.print("You can write anything to this file: ");
                String text = s.nextLine();

                fw.write(text + " \n");
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
            } catch (IOException e) {
                System.out.println("An error occurred while writing file!");
            } finally {
                try {
                    if (fw != null) {
                        fw.close();
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while closing file!");
                }
            }
    }
}

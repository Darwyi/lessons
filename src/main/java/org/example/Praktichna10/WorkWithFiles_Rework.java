package org.example.Praktichna10;

import javax.sound.sampled.Line;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WorkWithFiles_Rework {
    public static Scanner s = new Scanner(System.in);


    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("""
                    Write to the .txt file (1)       Read all .txt file (2)
                    Print diapason*(3)               Exit program (3)
                    
                    *: first number start,second number - end""");

            switch (s.nextLine()) {
                case "1":
                    WriteFile();
                    break;
                case "2":
                    ReadFile();
                    break;

                case "3":
                    PrintFromNumToNum();

                case "4":
                    running = false;
                    s.close();
                    break;
            }
        }
    }

    public static void PrintFromNumToNum() {

        int Start = 0;
        int End = 0;

        ArrayList<String> FileContent = new ArrayList<>();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader("File.txt"))) {
            while ((line = reader.readLine()) != null) {
                FileContent.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Your file has " + FileContent.size() + " lines." );

        try {
            System.out.print("Input start of diapason: ");

            Start = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Input a number!");
        }

        try {
            System.out.print("Input end of diapason: ");

            End = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Input a number!");
        }

        s.close();

        for (int i = Start - 1; i < End && i < FileContent.size(); i++) {
            System.out.println((i + 1) + ": " + FileContent.get(i));
        }

    }

    public static void ReadFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader("File.txt"))) {
            String line;
            int LineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println(LineNumber++ + " " + line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriteFile() {

        ArrayList<String> AddingContent = new ArrayList<>(); // щоб не було пытань про такий вид массиву то я читав про нього в цій статті
        //https://javarush.com/groups/posts/dinamicheskie-massivy-java
        // він мені знадобився тому-що я не зрозумів як зробити самозмінуванний массив, тобто щоб він не потребував ініціалізації (введення обмежень)
        ArrayList<String> PoperedniLines = new ArrayList<>();
        String line;
        int LineNum = 1;


        try (BufferedReader reader = new BufferedReader(new FileReader("File.txt"))) {
            while ((line = reader.readLine()) != null) {
                PoperedniLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int LineNumber = PoperedniLines.size() + 1;
        System.out.print(LineNumber++ + " ");

        while(!(line = s.nextLine()).equals("stop")) {
            System.out.print(LineNumber + " ");
            AddingContent.add(line);
            LineNumber++;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("File.txt", true))) {

            for (String l : AddingContent) {
                writer.write(l + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

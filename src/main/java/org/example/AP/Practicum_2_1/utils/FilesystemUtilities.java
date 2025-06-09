package org.example.AP.Practicum_2_1.utils;

import java.io.File;
import java.util.Scanner;

public class FilesystemUtilities {
    public static File createFile(Scanner scanner) {
        System.out.print("Enter FileName: ");
        String filename = scanner.nextLine();
        return new File(filename);
    }

    public static File loadFile(Scanner scanner) {
        while (true) {
            System.out.print("Input filepath: ");
            String filepath = scanner.nextLine();
            File file = new File(filepath);
            if (file.exists()) {
                return file;
            } else {
                System.out.println("File not found. Try again.");
            }
        }
    }
}

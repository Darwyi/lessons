package org.example.Praktichna11;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ToDo {
    private static final Scanner s = new Scanner(System.in); // про final прочитав тут https://javarush.com/groups/posts/591-vot-tak-final
    private static final String[] ToDosDate = new String[50];
    private static final String[][] ToDos = new String[50][];
    private static int AddToDoCount = 0;

    public static void main(String[] args) {
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
                    s.close();
                    break;
                default:
                    System.out.println("Enter valid choose!");
                    break;
            }
        }
    }

    public static void ViewAllToDos() {
        if (AddToDoCount == 0) {
            System.out.println("Ypu need add some todos firstly!");
        } else {
            for (int i = 0; i < AddToDoCount + 1; i++) {
                if (ToDosDate[i] == null) {
                    System.out.print("");
                }
                if (ToDosDate[i] != null) {
                    System.out.println("Todo: " + i + "\n\tDate: " + ToDosDate[i]);
                    for (String line : ToDos[i]) {
                        if (line == null) {
                            System.out.print("");
                        } else {
                            System.out.println("\t" + line);
                        }
                    }
                }
            }
        }
    }

    public static void DeleteTodo() {
        if (AddToDoCount == 0){
            System.out.println("You dont have todos now");
            return;
        }

        ViewAllToDos();
        System.out.println("Виберіть запис який хочете видалитиЖ ");

        String chooseToDelete = s.nextLine();

        int intCTD = 0;

        try {
            intCTD = Integer.parseInt(chooseToDelete);
        } catch (Exception e) {
            System.out.println("Enter a valid number!");
        }

        if (intCTD > AddToDoCount || intCTD < 0) {
            System.out.println("This Todo is not exists!");
            return;
        }

        for (int i = 0; i < AddToDoCount; i++) {
            ToDosDate[i] = null;
            ToDos[i] = null;
            AddToDoCount--;
        }
    }

    public static void AddNewTodo() {
        if (AddToDoCount >= ToDos.length) {
            System.out.println("You can't have more than 50 Todos!");
            return;
        }

        String DateInput;
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        while (true) {
            System.out.print("Enter date (yyyy-MM-dd): ");
            DateInput = s.nextLine();
            try {
                date = dateFormat.parse(DateInput);
                break;
            } catch (ParseException e) {
                System.out.println("Not a valid date!");
            }
        }

        System.out.println("Add new Todo (enter 'stop' to exit from adding(Todo saves), max todo length is 100 lines): ");

        int lineCount = 0;
        String[] lines = new String[100];

        while (true) {
            String line = s.nextLine();
            if (line.equalsIgnoreCase("stop")) {
                break;
            }

            if (lineCount < ToDos.length) {
                lines[lineCount++] = line;
            } else {
                System.out.println("Max todo length is 100 lines.");
                break;
            }
        }

        String[] entryLines = new String[lineCount];
        for (int i = 0; i < lineCount; i++) {
            entryLines[i] = lines[i];
        }

        ToDosDate[AddToDoCount] = DateInput;
        ToDos[AddToDoCount] = entryLines;
        AddToDoCount++;

        System.out.println("Todo added");
    }
}

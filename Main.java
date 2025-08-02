package Student_Report_Card_System;

import java.util.*;

public class Main {

    public static boolean login(Scanner sc) {
        String USERNAME = "admin";
        String PASSWORD = "admin123";
        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Enter Admin Username: ");
            String inputUser = sc.nextLine();
            System.out.print("Enter Admin Password: ");
            String inputPass = sc.nextLine();

            if (inputUser.equals(USERNAME) && inputPass.equals(PASSWORD)) {
                System.out.println("Login successful. Welcome, Admin!");
                return true;
            } else {
                attempts--;
                System.out.println("Incorrect credentials. Attempts left: " + attempts);
            }
        }

        System.out.println("Too many failed attempts. Exiting...");
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Admin Login First
        if (!login(sc)) {
            sc.close();
            return; // exit if login fails
        }

        ReportCardSystem system = new ReportCardSystem();
        int choice;

        do {
            System.out.println("\n==== Student Report Card System ====");
            System.out.println("1. Add Student Report");
            System.out.println("2. View All Reports");
            System.out.println("3. Search by Roll Number");
            System.out.println("4. Search by Name");
            System.out.println("5. Delete Student Record");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter number of subjects: ");
                    int subjectCount = sc.nextInt();
                    sc.nextLine();

                    HashMap<String, Integer> subjectMarks = new HashMap<>();
                    for (int i = 0; i < subjectCount; i++) {
                        System.out.print("Enter subject name " + (i + 1) + ": ");
                        String subject = sc.nextLine();
                        System.out.print("Enter marks for " + subject + ": ");
                        int marks = sc.nextInt();
                        sc.nextLine();
                        subjectMarks.put(subject, marks);
                    }

                    system.addStudent(new Student(roll, name, subjectMarks));
                    break;

                case 2:
                    system.viewAllReports();
                    break;

                case 3:
                    System.out.print("Enter Roll Number to search: ");
                    int searchRoll = sc.nextInt();
                    system.searchByRollNo(searchRoll);
                    break;

                case 4:
                    System.out.print("Enter name to search: ");
                    String searchName = sc.nextLine();
                    system.searchByName(searchName);
                    break;

                case 5:
                    System.out.print("Enter Roll Number to delete: ");
                    int deleteRoll = sc.nextInt();
                    system.deleteByRollNo(deleteRoll);
                    break;

                case 6:
                    System.out.println("Exiting system...");
                    break;

            } } while (choice != 6);


        sc.close();
    }

}

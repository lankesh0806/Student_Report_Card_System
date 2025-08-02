package Student_Report_Card_System;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ReportCardSystem {
    private ArrayList<Student> students;
    private final String FILE = "report.dat";

    public ReportCardSystem() {
        students = loadFromFile();
    }

    public void addStudent(Student s) {
        students.add(s);
        saveToFile();
        System.out.println("Student report saved successfully.");
    }

    public void searchByName(String name) {
        boolean found = false;
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No student found with name: " + name);
        }
    }

    public void deleteByRollNo(int rollNo) {
        boolean removed = false;

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getRollNo() == rollNo) {
                iterator.remove();
                removed = true;
                break;
            }
        }

        if (removed) {
            saveToFile();
            System.out.println("Student with roll number " + rollNo + " has been deleted.");
        } else {
            System.out.println("Student not found with roll number: " + rollNo);
        }
    }

    public void viewAllReports() {
        if (students.isEmpty()) {
            System.out.println("No student reports available.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public void searchByRollNo(int rollNo) {
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                System.out.println(s);
                return;
            }
        }
        System.out.println("Student with roll number " + rollNo + " not found.");
    }

    private void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    private ArrayList<Student> loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            return (ArrayList<Student>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}


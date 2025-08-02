package Student_Report_Card_System;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Student implements Serializable {
    private int rollNo;
    private String name;
    private HashMap<String, Integer> subjectMarks;
    private int total;
    private double average;
    private String grade;

    public Student(int rollNo, String name, HashMap<String, Integer> subjectMarks) {
        this.rollNo = rollNo;
        this.name = name;
        this.subjectMarks = subjectMarks;
        calculate();
    }

    private void calculate() {
        total = 0;
        for (int mark : subjectMarks.values()) {
            total += mark;
        }
        average = total / (double) subjectMarks.size();

        if (average >= 90) grade = "A+";
        else if (average >= 75) grade = "A";
        else if (average >= 60) grade = "B";
        else if (average >= 45) grade = "C";
        else grade = "Fail";
    }

    public int getRollNo() { return rollNo; }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nRoll No: ").append(rollNo)
                .append("\nName: ").append(name)
                .append("\nMarks:\n");

        for (Map.Entry<String, Integer> entry : subjectMarks.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        sb.append("Total: ").append(total)
                .append("\nAverage: ").append(String.format("%.2f", average))
                .append("\nGrade: ").append(grade);

        return sb.toString();
    }
}

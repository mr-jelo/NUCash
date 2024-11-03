package com.example.nucash;

import java.util.Arrays;
import java.util.List;

public class StudentData {
    private static final List<Student> studentList = Arrays.asList(
            new Student("John Doe", "2023-000001"),
            new Student("Jane Smith", "2023-000002"),
            new Student("Michael Johnson", "2024-000003"),
            new Student("Emily Davis", "2024-000004"),
            new Student("William Brown", "2024-000005"),
            new Student("Olivia Garcia", "2024-000006"),
            new Student("James Miller", "2024-000007"),
            new Student("Sophia Wilson", "2024-000008"),
            new Student("Benjamin Anderson", "2024-000009"),
            new Student("Isabella Thomas", "2024-000010")
    );

    public static List<Student> getStudentList() {
        return studentList;
    }
}

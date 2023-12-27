package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();

        try {
            studentDAO.createTable();

            Student newStudent = new Student("John Doe", 20);
            studentDAO.insertStudent(newStudent);

            for (Student student : studentDAO.getAllStudents()) {
                System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName() + ", Age: " + student.getAge());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
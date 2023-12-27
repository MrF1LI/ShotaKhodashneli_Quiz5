package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:~/studentsDB", "sa", "");
    }

    public void createTable() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS students (" +
                    "ID IDENTITY NOT NULL PRIMARY KEY, " +
                    "name VARCHAR(50), " +
                    "age INT)");
        }
    }

    public void insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.executeUpdate();
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getLong("ID"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                students.add(student);
            }
        }
        return students;
    }

}

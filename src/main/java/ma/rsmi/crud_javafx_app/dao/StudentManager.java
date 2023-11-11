package ma.rsmi.crud_javafx_app.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ma.rsmi.crud_javafx_app.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentManager {

    private static Connection getConnection() {
        ResourceBundle rb = ResourceBundle.getBundle("db-config");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    rb.getString("mysql.url"),
                    rb.getString("user"),
                    rb.getString("password"));

            System.out.println("✅ Connection succeeded: ");


        } catch (SQLException e) {
            System.out.println("❌ Connection Failed: ");
            e.printStackTrace();
        }

        return connection;
    }

    public  ObservableList<Student> findAll() {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<Student> students = FXCollections.observableArrayList();

        connection = getConnection();
        String query = "SELECT * FROM students";
        try {
            pstm = connection.prepareStatement(query);


            rs = pstm.executeQuery();

            while (rs.next())
                students.add(new Student(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));
            System.out.println("✅ query succeeded: " + query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("❌ query failed: " + query);
        } finally {
            try {
                if (connection != null) connection.close();
                if (pstm != null) pstm.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

        return students;
    }

    public  void saveStudent(Student student) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        connection = getConnection();
        String query = "INSERT INTO students(firstname, lastname, course) VALUES(?, ?, ?)";
        try {
            pstm = connection.prepareStatement(query);
            pstm.setString(1, student.getFirstname());
            pstm.setString(2, student.getLastname());
            pstm.setString(3, student.getCourse());

            pstm.executeUpdate();
            System.out.println("✅ query succeeded: " + query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("❌ query failed: " + query);
        } finally {
            try {
                if (connection != null) connection.close();
                if (pstm != null) pstm.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

    public  void updateStudent(Student student) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        connection = getConnection();
        String query = "SELECT * FROM students WHERE id=?";

        try {
            pstm = connection.prepareStatement(query);
            pstm.setInt(1, student.getId());
            rs = pstm.executeQuery();
            System.out.println("✅ query succeeded: " + query);

            if (rs.isBeforeFirst()) {
                while (rs.next()){
                    Student oldStudent = new Student(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)

                    );
                }

                try {
                    query = "UPDATE students SET firstname=?, lastname=?, course=? WHERE id=?";
                    pstm = connection.prepareStatement(query);
                    pstm.setInt(4, student.getId());
                    pstm.setString(1, student.getFirstname());
                    pstm.setString(2, student.getLastname());
                    pstm.setString(3, student.getCourse());
                    pstm.executeUpdate();
                    System.out.println("✅ query succeeded: " + query);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("❌ query failed: " + query);
                }
            }else  {
                System.out.println("❌ No student were found with the given id: '" + student.getId() +"'");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("❌ query failed: " + query);
        } finally {
            try {
                if (connection != null) connection.close();
                if (pstm != null) pstm.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public  void deleteStudent(int id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        connection = getConnection();
        String query = "SELECT * FROM students WHERE id=?";

        try {
            pstm = connection.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            System.out.println("✅ query succeeded: " + query);

            if (rs.isBeforeFirst()) {
                try {
                    query = "DELETE FROM students WHERE id=?";
                    pstm = connection.prepareStatement(query);
                    pstm.setInt(1, id);
                    pstm.executeUpdate();
                    System.out.println("✅ query succeeded: " + query);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("❌ query failed: " + query);
                }
            }else  {
                System.out.println("❌ No student were found with the given id: '" + id +"'");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("❌ query failed: " + query);
        } finally {
            try {
                if (connection != null) connection.close();
                if (pstm != null) pstm.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public  void main(String[] args) {
        //saveStudent(new Student("Sara", "benyahya", "JEE: understanding Servlets ans JSP "));
        //System.out.println("Students -> " + findAll());
       // updateStudent(new Student(3,"Redouane","Guerchal","FULL STACK Web app with Spring boot and Angular."));

    }
}

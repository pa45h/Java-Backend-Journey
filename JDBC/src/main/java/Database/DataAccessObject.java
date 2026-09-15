package Database;

import java.sql.*;
import java.util.Scanner;

class Student {
    int id;
    String name;
    int age;
    Date dob;
}

class StudentDao {

    Connection connection;

    StudentDao() {
        String URL = "jdbc:mysql://localhost:3306/jdb";
        String USER = "root";
        String PWD = "9601704554p";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PWD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addStudent(int id, String name, int age, String dob) {

        try {
            String sql = "INSERT INTO std VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setDate(4, Date.valueOf(dob));
            preparedStatement.executeUpdate();
            System.out.println("Data Added Successfully!");
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchStudent() {
        try {
            String sql = "SELECT * FROM std";
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            System.out.println("ID\tName\tAge\tDOB");
            while (res.next()) {
                System.out.println(res.getInt("id") + "\t" + res.getString("name") + "\t" + res.getInt("age") + "\t" + res.getDate("dob"));
            }
            statement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchStudent(int id) {
        try {
            String sql = "SELECT * FROM std WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();
            System.out.println("ID\tName\tAge\tDOB");
            while (res.next()) {
                System.out.println(res.getInt("id") + "\t" + res.getString("name") + "\t" + res.getInt("age") + "\t" + res.getDate("dob"));
            }
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void removeStudent(int id) {
        try {
            String sql = "DELETE FROM std WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Data Removed Successfully!");
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

public class DataAccessObject {
    public static void main(String[] args) {

        System.out.println("***** JDBC CRUD OPERATIONS *****");
        Scanner sc = new Scanner(System.in);
        StudentDao sd = new StudentDao();
        while (true) {
            System.out.println("\n\t1. Add New Data");
            System.out.println("\t2. Fetch All Data");
            System.out.println("\t3. Fetch Data by id");
            System.out.println("\t4. Remove Date");
            System.out.println("\t5. Exit");

            System.out.println("\nSelect Operation : ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Enter id : ");
                    int id = sc.nextInt();
                    System.out.print("Enter name : ");
                    String name = sc.next();
                    System.out.print("Enter age : ");
                    int age = sc.nextInt();
                    System.out.print("Enter age : ");
                    String dob = sc.next();
                    sd.addStudent(id, name, age, dob);
                    break;
                case 2:
                    sd.fetchStudent();
                    break;
                case 3:
                    System.out.print("Enter id : ");
                    id = sc.nextInt();
                    sd.fetchStudent(id);
                    break;
                case 4:
                    System.out.print("Enter id : ");
                    id = sc.nextInt();
                    sd.removeStudent(id);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Enter Valid Choice!");
                    break;
            }
        }
    }
}

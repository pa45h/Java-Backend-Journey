package Database;

import java.sql.*;
import java.util.Scanner;

public class AddData {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdb", "root", "9601704554p");

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter id : ");
            int id = sc.nextInt();
            System.out.print("Enter name : ");
            String name = sc.next();
            System.out.print("Enter age : ");
            int age = sc.nextInt();
            System.out.print("Enter dob : ");
            String dt = sc.next();
            Date dob = Date.valueOf(dt);

            String query = "insert into std values (?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setDate(4, dob);

            int rs = ps.executeUpdate();

            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from std");

            System.out.println("\n\n");
            while (res.next()) {
                System.out.println(res.getInt("id") + " " + res.getString("name") + " " + res.getInt("age") + " " + res.getDate("dob"));
            }

            st.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

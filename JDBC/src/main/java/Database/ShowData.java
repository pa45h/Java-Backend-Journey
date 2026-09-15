package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShowData {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdb", "root", "9601704554p");
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from std");

            while (res.next()) {
                System.out.println(res.getInt("id") + " " + res.getString("name") + " " + res.getInt("age") + " " + res.getDate("dob"));
            }

            st.close();
            conn.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package web.app.db;

import java.sql.*;

/**
 * Created by dan on 13.08.2017.
 */
public class UserDBAccess {


    final static String URL = "jdbc:postgresql://54.93.65.5:5432/5Dan";
    final static String USERNAME = "fasttrackit_dev";
    final static String PASSWORD = "fasttrackit_dev";

    public int isUserPwdOK(String user, String pwd) {

        int found = -1;
        try {
            Class.forName("org.postgresql.Driver");

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);


            // 4. create a query statement
            Statement st = conn.createStatement();

            // 5. execute a query
            String query = "SELECT id FROM useri where nume='"+user+"' and parola='"+pwd+"'";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);

            // 6. iterate the result set and print the values


            while (rs.next()) {
                found = rs.getInt("id");

            }

            // 7. close the objects
            rs.close();
            st.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return found;

    }


    // instead of @Test
    public static void main(String[] args) {
        UserDBAccess udb = new UserDBAccess();
        int gasit;
        gasit = udb.isUserPwdOK("dan", "password2");
        System.out.println(gasit);

    }

}


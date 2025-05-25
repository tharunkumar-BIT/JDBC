import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String password = "";
        String query = "select * from employee";

        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        // initially rs will be pointing to headings of the columns
        rs.next(); // move to the first record
        System.out.println("Id is " + rs.getInt(1));
        System.out.println("Name is " + rs.getString(2));
        System.out.println("Salary is " + rs.getInt(3));

        con.close();
    }
}

import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws Exception {
        readRecords();
//        insertRecords();
    }

    public static void readRecords() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String password = "";
        String query = "select * from employee";

        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query); // use when reading data
        // returns result set.

        // initially rs will be pointing to headings of the columns
        // rs.next(); // move to the first record
        while(rs.next()) {
            System.out.println("Id is " + rs.getInt(1));
            System.out.println("Name is " + rs.getString(2));
            System.out.println("Salary is " + rs.getInt(3));
        }
        con.close();
    }

    // insert records by manually hardcoding
    public static void insertRecords() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String password = "";
        String query = "insert into employee values (4, 'Priya', 250000)";

        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query); // use when updating a data
        // returns number of rows affected.

        System.out.println("Number of rows affected:" + rows);
        con.close();
    }
}

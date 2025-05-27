import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws Exception {
//        readRecords();
//        insertRecords();
//        insertVars();
//        insertUsingPst();
//        delete();
//        update();
//        updatePst();
//        sp();
        sp2();
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

    // insert records using variables
    public static void insertVars() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String password = "";

        int id = 5;
        String name = "Varun";
        int salary = 300000;

        // "insert into employee values (5, 'Varun', 300000)"
        String query = "insert into employee values (" + id + ", '" + name + "', " + salary + ")";
        // difficult to write this line

        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query); // use when updating a data
        // returns number of rows affected.

        System.out.println("Number of rows affected:" + rows);
        con.close();
    }

    // insert records with prepared statement
    public static void insertUsingPst() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String password = "";

        int id = 6;
        String name = "Karthi";
        int salary = 350000;

        // "insert into employee values (5, 'Varun', 300000)"
        String query = "insert into employee values (?,?,?);";

        Connection con = DriverManager.getConnection(url, userName, password);

        PreparedStatement pst = con.prepareStatement(query);
        // nth index, variable
        pst.setInt(1, id);
        pst.setString(2, name);
        pst.setInt(3, salary);

        int rows = pst.executeUpdate();
        System.out.println("Number of Rows affected in pst: " + rows);
        con.close();
    }

    // delete record
    public static void delete() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String password = "";

        int id = 5;

        String query = "delete from employee where emp_id = " + id;

        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query); // we can use it also for deleting record
        // returns number of rows affected.

        System.out.println("Number of rows affected in delete:" + rows);
        con.close();
    }

    // update record by hardcoding
    public static void update() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String password = "";

        String query = "update employee set salary = 150000 where emp_id = 1";

        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query); // we can use it also for updating record
        // returns number of rows affected.

        System.out.println("Number of rows affected in delete:" + rows);
        con.close();
    }

    // update record by prepared statement
    public static void updatePst() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String password = "";

        String query = "update employee set salary = ? where emp_id = ?";

        Connection con = DriverManager.getConnection(url, userName, password);

        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, 250000);
        pst.setInt(2, 1);

        int rows = pst.executeUpdate(); // we can use it also for updating record
        // returns number of rows affected.

        System.out.println("Number of rows affected in delete:" + rows);
        con.close();
    }

    // 3 Types of Statements
    // normal statements
    // prepared statements
    // callable statements , for calling procedures which is created in mysql

    // calling simple stored procedure
    public static void sp() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String password = "";

        Connection con = DriverManager.getConnection(url, userName, password);

        CallableStatement cst = con.prepareCall("{call GetEmp()}");

        ResultSet rs = cst.executeQuery(); // in GetEmp() function it is only reading the data. So we use executeQuery()

        while(rs.next()) {
            System.out.println("Id is " + rs.getInt(1));
            System.out.println("Name is " + rs.getString(2));
            System.out.println("Salary is " + rs.getInt(3));
        }
        con.close();
    }

    // calling simple stored procedure with input parameters
    public static void sp2() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String password = "";

        int id = 3;

        Connection con = DriverManager.getConnection(url, userName, password);

        CallableStatement cst = con.prepareCall("{call GetEmpById(?)}");
        cst.setInt(1, id);
        ResultSet rs = cst.executeQuery(); // in GetEmp() function it is only reading the data. So we use executeQuery()

        while(rs.next()) {
            System.out.println("Id is " + rs.getInt(1));
            System.out.println("Name is " + rs.getString(2));
            System.out.println("Salary is " + rs.getInt(3));
        }
        con.close();
    }
}

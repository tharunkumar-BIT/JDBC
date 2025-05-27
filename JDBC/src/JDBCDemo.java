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
//        sp2();
//        sp3();
        commitDemo();
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

    // calling simple stored procedure with input and output parameters
    public static void sp3() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String password = "";

        int id = 3;

        Connection con = DriverManager.getConnection(url, userName, password);

        CallableStatement cst = con.prepareCall("{call GetEmpNameById(?,?)}");
        cst.setInt(1, id);
        cst.registerOutParameter(2, Types.VARCHAR); // store the result
        cst.executeUpdate(); // it is not only reading but also updating the variable do use executeUpdate()

        System.out.println(cst.getString(2)); // get the result
        con.close();
    }

    // commit vs autocommit
    public static void commitDemo() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String userName = "root";
        String password = "";

        // correct query which will be executed successfully
        String query1 = "update employee set salary = 550000 where emp_id = 1";
        // incorrect query which will give error.
        String query2 = "updat employee set salary = 550000 where emp_id = 2";

        // to provide atomicity if we are doing a change in DB we need to do it completely, or never do it at all.
        // if autocommit is ON we won't be able to rollback to previous commit
        Connection con = DriverManager.getConnection(url, userName, password);
        // turn off autocommit
        con.setAutoCommit(false); // by default it is true.
        Statement st = con.createStatement();
        int rows1 = st.executeUpdate(query1);
        System.out.println("Rows Affected: " + rows1);
        int rows2 = st.executeUpdate(query2);
        System.out.println("Rows Affected: " + rows2);

        if(rows1 > 0 && rows2 > 0){
            con.commit();
            // if both statements are true it will commit the changes
            // even if one of the statements is wrong, it will not be commited and hence DB won't be changed.
        }

        con.close();
    }
}

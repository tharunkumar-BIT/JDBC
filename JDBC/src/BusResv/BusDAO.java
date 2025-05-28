package BusResv;

import java.sql.*;

// Bus Data Access Object
public class BusDAO {
    public void displayBusInfo() throws SQLException {
        String query  = "select * from bus";
        Connection con = DbConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rst = st.executeQuery(query);

        while (rst.next()){
            System.out.println("Bus No: " + rst.getInt(1));
            if(rst.getInt(2) == 0)
                System.out.println("Ac: no");
            else
                System.out.println("Ac: yes");
            System.out.println("Capacity: " + rst.getInt(3));
        }
        System.out.println("-------------------------------------");
    }

    public int getCapacity(int id) throws SQLException{
        String query = "select capacity from bus where id = " + id;
        Connection con = DbConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rst = st.executeQuery(query);
        rst.next();
        return rst.getInt(1);
    }
}

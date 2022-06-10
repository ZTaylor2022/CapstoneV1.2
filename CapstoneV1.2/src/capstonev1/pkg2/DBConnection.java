/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstonev1.pkg2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Vanessa Quinteros
 */
public class DBConnection {
    
    public Connection dbConn;
    public Statement commStmt;
    public ResultSet dbResults;
    
    public void sendDBCommand(String sqlQuery)
    {
        // Set up your connection strings
        // IF YOU ARE IN CIS330 NOW: use YOUR Oracle Username/Password
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "emp"; // Change to YOUR Oracle username
        String userPASS = "emp"; // Change to YOUR Oracle password
        OracleDataSource ds;
        
        // Clear Box Testing - Print each query to check SQL syntax
        //  sent to this method.
        // You can comment this line out when your program is finished
       //System.out.println(sqlQuery);
        
        // Lets try to connect
        try
        {
            // instantiate a new data source object
            ds = new OracleDataSource();
            // Where is the database located? Web? Local?
            ds.setURL(URL);
            // Send the user/pass and get an open connection.
            dbConn = ds.getConnection(userID,userPASS);

            commStmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            dbResults = commStmt.executeQuery(sqlQuery); // Sends the Query to the DB

        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        }
    }

}

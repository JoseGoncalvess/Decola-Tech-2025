package org.learnofJava.config;


import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor
public class ConnectionUltil {
    public  static Connection getConnection() throws SQLException{
          String url = "jdbc:mysql://localhost:3306/employees_db";
         String user = "user";
         String password = "root";

    return DriverManager.getConnection(url,user,password);
    }

}

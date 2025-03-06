package org.newTechDeveloper.persistence.config

import lombok.AccessLevel
import lombok.NoArgsConstructor
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class  DbConectionConfig {

  companion object{
      @Throws(SQLException::class)
      fun getConnection(): Connection {
          val url = "jdbc:mysql://localhost:3306/board"
          val user = "user"
          val password = "root"
          var connection = DriverManager.getConnection(url, user, password)
          connection.autoCommit= false
          return connection;
      }
  }

}
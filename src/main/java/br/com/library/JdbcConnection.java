package br.com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
     private static final String URL = "jdbc:mysql://localhost:3306/jaspercurso?serverTimezone=America/Sao_Paulo";
     private static final String USER = "root";
     private static final String PASSWORD = "root";
     private static final String Driver = "com.mysql.cj.jdbc.Driver";

     public static Connection getConnection() {
         Connection connection = null;
         try{
             Class.forName(Driver);
             connection = DriverManager.getConnection(URL, USER, PASSWORD);
         } catch (ClassNotFoundException e) {
             throw new RuntimeException(e);
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
         return connection;
     }
}

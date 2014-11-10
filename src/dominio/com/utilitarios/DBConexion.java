package dominio.com.utilitarios;

import java.sql.*;

public  class DBConexion {

     // PASO 1: Definir una URL de conexion
       private static String url = "jdbc:mysql://localhost:3306/bd_subasta?user=root&password=";
       
       
    public static Connection getConnection() {
        Connection con = null;
           try {
               // PASO 2: Cargar el driver
               // OJO que puede lanzar la excepcion ClassNotFoundException
               Class.forName("com.mysql.jdbc.Driver");
               // PASO 3: Obtener una conexion a la BD
               // OJO que puede lanzar un SQLException
               con = DriverManager.getConnection(url);
       
           } catch (ClassNotFoundException ex) {
               System.out.println("Verifica tu driver en el classpath");
           } catch (SQLException ex) {
               System.out.println("Verifica tus parametros de conexion");
           }
       
           return con;
    }
    
}

package dominio.com.jdbc;

import dominio.com.Cliente;
import dominio.com.utilitarios.DBConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SubastaDAO implements SubastaIF{
    @Override
    public boolean  validarLogueo(String usuario, String contraseña){
       boolean estado=false;
       // 1. obtenemos una conexion a b.d.
        Connection cn =  DBConexion.getConnection();
          // Definir la sentencia sql
       // usar PreparedStatement
         // 2. definimos la sentencia a ejecutar
        String sql="SELECT * FROM bd_subasta.vendedor WHERE  usuario = ?  AND  contraseña = ?";
        
        PreparedStatement ps=null;
        
        try {

           // 3. Preparamos la sentencia
            ps=cn.prepareStatement(sql);

            // 4. si hay interrogantes, les asignamos un valor 
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
          
            // 5. Ejecutar la sentencia preparada
            ResultSet rs=ps.executeQuery();
            estado=rs.next();
          // 6. cerramos conexion
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            try {
                //liberar recursos
               //puede lanzar excepciones SQLException
                ps.close();
                cn.close();
            } catch (SQLException ex) {
               
            }
        }
           return estado;
      }

    @Override
     public void registrarNuevoUsuario(Cliente refCli, String tipo){
    
     // Invocar al metodo getConnection de la clase DBCOnexion
        Connection cn=DBConexion.getConnection();
        PreparedStatement ps=null;
        // Definir la sentencia sql
       // usar PreparedStatement
         String sql="INSERT into bd_subasta."+tipo+" values(?,?,?,?,?)";
        try {
             ps=cn.prepareStatement(sql);
            // le damos valores a las interrogantes
            ps.setInt(2, refCli.getDni() );
            ps.setString(3,refCli.getNombre());
            ps.setString(4, refCli.getApellido());
            ps.setString(5, refCli.getUsuario() );
            ps.setString(6, refCli.getContraseña());
           
            int rc = ps.executeUpdate();
            System.out.println("Cantidad de registros afectados : " + rc);
          
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            try {
                //liberar recursos
               //puede lanzar excepciones SQLException
                ps.close();
                cn.close();
            } catch (SQLException ex) {
               
            }
        }
    }
    
}

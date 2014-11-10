package dominio.com.jdbc;

import dominio.com.Cliente;
import dominio.com.Comprador;
import dominio.com.Piezas;
import dominio.com.Vendedor;
import dominio.com.utilitarios.DBConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SubastaDAO implements SubastaIF{
    @Override
    public Cliente validarLogueo(String usuario, String contraseña){
        Cliente rpta = null;
        // 1. obtenemos una conexion a b.d.
        Connection cn =  DBConexion.getConnection();
        // Definir la sentencia sql
        // usar PreparedStatement
        // 2. definimos la sentencia a ejecutar
        String sql="SELECT * FROM bd_subasta.cliente WHERE  Usuario = ?  AND  Clave = ?";
        
        PreparedStatement ps=null;
        
        try {

            // 3. Preparamos la sentencia
            ps=cn.prepareStatement(sql);

            // 4. si hay interrogantes, les asignamos un valor 
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
          
            // 5. Ejecutar la sentencia preparada
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
                String tipo = rs.getString("Tipo");
                if(tipo.equals("vendedor")){
                    rpta = new Vendedor();
                    rpta.setUsuario(usuario);
                    ((Vendedor)rpta).setPiezas(recuperarxVendedor((Vendedor)rpta));
                }else{
                    rpta = new Comprador();
                    rpta.setUsuario(usuario);
                }
                rpta.setApellido(rs.getString("Apellido"));
                rpta.setNombre(rs.getString("Nombre"));
                rpta.setDni(rs.getInt("DNI"));
                rpta.setContraseña(contraseña);
            }
            
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
           return rpta;
      }

    @Override
     public void registrarNuevoUsuario(Cliente refCli, String tipo){
    
     // Invocar al metodo getConnection de la clase DBCOnexion
        Connection cn=DBConexion.getConnection();
        PreparedStatement ps=null;
        // Definir la sentencia sql
       // usar PreparedStatement
         String sql="INSERT into bd_subasta.cliente (DNI, Nombre, Apellido, Usuario, Clave, Tipo) values(?,?,?,?,?,?)";
        try {
             ps=cn.prepareStatement(sql);
            // le damos valores a las interrogantes
            ps.setInt(1, refCli.getDni() );
            ps.setString(2,refCli.getNombre());
            ps.setString(3, refCli.getApellido());
            ps.setString(4, refCli.getUsuario() );
            ps.setString(5, refCli.getContraseña());
            ps.setString(6, tipo);
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

    @Override
    public List<Piezas> recuperarTodas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Piezas> recuperarxVendedor(Vendedor c) {
        List<Piezas> piezas = null;
        Connection cn=DBConexion.getConnection();
        PreparedStatement ps=null;
        ResultSet rs = null;
        String sql = "Select * from bd_subasta.pieza where idvendedor=?";
        try {
            ps=cn.prepareStatement(sql);
            ps.setString(1, c.getUsuario());
            rs = ps.executeQuery();
            while(rs.next()){
                Piezas p = new Piezas();
                p.setImagen(rs.getBytes("Imagen"));
                p.setAutor(rs.getString("Autor"));
                p.setTitulo(rs.getString("Titulo"));
                p.setPrecioBase(rs.getFloat("PrecioBase"));
                p.setC(c);
            }
        } catch (SQLException ex) {
            
        } finally {
            try {
                ps.close();
                rs.close();
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubastaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return piezas;
    }
    
}

package dominio.com.jdbc;

import dominio.com.Cliente;

public interface SubastaIF {
      public void registrarNuevoUsuario(Cliente refCli, String tipo);
    
  public boolean  validarLogueo(String usuario, String contraseña);
     
    
}

package dominio.com.jdbc;

import dominio.com.*;
import java.util.List;

public interface SubastaIF {
    
    public void registrarNuevoUsuario(Cliente refCli, String tipo);
    public Cliente  validarLogueo(String usuario, String contraseña);
    public List<Piezas> recuperarTodas();
    public List<Piezas> recuperarxVendedor(Vendedor c);
    
}

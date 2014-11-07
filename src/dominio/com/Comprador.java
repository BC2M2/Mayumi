package dominio.com;

import java.util.List;

public class Comprador extends Cliente{
    private float cuenta;

    public Comprador() {
    }

    public Comprador(String nombre, String apellido, int dni, String usuario, String contraseña) {
        super(nombre, apellido, dni, usuario, contraseña);
    }

    public float getCuenta() {
        return cuenta;
    }

    public void setCuenta(float cuenta) {
        this.cuenta = cuenta;
    }
    
    
    
    
    
}

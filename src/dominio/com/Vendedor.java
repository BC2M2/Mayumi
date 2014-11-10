package dominio.com;

import java.util.List;

public class Vendedor extends Cliente {
    
    private List<Piezas> piezas;

    public Vendedor() {
    }

    public Vendedor(List<Piezas> piezas, String nombre, String apellido, int dni, String usuario, String contraseña) {
        super(nombre, apellido, dni, usuario, contraseña);
        this.piezas = piezas;
    }

    public List<Piezas> getPiezas() {
        return piezas;
    }

    public void setPiezas(List<Piezas> piezas) {
        this.piezas = piezas;
    }
    
}

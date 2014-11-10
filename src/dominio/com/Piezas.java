package dominio.com;
public class Piezas {
    private String titulo;
    private String autor;
    private float precioBase;
    private byte[] imagen;
    private Vendedor c;

    public Piezas() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public float getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(float precioBase) {
        this.precioBase = precioBase;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Vendedor getC() {
        return c;
    }

    public void setC(Vendedor c) {
        this.c = c;
    }
    
    
}

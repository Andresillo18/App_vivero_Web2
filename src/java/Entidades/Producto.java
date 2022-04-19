package Entidades;

/**
 * 28-3-22
 *
 * @author Andrés Villalobos Y Redwin
 */
public class Producto {
// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">    

    private int codProducto;
    private String tipoProducto;
    private String nombre;
    private String descripcion;
    private float precio;
    private int cantDisponible;
    private boolean existe;

// </editor-fold>
// <editor-fold desc="PROPIEDADES" defaultstate="collapsed">    
    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantDisponible() {
        return cantDisponible;
    }

    public void setCantDisponible(int cantDisponible) {
        this.cantDisponible = cantDisponible;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
// </editor-fold>

// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed">    
    //Vacío
    public Producto() {
        codProducto = 0;
        tipoProducto = "";
        nombre = "";
        descripcion = "";
        precio = 0;
        cantDisponible = 0;
        existe = false;
    }

    public Producto(int codProducto, String tipoProducto, String nombre, String descripcion, float precio, int cantDisponible) {
        this.codProducto = codProducto;
        this.tipoProducto = tipoProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantDisponible = cantDisponible;
        this.existe = true;
    }

// </editor-fold>   
}

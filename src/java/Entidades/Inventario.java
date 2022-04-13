package Entidades;

/**
 * 28-3-22
 *
 * @author Andrés Villalobos
 */
public abstract class Inventario {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">    
    private String nombre;
    private String descripcion;
    private float precio;
    private int cantidad_disponible;
// </editor-fold>

// <editor-fold desc="PROPIEDADES" defaultstate="collapsed"> 
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

    public int getCantidad_disponible() {
        return cantidad_disponible;
    }

    public void setCantidad_disponible(int cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }
// </editor-fold>

// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed">    
    public Inventario() {
        nombre = "";
        descripcion = "";
        precio = 0;
        cantidad_disponible = 0;
    }

    public Inventario(String nombre, String descripcion, float precio, int cantidad_disponible) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad_disponible = cantidad_disponible;
    }
// </editor-fold>

// <editor-fold desc="Métodos" defaultstate="collapsed">    
    public abstract void Crear();   
    
    public abstract void Eliminar();
    
    public abstract void Modificar();
// </editor-fold>
    
}

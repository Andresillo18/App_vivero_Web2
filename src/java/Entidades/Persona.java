package Entidades;

/**
 * 28-3-22
 *
 * @author Andrés Villalobos
 */
public abstract class Persona {
    
// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">

    private int id;
    private String nombre;
    private String Apellido1;
    private String telefono;
    private boolean estado;
// </editor-fold>

// <editor-fold desc="PROPIEDADES" defaultstate="collapsed">    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return Apellido1;
    }

    public void setApellido1(String Apellido1) {
        this.Apellido1 = Apellido1;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
// </editor-fold>   

// <editor-fold desc="CONSTRUCTOR" defaultstate="collapsed">    
    public Persona() {
    }

    public Persona(int id, String nombre, String Apellido1, String telefono, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.Apellido1 = Apellido1;
        this.telefono = telefono;
        this.estado = estado;
    }
// </editor-fold>

// <editor-fold desc="Métodos" defaultstate="collapsed">    
    public abstract void Crear();

    public abstract void Eliminar();

    public abstract void Modificar();
// </editor-fold>
    
}

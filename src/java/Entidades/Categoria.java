package Entidades;

/**
 * 29-3-22
 *
 * @author Andrés
 */
public class Categoria {   

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed"> 
    private int cod_categoria;
    private String nombre_categoria;
    private String descripcion;
    private boolean existe;

// </editor-fold>
    
// <editor-fold desc="PROPIEDADES" defaultstate="collapsed">    
     public int getCod_categoria() {
        return cod_categoria;
    }

    public void setCod_categoria(int cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
// </editor-fold>
    
// <editor-fold desc="CONSTRUCTOR" defaultstate="collapsed">    
    public Categoria() {
        cod_categoria = 0;
        nombre_categoria = "";
        descripcion = "";
        existe = false;
    }

    public Categoria(int cod_categoria, String nombre_categoria, String descripcion) {
        this.cod_categoria = cod_categoria;
        this.nombre_categoria = nombre_categoria;
        this.descripcion = descripcion;
        this.existe = true;
    }

// </editor-fold>
    
    // No todas las entidades ocupan métodos ya que solo sirven para transportar los datos***
}

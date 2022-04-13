package Entidades;

/**
 * 30-3-22
 *
 * @author Andrés Villalobos
 */
public class ClasPlanta {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">    
    private int cod_clasificacion;
    private int cod_planta;
    private int cod_categoria;
    private boolean existe;
// </editor-fold>

// <editor-fold desc="PROPIEDADES" defaultstate="collapsed">    
    public int getCod_clasificacion() {
        return cod_clasificacion;
    }

    public void setCod_clasificacion(int cod_clasificacion) {
        this.cod_clasificacion = cod_clasificacion;
    }

    public int getCod_planta() {
        return cod_planta;
    }

    public void setCod_planta(int cod_planta) {
        this.cod_planta = cod_planta;
    }

    public int getCod_categoria() {
        return cod_categoria;
    }

    public void setCod_categoria(int cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

// </editor-fold>
    
// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed">    
    public ClasPlanta() {
    }

    public ClasPlanta(int cod_clasificacion, int cod_planta, int cod_categoria) {
        this.cod_clasificacion = cod_clasificacion;
        this.cod_planta = cod_planta;
        this.cod_categoria = cod_categoria;
        this.existe = true;
    }

// </editor-fold>
    
//No se ocupan métodos
}

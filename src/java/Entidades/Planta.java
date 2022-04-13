package Entidades;

/**
 *
 * @author Andrés Villalobos
 */
public class Planta extends Inventario {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">    
    private int cod_planta;
    private int cantidad_Regado;
    private float tiempo_luz_solar;
    private String extras_caracteristicas;
    private boolean existe;
// </editor-fold>

// <editor-fold desc="PROPIEDADES" defaultstate="collapsed">    
    public int getCod_planta() {
        return cod_planta;
    }

    public void setCod_planta(int cod_planta) {
        this.cod_planta = cod_planta;
    }

    public int getCantidad_Regado() {
        return cantidad_Regado;
    }

    public void setCantidad_Regado(int cantidad_Regado) {
        this.cantidad_Regado = cantidad_Regado;
    }

    public float getTiempo_luz_solar() {
        return tiempo_luz_solar;
    }

    public void setTiempo_luz_solar(float tiempo_luz_solar) {
        this.tiempo_luz_solar = tiempo_luz_solar;
    }

    public String getExtras_caracteristicas() {
        return extras_caracteristicas;
    }

    public void setExtras_caracteristicas(String extras_caracteristicas) {
        this.extras_caracteristicas = extras_caracteristicas;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

// </editor-fold>
    
// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed">    
    public Planta() {
    }

    public Planta(int cod_planta, int cantidad_Regado, float tiempo_luz_solar, String extras_caracteristicas, boolean existe) {
        this.cod_planta = cod_planta;
        this.cantidad_Regado = cantidad_Regado;
        this.tiempo_luz_solar = tiempo_luz_solar;
        this.extras_caracteristicas = extras_caracteristicas;
        this.existe = existe;
    }

    public Planta(int cod_planta, String nombre, String descripcion, float precio, int cantidad_disponible, int cantidad_Regado, float tiempo_luz_solar, String extras_caracteristicas) {
        super(nombre, descripcion, precio, cantidad_disponible);
        this.cod_planta = cod_planta;
        this.cantidad_Regado = cantidad_Regado;
        this.tiempo_luz_solar = tiempo_luz_solar;
        this.extras_caracteristicas = extras_caracteristicas;
        this.existe = true;
    }

// </editor-fold>
    
// <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
    @Override
    public void Crear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Modificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
// </editor-fold>    

}

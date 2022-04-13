package Entidades;

//import java.util.Date;
import java.sql.Date;

/**
 * 28-3-22
 *
 * @author Andrés Villalobos
 */
public class Herramienta_Producto extends Inventario {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">    
    private int cod_herramienta_prod;
    private String material;
    private Date fechaVencimiento;
    private boolean existe;
// </editor-fold>

// <editor-fold desc="PROPIEDADES" defaultstate="collapsed">    
    public int getCod_herramienta_prod() {
        return cod_herramienta_prod;
    }

    public void setCod_herramienta_prod(int cod_herramienta_prod) {
        this.cod_herramienta_prod = cod_herramienta_prod;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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
    public Herramienta_Producto() {
    }

    public Herramienta_Producto(int cod_herramienta_prod, String material, Date fechaVencimiento, boolean existe) {
        this.cod_herramienta_prod = cod_herramienta_prod;
        this.material = material;
        this.fechaVencimiento = fechaVencimiento;
        this.existe = existe;
    }

    public Herramienta_Producto(int cod_herramienta_prod, String nombre, String descripcion, float precio, int cantidad_disponible, String material, Date fechaVencimiento) {
        super(nombre, descripcion, precio, cantidad_disponible);
        this.cod_herramienta_prod = cod_herramienta_prod;
        this.material = material;
        this.fechaVencimiento = fechaVencimiento;
        this.existe = true;
    }

// </editor-fold>   
    
// <editor-fold desc="Métodos" defaultstate="collapsed">
    @Override
    public void Crear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void Eliminar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void Modificar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

// </editor-fold>
}

package Entidades;

//import java.util.Date;
import java.sql.Date;

/**
 * 29-3-22
 *
 * @author Andrés Villalobos Y Redwin
 */
public class Detalle_Factura {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">    
    private int cod_detalle;
    private int cod_factura;
    private int codProducto;
    private int cantDetalle;
    private float total_pagar;
    private String observaciones;
    private String nombreProducto; // Se crea para utilizar otro campo de otra tabla
    private float precio; // Se crea para utilizar otro campo de otra tabla
    private boolean existe;

// </editor-fold> 
    
// <editor-fold desc="PROPIEDADES" defaultstate="collapsed">    
    public int getCod_detalle() {
        return cod_detalle;
    }

    public void setCod_detalle(int cod_detalle) {
        this.cod_detalle = cod_detalle;
    }

    public int getCod_factura() {
        return cod_factura;
    }

    public void setCod_factura(int cod_factura) {
        this.cod_factura = cod_factura;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public int getCantDetalle() {
        return cantDetalle;
    }

    public void setCantDetalle(int cantDetalle) {
        this.cantDetalle = cantDetalle;
    }

    public float getTotal_pagar() {
        return total_pagar;
    }

    public void setTotal_pagar(float total_pagar) {
        this.total_pagar = total_pagar;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

// </editor-fold>
    
// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed">    
    public Detalle_Factura() {
        cod_detalle = 0;
        cod_factura = 0;
        codProducto = 0;
        cantDetalle = 0;
        total_pagar = 0;
        observaciones = "";
        nombreProducto = "";
        precio = 0;
        existe = false;
    }

    public Detalle_Factura(int cod_detalle, int cod_factura, int codProducto,String nombreProducto ,  float precio, int cantDetalle, float total_pagar, String observaciones) {
        this.cod_detalle = cod_detalle;
        this.cod_factura = cod_factura;
        this.codProducto = codProducto;
        this.cantDetalle = cantDetalle;
        this.total_pagar = total_pagar;
        this.observaciones = observaciones;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.existe = true;
    }

// </editor-fold>  
    
}

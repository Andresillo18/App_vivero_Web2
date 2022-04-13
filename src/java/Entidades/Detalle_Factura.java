package Entidades;

//import java.util.Date;

import java.sql.Date;

/**
 * 29-3-22
 *
 * @author Andrés Villalobos
 */
public class Detalle_Factura {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">    
    private int cod_detalle;
    private int cod_factura;
    private int cod_herramienta_prod;
    private int cantidad_herramienta_prod;
    private int cod_planta;
    private int cantidad_plantas;
    private float total_pagar;
    private Date fecha;
    private String observaciones;
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

    public int getCod_herramienta_prod() {
        return cod_herramienta_prod;
    }

    public void setCod_herramienta_prod(int cod_herramienta_prod) {
        this.cod_herramienta_prod = cod_herramienta_prod;
    }

    public int getCantidad_herramienta_prod() {
        return cantidad_herramienta_prod;
    }

    public void setCantidad_herramienta_prod(int cantidad_herramienta_prod) {
        this.cantidad_herramienta_prod = cantidad_herramienta_prod;
    }

    public int getCod_planta() {
        return cod_planta;
    }

    public void setCod_planta(int cod_planta) {
        this.cod_planta = cod_planta;
    }

    public int getCantidad_plantas() {
        return cantidad_plantas;
    }

    public void setCantidad_plantas(int cantidad_plantas) {
        this.cantidad_plantas = cantidad_plantas;
    }

    public float getTotal_pagar() {
        return total_pagar;
    }

    public void setTotal_pagar(float total_pagar) {
        this.total_pagar = total_pagar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
// </editor-fold>

// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed">    
    public Detalle_Factura() {
    }

    public Detalle_Factura(int cod_detalle, int cod_factura, int cod_herramienta_prod, int cantidad_herramienta_prod, int cod_planta, int cantidad_plantas, float total_pagar, Date fecha, String observaciones) {
        this.cod_detalle = cod_detalle;
        this.cod_factura = cod_factura;
        this.cod_herramienta_prod = cod_herramienta_prod;
        this.cantidad_herramienta_prod = cantidad_herramienta_prod;
        this.cod_planta = cod_planta;
        this.cantidad_plantas = cantidad_plantas;
        this.total_pagar = total_pagar;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.existe = true;
    }

// </editor-fold>  
    
}

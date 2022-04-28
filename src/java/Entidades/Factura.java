package Entidades;

import java.sql.Date;

/**
 * 29-3-22
 *
 * @author Andrés Villalobos Y Redwin
 */
public class Factura {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">    
    private int cod_factura;
    private int cod_empleado;
    private String nombre_empleado;
    private int cod_cliente;
    private String nombre_cliente;
    private String estado;
    private Date fecha;
    private boolean existe;

// </editor-fold>

// <editor-fold desc="PROPIEDADES" defaultstate="collapsed">
    public int getCod_factura() {
        return cod_factura;
    }

    public void setCod_factura(int cod_factura) {
        this.cod_factura = cod_factura;
    }

    public int getCod_empleado() {
        return cod_empleado;
    }

    public void setCod_empleado(int cod_empleado) {
        this.cod_empleado = cod_empleado;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
// </editor-fold>

// <editor-fold desc="CONSTRUTORES" defaultstate="collapsed">    
    public Factura() {
        cod_factura = 0;
        cod_empleado = 0;
        nombre_empleado = "";
        cod_cliente = 0;
        nombre_cliente = "";
        estado = "Pendiente";
        fecha = null;  // new java.sql.Date(0);
        existe = false;
    }

   public Factura(int cod_factura, int cod_empleado, String nombre_empleado,  String nombre_cliente, String estado, int cod_cliente, Date fecha) {
        this.cod_factura = cod_factura;
        this.cod_empleado = cod_empleado;
        this.nombre_empleado = nombre_empleado;
        this.nombre_cliente = nombre_cliente;
        this.estado = estado;
        this.cod_cliente = cod_cliente;
        this.fecha = fecha;
        this.existe = true;
    }

// </editor-fold>
}

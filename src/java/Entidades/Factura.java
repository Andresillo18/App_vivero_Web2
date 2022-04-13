package Entidades;

/**
 * 29-3-22
 *
 * @author Andrés Villalobos
 */
public class Factura {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">    
    private int cod_factura;
    private int cod_empleado;
    private int cod_cliente;
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

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
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
    }

    public Factura(int cod_factura, int cod_empleado, int cod_cliente) {
        this.cod_factura = cod_factura;
        this.cod_empleado = cod_empleado;
        this.cod_cliente = cod_cliente;
        this.existe = true;
    }

// </editor-fold>
}

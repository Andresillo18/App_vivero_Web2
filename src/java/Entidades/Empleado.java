package Entidades;

/**
 * 29-3-22
 *
 * @author Andrés Villalobos
 */
public class Empleado extends Persona {

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">    
    private int cod_empleado;
    private int ventas_realizadas;
    private float bono;
    private boolean existe;
// </editor-fold>    

// <editor-fold desc="PROPIEDADES" defaultstate="collapsed">    
    public int getCod_empleado() {
        return cod_empleado;
    }

    public void setCod_empleado(int cod_empleado) {
        this.cod_empleado = cod_empleado;
    }

    public int getVentas_realizadas() {
        return ventas_realizadas;
    }

    public void setVentas_realizadas(int ventas_realizadas) {
        this.ventas_realizadas = ventas_realizadas;
    }

    public float getBono() {
        return bono;
    }

    public void setBono(float bono) {
        this.bono = bono;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
// </editor-fold>

// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed">    
    public Empleado() {
    }

    public Empleado(int cod_empleado, int ventas_realizadas, float bono, boolean existe) {
        this.cod_empleado = cod_empleado;
        this.ventas_realizadas = ventas_realizadas;
        this.bono = bono;
        this.existe = existe;
    }

    public Empleado(int cod_empleado, int id, String nombre, String Apellido1, String telefono, int ventas_realizadas, boolean estado, float bono) {
        super(id, nombre, Apellido1, telefono, estado);
        this.cod_empleado = cod_empleado;
        this.ventas_realizadas = ventas_realizadas;
        this.bono = bono;
        this.existe = true;
    }

// </editor-fold>
    
// <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
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

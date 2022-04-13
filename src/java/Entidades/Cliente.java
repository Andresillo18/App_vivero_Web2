package Entidades;

/**
 * 29-3-22
 * @author Andrés Villalobos
 */
public class Cliente extends Persona{    

// <editor-fold desc="ATRIBUTOS" defaultstate="collapsed">    
    private int cod_cliente;
    private boolean existe;
// </editor-fold>    
    
// <editor-fold desc="PROPIEDADES" defaultstate="collapsed">    
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
   
// <editor-fold desc="CONSTRUCTORES" defaultstate="collapsed">    
    
    public Cliente() {
    }

    public Cliente(int cod_cliente, boolean existe) {
        this.cod_cliente = cod_cliente;
        this.existe = existe;
    }

    public Cliente(int cod_cliente, int id, String nombre, String Apellido1, String telefono, boolean estado) {
        super(id, nombre, Apellido1, telefono, estado);
        this.cod_cliente = cod_cliente;
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

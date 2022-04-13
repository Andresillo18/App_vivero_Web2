package LogicaNegocio;

import AccesoDatos.ADFactura;
import Entidades.Factura;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 31-3-22
 * @author Andrés Villalobos
 */
public class LNFactura {
    
    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public LNFactura() {
        this._mensaje = "";
    }
    
    // <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
    //Método 1
    public int Insertar(Factura fact1) throws Exception {
        int id = -1;
        //Llamar a la capa de acceso a datos
        ADFactura adFactura;

        try {
            adFactura = new ADFactura();
            id = adFactura.Insertar(fact1);
            _mensaje = adFactura.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }

    //Método 2
    public int Modificar(Factura fact1) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADFactura adFactura;

        try {
            adFactura = new ADFactura();
            resultado = adFactura.Modificar(fact1);
            _mensaje = adFactura.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 3
    public int Eliminar(Factura fact1) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
          ADFactura adFactura;

        try {
            adFactura = new ADFactura();
            resultado = adFactura.Eliminar(fact1);
            _mensaje = adFactura.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 4
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception {
        ResultSet resultado;
             ADFactura adFactura;
          
        try {
            adFactura = new ADFactura();
            resultado = adFactura.ListaRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 5
    public List<Factura> ListaRegistros(String condicion) throws Exception {
        List<Factura> resultado = new ArrayList();
      ADFactura adFactura;
  
        try {
            adFactura = new ADFactura();
            resultado = adFactura.ListaRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 6
    public Factura ObtenerRegistro(String condicion) throws Exception {
        Factura fact1;
     ADFactura adFactura;
        
        try {
            adFactura = new ADFactura();
            fact1 = adFactura.ObtenerRegistro(condicion);

            if (fact1.isExiste()) {
                _mensaje = "Empleado recuperado";
            } else {
                _mensaje = "El empleado no existe";
            }
        } catch (Exception e) {
            throw e;
        }
        return fact1;
    }

// </editor-fold>
        
}

package LogicaNegocio;

import AccesoDatos.ADCliente;
import Entidades.Cliente;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 31-3-22
 * @author Andrés Villalobos
 */
public class LNCliente {
    
    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public LNCliente() {
        this._mensaje = "";
    }
    
// <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
    
     //Método 1
    public int Insertar(Cliente cliente) throws Exception {
        int id = -1;
        //Llamar a la capa de acceso a datos
        ADCliente adCliente;
        try {
            adCliente = new ADCliente();
            id = adCliente.Insertar(cliente);
            _mensaje = adCliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }

    //Método 2
    public int Modificar(Cliente cliente) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADCliente adCliente;
        try {
            adCliente = new ADCliente();
            resultado = adCliente.Modificar(cliente);
            _mensaje = adCliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    //Método 3
       public int Eliminar(Cliente cliente) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADCliente adCliente;
        try {
            adCliente = new ADCliente();
            resultado = adCliente.Eliminar(cliente);
            _mensaje = adCliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 4
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception {
        ResultSet resultado;
        ADCliente adCliente;
        try {
            adCliente = new ADCliente();
            resultado = adCliente.ListaRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 5
    public List<Cliente> ListaRegistros(String condicion) throws Exception {
        List<Cliente> resultado = new ArrayList();
        ADCliente adCliente;
        try {
            adCliente = new ADCliente();
            resultado = adCliente.ListaRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 6
    public Cliente ObtenerRegistro(String condicion) throws Exception {
        Cliente cliente;
        ADCliente adCliente;
        try {
            adCliente = new ADCliente();
            cliente = adCliente.ObtenerRegistro(condicion);

            if (cliente.isExiste()) {
                _mensaje = "Cliente recuperado";
            } else {
                _mensaje = "El cliente no existe";
            }
        } catch (Exception e) {
            throw e;
        }
        return cliente;
    }
    
// </editor-fold>

}

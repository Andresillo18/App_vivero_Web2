package LogicaNegocio;

import AccesoDatos.ADProductos;
import Entidades.Producto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 18-4-22
 * @author Andrés Villalobos Y Redwin
 */
public class LNProducto {
       //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public LNProducto() {
        this._mensaje = "";
    }

    // <editor-fold desc="MÉTODOS" defaultstate="collapsed">   
    
    //Método 1
    public int Insertar(Producto prod) throws Exception {
        int id = -1;
        //Llamar a la capa de acceso a datos
        ADProductos adProducto;

        try {
            adProducto = new ADProductos();
            id = adProducto.Insertar(prod);
            _mensaje = adProducto.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }

    //Método 2
    public int Modificar(Producto prod) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADProductos adProducto;

        try {
            adProducto = new ADProductos();
            resultado = adProducto.Modificar(prod);
            _mensaje = adProducto.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 3
    public int Eliminar(Producto prod) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADProductos adProducto;

        try {
            adProducto = new ADProductos();
            resultado = adProducto.Eliminar(prod);
            _mensaje = adProducto.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 4
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception {
        ResultSet resultado;
        ADProductos adProducto;

        try {
            adProducto = new ADProductos();
            resultado = adProducto.ListaRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 5
    public List<Producto> ListaRegistros(String condicion) throws Exception {
        List<Producto> resultado = new ArrayList();
        ADProductos adProducto;

        try {
            adProducto = new ADProductos();
            resultado = adProducto.ListaRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 6
    public Producto ObtenerRegistro(String condicion) throws Exception {
        Producto prod1;
        ADProductos adProducto;

        try {
            adProducto = new ADProductos();
            prod1 = adProducto.ObtenerRegistro(condicion);

            if (prod1.isExiste()) {
                _mensaje = "Producto recuperado";
            } else {
                _mensaje = "El producto no existe";
            }
        } catch (Exception e) {
            throw e;
        }
        return prod1;
    }

// </editor-fold>
}

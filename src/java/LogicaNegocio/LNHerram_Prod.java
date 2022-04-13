package LogicaNegocio;

import AccesoDatos.ADHerram_Prod;
import Entidades.Herramienta_Producto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 31-3-22
 *
 * @author Andrés Villalobos
 */
public class LNHerram_Prod {

    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public LNHerram_Prod() {
        this._mensaje = "";
    }

    // <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
    //Método 1
    public int Insertar(Herramienta_Producto herramienta_Producto) throws Exception {
        int id = -1;
        //Llamar a la capa de acceso a datos
        ADHerram_Prod adHerram_Prod;

        try {
            adHerram_Prod = new ADHerram_Prod();
            id = adHerram_Prod.Insertar(herramienta_Producto);
            _mensaje = adHerram_Prod.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }

    //Método 2
    public int Modificar(Herramienta_Producto herramienta_Producto) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADHerram_Prod adHerram_Prod;

        try {
            adHerram_Prod = new ADHerram_Prod();
            resultado = adHerram_Prod.Modificar(herramienta_Producto);
            _mensaje = adHerram_Prod.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 3
    public int Eliminar(Herramienta_Producto herramienta_Producto) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADHerram_Prod adHerram_Prod;

        try {
            adHerram_Prod = new ADHerram_Prod();
            resultado = adHerram_Prod.Eliminar(herramienta_Producto);
            _mensaje = adHerram_Prod.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 4
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception {
        ResultSet resultado;
        ADHerram_Prod adHerram_Prod;

        try {
            adHerram_Prod = new ADHerram_Prod();
            resultado = adHerram_Prod.ListaRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 5
    public List<Herramienta_Producto> ListaRegistros(String condicion) throws Exception {
        List<Herramienta_Producto> resultado = new ArrayList();
        ADHerram_Prod adHerram_Prod;

        try {
            adHerram_Prod = new ADHerram_Prod();
            resultado = adHerram_Prod.ListaRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 6
    public Herramienta_Producto ObtenerRegistro(String condicion) throws Exception {
        Herramienta_Producto herramienta_Producto;
        ADHerram_Prod adHerram_Prod;

        try {
            adHerram_Prod = new ADHerram_Prod();
            herramienta_Producto = adHerram_Prod.ObtenerRegistro(condicion);

            if (herramienta_Producto.isExiste()) {
                _mensaje = "Empleado recuperado";
            } else {
                _mensaje = "El empleado no existe";
            }
        } catch (Exception e) {
            throw e;
        }
        return herramienta_Producto;
    }

// </editor-fold>
    
}

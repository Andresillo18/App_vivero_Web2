package LogicaNegocio;

import AccesoDatos.ADCategoria;
import Entidades.Categoria;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 31-3-22
 * @author Andrés Villalobos
 */
public class LNCategoria {
    
    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public LNCategoria() {
        this._mensaje = "";
    }

// <editor-fold desc="MÉTODOS" defaultstate="collapsed">   
    
    //Método 1
    public int Insertar(Categoria categoria1) throws Exception {
        int id = -1;
        //Llamar a la capa de acceso a datos
        ADCategoria aDCategoria;
        try {
            aDCategoria = new ADCategoria();
            id = aDCategoria.Insertar(categoria1);
            _mensaje = aDCategoria.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }

    //Método 2  
    public int Modificar(Categoria categoria1) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADCategoria adCategoria;
        try {
            adCategoria = new ADCategoria();
            resultado = adCategoria.Modificar(categoria1);
            _mensaje = adCategoria.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    //Método 3
    public int Eliminar(Categoria categoria1) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADCategoria adCategoria;
        try {
            adCategoria = new ADCategoria();
            resultado = adCategoria.Eliminar(categoria1);
            _mensaje = adCategoria.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 4
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception {
        ResultSet resultado;
        ADCategoria adCategoria;
        try {
            adCategoria = new ADCategoria();
            resultado = adCategoria.ListaRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método5
    public List<Categoria> ListaRegistros(String condicion) throws Exception {
        List<Categoria> resultado = new ArrayList();
        ADCategoria adCategoria;
        try {
            adCategoria = new ADCategoria();
            resultado = adCategoria.ListaRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 6
    public Categoria ObtenerRegistro(String condicion) throws Exception {
        Categoria categoria1;
        ADCategoria adCategoria;
        try {
            adCategoria = new ADCategoria();
            categoria1 = adCategoria.ObtenerRegistro(condicion);

            if (categoria1.isExiste()) {
                _mensaje = "Categoría recuperado";
            } else {
                _mensaje = "La Categoría no existe";
            }
        } catch (Exception e) {
            throw e;
        }
        return categoria1;
    }

// </editor-fold>
    
}

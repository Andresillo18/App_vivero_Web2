package LogicaNegocio;

import AccesoDatos.ADClasPlanta;
import Entidades.ClasPlanta;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 31-3-22
 *
 * @author Andrés Villalobos
 */
public class LNClasPlanta {

    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public LNClasPlanta() {
        this._mensaje = "";
    }

// <editor-fold desc="MÉTODOS" defaultstate="collapsed">   
    //Método 1
    public int Insertar(ClasPlanta clasPlanta1) throws Exception {
        int id = -1;
        //Llamar a la capa de acceso a datos
        ADClasPlanta adClasPlanta;
        try {
            adClasPlanta = new ADClasPlanta();
            id = adClasPlanta.Insertar(clasPlanta1);
            _mensaje = adClasPlanta.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }

    //Método 2
    public int Modificar(ClasPlanta clasPlanta1) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADClasPlanta adClasPlanta;
        try {
            adClasPlanta = new ADClasPlanta();
            resultado = adClasPlanta.Modificar(clasPlanta1);
            _mensaje = adClasPlanta.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    //Método 3
    public int Eliminar(ClasPlanta clasPlanta1) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
         ADClasPlanta adClasPlanta;
        try {
            adClasPlanta = new ADClasPlanta();
            resultado = adClasPlanta.Eliminar(clasPlanta1);
            _mensaje = adClasPlanta.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 4
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception {
        ResultSet resultado;
       ADClasPlanta adClasPlanta;
        try {
            adClasPlanta = new ADClasPlanta();
            resultado = adClasPlanta.ListaRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 5
    public List<ClasPlanta> ListaRegistros(String condicion) throws Exception {
        List<ClasPlanta> resultado = new ArrayList();
        ADClasPlanta adClasPlanta;
        try {
            adClasPlanta = new ADClasPlanta();
            resultado = adClasPlanta.ListaRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 6
    public ClasPlanta ObtenerRegistro(String condicion) throws Exception {
        ClasPlanta clasPlanta1;
        ADClasPlanta adClasPlanta;
        try {
            adClasPlanta = new ADClasPlanta();
            clasPlanta1 = adClasPlanta.ObtenerRegistro(condicion);

            if (clasPlanta1.isExiste()) {
                _mensaje = "Categoría recuperado";
            } else {
                _mensaje = "La Categoría no existe";
            }
        } catch (Exception e) {
            throw e;
        }
        return clasPlanta1;
    }

// </editor-fold>
    
}

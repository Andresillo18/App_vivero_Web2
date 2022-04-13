package LogicaNegocio;

import AccesoDatos.ADPlanta;
import Entidades.Planta;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 31-3-22
 *
 * @author Andrés Villalobos
 */
public class LNPlanta {

    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public LNPlanta() {
        this._mensaje = "";
    }

    // <editor-fold desc="MÉTODOS" defaultstate="collapsed">   
    
    //Método 1
    public int Insertar(Planta planta1) throws Exception {
        int id = -1;
        //Llamar a la capa de acceso a datos
        ADPlanta adPlanta;

        try {
            adPlanta = new ADPlanta();
            id = adPlanta.Insertar(planta1);
            _mensaje = adPlanta.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }

    //Método 2
    public int Modificar(Planta planta1) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADPlanta adPlanta;

        try {
            adPlanta = new ADPlanta();
            resultado = adPlanta.Modificar(planta1);
            _mensaje = adPlanta.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 3
    public int Eliminar(Planta planta1) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADPlanta adPlanta;

        try {
            adPlanta = new ADPlanta();
            resultado = adPlanta.Eliminar(planta1);
            _mensaje = adPlanta.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 4
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception {
        ResultSet resultado;
        ADPlanta adPlanta;

        try {
            adPlanta = new ADPlanta();
            resultado = adPlanta.ListaRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 5
    public List<Planta> ListaRegistros(String condicion) throws Exception {
        List<Planta> resultado = new ArrayList();
        ADPlanta adPlanta;

        try {
            adPlanta = new ADPlanta();
            resultado = adPlanta.ListaRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 6
    public Planta ObtenerRegistro(String condicion) throws Exception {
        Planta planta1;
        ADPlanta adPlanta;

        try {
            adPlanta = new ADPlanta();
            planta1 = adPlanta.ObtenerRegistro(condicion);

            if (planta1.isExiste()) {
                _mensaje = "Empleado recuperado";
            } else {
                _mensaje = "El empleado no existe";
            }
        } catch (Exception e) {
            throw e;
        }
        return planta1;
    }

// </editor-fold>
    
}

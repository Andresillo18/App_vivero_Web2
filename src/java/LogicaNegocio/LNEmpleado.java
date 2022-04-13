package LogicaNegocio;

import AccesoDatos.ADEmpleado;
import Entidades.Empleado;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 31-3-22
 *
 * @author Andrés Villalobos
 */
public class LNEmpleado {

    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public LNEmpleado() {
        this._mensaje = "";
    }

    // <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
    //Método 1
    public int Insertar(Empleado empleado1) throws Exception {
        int id = -1;
        //Llamar a la capa de acceso a datos
        ADEmpleado adEmpleado;

        try {
            adEmpleado = new ADEmpleado();
            id = adEmpleado.Insertar(empleado1);
            _mensaje = adEmpleado.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }

    //Método 2
    public int Modificar(Empleado empleado1) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADEmpleado adEmpleado;

        try {
            adEmpleado = new ADEmpleado();
            resultado = adEmpleado.Modificar(empleado1);
            _mensaje = adEmpleado.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 3
    public int Eliminar(Empleado empleado1) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADEmpleado adEmpleado;

        try {
            adEmpleado = new ADEmpleado();
            resultado = adEmpleado.Eliminar(empleado1);
            _mensaje = adEmpleado.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 4
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception {
        ResultSet resultado;
        ADEmpleado adEmpleado;

        try {
            adEmpleado = new ADEmpleado();
            resultado = adEmpleado.ListaRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 5
    public List<Empleado> ListaRegistros(String condicion) throws Exception {
        List<Empleado> resultado = new ArrayList();
        ADEmpleado adEmpleado;

        try {
            adEmpleado = new ADEmpleado();
            resultado = adEmpleado.ListaRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 6
    public Empleado ObtenerRegistro(String condicion) throws Exception {
        Empleado empleado;
        ADEmpleado adEmpleado;

        try {
            adEmpleado = new ADEmpleado();
            empleado = adEmpleado.ObtenerRegistro(condicion);

            if (empleado.isExiste()) {
                _mensaje = "Empleado recuperado";
            } else {
                _mensaje = "El empleado no existe";
            }
        } catch (Exception e) {
            throw e;
        }
        return empleado;
    }

    //Método 7
    public float ObtenerBono(int cod_empleado, int mes) throws Exception {
          float bono = 0;
        ADEmpleado adEmpleado;
        
        try {
            adEmpleado = new ADEmpleado();
            bono = adEmpleado.ObtenerBono(cod_empleado,mes);
        } catch (Exception e) {
            throw e;
        }
        return bono;
    }

// </editor-fold>

}

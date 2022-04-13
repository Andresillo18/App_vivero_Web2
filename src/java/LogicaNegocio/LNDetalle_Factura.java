package LogicaNegocio;

import AccesoDatos.ADDetalle_Factura;
import Entidades.Detalle_Factura;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 31-3-22
 *
 * @author Andrés Villalobos
 */
public class LNDetalle_Factura {

    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public LNDetalle_Factura() {
        this._mensaje = "";
    }

// <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
    //Método 1
    public int Insertar(Detalle_Factura DF) throws Exception {
        int id = -1;
        //Llamar a la capa de acceso a datos
        ADDetalle_Factura adDetalle_Factura;
        try {
            adDetalle_Factura = new ADDetalle_Factura();
            id = adDetalle_Factura.Insertar(DF);
            _mensaje = adDetalle_Factura.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }

    //Método 2
    public int Modificar(Detalle_Factura DF) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADDetalle_Factura adDetalle_Factura;
        try {
            adDetalle_Factura = new ADDetalle_Factura();
            resultado = adDetalle_Factura.Modificar(DF);
            _mensaje = adDetalle_Factura.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 3
    public int Eliminar(Detalle_Factura DF) throws Exception {
        int resultado = 0;
        //Llamar a la capa de acceso a datos
        ADDetalle_Factura adDetalle_Factura;
        try {
            adDetalle_Factura = new ADDetalle_Factura();
            resultado = adDetalle_Factura.Eliminar(DF);
            _mensaje = adDetalle_Factura.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 4
    public ResultSet ListarRegistros(String condicion, String orden) throws Exception {
        ResultSet resultado;
        ADDetalle_Factura adDetalle_Factura;
        try {
            adDetalle_Factura = new ADDetalle_Factura();
            resultado = adDetalle_Factura.ListaRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 5
    public List<Detalle_Factura> ListaRegistros(String condicion) throws Exception {
        List<Detalle_Factura> resultado = new ArrayList();
        ADDetalle_Factura adDetalle_Factura;
        try {
            adDetalle_Factura = new ADDetalle_Factura();
            resultado = adDetalle_Factura.ListaRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Método 6
    public Detalle_Factura ObtenerRegistro(String condicion) throws Exception {
        Detalle_Factura DF;
        ADDetalle_Factura adDetalle_Factura;
        try {
            adDetalle_Factura = new ADDetalle_Factura();
            DF = adDetalle_Factura.ObtenerRegistro(condicion);

            if (DF.isExiste()) {
                _mensaje = "Detalle de Factura recuperado";
            } else {
                _mensaje = "El Detalle de Factura no existe";
            }
        } catch (Exception e) {
            throw e;
        }
        return DF;
    }

    //Método 7
    //Enviando y recibiendo parámetros obtenidos para el proceso almacenado
    public float Resumir_Ventas(int mes) throws Exception {
        float resumen ;
        ADDetalle_Factura adDetalle_Factura;
        
        try {
            adDetalle_Factura = new ADDetalle_Factura();
            resumen = adDetalle_Factura.Resumir_Ventas(mes);
        } catch (Exception e) {
            throw e;
        }
        return resumen;
    }
    
// </editor-fold>

}

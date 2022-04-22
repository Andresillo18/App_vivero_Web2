package AccesoDatos;

import static AccesoDatos.ClaseConexion.getConnection;
import Entidades.Detalle_Factura;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * 30-2-22
 *
 * @author Andrés Villalobos Y Redwin
 */
public class ADDetalle_Factura {

    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public ADDetalle_Factura() throws Exception {
        _mensaje = "";
    }

    // <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
    // Método1
    public int Insertar(Detalle_Factura DF) throws Exception {
        int cod_cliente = -1; // el -1 significa que no existe, por ahora
        String sentencia = "INSERT INTO Detalle_Factura (COD_FACTURA, codProducto, cantDetalle, TOTAL_PAGAR, FECHA, OBSERVACIONES) VALUES (?, ?, ?, ?, ?, ?)";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement PS = _conexion.prepareStatement(sentencia, PreparedStatement.RETURN_GENERATED_KEYS); // Envía la sentencia según la entidad y regresa las llaves auto generadas

            //Se registra los argumentos de la consulta            
            PS.setInt(1, DF.getCod_factura());
            PS.setInt(2, DF.getCodProducto());
            PS.setInt(3, DF.getCantDetalle());
            PS.setFloat(4, DF.getTotal_pagar());
            PS.setDate(5, DF.getFecha());
            PS.setString(6, DF.getObservaciones());

            PS.executeUpdate(); // Se ejecuta la sentencia- retorna true o false 

            ResultSet rs = PS.getGeneratedKeys(); // El ResultSet es de una celda porque obtiene los identity de un INSERT

            if (rs != null && rs.next()) {
                cod_cliente = rs.getInt(1); //busca el unico registro de la unica columna
                _mensaje = "Detalle de Factura ingresado satisfactoriamente";
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }
        return cod_cliente;
    }

    //Método2
    public int Modificar(Detalle_Factura DF) throws Exception {
        int result = 0; // No ha obtenido ningún resultado        
        String sentencia = "UPDATE Detalle_Factura SET COD_FACTURA = ?, codProducto = ?, cantDetalle = ?, TOTAL_PAGAR = ?, FECHA = ?, OBSERVACIONES = ? WHERE COD_DETALLE = ? ";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setInt(1, DF.getCod_factura());
            ps.setInt(2, DF.getCodProducto());
            ps.setInt(3, DF.getCantDetalle());
            ps.setFloat(4, DF.getTotal_pagar());
            ps.setDate(5, DF.getFecha());
            ps.setString(6, DF.getObservaciones());
            ps.setInt(7, DF.getCod_detalle());

            result = ps.executeUpdate();

            if (result > 0) { // devuelve las fulas afectadas y si hubo mas de una avisa
                _mensaje = "Registro modificado!";
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }

        return result;
    }

    //Método3
    public int Eliminar(Detalle_Factura DF) throws Exception {
        int result = 0;
        String sentencia = "DELETE Detalle_Factura WHERE codProducto = ?";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setInt(1, DF.getCodProducto());

            result = ps.executeUpdate();

            if (result > 0) {
                _mensaje = "Registro eliminado!";
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }

        return result;
    }

    //Método4
    public ResultSet ListaRegistros(String condicion, String orden) throws Exception {
        ResultSet rs = null; // Tendrá la tabla
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            Statement Stm = _conexion.createStatement(); // Se usa un statement ya que lo que se enviará no tendrá un parámetro de entrada
            String sentencia = "SELECT COD_DETALLE,DF.COD_FACTURA,DF.codProducto, nombre, precio, cantDetalle, TOTAL_PAGAR , FECHA, OBSERVACIONES FROM Productos P INNER JOIN  Detalle_Factura DF ON P.codProducto = DF.codProducto inner JOIN Factura F ON DF.COD_FACTURA = F.COD_FACTURA ";

            if (!condicion.equals("")) { // Si se envío una condición
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            if (!orden.equals("")) {
                sentencia = String.format("%s ORDER BY %s", sentencia, orden);
            }

            rs = Stm.executeQuery(sentencia);

        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }

        return rs;
    }

    //Método5
    // Devuelve una lista con objetos Categoria
    public List<Detalle_Factura> ListaRegistros(String condicion) throws Exception {
        List<Detalle_Factura> list1 = new ArrayList();
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            Statement Stm = _conexion.createStatement(); // Siempre se debe estable esta conexión con la BD

            String sentencia = "SELECT COD_DETALLE,DF.COD_FACTURA,DF.codProducto, nombre, precio, cantDetalle, TOTAL_PAGAR , FECHA, OBSERVACIONES FROM Productos P INNER JOIN  Detalle_Factura DF ON P.codProducto = DF.codProducto inner JOIN Factura F ON DF.COD_FACTURA = F.COD_FACTURA ";

            if (!condicion.equals("")) { // Si se envío una condición
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Se usa un bucle siempre para saber lo que tiene un ResultSet
            while (rs.next()) { // Esto solo leerá el único registro que tiene
                list1.add(new Detalle_Factura(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getFloat(5), rs.getInt(6), rs.getFloat(7), rs.getDate(8), rs.getString(9))); // Solo le envía un objeto
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }

        return list1;
    }

    //Método6
    public Detalle_Factura ObtenerRegistro(String condicion) throws Exception {
        Detalle_Factura DF = new Detalle_Factura(); // este es el objeto que devolverá 
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            String sentencia = "SELECT COD_DETALLE,DF.COD_FACTURA,DF.codProducto, nombre, precio, cantDetalle, TOTAL_PAGAR , FECHA, OBSERVACIONES FROM Productos P INNER JOIN  Detalle_Factura DF ON P.codProducto = DF.codProducto inner JOIN Factura F ON DF.COD_FACTURA = F.COD_FACTURA ";

            Statement Stm = _conexion.createStatement(); // Se usa create ya que no envía parametros a la sentencia

            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Lo que devuelve la columna se establece a los atributos de su entidad (se usan las propiedades de encapsulamiento)
            if (rs.next()) { // Solo devolverá un registro
                DF.setCod_detalle(rs.getInt(1));
                DF.setCod_factura(rs.getInt(2));
                DF.setCodProducto(rs.getInt(3));
                DF.setNombreProducto(rs.getString(4));
                DF.setPrecio(rs.getFloat(5));
                DF.setCantDetalle(rs.getInt(6));
                DF.setTotal_pagar(rs.getFloat(7));
                DF.setFecha(rs.getDate(8));
                DF.setObservaciones(rs.getString(9));
                DF.setExiste(true);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }

        return DF;
    }

    //Método7 - Llama un proceso almacenado para obetener el resumen de las ventas según el mes escogido
    //Recibe el mes a saber el resumen
    public float Resumir_Ventas(int mes) throws Exception {
        float resumen = 0;
        int resultado = 0; // las filas afectadas 
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            CallableStatement cs = _conexion.prepareCall("{call SP_RESUMIR_VENTAS(?,?)}");

            //Se establecen los parámetros a enviar
            cs.setInt(1, (mes + 1));
            cs.setFloat(2, resumen);

            //Y los parámetros OUT del SP
            cs.registerOutParameter(2, Types.FLOAT);

            resultado = cs.executeUpdate(); // Devuelve las filas afectadas

            resumen = cs.getFloat(2); // Se obtiene el parámetro de salida         

        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }

        return resumen;
    }
// </editor-fold>

}

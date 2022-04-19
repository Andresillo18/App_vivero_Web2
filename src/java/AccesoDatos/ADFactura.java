package AccesoDatos;

import static AccesoDatos.ClaseConexion.getConnection;
import Entidades.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 31-3-22
 *
 * @author Andrés Villalobos Y Redwin
 */
public class ADFactura {

    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public ADFactura() throws Exception {
        _mensaje = "";
    }

    // <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
    // Método1
    public int Insertar(Factura fact) throws Exception {
        int cod_factura = -1; // el -1 significa que no existe, por ahora
        String sentencia = "INSERT INTO Factura (COD_EMPLEADO, COD_CLIENTE) VALUES (?,?)";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement PS = _conexion.prepareStatement(sentencia, PreparedStatement.RETURN_GENERATED_KEYS); // Envía la sentencia según la entidad y regresa las llaves auto generadas

            //Se registra los argumentos de la consulta            
            PS.setInt(1, fact.getCod_empleado());
            PS.setInt(2, fact.getCod_cliente());

            PS.execute(); // Se ejecuta la sentencia- retorna true o false 

            ResultSet rs = PS.getGeneratedKeys(); // El ResultSet es de una celda porque obtiene los identity de un INSERT

            if (rs != null && rs.next()) {
                cod_factura = rs.getInt(1); //busca el unico registro de la unica columna
                _mensaje = "Factura ingresado satisfactoriamente";
            }

        } catch (Exception e) {
        } finally {
            if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }
        return cod_factura;
    }

    //Método2
    public int Modificar(Factura fact) throws Exception {
        int result = 0; // No ha obtenido ningún resultado        
        String sentencia = "UPDATE Factura SET COD_EMPLEADO = ?, COD_CLIENTE =? WHERE COD_FACTURA = ? ";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setInt(1, fact.getCod_empleado());
            ps.setInt(2, fact.getCod_cliente());
            ps.setInt(3, fact.getCod_factura());

            result = ps.executeUpdate();

            if (result > 0) { // devuelve las filas afectadas y si hubo mas de una avisa
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
    public int Eliminar(Factura fact) throws Exception {
        int result = 0;
        String sentencia = "DELETE Factura WHERE COD_FACTURA = ?";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setInt(1, fact.getCod_factura());

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
            String sentencia = "SELECT COD_FACTURA, COD_EMPLEADO, COD_CLIENTE FROM Factura";

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
    public List<Factura> ListaRegistros(String condicion) throws Exception {
        List<Factura> list1 = new ArrayList();
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            Statement Stm = _conexion.createStatement(); // Siempre se debe estable esta conexión con la BD

            String sentencia = "SELECT COD_FACTURA, Factura.COD_EMPLEADO,Empleado.NOMBRE,Cliente.NOMBRE, Factura.COD_CLIENTE FROM Cliente INNER JOIN Factura "
                    + "ON Cliente.COD_CLIENTE =Factura.COD_CLIENTE INNER JOIN Empleado ON Factura.COD_EMPLEADO = Empleado.COD_EMPLEADO";

            if (!condicion.equals("")) { // Si se envío una condición
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Se usa un bucle siempre para saber lo que tiene un ResultSet
            while (rs.next()) { // Esto solo leerá el único registro que tiene
                list1.add(new Factura(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5))); // Solo le envía un objeto
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
    public Factura ObtenerRegistro(String condicion) throws Exception {
        Factura fact = new Factura(); // este es el objeto que devolverá 
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            String sentencia = "SELECT COD_FACTURA, COD_EMPLEADO, COD_CLIENTE FROM Factura";

            Statement Stm = _conexion.createStatement(); // Se usa create ya que no envía parametros a la sentencia

            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Lo que devuelve la columna se establece a los atributos de su entidad (se usan las propiedades de encapsulamiento)
            if (rs.next()) { // Solo devolverá un registro
                fact.setCod_factura(rs.getInt(1));
                fact.setCod_empleado(rs.getInt(2));
                fact.setCod_cliente(rs.getInt(3));
                fact.setExiste(true);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }

            return fact;
        }
    }
// </editor-fold>
    
}

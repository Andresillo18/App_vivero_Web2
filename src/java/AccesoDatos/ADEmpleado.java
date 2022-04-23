package AccesoDatos;

import static AccesoDatos.ClaseConexion.getConnection;
import Entidades.Empleado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * 31-3-22
 *
 * @author Andrés Villalobos Y Redwin
 */
public class ADEmpleado {

    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public ADEmpleado() throws Exception {
        _mensaje = "";
    }

    // <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
    // Método1
    public int Insertar(Empleado empleado1) throws Exception {
        int cod_empleado = -1; // el -1 significa que no existe, por ahora
        String sentencia = "INSERT INTO Empleado (ID, NOMBRE,APELLIDO1, TELEFONO, VENTAS_REALIZADAS, ESTADO, BONO) VALUES (?,?,?,?,?,?,?)";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement PS = _conexion.prepareStatement(sentencia, PreparedStatement.RETURN_GENERATED_KEYS); // Envía la sentencia según la entidad y regresa las llaves auto generadas

            //Se registra los argumentos de la consulta            
            PS.setString(1, empleado1.getId());
            PS.setString(2, empleado1.getNombre());
            PS.setString(3, empleado1.getApellido1());
            PS.setString(4, empleado1.getTelefono());
            PS.setInt(5, empleado1.getVentas_realizadas());
            PS.setBoolean(6, empleado1.isEstado());
            PS.setFloat(7, empleado1.getBono());

            PS.execute(); // Se ejecuta la sentencia- retorna true o false 

            ResultSet rs = PS.getGeneratedKeys(); // El ResultSet es de una celda porque obtiene los identity de un INSERT

            if (rs != null && rs.next()) {
                cod_empleado = rs.getInt(1); //busca el unico registro de la unica columna
                _mensaje = "Empleado ingresado satisfactoriamente";
            }

        } catch (Exception e) {
        } finally {
            if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }
        return cod_empleado;
    }

    //Método2
    public int Modificar(Empleado empleado1) throws Exception {
        int result = 0; // No ha obtenido ningún resultado        
        String sentencia = "UPDATE Empleado SET ID = ?, NOMBRE =?, APELLIDO1 =?, TELEFONO=?, VENTAS_REALIZADAS =?, ESTADO =?,BONO=? WHERE COD_EMPLEADO = ? ";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setString(1, empleado1.getId());
            ps.setString(2, empleado1.getNombre());
            ps.setString(3, empleado1.getApellido1());
            ps.setString(4, empleado1.getTelefono());
            ps.setInt(5, empleado1.getVentas_realizadas());
            ps.setBoolean(6, empleado1.isEstado());
            ps.setFloat(7, empleado1.getBono());
            ps.setInt(8, empleado1.getCod_empleado());

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
    public int Eliminar(Empleado empleado1) throws Exception {
        int result = 0;
        String sentencia = "DELETE Empleado WHERE COD_EMPLEADO = ?";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setInt(1, empleado1.getCod_empleado());

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
            String sentencia = "SELECT COD_EMPLEADO, ID, NOMBRE,APELLIDO1, TELEFONO, VENTAS_REALIZADAS, ESTADO, BONO FROM Empleado";

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
    public List<Empleado> ListaRegistros(String condicion) throws Exception {
        List<Empleado> list1 = new ArrayList();
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            Statement Stm = _conexion.createStatement(); // Siempre se debe estable esta conexión con la BD

            String sentencia = "SELECT COD_EMPLEADO, ID, NOMBRE,APELLIDO1, TELEFONO, VENTAS_REALIZADAS, ESTADO, BONO FROM Empleado";

            if (!condicion.equals("")) { // Si se envío una condición
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Se usa un bucle siempre para saber lo que tiene un ResultSet
            while (rs.next()) { // Esto solo leerá el único registro que tiene
                list1.add(new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getBoolean(7), rs.getFloat(8))); // Solo le envía un objeto
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
    public Empleado ObtenerRegistro(String condicion) throws Exception {
        Empleado empleado1 = new Empleado(); // este es el objeto que devolverá 
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            String sentencia = "SELECT COD_EMPLEADO, ID, NOMBRE,APELLIDO1, TELEFONO, VENTAS_REALIZADAS, ESTADO, BONO FROM Empleado";

            Statement Stm = _conexion.createStatement(); // Se usa create ya que no envía parametros a la sentencia

            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Lo que devuelve la columna se establece a los atributos de su entidad (se usan las propiedades de encapsulamiento)
            if (rs.next()) { // Solo devolverá un registro
                empleado1.setCod_empleado(rs.getInt(1));
                empleado1.setId(rs.getString(2));
                empleado1.setNombre(rs.getString(3));
                empleado1.setApellido1(rs.getString(4));
                empleado1.setTelefono(rs.getString(5));
                empleado1.setVentas_realizadas(rs.getInt(6));
                empleado1.setEstado(rs.getBoolean(7));
                empleado1.setBono(rs.getFloat(8));
                empleado1.setExiste(true);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }

        return empleado1;
    }

    //Método7 - Llama un proceso almacenado para obtener el bono según las ventas realizadas especificando el empleado
    //Recibe el id del empleado y mes
    public float ObtenerBono(int cod_empleado, int mes) throws Exception {
        float bono = 0;
        int resultado = 0;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            CallableStatement cs = _conexion.prepareCall("{call SP_BONO(?,?,?)}");

            //Se establecen los parámetros a enviar
            cs.setInt(1, cod_empleado);
            cs.setInt(2, (mes + 1));
            cs.setFloat(3, bono);

            //Y los parámetros OUT del SP
            cs.registerOutParameter(3, Types.VARCHAR);

            resultado = cs.executeUpdate(); // Devuelve las filas afectadas

            if (resultado > 0) {
                bono = cs.getFloat(3); // Se obtiene el parámetro de salida
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }

        return bono;
    }
// </editor-fold>

}

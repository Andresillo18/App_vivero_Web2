package AccesoDatos;

import static AccesoDatos.ClaseConexion.getConnection;
import Entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 30-3-22
 *
 * @author Andrés Villalobos Y Redwin
 */
public class ADCliente {

    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public ADCliente() throws Exception {
        _mensaje = "";
    }

    // <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
    // Método1
    public int Insertar(Cliente cliente1) throws Exception {
        int cod_cliente = -1; // el -1 significa que no existe, por ahora
        String sentencia = "INSERT INTO Cliente (ID, NOMBRE,APELLIDO1, TELEFONO, ESTADO) VALUES (?,?,?,?,?)";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement PS = _conexion.prepareStatement(sentencia, PreparedStatement.RETURN_GENERATED_KEYS); // Envía la sentencia según la entidad y regresa las llaves auto generadas

            //Se registra los argumentos de la consulta            
            PS.setInt(1, cliente1.getId());
            PS.setString(2, cliente1.getNombre());
            PS.setString(3, cliente1.getApellido1());
            PS.setString(4, cliente1.getTelefono());
            PS.setBoolean(5, cliente1.isEstado());

            PS.execute(); // Se ejecuta la sentencia- retorna true o false 

            ResultSet rs = PS.getGeneratedKeys(); // El ResultSet es de una celda porque obtiene los identity de un INSERT

            if (rs != null && rs.next()) {
                cod_cliente = rs.getInt(1); //busca el unico registro de la unica columna
                _mensaje = "Cliente ingresado satisfactoriamente";
            }

        } catch (Exception e) {
        } finally {
            _conexion.close(); // Siempre debe cerrar conexiones
        }
        return cod_cliente;
    }

    //Método2
    public int Modificar(Cliente cliente1) throws Exception {
        int result = 0; // No ha obtenido ningún resultado        
        String sentencia = "UPDATE Cliente SET ID = ?, NOMBRE =?, APELLIDO1 =?, TELEFONO=?, ESTADO =? WHERE COD_CLIENTE = ? ";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setInt(1, cliente1.getId());
            ps.setString(2, cliente1.getNombre());
            ps.setString(3, cliente1.getApellido1());
            ps.setString(4, cliente1.getTelefono());
            ps.setBoolean(5, cliente1.isEstado());
            ps.setInt(6, cliente1.getCod_cliente());

            result = ps.executeUpdate();

            if (result > 0) { // devuelve las fulas afectadas y si hubo mas de una avisa
                _mensaje = "Registro modificado!";
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _conexion.close(); // Se cerrará siempre que se manipule la BD
        }

        return result;
    }

    //Método3
    public int Eliminar(Cliente cliente1) throws Exception {
        int result = 0;
        String sentencia = "DELETE Cliente WHERE COD_CLIENTE = ?";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setInt(1, cliente1.getCod_cliente());

            result = ps.executeUpdate();

            if (result > 0) {
                _mensaje = "Registro eliminado!";
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _conexion.close();
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
            String sentencia = "SELECT COD_CLIENTE, ID, NOMBRE,APELLIDO1, TELEFONO, ESTADO FROM Cliente";

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
            _conexion.close();
        }

        return rs;
    }

    //Método5
    // Devuelve una lista con objetos Categoria
    public List<Cliente> ListaRegistros(String condicion) throws Exception {
        List<Cliente> list1 = new ArrayList();
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            Statement Stm = _conexion.createStatement(); // Siempre se debe estable esta conexión con la BD

            String sentencia = "SELECT COD_CLIENTE, ID, NOMBRE,APELLIDO1, TELEFONO, ESTADO FROM Cliente";

            if (!condicion.equals("")) { // Si se envío una condición
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Se usa un bucle siempre para saber lo que tiene un ResultSet
            while (rs.next()) { // Esto solo leerá el único registro que tiene
                list1.add(new Cliente(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6))); // Solo le envía un objeto
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _conexion.close();
        }

        return list1;
    }

    //Método6
    public Cliente ObtenerRegistro(String condicion) throws Exception {
        Cliente cliente1 = new Cliente(); // este es el objeto que devolverá 
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            String sentencia = "SELECT COD_CLIENTE, ID, NOMBRE,APELLIDO1, TELEFONO, ESTADO FROM Cliente";
            Statement Stm = _conexion.createStatement(); // Se usa create ya que no envía parametros a la sentencia

            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Lo que devuelve la columna se establece a los atributos de su entidad (se usan las propiedades de encapsulamiento)
            if (rs.next()) { // Solo devolverá un registro
                cliente1.setCod_cliente(rs.getInt(1));
                cliente1.setId(rs.getInt(2));
                cliente1.setNombre(rs.getString(3));
                cliente1.setApellido1(rs.getString(4));
                cliente1.setTelefono(rs.getString(5));
                cliente1.setEstado(rs.getBoolean(6));
                cliente1.setExiste(true);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _conexion.close();
        }

        return cliente1;
    }
// </editor-fold>

}

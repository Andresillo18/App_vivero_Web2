package AccesoDatos;

import static AccesoDatos.ClaseConexion.getConnection;
import Entidades.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 18-04-22
 *
 * @author Andrés Villalobos Y Redwin
 */
public class ADProductos {
    //ATRIBUTOS

    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public ADProductos() {
        this._mensaje = "";
    }

// <editor-fold desc="This is my custom folding" defaultstate="collapsed">    
    // Método1
    public int Insertar(Producto prod) throws Exception {
        int codProducto = -1; // el -1 significa que no existe, por ahora
        String sentencia = "INSERT INTO Productos (tipoProducto, nombre, descripcion, precio, cantDisponible) VALUES (?,?,?,?,?)";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement PS = _conexion.prepareStatement(sentencia, PreparedStatement.RETURN_GENERATED_KEYS); // Envía la sentencia según la entidad y regresa las llaves auto generadas

            //Se registra los argumentos de la consulta                        
            PS.setString(1, prod.getTipoProducto());
            PS.setString(2, prod.getNombre());
            PS.setString(3, prod.getDescripcion());
            PS.setFloat(4, prod.getPrecio());
            PS.setInt(5, prod.getCantDisponible());

            PS.execute(); // Se ejecuta la sentencia- retorna true o false 

            ResultSet rs = PS.getGeneratedKeys(); // El ResultSet es de una celda porque obtiene los identity de un INSERT

            if (rs != null && rs.next()) {
                codProducto = rs.getInt(1); //busca el unico registro de la unica columna
                _mensaje = "Planta ingresado satisfactoriamente";
            }

        } catch (Exception e) {
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return codProducto;
    }

    //Método2
    public int Modificar(Producto prod) throws Exception {
        int result = 0; // No ha obtenido ningún resultado        
        String sentencia = "UPDATE Productos SET tipoProducto = ?, nombre =?, descripcion= ?,precio=?,cantDisponible=? WHERE codProducto = ? ";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setString(1, prod.getTipoProducto());
            ps.setString(2, prod.getNombre());
            ps.setString(3, prod.getDescripcion());
            ps.setFloat(4, prod.getPrecio());
            ps.setInt(5, prod.getCantDisponible());
            ps.setInt(6, prod.getCodProducto());

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
    public int Eliminar(Producto prod) throws Exception {
        int result = 0;
        String sentencia = "DELETE Productos WHERE codProducto= ?";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setInt(1, prod.getCodProducto());

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
            String sentencia = "SELECT codProducto, tipoProducto, nombre, descripcion, precio, cantDisponible FROM Productos";

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
    public List<Producto> ListaRegistros(String condicion) throws Exception {
        List<Producto> list1 = new ArrayList();
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            Statement Stm = _conexion.createStatement(); // Siempre se debe estable esta conexión con la BD

            String sentencia = "SELECT codProducto, tipoProducto, nombre, descripcion, precio, cantDisponible FROM Productos";

            if (!condicion.equals("")) { // Si se envío una condición
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Se usa un bucle siempre para saber lo que tiene un ResultSet
            while (rs.next()) { // Esto solo leerá el único registro que tiene
                list1.add(new Producto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getInt(6))); // Solo le envía un objeto
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
    public Producto ObtenerRegistro(String condicion) throws Exception {
        Producto prod = new Producto(); // este es el objeto que devolverá 
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            String sentencia = "SELECT codProducto, tipoProducto, nombre, descripcion, precio, cantDisponible FROM Productos";

            Statement Stm = _conexion.createStatement(); // Se usa create ya que no envía parametros a la sentencia

            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Lo que devuelve la columna se establece a los atributos de su entidad (se usan las propiedades de encapsulamiento)
            if (rs.next()) { // Solo devolverá un registro
                prod.setCodProducto(rs.getInt(1));
                prod.setTipoProducto(rs.getString(2));
                prod.setNombre(rs.getString(3));
                prod.setDescripcion(rs.getString(4));
                prod.setPrecio(rs.getFloat(5));
                prod.setCantDisponible(rs.getInt(6));
                prod.setExiste(true);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }

        return prod;
    }
    
// </editor-fold>
}

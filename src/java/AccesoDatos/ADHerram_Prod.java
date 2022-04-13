package AccesoDatos;

import static AccesoDatos.ClaseConexion.getConnection;
import Entidades.Herramienta_Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 31-3-22
 *
 * @author Andrés Villalobos
 */
public class ADHerram_Prod {

    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public ADHerram_Prod() throws Exception {
        _mensaje = "";
    }

    // <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
    // Método1
    public int Insertar(Herramienta_Producto Herram_Prod) throws Exception {
        int cod_factura = -1; // el -1 significa que no existe, por ahora
        String sentencia = "INSERT INTO Herramienta_Producto (NOMBRE, DESCRIPCION, PRECIO, CANTIDAD_DISPONIBLE, MATERIAL, FECHA_VENCIMIENTO) VALUES (?,?,?,?,?,?)";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement PS = _conexion.prepareStatement(sentencia, PreparedStatement.RETURN_GENERATED_KEYS); // Envía la sentencia según la entidad y regresa las llaves auto generadas

            //Se registra los argumentos de la consulta                        
            PS.setString(1, Herram_Prod.getNombre());
            PS.setString(2, Herram_Prod.getDescripcion());
            PS.setFloat(3, Herram_Prod.getPrecio());
            PS.setInt(4, Herram_Prod.getCantidad_disponible());
            PS.setString(5, Herram_Prod.getMaterial());
            PS.setDate(6, Herram_Prod.getFechaVencimiento());

            PS.execute(); // Se ejecuta la sentencia- retorna true o false 

            ResultSet rs = PS.getGeneratedKeys(); // El ResultSet es de una celda porque obtiene los identity de un INSERT

            if (rs != null && rs.next()) {
                cod_factura = rs.getInt(1); //busca el unico registro de la unica columna
                _mensaje = "Herramienta o Producto ingresado satisfactoriamente";
            }

        } catch (Exception e) {
        } finally {
            _conexion.close(); // Siempre debe cerrar conexiones
        }
        return cod_factura;
    }

    //Método2
    public int Modificar(Herramienta_Producto Herram_Prod) throws Exception {
        int result = 0; // No ha obtenido ningún resultado        
        String sentencia = "UPDATE Herramienta_Producto SET NOMBRE = ?, DESCRIPCION =?, PRECIO= ?,CANTIDAD_DISPONIBLE=?,MATERIAL=?, FECHA_VENCIMIENTO=? WHERE COD_HERRAMIENTA_PROD = ? ";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setString(1, Herram_Prod.getNombre());
            ps.setString(2, Herram_Prod.getDescripcion());
            ps.setFloat(3, Herram_Prod.getPrecio());
            ps.setInt(4, Herram_Prod.getCantidad_disponible());
            ps.setString(5, Herram_Prod.getMaterial());
            ps.setDate(6, Herram_Prod.getFechaVencimiento());
            ps.setInt(7, Herram_Prod.getCod_herramienta_prod());

            result = ps.executeUpdate();

            if (result > 0) { // devuelve las filas afectadas y si hubo mas de una avisa
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
    public int Eliminar(Herramienta_Producto Herram_Prod) throws Exception {
        int result = 0;
        String sentencia = "DELETE Herramienta_Producto WHERE COD_HERRAMIENTA_PROD = ?";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setInt(1, Herram_Prod.getCod_herramienta_prod());

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
            String sentencia = "SELECT COD_HERRAMIENTA_PROD, NOMBRE, DESCRIPCION, PRECIO, CANTIDAD_DISPONIBLE, MATERIAL,FECHA_VENCIMIENTO FROM Herramienta_Producto";

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
    public List<Herramienta_Producto> ListaRegistros(String condicion) throws Exception {
        List<Herramienta_Producto> list1 = new ArrayList();
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            Statement Stm = _conexion.createStatement(); // Siempre se debe estable esta conexión con la BD

            String sentencia = "SELECT COD_HERRAMIENTA_PROD, NOMBRE, DESCRIPCION, PRECIO, CANTIDAD_DISPONIBLE, MATERIAL,FECHA_VENCIMIENTO FROM Herramienta_Producto";

            if (!condicion.equals("")) { // Si se envío una condición
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Se usa un bucle siempre para saber lo que tiene un ResultSet
            while (rs.next()) { // Esto solo leerá el único registro que tiene
                list1.add(new Herramienta_Producto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getString(6), rs.getDate(7))); // Solo le envía un objeto
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _conexion.close();
        }

        return list1;
    }

    //Método6
    public Herramienta_Producto ObtenerRegistro(String condicion) throws Exception {
        Herramienta_Producto Herram_Prod = new Herramienta_Producto(); // este es el objeto que devolverá 
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            String sentencia = "SELECT COD_HERRAMIENTA_PROD, NOMBRE, DESCRIPCION, PRECIO, CANTIDAD_DISPONIBLE, MATERIAL,FECHA_VENCIMIENTO FROM Herramienta_Producto";

            Statement Stm = _conexion.createStatement(); // Se usa create ya que no envía parametros a la sentencia

            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Lo que devuelve la columna se establece a los atributos de su entidad (se usan las propiedades de encapsulamiento)
            if (rs.next()) { // Solo devolverá un registro
                Herram_Prod.setCod_herramienta_prod(rs.getInt(1));
                Herram_Prod.setNombre(rs.getString(2));
                Herram_Prod.setDescripcion(rs.getString(3));
                Herram_Prod.setPrecio(rs.getFloat(4));
                Herram_Prod.setCantidad_disponible(rs.getInt(5));
                Herram_Prod.setMaterial(rs.getString(6));
                Herram_Prod.setFechaVencimiento(rs.getDate(7));
                Herram_Prod.setExiste(true);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            _conexion.close();
        }

        return Herram_Prod;
    }

// </editor-fold>
}

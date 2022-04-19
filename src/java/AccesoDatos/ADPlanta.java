package AccesoDatos;

import static AccesoDatos.ClaseConexion.getConnection;
import Entidades.Planta;
import java.sql.Connection;
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
public class ADPlanta {

    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public ADPlanta() throws Exception {
        _mensaje = "";
    }

    // <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
    // Método1
    public int Insertar(Planta planta1) throws Exception {
        int cod_factura = -1; // el -1 significa que no existe, por ahora
        String sentencia = "INSERT INTO Planta (NOMBRE, DESCRIPCION, PRECIO, CANTIDAD_DISPONIBLE, CANTIDAD_REGADO, TIEMPOLUZ_SOLAR, EXTRAS_CARACTERISTICAS) VALUES (?,?,?,?,?,?,?)";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement PS = _conexion.prepareStatement(sentencia, PreparedStatement.RETURN_GENERATED_KEYS); // Envía la sentencia según la entidad y regresa las llaves auto generadas

            //Se registra los argumentos de la consulta                        
            PS.setString(1, planta1.getNombre());
            PS.setString(2, planta1.getDescripcion());
            PS.setFloat(3, planta1.getPrecio());
            PS.setInt(4, planta1.getCantidad_disponible());
            PS.setInt(5, planta1.getCantidad_Regado());
            PS.setFloat(6, planta1.getTiempo_luz_solar());
            PS.setString(7, planta1.getExtras_caracteristicas());

            PS.execute(); // Se ejecuta la sentencia- retorna true o false 

            ResultSet rs = PS.getGeneratedKeys(); // El ResultSet es de una celda porque obtiene los identity de un INSERT

            if (rs != null && rs.next()) {
                cod_factura = rs.getInt(1); //busca el unico registro de la unica columna
                _mensaje = "Planta ingresado satisfactoriamente";
            }

        } catch (Exception e) {
        } finally {
            _conexion.close(); // Siempre debe cerrar conexiones
        }
        return cod_factura;
    }

    //Método2
    public int Modificar(Planta planta1) throws Exception {
        int result = 0; // No ha obtenido ningún resultado        
        String sentencia = "UPDATE Planta SET NOMBRE = ?, DESCRIPCION =?, PRECIO= ?,CANTIDAD_DISPONIBLE=?,CANTIDAD_REGADO=?, TIEMPOLUZ_SOLAR=?, EXTRAS_CARACTERISTICAS=? WHERE COD_PLANTA = ? ";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setString(1, planta1.getNombre());
            ps.setString(2, planta1.getDescripcion());
            ps.setFloat(3, planta1.getPrecio());
            ps.setInt(4, planta1.getCantidad_disponible());
            ps.setInt(5, planta1.getCantidad_Regado());
            ps.setFloat(6, planta1.getTiempo_luz_solar());
            ps.setString(7, planta1.getExtras_caracteristicas());
            ps.setInt(8, planta1.getCod_planta());

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
    public int Eliminar(Planta planta1) throws Exception {
        int result = 0;
        String sentencia = "DELETE Planta WHERE COD_PLANTA= ?";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setInt(1, planta1.getCod_planta());

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
            String sentencia = "SELECT COD_PLANTA, NOMBRE, DESCRIPCION, PRECIO, CANTIDAD_DISPONIBLE, CANTIDAD_REGADO,TIEMPOLUZ_SOLAR, EXTRAS_CARACTERISTICAS FROM Planta";

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
    public List<Planta> ListaRegistros(String condicion) throws Exception {
        List<Planta> list1 = new ArrayList();
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            Statement Stm = _conexion.createStatement(); // Siempre se debe estable esta conexión con la BD

            String sentencia = "SELECT COD_PLANTA, NOMBRE, DESCRIPCION, PRECIO, CANTIDAD_DISPONIBLE, CANTIDAD_REGADO,TIEMPOLUZ_SOLAR, EXTRAS_CARACTERISTICAS FROM Planta";

            if (!condicion.equals("")) { // Si se envío una condición
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Se usa un bucle siempre para saber lo que tiene un ResultSet
            while (rs.next()) { // Esto solo leerá el único registro que tiene
                list1.add(new Planta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6), rs.getFloat(7), rs.getString(8))); // Solo le envía un objeto
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _conexion.close();
        }

        return list1;
    }

    //Método6
    public Planta ObtenerRegistro(String condicion) throws Exception {
        Planta planta1 = new Planta(); // este es el objeto que devolverá 
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            String sentencia = "SELECT COD_PLANTA, NOMBRE, DESCRIPCION, PRECIO, CANTIDAD_DISPONIBLE, CANTIDAD_REGADO,TIEMPOLUZ_SOLAR, EXTRAS_CARACTERISTICAS FROM Planta";

            Statement Stm = _conexion.createStatement(); // Se usa create ya que no envía parametros a la sentencia

            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Lo que devuelve la columna se establece a los atributos de su entidad (se usan las propiedades de encapsulamiento)
            if (rs.next()) { // Solo devolverá un registro
                planta1.setCod_planta(rs.getInt(1));
                planta1.setNombre(rs.getString(2));
                planta1.setDescripcion(rs.getString(3));
                planta1.setPrecio(rs.getFloat(4));
                planta1.setCantidad_disponible(rs.getInt(5));
                planta1.setCantidad_Regado(rs.getInt(6));
                planta1.setTiempo_luz_solar(rs.getFloat(7));
                planta1.setExtras_caracteristicas(rs.getString(8));
                planta1.setExiste(true);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            _conexion.close();
        }

        return planta1;
    }

// </editor-fold>
    
}

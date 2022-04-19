package AccesoDatos;

import Entidades.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
// Si uno desea utilizar los métodos de otra clase, como si fueran de esta clase:
import static AccesoDatos.ClaseConexion.getConnection;

/**
 * 29-3-22
 *
 * @author Andrés Villalobos Y Redwin
 */
public class ADCategoria {

    //**Ahora la conexión no se realizará cuando se llama a la clase, sino cuando se usa cuando método y ese mismo lo cierra
    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    //CONSTRUCTOR    
    public ADCategoria() throws Exception { //Se capacita para enviar excepciones        
        _mensaje = "";
    }

// <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
    // Método1
    public int Insertar(Categoria categoria) throws Exception {
        int id_categoria = -1; // el -1 significa que no existe, por ahora
        String sentencia = "INSERT INTO Categoria (NOMBRE_CATEGORIA,DESCRIPCION) VALUES (?,?)";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement PS = _conexion.prepareStatement(sentencia, PreparedStatement.RETURN_GENERATED_KEYS); // Envía la sentencia según la entidad y regresa las llaves auto generadas

            //Se registra los argumentos de la consulta
            PS.setString(1, categoria.getNombre_categoria());
            PS.setString(2, categoria.getDescripcion());

            PS.execute(); // Se ejecuta la sentencia- retorna true o false 

            ResultSet rs = PS.getGeneratedKeys(); // El ResultSet es de una celda porque obtiene los identity de un INSERT

            if (rs != null && rs.next()) {
                id_categoria = rs.getInt(1); //busca el unico registro de la unica columna
                _mensaje = "Categoría ingresado satisfactoriamente";
            }

        } catch (Exception e) {
        } finally {
             if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }
        return id_categoria;
    }

    //Método2
    public int Modificar(Categoria categoria) throws Exception {
        int result = 0; // No ha obtenido ningún resultado        
        String sentencia = "UPDATE Categoria SET NOMBRE_CATEGORIA = ?, DESCRIPCION =? WHERE COD_CATEGORIA = ? ";
        Connection _conexion = null;

        try {
            _conexion = ClaseConexion.getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setString(1, categoria.getNombre_categoria());
            ps.setString(2, categoria.getDescripcion());
            ps.setInt(3, categoria.getCod_categoria());

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
    public int Eliminar(Categoria categoria) throws Exception {
        int result = 0;
        String sentencia = "DELETE Categoria WHERE COD_CATEGORIA = ?";
        Connection _conexion = null;

        try {
            _conexion = ClaseConexion.getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setInt(1, categoria.getCod_categoria());

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
            _conexion = ClaseConexion.getConnection();
            Statement Stm = _conexion.createStatement(); // Se usa un statement ya que lo que se enviará no tendrá un parámetro de entrada
            String sentencia = "SELECT COD_CATEGORIA, NOMBRE_CATEGORIA, DESCRIPCION FROM Categoria";

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
    public List<Categoria> ListaRegistros(String condicion) throws Exception {
        List<Categoria> list1 = new ArrayList();
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = ClaseConexion.getConnection();
            Statement Stm = _conexion.createStatement(); // Siempre se debe estable esta conexión con la BD

            String sentencia = "SELECT COD_CATEGORIA, NOMBRE_CATEGORIA, DESCRIPCION FROM Categoria";

            if (!condicion.equals("")) { // Si se envío una condición
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Se usa un bucle siempre para saber lo que tiene un ResultSet
            while (rs.next()) { // Esto solo leerá el único registro que tiene
                list1.add(new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3))); // Solo le envía un objeto
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
    public Categoria ObtenerRegistro(String condicion) throws Exception {
        Categoria categoria1 = new Categoria(); // este es el objeto que devolverá 
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = ClaseConexion.getConnection();
            String sentencia = "SELECT COD_CATEGORIA, NOMBRE_CATEGORIA, DESCRIPCION FROM Categoria";
            Statement Stm = _conexion.createStatement(); // Se usa create ya que no envía parametros a la sentencia

            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Lo que devuelve la columna se establece a los atributos de su entidad (se usan las propiedades de encapsulamiento)
            if (rs.next()) { // Solo devolverá un registro
                categoria1.setCod_categoria(rs.getInt(1));
                categoria1.setNombre_categoria(rs.getString(2));
                categoria1.setDescripcion(rs.getString(3));
                categoria1.setExiste(true);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }

        return categoria1;
    }
// </editor-fold>

}

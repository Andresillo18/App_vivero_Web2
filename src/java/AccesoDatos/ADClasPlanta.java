package AccesoDatos;

import static AccesoDatos.ClaseConexion.getConnection;
import Entidades.ClasPlanta;
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
public class ADClasPlanta {

    //ATRIBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    public ADClasPlanta() throws Exception {
        _mensaje = "";
    }

// <editor-fold desc="MÉTODOS" defaultstate="collapsed">    
    // Método1
    public int Insertar(ClasPlanta clasPlanta1) throws Exception {
        int cod_clasificacion = -1; // el -1 significa que no existe, por ahora
        String sentencia = "INSERT INTO Clasificacion_Planta (COD_PLANTA, COD_CATEGORIA) VALUES (?,?)";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement PS = _conexion.prepareStatement(sentencia, PreparedStatement.RETURN_GENERATED_KEYS); // Envía la sentencia según la entidad y regresa las llaves auto generadas

            //Se registra los argumentos de la consulta            
            PS.setInt(1, clasPlanta1.getCod_planta());
            PS.setInt(2, clasPlanta1.getCod_categoria());

            PS.execute(); // Se ejecuta la sentencia- retorna true o false 

            ResultSet rs = PS.getGeneratedKeys(); // El ResultSet es de una celda porque obtiene los identity de un INSERT

            if (rs != null && rs.next()) {
                cod_clasificacion = rs.getInt(1); //busca el unico registro de la unica columna
                _mensaje = "Clasificación ingresado satisfactoriamente";
            }

        } catch (Exception e) {
        } finally {
              if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }
        return cod_clasificacion;
    }

    //Método2
    public int Modificar(ClasPlanta clasPlanta1) throws Exception {
        int result = 0; // No ha obtenido ningún resultado        
        String sentencia = "UPDATE Clasificacion_Planta SET COD_PLANTA = ?, COD_CATEGORIA =? WHERE COD_CLASIFICACION = ? ";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setInt(1, clasPlanta1.getCod_planta());
            ps.setInt(2, clasPlanta1.getCod_categoria());
            ps.setInt(3, clasPlanta1.getCod_clasificacion());

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
    public int Eliminar(ClasPlanta clasPlanta1) throws Exception {
        int result = 0;
        String sentencia = "DELETE Clasificacion_Planta WHERE COD_CLASIFICACION = ?";
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            ps.setInt(1, clasPlanta1.getCod_clasificacion());

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
            String sentencia = "SELECT COD_CLASIFICACION, COD_PLANTA, COD_CATEGORIA FROM Clasificacion_Planta";

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
    // Devuelve una lista con objetos 
    public List<ClasPlanta> ListaRegistros(String condicion) throws Exception {
        List<ClasPlanta> list1 = new ArrayList();
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            Statement Stm = _conexion.createStatement(); // Siempre se debe estable esta conexión con la BD

            String sentencia = "SELECT COD_CLASIFICACION, COD_PLANTA, COD_CATEGORIA FROM Clasificacion_Planta";

            if (!condicion.equals("")) { // Si se envío una condición
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Se usa un bucle siempre para saber lo que tiene un ResultSet
            while (rs.next()) { // Esto solo leerá el único registro que tiene
                list1.add(new ClasPlanta(rs.getInt(1), rs.getInt(2), rs.getInt(3))); // Solo le envía un objeto
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
    public ClasPlanta ObtenerRegistro(String condicion) throws Exception {
        ClasPlanta clasPlanta1 = new ClasPlanta(); // este es el objeto que devolverá 
        ResultSet rs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            String sentencia = "SELECT COD_CLASIFICACION, COD_PLANTA, COD_CATEGORIA FROM Clasificacion_Planta";
            Statement Stm = _conexion.createStatement(); // Se usa create ya que no envía parametros a la sentencia

            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion); // Interpolación de Strings 
            }

            rs = Stm.executeQuery(sentencia);

            // Lo que devuelve la columna se establece a los atributos de su entidad (se usan las propiedades de encapsulamiento)
            if (rs.next()) { // Solo devolverá un registro
                clasPlanta1.setCod_clasificacion(rs.getInt(1));
                clasPlanta1.setCod_planta(rs.getInt(2));
                clasPlanta1.setCod_categoria(rs.getInt(3));
                clasPlanta1.setExiste(true);
            }
        } catch (Exception e) {
            throw e;
        } finally {
              if (_conexion != null) {

                ClaseConexion.close(_conexion);
            }
        }

        return clasPlanta1;
    }
// </editor-fold>

}

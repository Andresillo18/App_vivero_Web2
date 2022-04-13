package AccesoDatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 12-4-22
 *
 * @author Andrés Villalobos
 */
//Esta clase se encargará de cerrar las conexiones a la BD
public class ClaseConexion {

    // CADENA DE CONEXIÓN: 
    //Es estática y una constante porque se considera que nunca va a cambiar y siempre que una variable es constante es bueno que sea static
    private static final String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=BD_VIVERO;user=sa;password=sa;";

// <editor-fold desc="Métodos" defaultstate="collapsed">            
    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        return DriverManager.getConnection(connectionString);
    }
// </editor-fold>

// <editor-fold desc="Métodos Close" defaultstate="collapsed">
    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void close(Statement st) throws SQLException {
        st.close();
    }

    public static void close(PreparedStatement pst) throws SQLException {
        pst.close();
    }

    public static void close(CallableStatement cst) throws SQLException {
        cst.close();
    }

    public static void close(Connection conexion) throws SQLException {
        conexion.close();
    }
// </editor-fold>

}

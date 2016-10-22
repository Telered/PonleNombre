package tequiero.com.ponlenombre;
/*
 * PROYECTO FINAL Programación JAVA + BBDD + Entornos de desarrollo
 * Félix Baltanás 1ºDAM -- felixbaltanas@hotmail.com
 * Centro Integral de Formación Los Enlaces
 * Zaragoza, España
 */


        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;

public class DataBaseSegura extends DataBase {

    // Esta clase está extendida a DataBase (extends DataBase...{ )

    // Este método eleva los parámetros a método en Clase padre

    public DataBaseSegura(String bd, String login, String password, String servidorMysql) {
        super(bd, login, password, servidorMysql);
    }




}

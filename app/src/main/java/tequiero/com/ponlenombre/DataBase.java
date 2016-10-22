package tequiero.com.ponlenombre;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataBase {

    private static java.sql.Connection conexion2;
    String bd;
    String login;
    String password;
    String servidorMysql;
    Connection conexion;
    Object[][] dtPersona;

    /**
     * Esta Clase sirve para conectar con la base de datos SQL local alojada en
     * el PC
     *
     *
     * Se abre un método que this los parámetros. Se crean 4 parámetros String:
     * bd,login,password y servidorMysql
     *
     * @param bd
     * @param login
     * @param password
     * @param servidorMysql
     */
    public DataBase(String bd, String login, String password, String servidorMysql) {

        this.bd = bd;
        this.login = login;
        this.password = password;
        this.servidorMysql = servidorMysql;
    }

    /**
     * Metodo que abre la conexión con la base de datos. Se crea un boleano con
     * el que contestar a abrirConexión: Si se abre, dará true.
     *
     * @return
     */
    public boolean abrirConexion() {

        boolean estado = false;

        try {
            // Se carga el driver con Class.forName("-------------");
            Class.forName("com.mysql.jdbc.Driver");

            // Se crea la conexión:   --> Si da error --> estado sigue en false
            // Se abre el método de DriveManager getConnection
            conexion2 = DriverManager.getConnection(servidorMysql + bd, login, password);
            estado = true;
        } catch (SQLException e) {
            System.out.println("SQL Exception:\n" + e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception:\n" + e.toString());
        } catch (Exception e) {
            System.out.println("Exception:\n" + e.toString());
        }

        return estado;
    }

    /**
     * Metodo que cierra la conexion con la base de datos.
     */
    public void cerrarConexion() {

        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int ejecutaUpdate(String statement) {
        int n = 0;
        try {
            Statement st = conexion.createStatement();
            //  System.out.println("La sentencia es: " + statement);
            n = st.executeUpdate(statement);
        } catch (SQLException ex) {
            System.out.println("SQL Exception:\n" + ex.getMessage());
        }
        return n;
    }


    public int ejecutaCambio(String consulta) {
        Statement st = null;
        int rs = 0;
        try {
            st = conexion.createStatement();
            rs = st.executeUpdate(consulta);
        } catch (SQLException ex) {
            System.out.println("Error sql: " + ex.getMessage());
        } catch (NullPointerException npe) {
            System.out.println(npe);
        }
//        try {
//            st.close();
//        } catch (SQLException ex) {
//            System.out.println("Error sql: " + ex.getMessage());
//        }
        return rs;
    }

    public static ResultSet ejecutaConsulta(String consulta) {
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conexion2.createStatement();
            rs = st.executeQuery(consulta);
        } catch (SQLException ex) {
            System.out.println("Error sql: " + ex.getMessage());
        } catch (NullPointerException npe) {
            System.out.println(npe);
        }
//        try {
//            st.close();
//        } catch (SQLException ex) {
//            System.out.println("Error sql: " + ex.getMessage());
//        }
        return rs;
    }



}




package tequiero.com.ponlenombre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PonleNombre extends AppCompatActivity {
    private EditText txtNombre;
    private Button btnAceptar;
    private TextView testo,testa;
    String bd;
    String login;
    String password;
    String servidorMysql;
    static Connection conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ponle_nombre);

//Obtenemos una referencia a los controles de la interfaz
        txtNombre = (EditText) findViewById(R.id.editText);
        btnAceptar = (Button) findViewById(R.id.b1);
        testo = (TextView) findViewById(R.id.tv1);
        testa = (TextView) findViewById(R.id.textView2);
        //Implementamos el evento click del botón
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(PonleNombre.this, Resultados.class);

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                b.putString("NOMBRE: ", txtNombre.getText().toString());

                //Añadimos la información al intent
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });


        String bd = "personas";
        String user = "user";
        String password = "1234";
        String servidor = "jdbc:mysql://localhost/";

        // DataBaseSegura es una clase MUCHO MÁS PROTEGIDA QUE DataBase
        // Se crea el objeto db DataBaseSegura (hijo de DataBase)
        //
        // es como poner Animal oso = nuevo mamífero (hijo heredado de DataBase)
        //
        // Se utilizarán los métodos de DataBase si no están en DataBaseSegura
        DataBase db = new DataBase(bd, user, password, servidor);

        // Se intenta (DEVUELVE UN TRUE O FALSE) abrir la base de datos
        if (db.abrirConexion()) {

            // JOptionPane.showMessageDialog(null, "Abierta la base de datos", "OK", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("Error en la apertura de la base de datos");
        }
        ResultSet rs;
        String cadena = "";
        int habit = 0;
        try {
            String sql = "SELECT nombre,frecuencia FROM nombres where nombre='ANTONIO'";
            ResultSet rt = ejecutaConsulta(sql);

         testo.setText(rt.getString(1));
            testa.setText(rt.getString(2));
            } catch (SQLException ex) {
                //String ss = ex.printStackTrace();
                      }

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ponle_nombre, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }













    public boolean abrirConexion() {

        boolean estado = false;

        try {
            // Se carga el driver con Class.forName("-------------");
            Class.forName("com.mysql.jdbc.Driver");

            // Se crea la conexión:   --> Si da error --> estado sigue en false
            // Se abre el método de DriveManager getConnection
            conexion = DriverManager.getConnection(servidorMysql + bd, login, password);
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

    public static ResultSet ejecutaConsulta(String consulta) {
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conexion.createStatement();
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

    public void cerrarConexion() {

        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
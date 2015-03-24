/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
     private static Connection conexion = null;

    private static void conectar() {
        try {
            //carga el driver para la conexion
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //la conexion mediante el metodo getConnection toma 
            //la direccion de la bd en localhost,el usuario de bd y la contraseña 
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbprolevel", "gerente", "j3216514086");
        } catch (SQLException sqlException) {
            System.out.println("Error de MySQL: " + sqlException.getMessage());
        } catch (Exception exception) {
            System.out.println("Error " + exception.getMessage());
        }
    }
    //constructor
    private Conexion() {
    }
    
    //solo permite crear un objeto y utilizarlo
    //singleton
    public static Connection getInstance() {
        if (conexion == null) {
            conectar();
        }
        return conexion;
    }
    
}

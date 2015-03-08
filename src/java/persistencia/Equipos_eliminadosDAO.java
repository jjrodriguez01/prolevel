/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import modelo.Equipos_eliminadosDTO;

import utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class Equipos_eliminadosDAO {
      Connection conexion = null;
    //instanciamos preparestatment
    PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    String mensaje = "";
    //variable que cuenta las filas afectadas
    int rtdo = 0;

    ResultSet rs;

    public Equipos_eliminadosDAO() {
        conexion = Conexion.getInstance();
    }

    public String insertar(Equipos_eliminadosDTO usu) {

        try {
            //sentencia sql
            String sql = "INSERT INTO Equipos_eliminados(CodigoEquipo,IdTorneo)VALUES(?,?)";
            //pasamos la sentencia la conexion mediante el prepare staement
            statement = conexion.prepareStatement(sql);
            //obtenemos los datos del dto de la tabla
            statement.setInt(1, usu.getCodigoEquipo());
            statement.setInt(2, usu.getIdTorneo());
           
            //ejecuta el insert
            rtdo = statement.executeUpdate();
            //si se afectaron campos 
            if (rtdo != 0) {
                System.out.println("se insertaron:" + rtdo + "Datos");
                //si no se afecto la tabla
            } else {
                mensaje = "Error";
            }
        } 
        catch (SQLException sqlexception) {
            mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();
        }
        //devolvemos el mensaje al usuario
        return mensaje;
    }

    public String actulizar(Equipos_eliminadosDTO usu) {
        try {
            //preparamos la sentencia sql
            String sql = "UPDATE Equipos_eliminados SET CodigoEquipo=?,IdTorneo=? WHERE CodigoEquipo=?;";
            //pasamos el query a la conexion
           //sacamos los datos del dto de la tabla
            statement = conexion.prepareStatement(sql);
            statement.setInt(2, usu.getCodigoEquipo());
            statement.setInt(3, usu.getIdTorneo());
           
            //el resulset trae el numero de rows afectadas
            rtdo = statement.executeUpdate();
            if (rtdo != 0) {

              mensaje="El Campo Se a modificado:" + rtdo + "saludes";

            } else {
                mensaje = "Error";
            }
        } catch (SQLException sqlexception) {
            mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        }

        return mensaje;

    }

    public String eliminar(Equipos_eliminadosDTO usu) {
        try {
            statement = conexion.prepareStatement("Delete from Equipos_eliminados where CodigoEquipo=?;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, usu.getCodigoEquipo());
            rtdo = statement.executeUpdate();

            if (rtdo != 0) {
                mensaje="El siguiente campo" + rtdo + "se elimino Corretamente";
            } else {
                mensaje = "Ocurrio Un Error";
            }
        } catch (SQLException sqlexception) {
            mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        }

        return mensaje;
    }

    public List<Equipos_eliminadosDTO> listarTodo() {
        //creamos el array que va a contener los datos de la consulta    
        ArrayList<Equipos_eliminadosDTO> listarUsuarios = new ArrayList();

        try {
            String query = "SELECT  Equipos_eliminados as CodigoEquipo, idTorneo"
                    + " FROM Equipos_eliminados ";
            statement = conexion.prepareStatement(query);
            rs = statement.executeQuery();
            //mientras que halla registros cree un nuevo dto y pasele la info
            while (rs.next()) {
                //crea un nuevo dto
               Equipos_eliminadosDTO usu = new Equipos_eliminadosDTO();
                //le pasamos los datos que se encuentren
                usu.setCodigoEquipo(rs.getInt("CodigoEquipo"));
                usu.setIdTorneo(rs.getInt("idTorneo"));
               
                //agregamos el objeto dto al arreglo
                listarUsuarios.add(usu);

            }
        } catch (SQLException sqlexception) {
           mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        } finally {

        }
        //devolvemos el arreglo
        return listarUsuarios;

    }

    public String listarUno(Equipos_eliminadosDTO elim) {
        try {
            //preparamos la consulta 
            statement = conexion.prepareStatement("SELECT CodigoEquipo,idTorneo FROM Equipos_eliminados "
                    + "WHERE codigoEquipo = ? ;");
            statement.setInt(1, elim.getCodigoEquipo());
            rs = statement.executeQuery();
            //mientras halla registros
            while (rs.next()) {
                elim.setCodigoEquipo(rs.getInt("CodigoEquipo"));
                elim.setIdTorneo(rs.getInt("Torneo"));
               

            }

        } catch (SQLException ex) {
            mensaje = "Error inesperado: " + ex.getMessage() + " codigo de error " + ex.getErrorCode();
        }
        //devolvemos el usuario que se encontro
        return "" + elim;
    }

}


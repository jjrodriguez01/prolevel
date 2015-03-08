/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import modelo.PartidoDTO;
import utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class PartidoDAO {
 

     Connection conexion = null;
    //instanciamos preparestatment
    PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    String mensaje = "";
    //variable que cuenta las filas afectadas
    int rtdo = 0;

    ResultSet rs;

    public PartidoDAO() {
        conexion = Conexion.getInstance();
    }

    public String insertar(PartidoDTO part) {

        try {
            //sentencia sql
            String sql = "INSERT INTO PartidoDTO (idTabla,idPartido,marcadorEquipo1,marcadorEquipo2,numeroCancha)VALUES(?,?,?,?,?)";
            //pasamos la sentencia la conexion mediante el prepare staement
            statement = conexion.prepareStatement(sql);
            //obtenemos los datos del dto de la tabla
            statement.setInt(1, part.getIdTabla());
            statement.setInt(2, part.getIdPartido());
            statement.setInt(3, part.getMarcadorEquipo1());
            statement.setInt(4, part.getMarcadorEquipo2());
            statement.setInt(5, part.getNumeroCancha());
           

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

    public String actualizar(PartidoDTO part) {
        try {
            //preparamos la sentencia sql
            String sql = "UPDATE Partido SET idTabla=?,idPartido=?,marcadorEquipo1=?,macadorEquipo2=?,numeroCancha=?;";
            //pasamos el query a la conexion
           //sacamos los datos del dto de la tabla
            statement = conexion.prepareStatement(sql);
            statement.setInt(2, part.getIdTabla());
            statement.setInt(3, part.getIdPartido());
            statement.setInt(4, part.getMarcadorEquipo1());
            statement.setInt(5, part.getMarcadorEquipo2());
            statement.setInt(7, part.getNumeroCancha());
           
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

    public String eliminar(PartidoDTO part) {
        try {
            statement = conexion.prepareStatement("Delete from Partido where idTabla=?;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, part.getIdTabla());
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

    public List<PartidoDTO> listarTodo() {
        //creamos el array que va a contener los datos de la consulta    
        ArrayList<PartidoDTO> listarUsuarios = new ArrayList();

        try {
            String query = "SELECT  partido as idTabla, idPartido, marcadorEquipo1, marcadorEquipo2, numeroCancha "
                    + " FROM partido ";
            statement = conexion.prepareStatement(query);
            rs = statement.executeQuery();
            //mientras que halla registros cree un nuevo dto y pasele la info
            while (rs.next()) {
                //crea un nuevo dto
                PartidoDTO part = new PartidoDTO();
                //le pasamos los datos que se encuentren
                part.setIdTabla(rs.getInt("idTabla"));
                part.setIdPartido(rs.getInt("idPartido"));
                part.setMarcadorEquipo1(rs.getInt("marcadorEquipo1"));
                part.setMarcadorEquipo2(rs.getInt("marcadorEquipo2"));
                part.setNumeroCancha(rs.getInt("numeroCancha"));
              
                //agregamos el objeto dto al arreglo
                listarUsuarios.add(part);

            }
        } catch (SQLException sqlexception) {
            mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        } finally {

        }
        //devolvemos el arreglo
        return listarUsuarios;

    }

    public String listarUno(PartidoDTO part) {
        try {
            //preparamos la consulta 
            statement = conexion.prepareStatement("SELECT partido,"
                    + "marcadorEquipo1, marcadorEquipo1,marcadorEquipo2,numeroCancha FROM Equipo "
                    + "WHERE idTabla = ? ;");
            statement.setInt(1, part.getIdTabla());
            rs = statement.executeQuery();
            //mientras halla registros
            while (rs.next()) {
                 part.setIdTabla(rs.getInt("idTabla"));
                part.setIdPartido(rs.getInt("idPartido"));
                part.setMarcadorEquipo1(rs.getInt("marcadorEquipo1"));
                part.setMarcadorEquipo2(rs.getInt("marcadorEquipo2"));
                part.setNumeroCancha(rs.getInt("numeroCancha"));

            }

        } catch (SQLException ex) {
            mensaje = "Error inesperado: " + ex.getMessage() + " codigo de error " + ex.getErrorCode();
        }
        //devolvemos el usuario que se encontro
        return "" + part;
    }

}   


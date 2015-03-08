/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;


import utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;
import modelo.CalendarioDTO;
public class CalendarioDAO {
 
     Connection conexion = null;
    //instanciamos preparestatment
    PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    String mensaje = "";
    //variable que cuenta las filas afectadas
    int rtdo = 0;

    ResultSet rs;

    public CalendarioDAO() {
        conexion = Conexion.getInstance();
    }

    public String insertar(CalendarioDTO cal) {

        try {
            //sentencia sql
            String sql = "INSERT INTO Calendario(codigoCalendario,equipo1,equipo2,fecha,hora,idTorneo,calendariocol)VALUES(?,?,?,?,?,?,?)";
            //pasamos la sentencia la conexion mediante el prepare staement
            statement = conexion.prepareStatement(sql);
            //obtenemos los datos del dto de la tabla
            statement.setInt(1, cal.getCodigoCalendario());
            statement.setInt(2, cal.getEquipo1());
            statement.setInt(3, cal.getEquipo2());
            statement.setDate(4, cal.getFecha());
            statement.setTime(5, cal.getHora());
            statement.setInt(6, cal.getIdTorneo());
            statement.setString(7, cal.getCalendariocol());
        

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

    public String actualizar(CalendarioDTO cal) {
        try {
            //preparamos la sentencia sql
            String sql = "UPDATE Calendario SET codigoCalendario=?,equipo1=?,equipo2=?,fecha=?,hora=?,idTorneo=?,calendariocol=? WHERE codigoCalendario=?;";
            //pasamos el query a la conexion
           //sacamos los datos del dto de la tabla
            statement = conexion.prepareStatement(sql);
            statement.setInt(2, cal.getEquipo1());
            statement.setInt(3, cal.getEquipo2());
            statement.setDate(4, cal.getFecha());
            statement.setTime(5,cal.getHora());
            statement.setInt(7, cal.getIdTorneo());
            statement.setString(8, cal.getCalendariocol());
            
            //el resulset trae el numero de rows afectadas
            rtdo = statement.executeUpdate();
            if (rtdo != 0) {
                mensaje = "elCampo se ha modificado: "+rtdo+ "saludes";

            } else {
                mensaje = "Error";
            }
        } catch (SQLException sqlexception) {
         mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();
        }

        return mensaje;

    }

    public String eliminar(CalendarioDTO cal) {
        try {
            statement = conexion.prepareStatement("Delete from usuarios where idUsuario=?;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, cal.getCodigoCalendario());
            rtdo = statement.executeUpdate();

            if (rtdo != 0) {
                System.out.println("El siguiente campo" + rtdo + "se elimino Corretamente");
            } else {
                mensaje = "Ocurrio Un Error";
            }
        } catch (SQLException sqlexception) {
             mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        }

        return mensaje;
    }

    public List<CalendarioDTO> listarTodo() {
        //creamos el array que va a contener los datos de la consulta    
        ArrayList<CalendarioDTO> listarUsuarios = new ArrayList();

        try {
            String query = "SELECT   Calendario as codigoCalendario,equipo1,equipo2,fecha,hora,idTorneo,calendariocol "
                    + " FROM Calendario ";
            statement = conexion.prepareStatement(query);
            rs = statement.executeQuery();
            //mientras que halla registros cree un nuevo dto y pasele la info
            while (rs.next()) {
                //crea un nuevo dto
                CalendarioDTO cal = new CalendarioDTO();
                //le pasamos los datos que se encuentren
                cal.setCodigoCalendario(rs.getInt("codigoCalendario"));
                cal.setEquipo1(rs.getInt("equipo1"));
                cal.setEquipo2(rs.getInt("equipo2"));
                cal.setFecha(rs.getDate("fecha"));
                cal.setHora(rs.getTime("hora"));
                cal.setIdTorneo(rs.getInt("idTorneo"));
                cal.setCalendariocol(rs.getString("calendariocol"));
                
                //agregamos el objeto dto al arreglo
                listarUsuarios.add(cal);

            }
        } catch (SQLException sqlexception) {
            mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        } finally {

        }
        //devolvemos el arreglo
        return listarUsuarios;

    }

    public String listarUno(CalendarioDTO cal) {
        try {
            //preparamos la consulta 
            statement = conexion.prepareStatement("SELECT codigoCalendario,"
                    + "codigoCalendario,equipo1,equipo2,fecha,hora,idTorneo,calendariocol  FROM Calendario"
                    + "WHERE codigoCalendario = ? ;");
            statement.setInt(1, cal.getCodigoCalendario());
            rs = statement.executeQuery();
            //mientras halla registros
            while (rs.next()) {
                cal.setCodigoCalendario(rs.getInt("codigoCalendario"));
                cal.setEquipo1(rs.getInt("equipo1"));
                cal.setEquipo2(rs.getInt("equipo2"));
                cal.setFecha(rs.getDate("fecha"));
                cal.setHora(rs.getTime("hora"));
                cal.setIdTorneo(rs.getInt("idTorneo"));
                cal.setCalendariocol(rs.getString("calendariocol"));
               
            }

        } catch (SQLException ex) {
            mensaje = "Error inesperado: " + ex.getMessage() + " codigo de error " + ex.getErrorCode();
        }
        //devolvemos el usuario que se encontro
        return "" + cal;
    }

}
  


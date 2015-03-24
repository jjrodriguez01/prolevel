/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import modelo.ReservasDTO;
import utilidades.Conexion;
public class ReservasDAO {
    
    Connection conexion = null;
    //instanciamos preparestatment
    PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    String mensaje = "";
    //variable que cuenta las filas afectadas
    int rtdo = 0;

    ResultSet rs;

    public ReservasDAO() {
        conexion = Conexion.getInstance();
    }

    public String insertar(ReservasDTO res) {

        try {
            //sentencia sql
            String sql = "INSERT INTO Reservas(codigo,fecha,hora,usuarioIdUsuario,numeroCancha,estado) "
                    + "VALUES(?,?,?,?,?,?)";
            //pasamos la sentencia la conexion mediante el prepare staement
            statement = conexion.prepareStatement(sql);
            //obtenemos los datos del dto de la tabla
            statement.setInt(1, res.getCodigo());
            statement.setString(2, res.getFecha());
            statement.setString(3, res.getHora());
            statement.setInt(4, res.getUsuarioIdUsuario());
            statement.setInt(5, res.getNumeroCancha());
            statement.setBoolean(6, res.isEstado());

            //ejecuta el insert
            rtdo = statement.executeUpdate();
            //si se afectaron campos 
            if (rtdo != 0) {
                System.out.println("se inserto la rserva");
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

    public String actualizar (ReservasDTO res) {
        try {
            //preparamos la sentencia sql
            String sql = "UPDATE reservas  SET fecha=?,hora=?,usuarioIdUsuario=?,numeroCancha=? WHERE numeroCancha=?;";
            //pasamos el query a la conexion
           //sacamos los datos del dto de la tabla
            statement = conexion.prepareStatement(sql);
            statement.setString(2, res.getFecha());
            statement.setString(3, res.getHora());
            statement.setInt(4, res.getUsuarioIdUsuario());
            statement.setInt(5, res.getNumeroCancha());
            statement.setBoolean(6, res.isEstado());
            //el resulset trae el numero de rows afectadas
            rtdo = statement.executeUpdate();
            if (rtdo != 0) {

                mensaje="La reserva se ha modificado";

            } else {
                mensaje = "Error";
            }
        } catch (SQLException sqlexception) {
           mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        }

        return mensaje;

    }

    public String eliminar(int codigo) {
        try {
            statement = conexion.prepareStatement("Delete from reservas where codigo=?;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, codigo);
            rtdo = statement.executeUpdate();

            if (rtdo != 0) {
             mensaje="Se elimino la reserva";
            } else {
                mensaje = "Ocurrio Un Error";
            }
        } catch (SQLException sqlexception) {
         mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        }

        return mensaje;
    }

    public LinkedList<ReservasDTO> listarTodo() {
        //creamos el array que va a contener los datos de la consulta    
        LinkedList<ReservasDTO> listarReservas = new LinkedList();

        try {
            String query = "SELECT  * "
                    + " FROM reservas ";
            statement = conexion.prepareStatement(query);
            rs = statement.executeQuery();
            //mientras que halla registros cree un nuevo dto y pasele la info
            while (rs.next()) {
                //crea un nuevo dto
                ReservasDTO res = new ReservasDTO();
                //le pasamos los datos que se encuentren
                res.setCodigo(rs.getInt("codigo"));
                res.setFecha(rs.getString("fecha"));
                res.setHora(rs.getString("hora"));
                res.setUsuarioIdUsuario(rs.getInt("usuarioIdUsuario"));
                res.setNumeroCancha(rs.getInt("numeroCancha"));
                res.setEstado(rs.getBoolean("estado"));
                //agregamos el objeto dto al arreglo
                listarReservas.add(res);

            }
        } catch (SQLException sqlexception) {
           mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        } finally {

        }
        //devolvemos el arreglo
        return listarReservas;

    }

    public ReservasDTO listarUno(int codigo) {
        ReservasDTO reservas = new ReservasDTO();
        try {
            //preparamos la consulta 
            statement = conexion.prepareStatement("SELECT codigo,"
                    + "fecha,hora,usuarioIdUsuario,numeroCancha,estado"
                    + " FROM Reservas "
                    + "WHERE codigo = ? ;");
            statement.setInt(1, codigo);
            rs = statement.executeQuery();
            //mientras halla registros
            while (rs.next()) {
                
                reservas.setCodigo(rs.getInt("codigo"));
                reservas.setFecha(rs.getString("fecha"));
                reservas.setHora(rs.getString("hora"));
                reservas.setUsuarioIdUsuario(rs.getInt("usuarioIdUsuario"));
                reservas.setNumeroCancha(rs.getInt("numeroCancha"));
               reservas.setEstado(rs.getBoolean("estado"));
            }

        } catch (SQLException ex) {
            mensaje = "Error inesperado: " + ex.getMessage() + " codigo de error " + ex.getErrorCode();
        }
        //devolvemos el usuario que se encontro
        return reservas;
    }

}

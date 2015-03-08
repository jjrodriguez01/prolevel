/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;
import java.sql.CallableStatement;
 import modelo.TarjetasDTO;
import utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
public class TarjetasDAO {

     Connection conexion = null;
    //instanciamos preparestatment
    PreparedStatement statement=null;
    //variable que devuelve el metodo con el mensaje
    String mensaje = "";
    //variable que cuenta las filas afectadas
    int rtdo = 0;

    ResultSet rs=null;
    CallableStatement call = null;

    public TarjetasDAO() {
        conexion = Conexion.getInstance();
    }

    public String insertar(TarjetasDTO tar) {

        try {
            call=conexion.prepareCall("{call sp_aumentartarjetas(?,?,?,?,?)};");
           
            call.setInt(1, tar.getIdJugador());
            call.setInt(2, tar.getIdtorneo());
            call.setInt(3, tar.getTarjetaRoja());
            call.setInt(4, tar.getTarjetaAzul());
            
            call.registerOutParameter(5, Types.INTEGER);
            rtdo = call.executeUpdate();

            int salida = call.getInt(5);
            if ( rtdo > 0) {
                mensaje = "Se insertaron las tarjetas";
            }else {
                mensaje = "No se pudo insertar tarjetas";
        }
        } 
        catch (SQLException sqlexception) {
            mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();
        }
        //devolvemos el mensaje al usuario
        return mensaje;
    }
    public String insertarPrimer (TarjetasDTO tar){
        try {
            statement = conexion.prepareStatement("INSERT INTO tarjetas "
                    + " values(?,?,?,?);");
           
            statement.setInt(1, tar.getTarjetaAzul());
            statement.setInt(2, tar.getTarjetaRoja());
            statement.setInt(3, tar.getIdJugador());
            statement.setInt(4, tar.getIdtorneo());
            
            rtdo = statement.executeUpdate();
            
            if (rtdo > 0) {
                mensaje = "se insertaron las tarjetas";
            }else {
                mensaje = "No se pudo insertar tarjetas";
            
        }
        } 
        catch (SQLException sqlexception) {
            mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();
        }
        return mensaje;
    }

    public String actualizar(TarjetasDTO tar) {
        try {
            //preparamos la sentencia sql
            String sql = "UPDATE Tarjetas SET "
                    + " TarjetaAzul=?,TarjetaRoja=? "
                    + " WHERE idJugador=? and idTorneo=?;";
            //pasamos el query a la conexion
           //sacamos los datos del dto de la tabla
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, tar.getTarjetaAzul());
            statement.setInt(2, tar.getTarjetaRoja());
            statement.setInt(3, tar.getIdJugador());
            statement.setInt(4, tar.getIdtorneo());
           
            //el resulset trae el numero de rows afectadas
            rtdo = statement.executeUpdate();
            if (rtdo != 0) {

               mensaje="El Campo Se a modificado: " + rtdo + " campos";

            } else {
                mensaje = "Ocurrió Un Error";
            }
        } catch (SQLException sqlexception) {
             mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        }

        return mensaje;

    }

    public String eliminar(int Jugador, int Torneo) {
        try {
            statement = conexion.prepareStatement("Delete from tarjetas "
                    + "where idJugador=? and idTorneo=?;");
            
            statement.setInt(1,Jugador);
            statement.setInt(2, Torneo);
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

    public LinkedList<TarjetasDTO> listarTodo(int jugador, int torneo) {
        //creamos el array que va a contener los datos de la consulta    
        LinkedList<TarjetasDTO> listar = new LinkedList();

        try {
            String query = "SELECT  * "
                    + " FROM tarjetas ";
            statement = conexion.prepareStatement(query);
            rs = statement.executeQuery();
            //mientras que halla registros cree un nuevo dto y pasele la info
            while (rs.next()) {
                //crea un nuevo dto
                TarjetasDTO tarjetas = new TarjetasDTO();
                //le pasamos los datos que se encuentren
                tarjetas.setIdtorneo(rs.getInt("idTorneo"));
                tarjetas.setTarjetaAzul(rs.getInt("tarjetaAzul"));
                tarjetas.setTarjetaRoja(rs.getInt("tarjetaRoja"));
                tarjetas.setIdJugador(rs.getInt("idJugador"));
                //agregamos el objeto dto al arreglo
                listar.add(tarjetas);

            }
        } catch (SQLException sqlexception) {
             mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        } finally {

        }
        //devolvemos el arreglo
        return listar;
    }

    public LinkedList<TarjetasDTO> listarUno(int torneo, int jugador) {
        LinkedList<TarjetasDTO> listar = new LinkedList<TarjetasDTO>();
        try {
            //preparamos la consulta 
            statement = conexion.prepareStatement("SELECT "
                    + "tarjetaAzul, tarjetaRoja, idJugador, idTorneo"
                    + " FROM tarjetas "
                    + "WHERE idtorneo = ? and idJugador = ? ;");
            statement.setInt(1, torneo);
            statement.setInt(2, jugador);
            rs = statement.executeQuery();
            //mientras halla registros
            while (rs.next()) {
                TarjetasDTO tarjetas = new TarjetasDTO();
               tarjetas.setIdtorneo(rs.getInt("idTorneo"));
               tarjetas.setTarjetaAzul(rs.getInt("tarjetaAzul"));
                tarjetas.setTarjetaRoja(rs.getInt("tarjetaRoja"));
                tarjetas.setIdJugador(rs.getInt("idJugador"));
                listar.add(tarjetas);
            }

        } catch (SQLException ex) {
            mensaje = "Error inesperado: " + ex.getMessage() + " codigo de error " + ex.getErrorCode();
        }
        //devolvemos el usuario que se encontro
        return listar;
    }


}

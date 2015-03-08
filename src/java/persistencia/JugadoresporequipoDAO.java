/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import modelo.JugadoresporequipoDTO;

import utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JugadoresporequipoDAO {
  



     Connection conexion = null;
    //instanciamos preparestatment
    PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    String mensaje = "";
    //variable que cuenta las filas afectadas
    int rtdo = 0;

    ResultSet rs;

    public JugadoresporequipoDAO() {
        conexion = Conexion.getInstance();
    }

    public String insertar(int equipo, int jugador) {

        try {
            //sentencia sql
            String sql = "INSERT INTO Jugadoresporequipo(codigoequipo,codigojugador)VALUES(?,?)";
            //pasamos la sentencia la conexion mediante el prepare staement
            statement = conexion.prepareStatement(sql);
            //obtenemos los datos del dto de la tabla
            statement.setInt(1, equipo);
            statement.setInt(2, jugador);
            

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
            System.out.println("Ocurrio Un Error" + sqlexception.getMessage());
        }
        //devolvemos el mensaje al usuario
        return mensaje;
    }

    public String actualizar(int equipo, int jugador) {
        try {
            //preparamos la sentencia sql
            String sql = "UPDATE Jugadoresporequipo SET "
                    + " codigoEquipo=?,codigoJugador=? "
                    + "WHERE codigoEquipo=? and codigoJugador = ?;";
            //pasamos el query a la conexion
           //sacamos los datos del dto de la tabla
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, equipo);
            statement.setInt(2, jugador);
            statement.setInt(3, equipo);
            statement.setInt(4, jugador);
            //el resulset trae el numero de rows afectadas
            rtdo = statement.executeUpdate();
            if (rtdo != 0) {

                mensaje="El Campo Se a modificado:" + rtdo + "saludes";

            } else {
                mensaje = "Error";
            }
        } catch (SQLException sqlexception) {
            System.out.println("Ocurrio Un Error" + sqlexception.getMessage());

        }

        return mensaje;

    }

    public String eliminar(int jugador, int equipo) {
        try {
            statement = conexion.prepareStatement("Delete from Jugadoresporequipo "
                    + "where CodigoEquipo=? and codigoJugador=?;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, equipo);
            statement.setInt(2, jugador);
            rtdo = statement.executeUpdate();

            if (rtdo != 0) {
                System.out.println("Se eliminaron Corretamente " + rtdo + " registros");
            } else {
                mensaje = "Ocurrio Un Error";
            }
        } catch (SQLException sqlexception) {
            System.out.println("Ocurrio un error" + sqlexception.getMessage());

        }

        return mensaje;
    }

    public LinkedList<JugadoresporequipoDTO> listarTodo() {
        //creamos el array que va a contener los datos de la consulta    
        LinkedList<JugadoresporequipoDTO> jugadoreseq = new LinkedList();

        try {

            statement = conexion.prepareStatement("select "
                    + "concat(u.primerNombre, ' ',u.primerApellido)as jugador,e.nombre as equipo"
                    + " from usuarios as u inner join jugadoresporequipo as j  on \n" +
                       "u.idUsuario = j.codigoJugador inner join equipo as e "
                    + "on j.codigoEquipo=e.codigo ;");
            rs = statement.executeQuery();
            //mientras que halla registros cree un nuevo dto y pasele la info
            while (rs.next()) {
                //crea un nuevo dto
                JugadoresporequipoDTO jug = new JugadoresporequipoDTO();
                //le pasamos los datos que se encuentren
                jug.setNombreEquipo(rs.getString("equipo"));
                jug.setNombreJugador(rs.getString("jugador"));      
                //agregamos el objeto dto al arreglo
                jugadoreseq.add(jug);

            }
        } catch (SQLException sqle) {
            mensaje = "Ha ocurrido un error "+ sqle.getMessage();

        } finally {

        }
        //devolvemos el arreglo
        return jugadoreseq;

    }

    public LinkedList<JugadoresporequipoDTO> listarUno(int equipo, int jugador) {
         LinkedList<JugadoresporequipoDTO> jugadoreseq = new LinkedList();
        try {
            statement = conexion.prepareStatement("SELECT codigoEquipo,codigoJugador "
                    + " FROM Jugadoresporequipo "
                    + "where codigoEquipo=? and codigoJugador=?");
                 
            statement.setInt(1, equipo);
            statement.setInt(2, jugador);
            rs = statement.executeQuery();
            //mientras halla registros
            while (rs.next()) {
                JugadoresporequipoDTO jugadoreq = new JugadoresporequipoDTO();
                jugadoreq.setNombreEquipo(rs.getString("CodigoEquipo"));
                jugadoreq.setNombreJugador(rs.getString("codigoJugador"));
                jugadoreseq.add(jugadoreq);
            }

        } catch (SQLException ex) {
            mensaje = "Error inesperado: " + ex.getMessage() + " codigo de error " + ex.getErrorCode();
        }
        //devolvemos el usuario que se encontro
        return jugadoreseq;
    }
 public LinkedList<JugadoresporequipoDTO> listarJugadoresEq(int equipo) {
        //creamos el array que va a contener los datos de la consulta    
        LinkedList<JugadoresporequipoDTO> jugadoreseq = new LinkedList();

        try {

            statement = conexion.prepareStatement("select "
                + "concat(u.primerNombre, ' ',u.primerApellido)as jugador,"
                    + "u.idUsuario, e.nombre as equipo, e.codigo"
                + " from usuarios as u inner join jugadoresporequipo as j  on " 
                + "u.idUsuario = j.codigoJugador inner join equipo as e "
                + "on j.codigoEquipo=e.codigo where e.codigo=?;");
            statement.setInt(1, equipo);
            rs = statement.executeQuery();
            //mientras que halla registros cree un nuevo dto y pasele la info
            while (rs.next()) {
                //crea un nuevo dto
                JugadoresporequipoDTO jug = new JugadoresporequipoDTO();
                //le pasamos los datos que se encuentren
                jug.setNombreJugador(rs.getString("jugador"));
                jug.setCodigoJugador(rs.getInt("idUsuario"));
                jug.setNombreEquipo(rs.getString("equipo"));
                jug.setCodigoEquipo(rs.getInt("codigo"));
                //agregamos el objeto dto al arreglo
                jugadoreseq.add(jug);

            }
        } catch (SQLException sqle) {
            mensaje = "Ha ocurrido un error "+ sqle.getMessage();

        } finally {

        }
        //devolvemos el arreglo
        return jugadoreseq;

    }
}
  


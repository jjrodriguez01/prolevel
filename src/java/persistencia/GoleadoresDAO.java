/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.GoleadoresDTO;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import utilidades.MiExcepcion;

/**
 *
 * @author jeisson
 */
public class GoleadoresDAO {
    
    //instanciamos preparestatment
    PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    String mensaje = "";
    //variable que cuenta las filas afectadas
    int rtdo = 0;

    ResultSet rs =null;
    CallableStatement call= null;

    public synchronized String insertar(GoleadoresDTO gol, Connection conexion) throws MiExcepcion {

        try {
            
            call = conexion.prepareCall("{call sp_aumentarmarcador (?,?,?,?,?)};");
            call.setInt(1, gol.getNumeroGoles());
            call.setInt(2, gol.getIdTorneo());
            call.setInt(3, gol.getIdJugador());
            call.setInt(4, gol.getIdEquipo());
            
            call.registerOutParameter(5, Types.INTEGER);
            call.execute();
            int salida = call.getInt(5);
            if (salida == 1) {
                mensaje = "Marcador Asignado.";
            }else {
                mensaje = "No se pudo asignar marcador.";
            }
        } 
        catch (SQLException sqlexception) {
         throw new MiExcepcion("Error insertando goles", sqlexception);
        }
        //devolvemos el mensaje al usuario
        return mensaje;
    }

    public String actualizar(GoleadoresDTO gol, Connection conexion) throws MiExcepcion {
        try {
            //preparamos la sentencia sql
            String sql = "UPDATE tablaGoleadores SET numeroGoles=?, idTorneo=?, idJugador=? ,idEquipo=?  WHERE idJugador=? and idTorneo=? and idEquipo=?;";
            //pasamos el query a la conexion
           //sacamos los datos del dto de la tabla
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, gol.getNumeroGoles());
            statement.setInt(2, gol.getIdTorneo());
            statement.setInt(3, gol.getIdEquipo());
            statement.setInt(4, gol.getIdJugador());
           
            
            //el resulset trae el numero de rows afectadas
            rtdo = statement.executeUpdate();
            if (rtdo != 0) {

               mensaje="El Campo Se a modificado:" + rtdo + "saludes";

            } else {
                mensaje = "Error";
            }
        } catch (SQLException sqlexception) {
         throw new MiExcepcion("Error actualizando goles", sqlexception);

        }

        return mensaje;

    }

    public String eliminar(GoleadoresDTO gol, Connection conexion) throws MiExcepcion {
        try {
            statement = conexion.prepareStatement("Delete from tablaGoleadores where idTorneo=? and idJugador and idEquipo;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, gol.getNumeroGoles());
            statement.setInt(2, gol.getIdJugador());
            statement.setInt(3, gol.getIdEquipo());
            statement.setInt(4, gol.getIdTorneo());
            rtdo = statement.executeUpdate();

            if (rtdo != 0) {
               mensaje="El siguiente campo" + rtdo + "se elimino Corretamente";
            } else {
                mensaje = "Ocurrio Un Error";
            }
        } catch (SQLException sqlexception) {
            throw new MiExcepcion("Error eliminando goles", sqlexception);

        }

        return mensaje;
    }

    public List<GoleadoresDTO> listarTodo( Connection conexion) throws MiExcepcion {
        //creamos el array que va a contener los datos de la consulta    
        ArrayList<GoleadoresDTO> listarGoleadores = new ArrayList();

        try {
            String query = "SELECT  Cancha as numeroCancha, descripcion "
                    + " FROM Cancha ";
            statement = conexion.prepareStatement(query);
            rs = statement.executeQuery();
            //mientras que halla registros cree un nuevo dto y pasele la info
            while (rs.next()) {
                //crea un nuevo dto
                GoleadoresDTO gol = new GoleadoresDTO();
                //le pasamos los datos que se encuentren
                gol.setNumeroGoles(rs.getInt("numeroGoles"));
                gol.setIdEquipo(rs.getInt("idEquipo"));
                gol.setIdTorneo(rs.getInt("idTorneo"));
                gol.setIdJugador(rs.getInt("idJugador"));
                
               
                //agregamos el objeto dto al arreglo
                listarGoleadores.add(gol);

            }
        } catch (SQLException sqlexception) {
            throw new MiExcepcion("Error listando goles", sqlexception);

        } 
//        finally {
//            try{
//            if (statement != null) {
//                        statement.close();    
//                    }
//            }catch (SQLException sqlexception) {
//            throw new MiExcepcion("Error listando goles", sqlexception);
//
//        }
//        }
        //devolvemos el arreglo
        return listarGoleadores;

    }

    public GoleadoresDTO listarUno(GoleadoresDTO gol, Connection conexion) throws MiExcepcion {
        try {
            //preparamos la consulta 
            statement = conexion.prepareStatement("SELECT numeroGoles,idJugador, idTorneo, idEquipo"
                    + "WHERE idTorneo = ? and idJugador=? and idQuipo=? ;");
            statement.setInt(1,gol.getNumeroGoles());
            statement.setInt(2,gol.getIdJugador());
            statement.setInt(3,gol.getIdEquipo());
            statement.setInt(4,gol.getIdTorneo());
            rs = statement.executeQuery();
            //mientras halla registros
            while (rs.next()) {
               gol.setNumeroGoles(rs.getInt("numeroGoles"));
               gol.setIdEquipo(rs.getInt("idEquipo"));
               gol.setIdJugador(rs.getInt("idJugador"));
               gol.setIdTorneo(rs.getInt("idTorneo"));
               
            }

        } catch (SQLException ex) {
            throw new MiExcepcion("Error listando goles", ex);
        }
//        finally {
//            try{
//            if (statement != null) {
//                        statement.close();    
//                    }
//            }catch (SQLException sqlexception) {
//            throw new MiExcepcion("Error listando goles", sqlexception);
//
//        }
//        }
        //devolvemos el usuario que se encontro
        return gol;
    }
}

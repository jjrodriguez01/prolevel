/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import modelo.TablaPosicionesDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelo.PartidoDTO;
import utilidades.MiExcepcion;

public class TablaPosicionesDAO {

    //instanciamos preparestatment
    private PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    private String mensaje = "";
    //variable que cuenta las filas afectadas
    private int rtdo = 0;
    //calleableStatement
    CallableStatement call;

    ResultSet rs;


    public synchronized String insertar(PartidoDTO p,Connection conexion) throws MiExcepcion {
        try {
            call = conexion.prepareCall("{call sp_actalizarpos(?,?,?,?,?,?) }");
            call.setInt(1, p.getMarcador1());
            call.setInt(2, p.getMarcador2());
            call.setInt(3, p.getEquipo1());
            call.setInt(4, p.getEquipo2());
            call.setInt(5, p.getIdTorneo());

            call.registerOutParameter(6, Types.INTEGER);
            call.execute();
            
            int salida = call.getInt(6);
            if (salida == 1) {
                mensaje = "Se han actualizado las posiciones.";
            } else {
                mensaje = "No se pudo actualizar las posiciones.";
            }
        } catch (SQLException sqle) {
           throw new MiExcepcion("Error al insertar posiciones ", sqle);
        }
        return mensaje;
    }
    
    public synchronized String posEquipo1(PartidoDTO p,Connection conexion) throws MiExcepcion {

        try {

            statement = conexion.prepareStatement("INSERT INTO tablaposiciones "
                    + " (idTorneo, idEquipo, posicion, puntos, partidosJugados, partidosGanados, partidosEmpatados, partidosPerdidos, golesAnotados, golesRecibidos) "
                    + " VALUES(?,?,0,0,0,0,0,0,0,0);");
            //obtenemos los datos del dto de la tabla
            statement.setInt(1, p.getIdTorneo());
            statement.setInt(2, p.getEquipo1());
            //ejecuta el insert
            rtdo = statement.executeUpdate();
            //si se afectaron campos 
            if (rtdo != 0) {
                mensaje = "Se inserto correctamente";
                //si no se afecto la tabla
            } else {
                mensaje = "Error";
            }
        } 
        catch (SQLException sqlexception) {
            throw new MiExcepcion("Error insertando partidos", sqlexception);
        }
//        finally{
//            try{
//                statement.close();
//            }catch(SQLException sqlexception){
//                throw new MiExcepcion("Error insertando partidos", sqlexception);
//            }
//        }
        //devolvemos el mensaje al usuario
        return mensaje;
    }
    
    public synchronized String posEquipo2(PartidoDTO p,Connection conexion) throws MiExcepcion {

        try {

            statement = conexion.prepareStatement("INSERT INTO tablaposiciones "
                    + " (idTorneo, idEquipo, posicion, puntos, partidosJugados, partidosGanados, partidosEmpatados, partidosPerdidos, golesAnotados, golesRecibidos) "
                    + " VALUES(?,?,0,0,0,0,0,0,0,0);");
            //obtenemos los datos del dto de la tabla
            statement.setInt(1, p.getIdTorneo());
            statement.setInt(2, p.getEquipo2());
            //ejecuta el insert
            rtdo = statement.executeUpdate();
            //si se afectaron campos 
            if (rtdo != 0) {
                mensaje = "Se inserto correctamente";
                //si no se afecto la tabla
            } else {
                mensaje = "Error";
            }
        } 
        catch (SQLException sqlexception) {
            throw new MiExcepcion("Error insertando partidos", sqlexception);
        }
//        finally{
//            try{
//                statement.close();
//            }catch(SQLException sqlexception){
//                throw new MiExcepcion("Error insertando partidos", sqlexception);
//            }
//        }
        //devolvemos el mensaje al usuario
        return mensaje;
    }

    public String eliminar(TablaPosicionesDTO tab,Connection conexion) {

        try {
            statement = conexion.prepareStatement("Delete from tablaposiciones where idTorneo = ? and idEquipo;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, tab.getIdequipo());
            statement.setInt(2,tab.getIdtorneo());
            rtdo = statement.executeUpdate();

            if (rtdo != 0) {
                mensaje = "Se elimino corretamente";
            } else {
                mensaje = "Ocurrió Un Error";
            }
        } catch (SQLException sqlexception) {
            mensaje = "Ocurrió un error" + sqlexception.getMessage();

        }
        return mensaje;
    }
  public String actualizar(TablaPosicionesDTO tab,Connection conexion){
        
        try {
            statement = conexion.prepareStatement("UPDATE tablaPosiciones set golesAnotados= ?, "
                    + "golesRecibidos= ?,= ?, partidosEmpatados= ?, "
                    + " partidosGanados = ? ,partidosJugados = ?, partidosPerdidos = ?, posicion=?, puntos = ? "
                    + "WHERE idTorneo = ? and idEquipo=?");
            statement.setInt(1, tab.getGolesAnotados());
            statement.setInt(2, tab.getGolesRecibidos());
            statement.setInt(3, tab.getPartidosEmpatados()); 
            statement.setInt(4, tab.getPartidosGanados());
            statement.setInt(5, tab.getPartidosJugados());
            statement.setInt(6, tab.getPartidosPerdidos());
            statement.setInt(7, tab.getPosicion());
            statement.setInt(8, tab.getPuntos());
            statement.setInt(9, tab.getIdtorneo());
            statement.setInt(10, tab.getIdequipo());
            
            rtdo = statement.executeUpdate();
            
            if (rtdo != 0) {

                mensaje = "Se han modificado :" + rtdo + " registro";

            } else {
                mensaje = "Error";
            }
        }catch(SQLException sqle){
            mensaje = "Error: "+ sqle.getMessage();
        }
        
        return mensaje;
    }
//     public TablaPosicionesDTO listarUno(Connection conexion) {
//         TablaPosicionesDTO tab = new TablaPosicionesDTO();
//        try {
//            //preparamos la consulta       
//            statement = conexion.prepareStatement("SELECT * from tablaPosiciones "
//                    + "where idTorneo = ? and idEquipo=?;");
//            statement.setInt(1, tab.getIdtorneo());
//            statement.setInt(1, tab.getIdequipo());
//            rs = statement.executeQuery();
//            //mientras halla registros
//            while (rs.next()) {
//                tab.setIdtorneo(rs.getInt("idTorneo"));
//                tab.setIdequipo(rs.getInt("idEquipo"));
//                tab.setPosicion(rs.getInt("Posicion"));
//                tab.setPuntos(rs.getInt("Puntos"));
//                tab.setPartidosJugados(rs.getInt("PartidosJugados"));
//                tab.setPartidosGanados(rs.getInt("PartidosGanados"));
//                tab.setPartidosEmpatados(rs.getInt("PartidosEmpatados"));
//                tab.setPartidosPerdidos(rs.getInt("PartidosPerdidos"));
//                tab.setGolesAnotados(rs.getInt("GolesAnotados"));
//                tab.setGolesRecibidos(rs.getInt("Golesresividos"));             
//            }
//
//        } catch (SQLException ex) {
//            mensaje = "Error inesperado: " + ex.getMessage() + " codigo de error " + ex.getErrorCode();
//        }
//        //devolvemos el usuario que se encontro
//        return tab;
//    }
    
//    public List ListarTodo(Connection conexion){
//        
//        ArrayList<TablaPosicionesDTO> listarTablaPosiciones = new ArrayList();
//        try{
//            String sql= "SELECT * FROM tablaPosiciones where idTorneo=? and idEquipo=?";
//                                         
//            statement = conexion.prepareStatement(sql);
//            
//            rs = statement.executeQuery();
//            
//            while(rs.next()){
//                TablaPosicionesDTO cup = new TablaPosicionesDTO();
//                
//                cup.setIdtorneo(rs.getInt("idTorneo"));
//                cup.setIdequipo(rs.getInt("idEquipo"));
//                cup.setPosicion(rs.getInt("Posicion"));
//                cup.setPuntos(rs.getInt("Puntos"));
//                cup.setPartidosJugados(rs.getInt("PartidosJugados"));
//                cup.setPartidosGanados(rs.getInt("PartidosGanados"));
//                cup.setPartidosEmpatados(rs.getInt("PartidosEmpatados"));
//                cup.setGolesAnotados(rs.getInt("GolesAnotados"));
//                cup.setGolesRecibidos(rs.getInt("GolesResividos"));
//                cup.setPartidosPerdidos(rs.getInt("PartiosPerdidos"));
//                
//                listarTablaPosiciones.add(cup);
//            }
//            
//        }catch(SQLException sqle){
//            mensaje = "Error: "+ sqle.getMessage();
//        }
//        return listarTablaPosiciones;
//    }
    
}

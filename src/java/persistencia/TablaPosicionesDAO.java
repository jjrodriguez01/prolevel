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


    public String insertar(TablaPosicionesDTO tab,Connection conexion) throws MiExcepcion {
        try {
            call = conexion.prepareCall("{call sp_actalizarpos(?,?,?,?,?,?,?,?,?,?,?) }");
            call.setInt(1, tab.getIdtorneo());
            call.setInt(2, tab.getIdequipo());
            call.setInt(3, tab.getGolesAnotados());
            call.setInt(4, tab.getGolesRecibidos());
            call.setInt(5, tab.getPartidosEmpatados());
            call.setInt(6, tab.getPartidosGanados());
            call.setInt(7, tab.getPartidosJugados());
            call.setInt(8, tab.getPartidosPerdidos());
            call.setInt(9, tab.getPosicion());
            call.setInt(10, tab.getPuntos());

            call.registerOutParameter(11, Types.INTEGER);
            call.execute();
            
            int salida = call.getInt(11);
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

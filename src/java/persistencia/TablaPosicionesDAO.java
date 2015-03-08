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
import utilidades.Conexion;

public class TablaPosicionesDAO {

    private Connection conexion = null;
    //instanciamos preparestatment
    private PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    private String mensaje = "";
    //variable que cuenta las filas afectadas
    private int rtdo = 0;
    //calleableStatement
    CallableStatement call;

    ResultSet rs;

    public TablaPosicionesDAO() {
        conexion = Conexion.getInstance();
    }

    public String insertar(TablaPosicionesDTO tab) {
        try {
            call = conexion.prepareCall("{call sp_actalizarpos(?,?,?,?,?,?,?,?,?,?,?) }");
            call.setInt(1, tab.getIdtorneo());
            call.setInt(2, tab.getIdequipo());
            call.setInt(3, tab.getGolesAnotados());
            call.setInt(4, tab.getGolesResividos());
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
            mensaje = "Error :" + sqle.getMessage();
        }
        return mensaje;
    }

    public String eliminar(TablaPosicionesDTO tab) {

        try {
            statement = conexion.prepareStatement("Delete from torneo where idTorneo = ? and idEquipo;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, tab.getIdequipo());
            statement.setInt(2,tab.getIdtorneo());
            rtdo = statement.executeUpdate();

            if (rtdo != 0) {
                System.out.println("Se elimino " + rtdo + " registro corretamente");
            } else {
                mensaje = "Ocurrió Un Error";
            }
        } catch (SQLException sqlexception) {
            System.out.println("Ocurrió un error" + sqlexception.getMessage());

        }
        return mensaje;
    }
  public String actualizar(TablaPosicionesDTO tab){
        
        try {
            statement = conexion.prepareStatement("UPDATE tablaPosiciones set golesAnotados= ?, golesResividos= ?,= ?, partidosEmpatados= ?,"
                    + " partidosGanados = ? ,partidosJugados = ?, partidosPerdidos = ?, posicion=?, puntos = ? WHERE idTorneo = ? and idEquipo=?");
            statement.setInt(1, tab.getGolesAnotados());
            statement.setInt(2, tab.getGolesResividos());
            statement.setInt(3, tab.getIdequipo());
            statement.setInt(4, tab.getPartidosEmpatados());
            statement.setInt(5, tab.getPartidosGanados());
            statement.setInt(7, tab.getPartidosJugados());
            statement.setInt(8, tab.getPartidosPerdidos());
            statement.setInt(9, tab.getPosicion());
            statement.setInt(10, tab.getPuntos());
            statement.setInt(10, tab.getIdtorneo());
            
            rtdo = statement.executeUpdate();
            
            if (rtdo != 0) {

                System.out.println("Se han modificado :" + rtdo + " registro");

            } else {
                mensaje = "Error";
            }
        }catch(SQLException sqle){
            mensaje = "Error: "+ sqle.getMessage();
        }
        
        return mensaje;
    }
     public String listarUno(TablaPosicionesDTO tab) {
        try {
            //preparamos la consulta       
            statement = conexion.prepareStatement("SELECT * from tablaPosiciones where idTorneo = ? and idEquipo=?;");
            statement.setInt(1, tab.getIdtorneo());
            statement.setInt(1, tab.getIdequipo());
            rs = statement.executeQuery();
            //mientras halla registros
            while (rs.next()) {
                tab.setIdtorneo(rs.getInt("idTorneo"));
                tab.setIdequipo(rs.getInt("idEquipo"));
                tab.setPosicion(rs.getInt("Posicion"));
                tab.setPuntos(rs.getInt("Puntos"));
                tab.setPartidosJugados(rs.getInt("PartidosJugados"));
                tab.setPartidosGanados(rs.getInt("PartidosGanados"));
                tab.setPartidosEmpatados(rs.getInt("PartidosEmpatados"));
                tab.setPartidosPerdidos(rs.getInt("PartidosPerdidos"));
                tab.setGolesAnotados(rs.getInt("GolesAnotados"));
                tab.setGolesResividos(rs.getInt("Golesresividos"));
             
                
            }

        } catch (SQLException ex) {
            mensaje = "Error inesperado: " + ex.getMessage() + " codigo de error " + ex.getErrorCode();
        }
        //devolvemos el usuario que se encontro
        return "" +tab;
    }
    
    public List ListarTodo(){
        
        ArrayList<TablaPosicionesDTO> listarTablaPosiciones = new ArrayList();
        try{
            String sql= "SELECT * FROM tablaPosiciones where idTorneo=? and idEquipo=?";
                                         
            statement = conexion.prepareStatement(sql);
            
            rs = statement.executeQuery();
            
            while(rs.next()){
                TablaPosicionesDTO cup = new TablaPosicionesDTO();
                
                cup.setIdtorneo(rs.getInt("idTorneo"));
                cup.setIdequipo(rs.getInt("idEquipo"));
                cup.setPosicion(rs.getInt("Posicion"));
                cup.setPuntos(rs.getInt("Puntos"));
                cup.setPartidosJugados(rs.getInt("PartidosJugados"));
                cup.setPartidosGanados(rs.getInt("PartidosGanados"));
                cup.setPartidosEmpatados(rs.getInt("PartidosEmpatados"));
                cup.setGolesAnotados(rs.getInt("GolesAnotados"));
                cup.setGolesResividos(rs.getInt("GolesResividos"));
                cup.setPartidosPerdidos(rs.getInt("PartiosPerdidos"));
                
                listarTablaPosiciones.add(cup);
            }
            
        }catch(SQLException sqle){
            mensaje = "Error: "+ sqle.getMessage();
        }
        return listarTablaPosiciones;
    }
    public List ListaPaginacion(int pg, int numreg, int idTorneo){
        
        ArrayList<TablaPosicionesDTO> listarTablaPosiciones = new ArrayList();
        try{                               
            statement = conexion.prepareStatement("SELECT equipo.nombre, "
                    + "tablaposiciones.partidosJugados as PJ, "
                    + "tablaposiciones.partidosGanados as PG, "
                    + "tablaposiciones.partidosEmpatados as PE, "
                    + "tablaposiciones.partidosPerdidos as PP, "
                    + "tablaposiciones.golesAnotados as Goles, "
                    + "tablaposiciones.golesRecibidos as GC, "
                    + "tablaposiciones.golesAnotados-tablaposiciones.golesRecibidos AS GD, "
                    + "tablaposiciones.puntos as pts"
                    + "FROM equipo "
                    + "inner join equiposdeltorneo "
                    + "on equipo.codigo = equiposdeltorneo.equipoCodigo "
                    + "inner join tablaPosiciones "
                    + "on equiposdeltorneo.equipoCodigo = tablaposiciones.idEquipo "
                    + "WHERE equiposdeltorneo.torneoidtorneo=? "
                    + "and"
                    + "tablaposiciones.idTorneo = ?"
                    + "ORDER BY puntos DESC"
                    + "LIMIT "+(pg-1)*numreg+","+numreg+";");
            statement.setInt(1,idTorneo);
            statement.setInt(2,idTorneo);
            rs=statement.executeQuery();
            
            while(rs.next()){
                TablaPosicionesDTO cup = new TablaPosicionesDTO();
                
                cup.setIdtorneo(rs.getInt("idTorneo"));
                cup.setIdequipo(rs.getInt("idEquipo"));
                cup.setPosicion(rs.getInt("Posicion"));
                cup.setPuntos(rs.getInt("Puntos"));
                cup.setPartidosJugados(rs.getInt("PartidosJugados"));
                cup.setPartidosGanados(rs.getInt("PartidosGanados"));
                cup.setPartidosEmpatados(rs.getInt("PartidosEmpatados"));
                cup.setGolesAnotados(rs.getInt("GolesAnotados"));
                cup.setGolesResividos(rs.getInt("GolesResividos"));
                cup.setPartidosPerdidos(rs.getInt("PartiosPerdidos"));
                
                listarTablaPosiciones.add(cup);
                
            }
            
        }catch(SQLException sqle){
            mensaje = "Error: "+ sqle.getMessage();
        }
        return listarTablaPosiciones;
    }
    
    public int contarRegistros(int idTorneo){
        int registros = 0;
        try{
            statement=conexion.prepareStatement("SELECT * FROM tablaposiciones "
                    + "WHERE idtorneo = ?;");
            statement.setInt(1,idTorneo);
            rs = statement.executeQuery();
            
            if (rs!=null) {
                while(rs.next()){
                registros++;
            }
            return registros;
            }
              
            
        }catch(SQLException sqle){
            mensaje = sqle.getMessage();
        }
        return registros;
    }
}

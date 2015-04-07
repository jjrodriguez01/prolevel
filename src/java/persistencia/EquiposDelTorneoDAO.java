/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.EquiposdeltorneoDTO;
import utilidades.MiExcepcion;

/**
 *
 * @author jeisson
 */

public class EquiposDelTorneoDAO {

    Connection conexion = null;
    //instanciamos preparestatment
    PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    String mensaje = "";
    //variable que cuenta las filas afectadas
    int rtdo = 0;

    ResultSet rs;

    public EquiposDelTorneoDAO() {
        conexion = Conexion.getConnection();
    }
    
    public synchronized String insertar(int codigoequipo, int idTorneo){
        try {
            statement = conexion.prepareStatement("INSERT INTO equiposdeltorneo (equipocodigo, torneoidtorneo)"
                    + " VALUES (?,?);");
            statement.setInt(1, codigoequipo);
            statement.setInt(2, idTorneo);
            rtdo = statement.executeUpdate();
            if (rtdo>0) {
                mensaje = "Se inserto el equipo al torneo";
            }else{
                mensaje = "No se inserto el equipo";
            }
            
        } catch (SQLException ex) {
            mensaje = "Ha ocurrido un error "+ex.getMessage();
        }
        return mensaje;
    }
    
    public List<EquiposdeltorneoDTO> listarTodo(int idTorneo) throws MiExcepcion{
        ArrayList<EquiposdeltorneoDTO> equipos = new ArrayList();
        try {
            statement = conexion.prepareStatement("SELECT equipocodigo, torneoidtorneo"
                    + "FROM equiposdeltorneo WHERE torneoidtorneo = ?;");
            statement.setInt(1, idTorneo);
            rs = statement.executeQuery();
            while(rs.next()){
                EquiposdeltorneoDTO eq = new EquiposdeltorneoDTO();
                eq.setEquipoCodigo(rs.getInt("equipocodigo"));
                eq.setTorneoIdTorneo(rs.getInt("torneoidtorneo"));
                equipos.add(eq);
            }
        } catch (SQLException ex) {
            throw new MiExcepcion("Error", ex);
        }
        return equipos;
    }
}

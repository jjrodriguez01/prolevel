
package persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelo.TorneoDTO;
import utilidades.Conexion;

/**
 *
 * @author Sena
 */
public class LigaDAO {
    
    Connection conexion = null;
    //instanciamos preparestatment
    PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    String mensaje = "";
    //variable que cuenta las filas afectadas
    int rtdo = 0;
    CallableStatement call=null;
    ResultSet rs=null;

    public LigaDAO() {
        conexion = Conexion.getInstance();
    }

    public synchronized String insertar (TorneoDTO liga) {

        try {
            //sentencia sql
            call = conexion.prepareCall("call sp_torneoliga (?,?,?,?,?,?,?,?);");
            //pasamos la sentencia la conexion mediante el prepare staement
            call.setString(1, liga.getNombre());
            call.setString(2, liga.getFechaInicio());
            call.setString(3, liga.getFechaFin());
            call.setString(4, liga.getGenero());
            call.setInt(5, liga.getCapacidadEquipos());
            call.setInt(6, liga.getTipo());
            call.setBoolean(7, liga.isIdaVuelta());
            call.registerOutParameter(8, Types.INTEGER);
            call.execute();
            int salida = call.getInt(8);
            
            if (salida == 1) {
                mensaje = "Nueva liga creada.";
            }else {
                mensaje = "No se pudo crear la liga.";
            }
        } 
        catch (SQLException sqlexception) {
            mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();
        }
        //devolvemos el mensaje al usuario
        return mensaje;
    }

    public String actualizar(TorneoDTO liga) {
        try {
            statement = conexion.prepareStatement("UPDATE torneo SET nombre=?, "
                    + "fechaInicio =?, fechaFin = ?, genero = ?, "
                    + " WHERE idTorneo=?;");
            statement.setString(1, liga.getNombre());
            statement.setString(2, liga.getFechaInicio());
            statement.setString(3, liga.getFechaFin());
            statement.setString(4, liga.getGenero());
            statement.setInt(5, liga.getIdTorneo());
            rtdo = statement.executeUpdate();
            if (rtdo != 0) {
              mensaje="El Campo Se a modificado la liga";

            } else {
                mensaje = "Ocurrio un error";
            }
        } catch (SQLException sqlexception) {
            mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        }

        return mensaje;

    }

    public String eliminar(int id) {
        try {
            statement = conexion.prepareStatement("Delete from torneo where idTorneo=?;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, id);
            rtdo = statement.executeUpdate();

            if (rtdo != 0) {
                mensaje="Se eliminaron " + rtdo + " Corretamente";
            } else {
                mensaje = "Ocurrio Un Error";
            }
        } catch (SQLException sqlexception) {
            mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        }

        return mensaje;
    }

    public List<TorneoDTO> listarTodo() {
        //creamos el array que va a contener los datos de la consulta    
        ArrayList<TorneoDTO> listarLiga = new ArrayList();

        try {
            statement = conexion.prepareStatement("SELECT * FROM torneo"
                    + "inner join liga"
                    + "on torneo.idTorneo = liga.idLiga");
            rs = statement.executeQuery();
            while (rs.next()) {
               TorneoDTO liga = new TorneoDTO();
                liga.setIdTorneo(rs.getInt("idLiga"));
                liga.setNombre(rs.getString("nombre"));
                liga.setFechaInicio(rs.getString("fechaInicio"));
                liga.setFechaFin(rs.getString("fechaFin"));
                liga.setGenero(rs.getString("genero"));
                liga.setTipo(rs.getInt("tipo"));
                liga.setIdaVuelta(rs.getBoolean("idaVuelta"));
                
                listarLiga.add(liga);
            }
        } catch (SQLException sqlexception) {
           mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();
        } finally {

        }
        //devolvemos el arreglo
        return listarLiga;

    }

    public TorneoDTO listarUno(int id) {
        TorneoDTO liga = new TorneoDTO();
        try {
            //preparamos la consulta 
            statement = conexion.prepareStatement("SELECT * from torneo"
                    + " inner join liga"
                    + "on torneo.idTorneo = liga.idLiga"
                    + "WHERE idTorneo = ? and idLiga = ?");
            statement.setInt(1,id);
            statement.setInt(2,id);
            rs = statement.executeQuery();
            //mientras halla registros
            while (rs.next()) {
                
                liga.setIdTorneo(rs.getInt("idLiga"));
                liga.setNombre(rs.getString("nombre"));
                liga.setFechaInicio(rs.getString("fechaInicio"));
                liga.setFechaFin(rs.getString("fechaFin"));
                liga.setGenero(rs.getString("genero"));
                liga.setTipo(rs.getInt("tipo"));
                liga.setIdaVuelta(rs.getBoolean("idaVuelta"));
            }

        } catch (SQLException ex) {
            mensaje = "Error inesperado: " + ex.getMessage() + " codigo de error " + ex.getErrorCode();
        }
        return liga;
    }
}

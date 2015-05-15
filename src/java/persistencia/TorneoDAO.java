package persistencia;

import controlador.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelo.TorneoDTO;
import utilidades.MiExcepcion;

/**
 *
 * @author jeisson
 */
public class TorneoDAO {
    Connection conexion;
    //instanciamos preparestatment
    private PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    private String mensaje = "";
    //variable que cuenta las filas afectadas
    private int rtdo = 0;
    //calleableStatement
    CallableStatement call;

    ResultSet rs;

    public TorneoDAO() throws MiExcepcion {
        conexion = Conexion.getConnection();
    }
    
    
    
    public String eliminar (int idTorneo, Connection conexion){
        
        try {
            statement = conexion.prepareStatement("Delete from torneo where idTorneo = ?;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, idTorneo);
            rtdo = statement.executeUpdate();
            if (rtdo != 0) {
                mensaje = "Se eliminó el torneo";
            } else {
                mensaje = "Ocurrió Un Error";
            }
        } catch (SQLException sqlexception) {
            System.out.println("Ocurrió un error" + sqlexception.getMessage());

        }
        return mensaje;
    }
    
    public String actualizar (TorneoDTO copa, Connection conexion){
        
        try {
            statement = conexion.prepareStatement("UPDATE torneo set nombre = ?, fechaInicio= ?,fechaFin= ?, genero= ?, capacidadEquipos = ? WHERE idTorneo = ?");
            statement.setString(1, copa.getNombre());
            statement.setString(2, copa.getFechaInicio());
            statement.setString(3, copa.getFechaFin());
            statement.setString(4, copa.getGenero());
            statement.setInt(5, copa.getCapacidadEquipos());
            statement.setInt(6, copa.getIdTorneo());
            
            rtdo = statement.executeUpdate();
            
            if (rtdo != 0) {

                mensaje = "Se actualizo el torneo";

            } else {
                mensaje = "Error";
            }
        }catch(SQLException sqle){
            mensaje = "Error: "+ sqle.getMessage();
        }
        
        return mensaje;
    }
     public TorneoDTO listarUno(int id, Connection conexion) throws MiExcepcion {
         TorneoDTO torneo = new TorneoDTO(); 
        try {     
            statement = conexion.prepareStatement("SELECT * from torneo where idTorneo = ?;");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                torneo.setIdTorneo(rs.getInt("idTorneo"));
                torneo.setNombre(rs.getString("nombre"));
                torneo.setFechaInicio(rs.getString("fechaInicio"));
                torneo.setFechaFin(rs.getString("fechaFin"));
                torneo.setGenero(rs.getString("genero"));
            }

        } catch (SQLException ex) {
            throw new MiExcepcion("Error al listar los torneos", ex);
        }
        return torneo;
    }
    
    public List ListarTodo(Connection conexion) throws MiExcepcion{
        
        ArrayList<TorneoDTO> listarCopas = new ArrayList();
        try{
            String sql= "SELECT * FROM torneo;";
                                         
            statement = conexion.prepareStatement(sql);
            
            rs=statement.executeQuery();
            
            while(rs.next()){
                TorneoDTO cup = new TorneoDTO();                
                cup.setIdTorneo(rs.getInt("idTorneo"));
                cup.setNombre(rs.getString("nombre"));
                cup.setFechaInicio(rs.getString("fechaInicio"));
                cup.setFechaFin(rs.getString("fechaFin"));
                cup.setGenero(rs.getString("genero"));
                cup.setCapacidadEquipos(rs.getInt("capacidadEquipos"));
                           
                listarCopas.add(cup);
                
            }
            
        }catch(SQLException sqle){
            throw new MiExcepcion("Error al listar los torneos", sqle);
        }
        return listarCopas;
    }
    
    public boolean hayTercerPuestoEli(int idTorneo, Connection conexion) throws MiExcepcion{
        boolean tercer = false;
        try{
            statement = conexion.prepareStatement("SELECT tercerPuesto FROM eliminatoria "
                    + "WHERE idEliminatoria = ?;");
            statement.setInt(1, idTorneo);
            rs = statement.executeQuery();
            
            while(rs.next()){
                tercer = rs.getBoolean("tercerPuesto");
            }
            
        }catch(SQLException sqle){
            throw new MiExcepcion("Error "+sqle.getMessage(),sqle);
        }
        return tercer;
    }
}

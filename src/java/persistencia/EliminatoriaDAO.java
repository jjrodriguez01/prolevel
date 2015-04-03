package persistencia;

import modelo.TorneoDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import utilidades.Conexion;

/**
 *
 * @author jeisson
 */
public class EliminatoriaDAO {
    
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
    
     public EliminatoriaDAO (){
        conexion = Conexion.getInstance();
    }
    
    public synchronized String insertar(TorneoDTO eliminatoria){
        try {
        call = conexion.prepareCall("{call sp_torneoeliminatoria(null,?,?,?,?,?,?,?) }");
        call.setInt(1, eliminatoria.getIdTorneo());
        call.setString(2, eliminatoria.getNombre());
        call.setString(3, eliminatoria.getFechaInicio());
        call.setString(4, eliminatoria.getFechaFin());
        call.setString(5, eliminatoria.getGenero());
        call.setInt(6, eliminatoria.getCapacidadEquipos());
        call.setBoolean(7, eliminatoria.isIdaVuelta());
       
       
        call.registerOutParameter(8, Types.INTEGER);
        int salida = call.getInt(8);
            if (salida == 1) {
                mensaje = "Nueva eliminatoria creada.";
            }else {
                mensaje = "No se pudo crear la eliminatoria.";
            }
        }
        catch(SQLException sqle){
            mensaje = "Error :" + sqle.getMessage();
        }
        return mensaje;
    }
    
    public String eliminar(int id){
        
        try {
            statement = conexion.prepareStatement("Delete from torneo where idTorneo = ?;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, id);
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
    
    public String actualizar(TorneoDTO eliminatoria){
        
        try {
            statement = conexion.prepareStatement("UPDATE torneo set nombre = ?, "
                    + "fechaInicio= ?,fechaFin= ?, genero= ?, capacidadEquipos = ? WHERE idTorneo = ?");
            statement.setString(1, eliminatoria.getNombre());
            statement.setString(2, eliminatoria.getFechaInicio());
            statement.setString(3, eliminatoria.getFechaFin());
            statement.setString(4, eliminatoria.getGenero());
            statement.setInt(5, eliminatoria.getCapacidadEquipos());
            statement.setInt(6, eliminatoria.getIdTorneo());            
            rtdo = statement.executeUpdate();            
            if (rtdo != 0) {
                System.out.println("Se ha modificado la eliminatoria");
            } else {
                mensaje = "Error, no se pudo modificar";
            }
        }catch(SQLException sqle){
            mensaje = "Error: "+ sqle.getMessage();
        }       
        return mensaje;
    }
     public TorneoDTO listarUno() {
         TorneoDTO eliminatoria = new TorneoDTO();
        try {
            //preparamos la consulta       
            statement = conexion.prepareStatement("SELECT * from torneo where idTorneo = ?;");
            statement.setInt(1, eliminatoria.getIdTorneo());
            rs = statement.executeQuery();
            //mientras halla registros
            while (rs.next()) {
                eliminatoria.setIdTorneo(rs.getInt("idTorneo"));
                eliminatoria.setNombre(rs.getString("nombre"));
                eliminatoria.setFechaInicio(rs.getString("fechaInicio"));
                eliminatoria.setFechaFin(rs.getString("fechaFin"));
                eliminatoria.setGenero(rs.getString("genero"));
                eliminatoria.setCapacidadEquipos(rs.getInt("capacidadEquipos"));
                eliminatoria.setIdaVuelta(rs.getBoolean("idaVuelta"));             
            }

        } catch (SQLException ex) {
            mensaje = "Error inesperado: " + ex.getMessage() + " codigo de error " + ex.getErrorCode();
        }
        //devolvemos el usuario que se encontro
        return eliminatoria;
    }
    
    public List ListarTodo(){
        
        ArrayList<TorneoDTO> listarEliminatorias = new ArrayList();
        try{
            String sql= "SELECT * FROM dbprolevel.torneo inner join eliminatoria on" +
"torneo.idTorneo = eliminatoria.idEliminatoria;";                                         
            statement = conexion.prepareStatement(sql);           
            rs = statement.executeQuery();           
            while(rs.next()){
                TorneoDTO cup = new TorneoDTO();
                
                cup.setIdTorneo(rs.getInt("idEliminatoria"));
                cup.setNombre(rs.getString("nombre"));
                cup.setFechaInicio(rs.getString("fechaInicio"));
                cup.setFechaFin(rs.getString("fechaFin"));
                cup.setGenero(rs.getString("genero"));
                cup.setCapacidadEquipos(rs.getInt("capacidadEquipos"));
                cup.setIdaVuelta(rs.getBoolean("idaVuelta"));
                
                listarEliminatorias.add(cup);
            }
            
        }catch(SQLException sqle){
            mensaje = "Error: "+ sqle.getMessage();
        }
        return listarEliminatorias;
    }

}

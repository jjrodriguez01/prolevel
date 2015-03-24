/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;


import modelo.EquiposdeltorneoDTO;

import utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

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
        conexion = Conexion.getInstance();
    }

    public String insertar(EquiposdeltorneoDTO usu) {

        try {
            //sentencia sql
            String sql = "INSERT INTO Equiposdeltorneo(EquipoCodigo,torneoIdTorneo)VALUES(?,?)";
            //pasamos la sentencia la conexion mediante el prepare staement
            statement = conexion.prepareStatement(sql);
            //obtenemos los datos del dto de la tabla
            statement.setInt(1, usu.getEquipoCodigo());
            statement.setInt(2, usu.getTorneoIdTorneo());
            

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
            mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();
        }
        //devolvemos el mensaje al usuario
        return mensaje;
    }

    public String actualizar(EquiposdeltorneoDTO usu) {
        try {
            //preparamos la sentencia sql
            String sql = "UPDATE Equiposdeltorneo SET EquipoCodigo=?,TorneoIdTorneo=? WHERE EquipoCodigo=?;";
            //pasamos el query a la conexion
           //sacamos los datos del dto de la tabla
            statement = conexion.prepareStatement(sql);
            statement.setInt(2, usu.getTorneoIdTorneo());
           
            
            //el resulset trae el numero de rows afectadas
            rtdo = statement.executeUpdate();
            if (rtdo != 0) {

              mensaje="El Campo Se a modificado:" + rtdo + "saludes";

            } else {
                mensaje = "Error";
            }
        } catch (SQLException sqlexception) {
            mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        }

        return mensaje;

    }

    public String eliminar(EquiposdeltorneoDTO usu) {
        try {
            statement = conexion.prepareStatement("Delete from usuarios where idUsuario=?;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, usu.getEquipoCodigo());
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

    public List<EquiposdeltorneoDTO> listarTodo() {
        //creamos el array que va a contener los datos de la consulta    
        ArrayList<EquiposdeltorneoDTO> listarUsuarios = new ArrayList();

        try {
            String query = "SELECT  Equiposdeltorneo as EquipoCodigo, TorneoIdTorneo"
                    + " FROM Equiposdeltorneo ";
            statement = conexion.prepareStatement(query);
            rs = statement.executeQuery();
            //mientras que halla registros cree un nuevo dto y pasele la info
            while (rs.next()) {
                //crea un nuevo dto
                EquiposdeltorneoDTO usu = new EquiposdeltorneoDTO();
                //le pasamos los datos que se encuentren
                usu.setEquipoCodigo(rs.getInt("Codigo"));
                usu.setTorneoIdTorneo(rs.getInt("torneo"));
                
                //agregamos el objeto dto al arreglo
                listarUsuarios.add(usu);

            }
        } catch (SQLException sqlexception) {
           mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        } finally {

        }
        //devolvemos el arreglo
        return listarUsuarios;

    }

    public String listarUno(EquiposdeltorneoDTO tor) {
        try {
            //preparamos la consulta 
            statement = conexion.prepareStatement("SELECT EquipoCodigo,TorneoIdTorneo FROM Equiposdeltorneo "
                    + "WHERE idUsuario = ? ;");
            statement.setInt(1, tor.getEquipoCodigo());
            rs = statement.executeQuery();
            //mientras halla registros
            while (rs.next()) {
                tor.setEquipoCodigo(rs.getInt("EquipoCodigo"));
                tor.setTorneoIdTorneo(rs.getInt("Torneo"));
               

            }

        } catch (SQLException ex) {
            mensaje = "Error inesperado: " + ex.getMessage() + " codigo de error " + ex.getErrorCode();
        }
        //devolvemos el usuario que se encontro
        return "" + tor;
    }

}
   

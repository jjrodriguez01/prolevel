/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import modelo.RolUsuarioDTO;

import utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class RolUsuarioDAO {
    
    Connection conexion = null;
    //instanciamos preparestatment
    PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    String mensaje = "";
    //variable que cuenta las filas afectadas
    int rtdo = 0;

    ResultSet rs;

    public RolUsuarioDAO() {
        conexion = Conexion.getInstance();
    }

    public String insertar(RolUsuarioDTO usu) {

        try {
            //sentencia sql
            String sql = "INSERT INTO RolUsuario(UsuarioIdUsuario,RolesIdRol)VALUES(?,?)";
            //pasamos la sentencia la conexion mediante el prepare staement
            statement = conexion.prepareStatement(sql);
            //obtenemos los datos del dto de la tabla
            statement.setInt(1, usu.getRolesidRol());
            statement.setInt(2, usu.getUsuarioIdUsuario());
            

            //ejecuta el insert
            rtdo = statement.executeUpdate();
            //si se afectaron campos 
            if (rtdo != 0) {
               mensaje="se insertaron:" + rtdo + "Datos";
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

    public String actualizar(RolUsuarioDTO usu) {
        try {
            //preparamos la sentencia sql
            String sql = "UPDATE RolUsuario SET RolesidRol=?,UsuarioIdUsuario=? WHERE RolesidRol=?;";
            //pasamos el query a la conexion
           //sacamos los datos del dto de la tabla
            statement = conexion.prepareStatement(sql);
            statement.setInt(2, usu.getUsuarioIdUsuario());
            
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

    public String eliminar(RolUsuarioDTO usu) {
        try {
            statement = conexion.prepareStatement("Delete from usuarios where idUsuario=?;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, usu.getRolesidRol());
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

    public List<RolUsuarioDTO> listarTodo() {
        //creamos el array que va a contener los datos de la consulta    
        ArrayList<RolUsuarioDTO> listarUsuarios = new ArrayList();

        try {
            String query = "SELECT  RolUsuario as RolesidRol,UsuarioIdUsuario"
                    + " FROM RolUsuario ";
            statement = conexion.prepareStatement(query);
            rs = statement.executeQuery();
            //mientras que halla registros cree un nuevo dto y pasele la info
            while (rs.next()) {
                //crea un nuevo dto
                RolUsuarioDTO usu = new RolUsuarioDTO();
                //le pasamos los datos que se encuentren
                usu.setRolesidRol(rs.getInt("Roles"));
                usu.setUsuarioIdUsuario(rs.getInt("Usuario"));
                
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

    public String listarUno(RolUsuarioDTO usuario) {
        try {
            //preparamos la consulta 
            statement = conexion.prepareStatement("SELECT UsuarioIdUsuario,"
                    + "rolesIdRol FROM RolUsuario "
                    + "WHERE idUsuario = ? ;");
            statement.setInt(1, usuario.getUsuarioIdUsuario());
            rs = statement.executeQuery();
            //mientras halla registros
            while (rs.next()) {
                usuario.setUsuarioIdUsuario(rs.getInt("IdUsuario"));
                usuario.setRolesidRol(rs.getInt("Roles"));
                

            }

        } catch (SQLException ex) {
            mensaje = "Error inesperado: " + ex.getMessage() + " codigo de error " + ex.getErrorCode();
        }
        //devolvemos el usuario que se encontro
        return "" + usuario;
    }

}
 

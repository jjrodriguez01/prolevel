/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import modelo.permisosDTO;
import utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class PermisosDAO {
    Connection conexion = null;
    //instanciamos preparestatment
    PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    String mensaje = "";
    //variable que cuenta las filas afectadas
    int rtdo = 0;

    ResultSet rs;

    public PermisosDAO() {
        conexion = Conexion.getInstance();
    }

    public String insertar(permisosDTO usu) {

        try {
            //sentencia sql
            String sql = "INSERT INTO permisos(idpermiso,descripcion)VALUES(?,?)";
            //pasamos la sentencia la conexion mediante el prepare staement
            statement = conexion.prepareStatement(sql);
            //obtenemos los datos del dto de la tabla
            statement.setInt(1, usu.getIdpermiso());
            statement.setString(2, usu.getDescripcion());
            
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

    public String actualizar(permisosDTO usu) {
        try {
            //preparamos la sentencia sql
            String sql = "UPDATE permisos SET idpermiso=?,descripcion=? WHERE idpermiso=?;";
            //pasamos el query a la conexion
           //sacamos los datos del dto de la tabla
            statement = conexion.prepareStatement(sql);
            statement.setString(2, usu.getDescripcion());
           
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

    public String eliminar(permisosDTO usu) {
        try {
            statement = conexion.prepareStatement("Delete from permisos where =?;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, usu.getIdpermiso());
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

    public List<permisosDTO> listarTodo() {
        //creamos el array que va a contener los datos de la consulta    
        ArrayList<permisosDTO> listarUsuarios = new ArrayList();

        try {
            String query = "SELECT  permisos as idpermiso, descripcion "
                    + " FROM permisos ";
            statement = conexion.prepareStatement(query);
            rs = statement.executeQuery();
            //mientras que halla registros cree un nuevo dto y pasele la info
            while (rs.next()) {
                //crea un nuevo dto
                permisosDTO usu = new permisosDTO();
                //le pasamos los datos que se encuentren
                usu.setIdpermiso(rs.getInt("permiso"));
                usu.setDescripcion(rs.getString("descripcion"));
                
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

    public String listarUno(permisosDTO usuario) {
        try {
            //preparamos la consulta 
            statement = conexion.prepareStatement("SELECT idpermiso,descripcion FROM permisos "
                    + "WHERE idpermiso = ? ;");
            statement.setInt(1, usuario.getIdpermiso());
            rs = statement.executeQuery();
            //mientras halla registros
            while (rs.next()) {
                usuario.setIdpermiso(rs.getInt("permiso"));
                usuario.setDescripcion(rs.getString("descripcion"));
               

            }

        } catch (SQLException ex) {
            mensaje = "Error inesperado: " + ex.getMessage() + " codigo de error " + ex.getErrorCode();
        }
        //devolvemos el usuario que se encontro
        return "" + usuario;
    }

}


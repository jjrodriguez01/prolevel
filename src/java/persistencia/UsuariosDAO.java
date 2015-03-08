/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import modelo.UsuariosDTO;
import org.jboss.logging.Logger;
import utilidades.Conexion;

public class UsuariosDAO {

    private Connection conexion = null;
    //instanciamos preparestatment
    private PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    private String mensaje = "";
    //variable que cuenta las filas afectadas
    private int rtdo = 0;

    ResultSet rs;

    public UsuariosDAO() {
        conexion = Conexion.getInstance();
    }

    public String insertar(UsuariosDTO usu) {

        try {
            //sentencia sql
            String sql = "INSERT INTO Usuarios(idUsuario,primerNombre, "
                    + "segundoNombre,primerApellido,segundoApellido,fechaNac,telefono,email,contraseña) "
                    + "VALUES(md5(?),?,?,?,?,?,?,?,?)";
            //pasamos la sentencia la conexion mediante el prepare staement
            statement = conexion.prepareStatement(sql);
            //obtenemos los datos del dto de la tabla
            statement.setLong(1,usu.getIdUsuario());
            statement.setString(2, usu.getPrimerNombre());
            statement.setString(3, usu.getSegundoNombre());
            statement.setString(4, usu.getPrimerApellido());
            statement.setString(5, usu.getSegundoApellido());
            statement.setString(6, usu.getFecha());
            statement.setString(7, usu.getEmail());
            statement.setString(8, usu.getTelefono());
            statement.setString(9, usu.getContraseña());

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
            System.out.println("Ocurrio Un Error" + sqlexception.getMessage());
        }
        //devolvemos el mensaje al usuario
        return mensaje;
    }

    public String actualizar(UsuariosDTO usu) {
        try {
            //preparamos la sentencia sql
            String sql = "UPDATE Usuarios SET primerNombre=?, "
                    + "segundoNombre=?,primerApellido=?, "
                    + "segundoApellido=?,telefono=?,email=?,contraseña=? WHERE idUsuario=?;";
            //pasamos el query a la conexion
           //sacamos los datos del dto de la tabla
            statement = conexion.prepareStatement(sql);
            statement.setString(1, usu.getPrimerNombre());
            statement.setString(2, usu.getSegundoNombre());
            statement.setString(3, usu.getPrimerApellido());
            statement.setString(4, usu.getSegundoApellido());
            statement.setString(5, usu.getTelefono());
            statement.setString(6, usu.getEmail());
            statement.setString(7, usu.getContraseña());
            statement.setLong(8, usu.getIdUsuario());
            //el resulset trae el numero de rows afectadas
            rtdo = statement.executeUpdate();
            if (rtdo != 0) {

                mensaje = "Se han modificado los datos";

            } else {
                mensaje = "Error";
            }
        } catch (SQLException sqlexception) {
            System.out.println("Ocurrio Un Error " + sqlexception.getMessage());

        }

        return mensaje;

    }

    public String eliminar(Long id) {
        try {
            statement = conexion.prepareStatement("Delete from usuarios where idUsuario=?;");
            //obtenemos el id del item a eliminar del dto
            statement.setLong(1, id);
            rtdo = statement.executeUpdate();

            if (rtdo != 0) {
                System.out.println("Se elimino " + rtdo + " registro corretamente");
            } else {
                mensaje = "Ocurrio Un Error";
            }
        } catch (SQLException sqlexception) {
            System.out.println("Ocurrio un error" + sqlexception.getMessage());

        }

        return mensaje;
    }

    public List<UsuariosDTO> listarTodo() {
        //creamos el array que va a contener los datos de la consulta    
        ArrayList<UsuariosDTO> listarUsuarios = new ArrayList();

        try {
            statement = conexion.prepareStatement("SELECT * FROM usuarios;");
            rs = statement.executeQuery();
            if(rs != null){            //mientras que halla registros cree un nuevo dto y pasele la info
            while (rs.next()) {
                //crea un nuevo dto
                UsuariosDTO usu = new UsuariosDTO();
                //le pasamos los datos que se encuentren
                usu.setIdUsuario(rs.getLong("idUsuario"));
                usu.setPrimerNombre(rs.getString("primerNombre"));
                usu.setSegundoNombre(rs.getString("segundoNombre"));
                usu.setPrimerApellido(rs.getString("primerApellido"));
                usu.setSegundoApellido(rs.getString("segundoApellido"));
                usu.setFecha(rs.getString("fechaNac"));
                usu.setEmail(rs.getString("email"));
                usu.setTelefono(rs.getString("telefono"));
                usu.setContraseña(rs.getString("contraseña"));
                //agregamos el objeto dto al arreglo
                listarUsuarios.add(usu);
            }
        }
        } catch (SQLException sqlexception) {
            mensaje = "Ocurrio un error" + sqlexception.getMessage();

        } finally {

        }
        //devolvemos el arreglo
        return listarUsuarios;
    }

    public UsuariosDTO listarUno(Long id) {
        UsuariosDTO usuario = new UsuariosDTO();
        try {
            //preparamos la consulta 
            statement = conexion.prepareStatement("SELECT idUsuario,"
                    + "primerNombre, segundoNombre,primerApellido,segundoApellido,"
                    + "fechaNac,telefono,email,contraseña FROM Usuarios;");
            rs = statement.executeQuery();
            //mientras halla registros
            if (rs!=null) {
            while (rs.next()) {
                usuario.setIdUsuario(rs.getLong("idUsuario"));
                usuario.setPrimerNombre(rs.getString("primerNombre"));
                usuario.setSegundoNombre(rs.getString("segundoNombre"));
                usuario.setPrimerApellido(rs.getString("primerApellido"));
                usuario.setSegundoApellido(rs.getString("segundoApellido"));
                usuario.setFecha(rs.getString("fechaNac"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setContraseña(rs.getString("contraseña")); 
            }
            }

        } catch (SQLException ex) {
            mensaje = "Error inesperado: " + ex.getMessage() + " codigo de error " + ex.getErrorCode();
        }
        //devolvemos el usuario que se encontro
        return usuario;
    }
    
        public UsuariosDTO validarUsuario(String email, String password) {
        UsuariosDTO user = new UsuariosDTO();
        String mensaje = "";
        ResultSet rs = null;
        try {
            statement = conexion.prepareStatement("SELECT * "
                    + "from usuarios where email=? and contraseña=?");

            statement.setString(1, email);
            statement.setString(2, password);

            rs = statement.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                user.setIdUsuario(rs.getLong("idUsuario"));
                user.setPrimerNombre(rs.getString("primerNombre"));
                user.setSegundoNombre(rs.getString("segundoNombre"));
                user.setPrimerApellido(rs.getString("primerApellido"));
                user.setSegundoApellido(rs.getString("segundoApellido"));
                user.setFecha(rs.getString("fechaNac"));
                user.setEmail(rs.getString("email"));
                user.setTelefono(rs.getString("telefono"));
                user.setContraseña(rs.getString("contraseña"));
                }
                
            } else {
                user=null;
            }
        } catch (SQLException sqle) {

            mensaje = " error " + sqle.getMessage();

        } finally {
        }

        return user;
    }
    public boolean ValidarRol(UsuariosDTO usu){
    boolean logeado=false;
    try{
      statement = conexion.prepareStatement("select * from usuarios as u  inner join rol_usuario as r"
        +" on u.idUsuario=r.usuarioIdUsuario where u.idusuario = ? and r.rolesIdRol=1;");
      statement.setLong(1, usu.getIdUsuario());
            rs = statement.executeQuery();
            usu.setIdUsuario(rs.getLong("idUsuario"));
            usu.setPrimerNombre(rs.getString("primerNombre"));
            usu.setSegundoNombre(rs.getString("segundoNombre"));
            usu.setPrimerApellido(rs.getString("primerApellido"));
            usu.setSegundoApellido(rs.getString("segundoApellido"));
            usu.setFecha(rs.getString("fechaNac"));
            usu.setEmail(rs.getString("email"));
            usu.setTelefono(rs.getString("telefono"));
            usu.setContraseña(rs.getString("contraseña"));
    if(usu!=null){
    return logeado=true;
    }
    }catch(SQLException ex){
        
    }
    return logeado; 
    }

}


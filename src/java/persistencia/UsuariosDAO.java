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
import java.util.List;
import modelo.UsuariosDTO;
import utilidades.Conexion;
import static controlador.seguridad.Encriptacion.encriptar;
import static controlador.seguridad.Encriptacion.desencriptar;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import utilidades.MiExcepcion;
public class UsuariosDAO {

    private Connection conexion = null;
    //instanciamos preparestatment
    private PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    private String mensaje = "";
    //variable que cuenta las filas afectadas
    private int rtdo = 0;
    //result set 
    private ResultSet rs;
    //llave
    
    public UsuariosDAO() {
        conexion = Conexion.getInstance();
    }
    /**
     * Inserta un usuario en la bd
     *
     * @param  usu
     *         un objeto usuariosDTO con los datos a insetat
     * @throws  MiException
     *          Excepcion peersonalizada
     */
    public synchronized String insertar(UsuariosDTO usu){
        try {
            //sentencia sql
            String sql = "INSERT INTO Usuarios(idUsuario,primerNombre, "
                    + "segundoNombre,primerApellido,segundoApellido,fechaNac,telefono,email,contraseña) "
                    + "VALUES(?,?,?,?,?,?,?,?,?);";
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
            byte[] passcript = encriptar(usu.getContraseña());
            statement.setBytes(9, passcript);

            //ejecuta el insert
            rtdo = statement.executeUpdate();
            //si se afectaron campos 
            if (rtdo != 0) {
                System.out.println("se inserto el usaurio correctamente");
                //si no se afecto la tabla
            } 
        } 
        catch (SQLException sqlexception) {
            mensaje = "Ocurrio Un Error  "+ sqlexception.getMessage();
        }catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e){
            mensaje = "Ha ocorrido in error encriptando su contraseña";
        }
        finally{
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            mensaje = "Ocurrio Un Error " + sqlexception.getMessage();
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
            mensaje = "Ocurrio un error" + sqlexception.getMessage();
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
    /**
     * Lista un usuario en la bd
     *
     * @param  id
     *         documento del usaurio
     * @throws  MiExcepcion
     *          Excepcion personalizada
     * @return objeto UsuarioDTO con los datos existentes o null si no se encontro con ese id
     */
    public UsuariosDTO listarUno(Long id)throws MiExcepcion {
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
            throw new MiExcepcion ("Error inesperado", ex);
        }
        //devolvemos el usuario que se encontro
        return usuario;
    }
    /**
     * Valida si el usuario existe en la base de datos
     * @param  email
     *         String con el correo
     *
     * @param  password
     *         contraseña del usuario
     */
        public long validarUsuario(String email, String password) throws MiExcepcion {
        long cc = 0;
        try {
            statement = conexion.prepareStatement("SELECT idUsuario "
                    + "from usuarios where email=? and contraseña=?;");
            byte[] passwordbd = encriptar(password);
            statement.setString(1, email);
            statement.setBytes(2, passwordbd);

            rs = statement.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                cc = rs.getLong("idUsuario");
                }     
            }
        } catch (SQLException sqle) {
           throw new MiExcepcion("Ha ocurrido un error", sqle);
        }catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e){
           throw new MiExcepcion("Ha ocorrido in error encriptando su contraseña. Por favor intentelo de nuevo.",e);
        }
        finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cc;
    }
    /**
     * Valida que el rol sea de administrador.
     *
     * @param  usu
     *         un objeto UsuariosDTO
     *
     * @throws MiExcepcion
     *          excepcion personalizada
     */
    public boolean ValidarRol(UsuariosDTO usu)throws MiExcepcion{
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
    }catch(SQLException sqle){
        throw new MiExcepcion("Ha ocurrido un error ", sqle);
    }
    return logeado; 
    }
    
    public String recuperar (String email){
        String password = ""; 
        UsuariosDTO udto = new UsuariosDTO();
        try{
            statement = conexion.prepareStatement("SELECT contraseña FROM usuarios "
                    + "WHERE email = ?;");
        statement.setString(1,email);
        rs = statement.executeQuery();
            while (rs.next()) {
                password = rs.getString("contraseña");
            }
    }catch(SQLException sqle){
        mensaje = "Ha ocurrido un error " + sqle.getMessage();
    }
        return password;
    }
    
    public String cambiarPass(long id, String newpass){
        try {
            statement =conexion.prepareStatement("UPDATE usuarios SET contraseña = ? "
                    + "WHERE idUsuario = ?;");
            byte[] pass = encriptar(newpass);
            statement.setBytes(1, pass);
            statement.setLong(2, id);
            rtdo = statement.executeUpdate();
            if (rtdo > 0) {
                mensaje = "Se cambió la contraseña";
            }
        } catch (SQLException ex) {
            mensaje = "Ha ocurrido un error = "+ ex.getMessage();
        }catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e){
            mensaje = "Ha ocurrido un error encriptando la contraseña";
        }
        return mensaje;
    }
    
    public StringBuilder validarDocumento(long cc)throws MiExcepcion{
        StringBuilder salida = new StringBuilder("");
        try {
            statement = conexion.prepareStatement("SELECT idUsuario FROM usuarios"
                    + " WHERE idusuario = ?;");
            statement.setLong(1, cc);
            rs = statement.executeQuery();
            if (rs.next()) {
                salida.append("El usuario ").append(cc).append(" ya se encuentra en el sistema");
            }else{
                salida.append("El usuario no se encuentra registrado en el sistema");
            }
        } catch (SQLException sqle) {
            throw new MiExcepcion("Error ", sqle); 
        }
        return salida;
    }
}


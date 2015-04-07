/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;


import utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CanchaDTO;
import modelo.EquipoDTO;
import modelo.PartidoDTO;
import modelo.TorneoDTO;
import utilidades.MiExcepcion;
public class PartidoDAO {
 
     Connection conexion = null;
    //instanciamos preparestatment
    PreparedStatement statement;
    //variable que devuelve el metodo con el mensaje
    String mensaje = "";
    //variable que cuenta las filas afectadas
    int rtdo = 0;

    ResultSet rs;

    public PartidoDAO() {
        conexion = Conexion.getInstance();
    }

    public synchronized String insertar(PartidoDTO cal) {

        try {
            //sentencia sql
            String sql = "INSERT INTO partidos(ronda,equipo1,equipo2,fecha,hora,idTorneo,cancha)"
                    + "VALUES(?,?,?,?,?,?,?);";
            //pasamos la sentencia la conexion mediante el prepare staement
            statement = conexion.prepareStatement(sql);
            //obtenemos los datos del dto de la tabla
            statement.setInt(1, cal.getRonda());
            statement.setInt(2, cal.getEquipo1());
            statement.setInt(3, cal.getEquipo2());
            statement.setString(4, cal.getFecha());
            statement.setString(5, cal.getHora());
            statement.setInt(6, cal.getIdTorneo());
            statement.setInt(7, cal.getCancha());
            //ejecuta el insert
            rtdo = statement.executeUpdate();
            //si se afectaron campos 
            if (rtdo != 0) {
                mensaje = "Se insertaron los partidos";
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

//    public String actualizar(PartidoDTO cal) {
//        try {
//            //preparamos la sentencia sql
//            String sql = "UPDATE Calendario SET ronda=?,equipo1=?,equipo2=?,fecha=?,hora=?,idTorneo=?,"
//                    + "calendariocol=? WHERE codigoCalendario=?;";
//            //pasamos el query a la conexion
//           //sacamos los datos del dto de la tabla
//            statement = conexion.prepareStatement(sql);
//            statement.setInt(2, cal.getEquipo1());
//            statement.setInt(3, cal.getEquipo2());
//            statement.setString(4, cal.getFecha());
//            statement.setString(5,cal.getHora());
//            statement.setInt(7, cal.getIdTorneo());
//            
//            //el resulset trae el numero de rows afectadas
//            rtdo = statement.executeUpdate();
//            if (rtdo != 0) {
//                mensaje = "elCampo se ha modificado: "+rtdo+ "saludes";
//
//            } else {
//                mensaje = "Error";
//            }
//        } catch (SQLException sqlexception) {
//         mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();
//        }
//
//        return mensaje;
//    }

    public String eliminar(PartidoDTO cal) {
        try {
            statement = conexion.prepareStatement("Delete from partidos where idTorneo = ?;");
            //obtenemos el id del item a eliminar del dto
            statement.setInt(1, cal.getIdTorneo());
            rtdo = statement.executeUpdate();

            if (rtdo != 0) {
                System.out.println("El siguiente campo" + rtdo + "se elimino Corretamente");
            } else {
                mensaje = "Ocurrio Un Error";
            }
        } catch (SQLException sqlexception) {
             mensaje = "Ha ocurrido un error "+ sqlexception.getMessage();

        }

        return mensaje;
    }

    public List<PartidoDTO> listarTodo() throws MiExcepcion {
        //creamos el array que va a contener los datos de la consulta    
        ArrayList<PartidoDTO> listar = new ArrayList();
        try {
            String query = "SELECT DISTINCT\n" +
"    ronda,\n" +
"    marcador1,\n" +
"    (select equipo.nombre from equipo where codigo=partidos.equipo1)as equipo1,\n" +
"    (select equipo.nombre from equipo where codigo=partidos.equipo2)as equipo2,\n" +
"    marcador2,\n" +
"    fecha,\n" +
"    hora,\n" +
"    torneo.nombre as Torneo,\n" +
"    cancha.descripcion as descripcion\n" +
"FROM\n" +
"    partidos\n" +
"INNER JOIN equiposdeltorneo\n" +
"ON partidos.equipo1 = equiposdeltorneo.equipoCodigo\n" +
"INNER JOIN equipo\n" +
"ON equiposdeltorneo.equipoCodigo = equipo.codigo\n" +
"INNER JOIN torneo\n" +
"ON partidos.idTorneo = torneo.idTorneo\n" +
"INNER JOIN cancha\n" +
"ON partidos.cancha = cancha.numeroCancha;";
            statement = conexion.prepareStatement(query);
            rs = statement.executeQuery();
            //mientras que halla registros cree un nuevo dto y pasele la info
            while (rs.next()) {
                //crea un nuevo dto
                PartidoDTO cal = new PartidoDTO();
                EquipoDTO equipouno = new EquipoDTO();
                EquipoDTO equipodos = new EquipoDTO();
                TorneoDTO torneo = new TorneoDTO();
                CanchaDTO cancha = new CanchaDTO();
                
                //le pasamos los datos que se encuentren
                cal.setRonda(rs.getInt("ronda"));
                cal.setMarcador1(rs.getInt("marcador1"));
                equipouno.setNombre(rs.getString("equipo1"));
                cal.setEquipouno(equipouno);
                equipodos.setNombre(rs.getString("equipo2"));
                cal.setMarcador2(rs.getInt("marcador2"));
                cal.setFecha(rs.getString("fecha"));
                cal.setHora(rs.getString("hora"));
                torneo.setNombre(rs.getString("torneo"));
                cal.setTorneo(torneo);
                cancha.setDescripcion(rs.getString("descripcion"));
                cal.setCanchas(cancha);
                //agregamos el objeto dto al arreglo
                listar.add(cal);
            }
        } catch (SQLException sqlexception) {
            throw new MiExcepcion("Error ", sqlexception);

        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                throw new MiExcepcion("Error ", ex);
            }
        }
        //devolvemos el arreglo
        return listar;

    }

    public List<PartidoDTO> listarUno(int ronda, int idTorneo) throws MiExcepcion {
        //creamos el array que va a contener los datos de la consulta    
        ArrayList<PartidoDTO> listar = new ArrayList();
        try {
            String query = "SELECT DISTINCT\n" +
"    ronda,\n" +
"    marcador1,\n" +
"    (select equipo.nombre from equipo where codigo=partidos.equipo1)as equipo1,\n" +
"    (select equipo.nombre from equipo where codigo=partidos.equipo2)as equipo2,\n" +
"    marcador2,\n" +
"    fecha,\n" +
"    hora,\n" +
"    torneo.nombre as Torneo,\n" +
"    cancha.descripcion as descripcion\n" +
"FROM\n" +
"    partidos\n" +
"INNER JOIN equiposdeltorneo\n" +
"ON partidos.equipo1 = equiposdeltorneo.equipoCodigo\n" +
"INNER JOIN equipo\n" +
"ON equiposdeltorneo.equipoCodigo = equipo.codigo\n" +
"INNER JOIN torneo\n" +
"ON partidos.idTorneo = torneo.idTorneo\n" +
"INNER JOIN cancha\n" +
"ON partidos.cancha = cancha.numeroCancha\n" +
"WHERE ronda = ? AND partidos.idTorneo = ?;";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, ronda);
            statement.setInt(2, idTorneo);
            rs = statement.executeQuery();
            //mientras que halla registros cree un nuevo dto y pasele la info
            while (rs.next()) {
                //crea un nuevo dto
                PartidoDTO cal = new PartidoDTO();
                EquipoDTO equipouno = new EquipoDTO();
                EquipoDTO equipodos = new EquipoDTO();
                TorneoDTO torneo = new TorneoDTO();
                CanchaDTO cancha = new CanchaDTO();
                
                //le pasamos los datos que se encuentren
                cal.setRonda(rs.getInt("ronda"));
                cal.setMarcador1(rs.getInt("marcador1"));
                equipouno.setNombre(rs.getString("equipo1"));
                cal.setEquipouno(equipouno);
                equipodos.setNombre(rs.getString("equipo2"));
                cal.setMarcador2(rs.getInt("marcador2"));
                cal.setFecha(rs.getString("fecha"));
                cal.setHora(rs.getString("hora"));
                torneo.setNombre(rs.getString("torneo"));
                cal.setTorneo(torneo);
                cancha.setDescripcion(rs.getString("descripcion"));
                cal.setCanchas(cancha);
                //agregamos el objeto dto al arreglo
                listar.add(cal);
            }
        } catch (SQLException sqlexception) {
            throw new MiExcepcion("Error ", sqlexception);

        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                throw new MiExcepcion("Error ", ex);
            }
        }
        //devolvemos el arreglo
        return listar;

    }

}
  


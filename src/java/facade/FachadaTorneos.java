/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controlador.Conexion;
import java.sql.Connection;
import java.util.List;
import modelo.CanchaDTO;
import modelo.EquipoDTO;
import modelo.EquiposdeltorneoDTO;
import modelo.TorneoDTO;
import persistencia.CanchaDAO;
import persistencia.EquipoDAO;
import persistencia.EquiposDelTorneoDAO;
import persistencia.GoleadoresDAO;
import persistencia.JugadoresporequipoDAO;
import persistencia.PartidoDAO;
import persistencia.TablaPosicionesDAO;
import persistencia.TarjetasDAO;
import persistencia.TorneoDAO;
import utilidades.MiExcepcion;

/**
 *
 * @author jeisson
 */
public class FachadaTorneos {

    TorneoDAO tdao;
    CanchaDAO cdao;
    TarjetasDAO tardao;
    JugadoresporequipoDAO jequipodao;
    EquiposDelTorneoDAO edtdao;
    EquipoDAO equipodao;
    GoleadoresDAO goleadoresdao;
    PartidoDAO partidodao;
    TablaPosicionesDAO tpdao;
    Connection conexion;
    public FachadaTorneos() {
        cdao = new CanchaDAO();
        tardao = new TarjetasDAO();
        jequipodao = new JugadoresporequipoDAO();
        equipodao = new EquipoDAO();
        edtdao = new EquiposDelTorneoDAO();
        goleadoresdao = new GoleadoresDAO();
        partidodao = new PartidoDAO();
        tpdao = new TablaPosicionesDAO();
        conexion = Conexion.getConnection();
    }
    
//    empiezo con torneoDAO
    
    public String actualizarTorneo(TorneoDTO tdto){
        return tdao.actualizar(tdto, conexion);
    }
    
    public String eliminarTorneo(int id){
        return tdao.eliminar(id, conexion);
    }
    
    public TorneoDTO listarTorneo(int id) throws MiExcepcion{
        return tdao.listarUno(id, conexion);
    }
    
    public List<TorneoDTO> listarTorneos() throws MiExcepcion{
        return tdao.ListarTodo(conexion);
    }
    
//    SIGO CON CANCHASDAO
    
    public String insertarCancha(CanchaDTO cancha){
        return cdao.insertar(cancha, conexion);
    }
    
    public String actualizarCancha(CanchaDTO cancha){
        return cdao.actualizar(cancha, conexion);
    }
    
    public String eliminarCancha(int numero){
        return cdao.eliminar(numero, conexion);
    }
 
    public List<CanchaDTO> listarCanchas () throws MiExcepcion{
        return cdao.listarTodo(conexion);
    }
    
    public CanchaDTO listarCancha (int numero) throws MiExcepcion{
        return cdao.listarUno(numero,conexion);
    }
    
//    SIGO CON EQUIPODAO
    
    public String insertarEquipo(String nombre){
        return equipodao.insertar(nombre, conexion);
    }
    
    public String actualizarEquipo(EquipoDTO eq){
        return equipodao.actualizar(eq, conexion);
    }
    
    public String eliminarEquipo(int codigo){
        return equipodao.eliminar(codigo, conexion);
    }
    
    public int existeEquipo(String nombre) throws MiExcepcion{
        return equipodao.existeEquipo(nombre, conexion);
    }
    
//    SIGO CON EQUIPOSDELTORNEODAO
            
    public String inscribirEquipos(int codigoequipo, int idTorneo){
        return edtdao.insertar(codigoequipo, idTorneo, conexion);
    }
    
    public List<EquiposdeltorneoDTO> listarEquiposInscritos(int idTorneo) throws MiExcepcion{
        return edtdao.listarTodo(idTorneo, conexion);
    }
    
    public List<String> correosJugadoresEquipo(int idTorneo, int codigoEquipo) throws MiExcepcion{
        return edtdao.correosJugadoresEquipo(idTorneo, codigoEquipo, conexion);
    }
    
//    SIGO CON JUGADORESPOREQUIPODAO
            
    public String inscribirJugadoraEquipo()
}

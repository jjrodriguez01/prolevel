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
import modelo.GoleadoresDTO;
import modelo.JugadoresporequipoDTO;
import modelo.PartidoDTO;
import modelo.TablaPosicionesDTO;
import modelo.TarjetasDTO;
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
        tdao = new TorneoDAO();
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
    
    public List<EquipoDTO> listarEquiposEnTorneo(int idTorneo) throws MiExcepcion{
        return equipodao.listarTodoTorneo(idTorneo, conexion);
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
            
    public String inscribirJugadorAEquipo(int equipo, long jugador) throws MiExcepcion{
        return jequipodao.insertar(equipo, jugador, conexion);
    }
    
    public String actualizarJugadorInscrito(int equipo, long jugador) throws MiExcepcion{
        return jequipodao.actualizar(equipo, jugador, conexion);
    }
    
    public String eliminarJugadoresEquipo(int equipo) throws MiExcepcion{
        return jequipodao.eliminar(equipo, conexion);
    }
    
    public List<JugadoresporequipoDTO> listarInscritos() throws MiExcepcion{
        return jequipodao.listarTodo(conexion);
    }
    
    public List<JugadoresporequipoDTO> jugadorDeEquipo(int equipo, long jugador) throws MiExcepcion{
        return jequipodao.listarUno(equipo, equipo, conexion);
    }
    
    public List<JugadoresporequipoDTO> jugadoresDeEquipo(int equipo) throws MiExcepcion{
        return jequipodao.listarJugadoresEq(equipo, conexion);
    }
    
//    AHORA PARTIDODAO
    
    public String insertarPartido(PartidoDTO partido) throws MiExcepcion{
        return partidodao.insertar(partido, conexion);
    }
    
    public String actualizarPartido(PartidoDTO partido) throws MiExcepcion{
        return partidodao.actualizar(partido, conexion);
    }
    
    public String eliminarPartido(PartidoDTO partido) throws MiExcepcion{
        return partidodao.eliminar(partido, conexion);
    }
    
    public List<PartidoDTO> listarPartidos() throws MiExcepcion{
        return partidodao.listarTodo(conexion);
    }
    
    public List<PartidoDTO> listarPrimeraRonda(int idTorneo) throws MiExcepcion{
        return partidodao.listarTodoPronda(idTorneo, conexion);
    }
    
    public List<PartidoDTO> listarUnPartido(int ronda,int idTorneo) throws MiExcepcion{
        return partidodao.listarUno(ronda, idTorneo, conexion);
    }
    
//    AHORA SIGO CON TARJETAS
    
    public String insertarTarjetas(TarjetasDTO tarjeta) throws MiExcepcion{
        return tardao.insertar(tarjeta, conexion);
    }
    
    public String insertarPrimera(TarjetasDTO tarjeta) throws MiExcepcion{
        return tardao.insertarPrimer(tarjeta, conexion);
    }
    
    public String actualizarTarjetas(TarjetasDTO tarjeta) throws MiExcepcion{
        return tardao.actualizar(tarjeta, conexion);
    }
    
    public String eliminarTarjetas(long jugador, int idTorneo) throws MiExcepcion{
        return tardao.eliminar(jugador, idTorneo, conexion);
    }
    
    public List<TarjetasDTO> listarTarjetasJugador(int torneo, int jugador) throws MiExcepcion{
        return tardao.listarUno(torneo, jugador, conexion);
    }
//    AHORA GOLEADORES DAO
    
    public String insertarGoles(GoleadoresDTO gol) throws MiExcepcion{
        return goleadoresdao.insertar(gol, conexion);
    }
    
//    AHORA POSICIONES
            
    public String insertarPosicion(TablaPosicionesDTO tp) throws MiExcepcion{
        return tpdao.insertar(tp, conexion);
    }    
    
}

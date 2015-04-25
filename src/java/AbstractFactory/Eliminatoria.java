/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractFactory;

import controlador.Conexion;
import facade.FachadaTorneos;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import modelo.EquiposdeltorneoDTO;
import modelo.PartidoDTO;
import modelo.TorneoDTO;
import persistencia.EliminatoriaDAO;
import utilidades.MiExcepcion;

/**
 *
 * @author jeisson
 */
public class Eliminatoria extends Torneo {

    EliminatoriaDAO eli;
    Connection conexion;

    public Eliminatoria() {
        eli = new EliminatoriaDAO();
        conexion = Conexion.getConnection();
    }
    
    
    @Override
    public String crear(TorneoDTO torneo) {
        return eli.insertar(torneo,conexion);
    }

    @Override
    public String modificar(TorneoDTO torneo) {
        return eli.actualizar(torneo,conexion);
    }

    @Override
    public String eliminar(int id) {
        return eli.eliminar(id,conexion);
    }

    @Override
    public List listarTodo() throws MiExcepcion {
        return eli.ListarTodo(conexion);
    }

    @Override
    public TorneoDTO listarUno(int id) throws MiExcepcion {
        return eli.listarUno(id,conexion);
    }
    /**
     * Crea los emparejamientos de una eliminatoria de 16 equipos
     * 
     * @param arr Equipos inscritos al torneo
     * @throws MiExcepcion 
     */
    public void primeraRondaDiesciseis(List<EquiposdeltorneoDTO> arr) throws MiExcepcion{
        ArrayList<EquiposdeltorneoDTO> arrayeq = (ArrayList)arr;
        Map<Integer,EquiposdeltorneoDTO> equipos = new TreeMap<Integer,EquiposdeltorneoDTO>();
        int clave = 0;
        for(EquiposdeltorneoDTO eq : arrayeq){
            clave++;
            equipos.put(clave, eq);
        }
        int ronda = 1;
        int idTorneo = equipos.get(1).getTorneoIdTorneo();
        int estado = 0;//estado del partido 0=por jugar
        FachadaTorneos partido = new FachadaTorneos();
        //instancio la cantidad de partidos que necesito
        //para un torneo de 16 equipos seran 8 en primera ronda
        PartidoDTO puno = new PartidoDTO();
        PartidoDTO pdos = new PartidoDTO();
        PartidoDTO ptres = new PartidoDTO();
        PartidoDTO pcuatro = new PartidoDTO();
        PartidoDTO pcinco = new PartidoDTO();
        PartidoDTO pseis = new PartidoDTO();
        PartidoDTO psiete = new PartidoDTO();
        PartidoDTO pocho = new PartidoDTO();
        
        //comienzo a insertar los partidos
        
        puno.setRonda(ronda);
        puno.setEquipo1(equipos.get(1).getEquipoCodigo());
        puno.setEquipo2(equipos.get(2).getEquipoCodigo());
        puno.setIdTorneo(idTorneo);
        int n1 = 1;//primer partido
        puno.setNumero(n1);
        puno.setEstado(estado);
        partido.insertarPartido(puno);
        
        pdos.setRonda(ronda);
        pdos.setEquipo1(equipos.get(3).getEquipoCodigo());
        pdos.setEquipo2(equipos.get(4).getEquipoCodigo());
        pdos.setIdTorneo(idTorneo);
        int n2 = 2;//segundo partido
        puno.setNumero(n2);
        puno.setEstado(estado);
        partido.insertarPartido(pdos);
        
        ptres.setRonda(ronda);
        ptres.setEquipo1(equipos.get(5).getEquipoCodigo());
        ptres.setEquipo2(equipos.get(6).getEquipoCodigo());
        ptres.setIdTorneo(idTorneo);
        int n3 = 3;
        puno.setNumero(n3);
        puno.setEstado(estado);
        partido.insertarPartido(ptres);
        
        pcuatro.setRonda(ronda);
        pcuatro.setEquipo1(equipos.get(7).getEquipoCodigo());
        pcuatro.setEquipo2(equipos.get(8).getEquipoCodigo());
        pcuatro.setIdTorneo(idTorneo);
        int n4 = 4;
        puno.setNumero(n4);
        puno.setEstado(estado);
        partido.insertarPartido(pcuatro);
        
        pcinco.setRonda(ronda);
        pcinco.setEquipo1(equipos.get(9).getEquipoCodigo());
        pcinco.setEquipo2(equipos.get(10).getEquipoCodigo());
        pcinco.setIdTorneo(idTorneo);
        int n5 = 5;
        puno.setNumero(n5);
        puno.setEstado(estado);
        partido.insertarPartido(pcinco);
        
        pseis.setRonda(ronda);
        pseis.setEquipo1(equipos.get(11).getEquipoCodigo());
        pseis.setEquipo2(equipos.get(12).getEquipoCodigo());
        pseis.setIdTorneo(idTorneo);
        int n6 = 6;
        puno.setNumero(n6);
        puno.setEstado(estado);
        partido.insertarPartido(pseis);
        
        psiete.setRonda(ronda);
        psiete.setEquipo1(equipos.get(13).getEquipoCodigo());
        psiete.setEquipo2(equipos.get(14).getEquipoCodigo());
        psiete.setIdTorneo(idTorneo);
        int n7 = 7;
        puno.setNumero(n7);
        puno.setEstado(estado);
        partido.insertarPartido(psiete);
        
        pocho.setRonda(ronda);
        pocho.setEquipo1(equipos.get(15).getEquipoCodigo());
        pocho.setEquipo2(equipos.get(16).getEquipoCodigo());
        pocho.setIdTorneo(idTorneo);
        int n8 = 8;
        puno.setNumero(n8);
        puno.setEstado(estado);
        partido.insertarPartido(pocho);
    }
    
    
    public void segundaRondaDiesciseis(List<EquiposdeltorneoDTO> arr) throws MiExcepcion{
        ArrayList<EquiposdeltorneoDTO> arrayeq = (ArrayList)arr;
        Map<Integer,EquiposdeltorneoDTO> equipos = new TreeMap<Integer,EquiposdeltorneoDTO>();
        int clave = 0;
        for(EquiposdeltorneoDTO eq : arrayeq){
            clave++;
            equipos.put(clave, eq);
        }
        int ronda = 1;
        int idTorneo = equipos.get(1).getTorneoIdTorneo();
        int estado = 0;//estado del partido 0=por jugar
        FachadaTorneos partido = new FachadaTorneos();
        //instancio la cantidad de partidos que necesito
        //para un torneo de 16 equipos seran 4 en segunda ronda
        PartidoDTO puno = new PartidoDTO();
        PartidoDTO pdos = new PartidoDTO();
        PartidoDTO ptres = new PartidoDTO();
        PartidoDTO pcuatro = new PartidoDTO();
        
        //comienzo a insertar los partidos
        
        puno.setRonda(ronda);
        puno.setEquipo1(equipos.get(1).getEquipoCodigo());
        puno.setEquipo2(equipos.get(3).getEquipoCodigo());
        puno.setIdTorneo(idTorneo);
        int n1 = 1;//primer partido
        puno.setNumero(n1);
        puno.setEstado(estado);
        partido.insertarPartido(puno);
        
        pdos.setRonda(ronda);
        pdos.setEquipo1(equipos.get(2).getEquipoCodigo());
        pdos.setEquipo2(equipos.get(4).getEquipoCodigo());
        pdos.setIdTorneo(idTorneo);
        int n2 = 2;//segundo partido
        puno.setNumero(n2);
        puno.setEstado(estado);
        partido.insertarPartido(pdos);
        
        ptres.setRonda(ronda);
        ptres.setEquipo1(equipos.get(7).getEquipoCodigo());
        ptres.setEquipo2(equipos.get(6).getEquipoCodigo());
        ptres.setIdTorneo(idTorneo);
        int n3 = 3;
        puno.setNumero(n3);
        puno.setEstado(estado);
        partido.insertarPartido(ptres);
        
        pcuatro.setRonda(ronda);
        pcuatro.setEquipo1(equipos.get(5).getEquipoCodigo());
        pcuatro.setEquipo2(equipos.get(8).getEquipoCodigo());
        pcuatro.setIdTorneo(idTorneo);
        int n4 = 4;
        puno.setNumero(n4);
        puno.setEstado(estado);
        partido.insertarPartido(pcuatro);
        
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractFactory;

import controlador.Conexion;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import modelo.EquiposdeltorneoDTO;
import modelo.PartidoDTO;
import modelo.TorneoDTO;
import persistencia.EliminatoriaDAO;
import persistencia.EquiposDelTorneoDAO;
import persistencia.PartidoDAO;
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
    
    public void primeraRondaDiesciseis(List<EquiposdeltorneoDTO> arr){
        ArrayList<EquiposdeltorneoDTO> arrayeq = (ArrayList)arr;
        Map<Integer,EquiposdeltorneoDTO> equipos = new TreeMap<Integer,EquiposdeltorneoDTO>();
        int clave = 0;
        for(EquiposdeltorneoDTO eq : arrayeq){
            clave++;
            equipos.put(clave, eq);
        }
        EquiposDelTorneoDAO edtdao = new EquiposDelTorneoDAO();
        int ronda = 1;
        int idTorneo = equipos.get(1).getTorneoIdTorneo();
        PartidoDAO partido = new PartidoDAO();
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
        partido.insertar(puno);
        
        pdos.setRonda(ronda);
        pdos.setEquipo1(equipos.get(3).getEquipoCodigo());
        pdos.setEquipo2(equipos.get(4).getEquipoCodigo());
        pdos.setIdTorneo(idTorneo);
        partido.insertar(pdos);
        
        ptres.setRonda(ronda);
        ptres.setEquipo1(equipos.get(5).getEquipoCodigo());
        ptres.setEquipo2(equipos.get(6).getEquipoCodigo());
        ptres.setIdTorneo(idTorneo);
        partido.insertar(ptres);
        
        pcuatro.setRonda(ronda);
        pcuatro.setEquipo1(equipos.get(7).getEquipoCodigo());
        pcuatro.setEquipo2(equipos.get(8).getEquipoCodigo());
        pcuatro.setIdTorneo(idTorneo);
        partido.insertar(pcuatro);
        
        pcinco.setRonda(ronda);
        pcinco.setEquipo1(equipos.get(9).getEquipoCodigo());
        pcinco.setEquipo2(equipos.get(10).getEquipoCodigo());
        pcinco.setIdTorneo(idTorneo);
        partido.insertar(pcinco);
        
        pseis.setRonda(ronda);
        pseis.setEquipo1(equipos.get(11).getEquipoCodigo());
        pseis.setEquipo2(equipos.get(12).getEquipoCodigo());
        pseis.setIdTorneo(idTorneo);
        partido.insertar(pseis);
        
        psiete.setRonda(ronda);
        psiete.setEquipo1(equipos.get(13).getEquipoCodigo());
        psiete.setEquipo2(equipos.get(14).getEquipoCodigo());
        psiete.setIdTorneo(idTorneo);
        partido.insertar(psiete);
        
        pocho.setRonda(ronda);
        pocho.setEquipo1(equipos.get(15).getEquipoCodigo());
        pocho.setEquipo2(equipos.get(16).getEquipoCodigo());
        pocho.setIdTorneo(idTorneo);
        partido.insertar(pocho);
    }
}

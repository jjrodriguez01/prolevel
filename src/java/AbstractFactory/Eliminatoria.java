/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractFactory;

import java.util.List;
import modelo.TorneoDTO;
import persistencia.EliminatoriaDAO;

/**
 *
 * @author jeisson
 */
public class Eliminatoria extends Torneo {

    EliminatoriaDAO eli = new EliminatoriaDAO();
    @Override
    public String crear(TorneoDTO torneo) {
        return eli.insertar(torneo);
    }

    @Override
    public String modificar(TorneoDTO torneo) {
        return eli.actualizar(torneo);
    }

    @Override
    public String eliminar(int id) {
        return eli.eliminar(id);
    }

    @Override
    public List listarTodo() {
        return eli.ListarTodo();
    }

    @Override
    public TorneoDTO listarUno(int id) {
        return eli.listarUno();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractFactory;

import java.util.List;
import modelo.TorneoDTO;
import persistencia.LigaDAO;

/**
 *
 * @author jeisson
 */
public class Liga extends Torneo{

    LigaDAO liga = new LigaDAO();
    @Override
    public String crear(TorneoDTO torneo) {
        return liga.insertar(torneo);
    }

    @Override
    public String modificar(TorneoDTO torneo) {
        return liga.actualizar(torneo);
    }

    @Override
    public String eliminar(int id) {
        return liga.eliminar(id);
    }

    @Override
    public List listarTodo() {
        return liga.listarTodo();
    }

    @Override
    public TorneoDTO listarUno(int id) {
        return liga.listarUno(id);
    }
    
}

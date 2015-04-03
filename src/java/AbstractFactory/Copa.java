/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractFactory;

import java.util.List;
import modelo.TorneoDTO;
import persistencia.CopaDAO;

/**
 *
 * @author jeisson
 */
public class Copa extends Torneo {
    CopaDAO cup = new CopaDAO();
    @Override
    public String crear(TorneoDTO copa){     
        return cup.insertar(copa);
    }
    @Override
    public String modificar(TorneoDTO copa){
        return cup.actualizar(copa);
    }
    @Override
    public String eliminar(int id){
        return cup.eliminar(id);
    }
    @Override
    public List listarTodo(){
        return cup.ListarTodo();
    }

    @Override
    public TorneoDTO listarUno(int id) {
        return cup.listarUno(id);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractFactory;

import java.util.List;
import modelo.TorneoDTO;

/**
 *
 * @author jeisson
 */
public abstract class Torneo {
    private TorneoDTO torneo;
    
    public abstract String crear(TorneoDTO torneo);
    public abstract String modificar(TorneoDTO torneo);
    public abstract String eliminar(int id);
    public abstract List listarTodo();
    public abstract TorneoDTO listarUno(int id);
}

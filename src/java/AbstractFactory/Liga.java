/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractFactory;

import controlador.Conexion;
import java.sql.Connection;
import java.util.List;
import modelo.TorneoDTO;
import persistencia.LigaDAO;
import utilidades.MiExcepcion;

/**
 *
 * @author jeisson
 */
public class Liga extends Torneo{

    LigaDAO liga;
    Connection conexion;

    public Liga() {
        liga = new LigaDAO();
        conexion = Conexion.getConnection();
    }
    
    
    @Override
    public String crear(TorneoDTO torneo) {
        return liga.insertar(torneo, conexion);
    }

    @Override
    public String modificar(TorneoDTO torneo) {
        return liga.actualizar(torneo, conexion);
    }

    @Override
    public String eliminar(int id) {
        return liga.eliminar(id, conexion);
    }

    @Override
    public List listarTodo() throws MiExcepcion {
        return liga.listarTodo(conexion);
    }

    @Override
    public TorneoDTO listarUno(int id) throws MiExcepcion {
        return liga.listarUno(id, conexion);
    }
    
}

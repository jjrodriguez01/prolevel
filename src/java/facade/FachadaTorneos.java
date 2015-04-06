/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import FactoryMethod.TorneoFactory;
import persistencia.CanchaDAO;
import persistencia.EquipoDAO;
import persistencia.EquiposDelTorneoDAO;
import persistencia.JugadoresporequipoDAO;
import persistencia.TarjetasDAO;

/**
 *
 * @author jeisson
 */
public class FachadaTorneos {

    TorneoFactory factory;
    CanchaDAO cdao;
    TarjetasDAO tardao;
    JugadoresporequipoDAO jequipodao;
    EquiposDelTorneoDAO edtorneodao;
    EquipoDAO equipodao;
    public FachadaTorneos() {
        factory = new TorneoFactory();
        cdao = new CanchaDAO();
        tardao = new TarjetasDAO();
        jequipodao = new JugadoresporequipoDAO();
        equipodao = new EquipoDAO();
        edtorneodao = new EquiposDelTorneoDAO();
        
    }
    
}

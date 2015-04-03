/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractFactory;

import modelo.TorneoDTO;

/**
 *
 * @author jeisson
 */
public class FabricaTorneo implements FabricaTorneos{

    @Override
    public Copa CreaCopa(TorneoDTO torneo) {
         return new Copa();
    }

    @Override
    public Liga creaLiga(TorneoDTO torneo) {
        return new Liga();
    }

    @Override
    public Eliminatoria creaEliminatoria(TorneoDTO torneo) {
       return new Eliminatoria();
    }
    
}

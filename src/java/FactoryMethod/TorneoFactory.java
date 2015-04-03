/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoryMethod;

import AbstractFactory.FabricaTorneo;
import AbstractFactory.Torneo;
import modelo.TorneoDTO;

/**
 *
 * @author jeisson
 */
public class TorneoFactory implements TorneoFactoryMethod{

    FabricaTorneo fabrica = new FabricaTorneo();
    
    /**
     * Devuelve el objeto de un torneo
     *
     * @param  tipo
     *         tipo de torneo requerido 1)copa 2)liga 3)eliminatoria
     *
     * @return  Torneo
     */
    @Override
    public Torneo crearTorneo(int tipo, TorneoDTO torneo) {
        if (tipo==1) {
            return fabrica.CreaCopa(torneo);
        }else if(tipo==2){
            return fabrica.creaLiga(torneo);
        }
        else if(tipo==3){
            return fabrica.creaEliminatoria(torneo);
        }
        else{
            return null;
        }
    }
    
}

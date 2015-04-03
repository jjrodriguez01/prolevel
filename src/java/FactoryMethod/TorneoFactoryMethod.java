/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoryMethod;

import AbstractFactory.Torneo;
import modelo.TorneoDTO;

/**
 *
 * @author jeisson
 */
public interface TorneoFactoryMethod {
    public Torneo crearTorneo(int tipo,TorneoDTO torneo);
}

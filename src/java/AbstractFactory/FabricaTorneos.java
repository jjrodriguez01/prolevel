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
public interface FabricaTorneos {
    
    Copa CreaCopa(TorneoDTO torneo);
    Liga creaLiga(TorneoDTO torneo);
    Eliminatoria creaEliminatoria(TorneoDTO torneo);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;
import facade.FachadaTorneos;
import modelo.*;
/**
 *
 * @author jeisson
 */
public class Metodos {
    public static void main(String[] args) throws MiExcepcion{
        FachadaTorneos facadeTorneos = new FachadaTorneos();
        PartidoDTO p = new PartidoDTO();
        p.setRonda(1);
        p.setEquipo1(1);
        p.setEquipo2(2);
        p.setMarcador1(1);
        p.setMarcador2(2);
        p.setIdTorneo(14);
        System.out.println(facadeTorneos.insertarMarcador(p));
        
    }
    
    
}
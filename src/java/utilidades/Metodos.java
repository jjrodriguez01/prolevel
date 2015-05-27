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
        FachadaTorneos facade = new FachadaTorneos();
        TablaPosicionesDTO tab = new TablaPosicionesDTO();
        tab = facade.listarUno(30, 44);
        System.out.println(tab.toString());
    }
    
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;
import facade.FachadaTorneos;
import java.util.ArrayList;
import modelo.*;
/**
 *
 * @author jeisson
 */
public class Metodos {
    public static void main(String[] args) throws MiExcepcion{
        FachadaTorneos facade = new FachadaTorneos();
        ArrayList<PartidoDTO> partidos = new ArrayList();
                    partidos = (ArrayList) facade.partidosUnEquipo(29, 1);
                    for (PartidoDTO p : partidos) {
                        System.out.println(p.getEquipouno().getNombre());
                        System.out.println(p.getMarcador1());
                        System.out.println(p.getMarcador2());
                        System.out.println(p.getEquipodos().getNombre());
        }
    }
    
    
}
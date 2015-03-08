/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;
import java.util.ArrayList;
import modelo.*;
import persistencia.*;
/**
 *
 * @author jeisson
 */
public class main {
    public static void main (String args[]){
        TorneoDAO TDAO = new TorneoDAO();
        ArrayList t = (ArrayList) TDAO.ListaPaginacion(0, 5);
        for (int i = 0; i < t.size(); i++) {
            System.out.println(t.get(i));
        }
    }
}

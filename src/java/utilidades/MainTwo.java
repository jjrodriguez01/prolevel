/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.ArrayList;
import persistencia.*;
import modelo.*;
/**
 *
 * @author jeisson
 */
public class MainTwo {
    public static void main(String[] args){
        ArrayList<String> arr = new ArrayList();
        ArrayList<String> arr2 = new ArrayList();
        StringBuilder s = new StringBuilder("");
        arr.add("hola");
        arr.add("esto");
        arr.add("es una");
        arr2.add("prueba");
        arr.addAll(arr2);
        for(int i= 0; i < arr.size(); i++){
            s.append(arr.get(i));
            
            if (i != arr.size() -1 && arr.size() > 0) {
                s.append(" ,");
            }
        }
        System.out.println(s);
        try{
        EquiposDelTorneoDAO edt = new EquiposDelTorneoDAO();
        ArrayList<String> eq1 = new ArrayList();
        eq1=(ArrayList)edt.correosJugadoresEquipo(1, 1);
        eq1.addAll((ArrayList)edt.correosJugadoresEquipo(1, 2));
        for(String str : eq1){
            System.out.println(str);
        }
        }catch(MiExcepcion mi){
            System.out.println(mi.toString());
        }
    }
}

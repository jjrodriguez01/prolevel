/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import modelo.*;
import persistencia.*;
/**
 *
 * @author jeisson
 */
public class main {
    public static void main (String args[]){
//        UsuariosDAO usu = new UsuariosDAO();
//        String pas="j3216514086";
//        long id = 1016036010;
//        System.out.println(usu.cambiarPass(id, pas));
        
        Map<Integer,String> equipos = new TreeMap<Integer,String>();
        equipos.put(1, "Arsenal");
        equipos.put(2, "Real");
        equipos.put(3, "Barca");
        equipos.put(4, "Chelsea");
        System.out.println(equipos.get(1));
//        Iterator it = equipos.keySet().iterator();
//        while(it.hasNext()){
//            Integer key = (Integer)it.next();
//            System.out.println("clave "+key+"valor"+equipos.get(key));
//        }
//        EquipoDTO e1 = new EquipoDTO();
//        e1.setCodigo(1);
//        e1.setNombre("Arsenal");
//        EquipoDTO e2 = new EquipoDTO();
//        e2.setCodigo(2);
//        e2.setNombre("Real");
//        EquipoDTO e3 = new EquipoDTO();
//        e3.setCodigo(3);
//        e3.setNombre("Barca");
//        EquipoDTO e4 = new EquipoDTO();
//        e4.setCodigo(4);
//        e4.setNombre("Chelsea");
//        ArrayList<EquipoDTO> arr = new ArrayList(); 
//        arr.add(e1);arr.add(e2);arr.add(e3);arr.add(e4);
        
//        enfrentar(arr);
}
    public static void enfrentar(List<EquipoDTO> arr){
        ArrayList<EquipoDTO> arrayeq = (ArrayList)arr;
        Map<Integer,EquipoDTO> equipos = new TreeMap<Integer,EquipoDTO>();
        int clave = 0;
        for(EquipoDTO eq : arrayeq){
            clave++;
            equipos.put(clave, eq);
        }
        Iterator it = equipos.keySet().iterator();
        while(it.hasNext()){
            Integer key = (Integer)it.next();
            System.out.println(equipos.get(key).getNombre()+" vs "+equipos.get(key).getNombre());
        }
    }
}

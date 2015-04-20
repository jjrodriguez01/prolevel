/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import controlador.correo.Correo;
import java.util.ArrayList;
import persistencia.*;
import modelo.*;
/**
 *
 * @author jeisson
 */
public class MainTwo {
    public static void main(String[] args){
       String asunto ="prueba";
       String cuerpo ="esto";
       String to = "jeisson.j_Rodriguez@hotmail.com";
       try{
        if (Correo.sendMail(asunto, cuerpo, to)) {
            System.out.println("si");
        }else{
            System.out.println("no");
        }
       }catch (MiExcepcion mie){
           System.out.println(mie.getMessage());
       }
    }
}

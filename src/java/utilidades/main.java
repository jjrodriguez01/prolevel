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
        UsuariosDAO usu = new UsuariosDAO();
        String pas="jscotamo8@misena.edu.co";
        String r = usu.recuperar(pas);
        System.out.println(r);
        
}
}

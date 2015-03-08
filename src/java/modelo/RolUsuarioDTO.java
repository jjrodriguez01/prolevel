/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Rambo
 */
public class RolUsuarioDTO {
   private int UsuarioIdUsuario;
   private int rolesidRol;

    /**
     * @return the UsuarioIdUsuario
     */
    public int getUsuarioIdUsuario() {
        return UsuarioIdUsuario;
    }

    /**
     * @param UsuarioIdUsuario the UsuarioIdUsuario to set
     */
    public void setUsuarioIdUsuario(int UsuarioIdUsuario) {
        this.UsuarioIdUsuario = UsuarioIdUsuario;
    }

    /**
     * @return the rolesidRol
     */
    public int getRolesidRol() {
        return rolesidRol;
    }

    /**
     * @param rolesidRol the rolesidRol to set
     */
    public void setRolesidRol(int rolesidRol) {
        this.rolesidRol = rolesidRol;
    }

    @Override
    public String toString() {
        return "RolUsuarioDTO \n" 
     + "\n UsuarioIdUsuario=" + UsuarioIdUsuario
     + "\n rolesidRol=" + rolesidRol;
    
    }
    
}

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
public class PartidoDTO {
    private int idTabla;
    private int idPartido;
    private int marcadorEquipo1;
    private int marcadorEquipo2;
    private int numeroCancha;

    /**
     * @return the idTabla
     */
    public int getIdTabla() {
        return idTabla;
    }

    /**
     * @param idTabla the idTabla to set
     */
    public void setIdTabla(int idTabla) {
        this.idTabla = idTabla;
    }

    /**
     * @return the idPartido
     */
    public int getIdPartido() {
        return idPartido;
    }

    /**
     * @param idPartido the idPartido to set
     */
    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    /**
     * @return the marcadorEquipo1
     */
    public int getMarcadorEquipo1() {
        return marcadorEquipo1;
    }

    /**
     * @param marcadorEquipo1 the marcadorEquipo1 to set
     */
    public void setMarcadorEquipo1(int marcadorEquipo1) {
        this.marcadorEquipo1 = marcadorEquipo1;
    }

    /**
     * @return the marcadorEquipo2
     */
    public int getMarcadorEquipo2() {
        return marcadorEquipo2;
    }

    /**
     * @param marcadorEquipo2 the marcadorEquipo2 to set
     */
    public void setMarcadorEquipo2(int marcadorEquipo2) {
        this.marcadorEquipo2 = marcadorEquipo2;
    }

    /**
     * @return the numeroCancha
     */
    public int getNumeroCancha() {
        return numeroCancha;
    }

    /**
     * @param numeroCancha the numeroCancha to set
     */
    public void setNumeroCancha(int numeroCancha) {
        this.numeroCancha = numeroCancha;
    }

    @Override
    public String toString() {
        return "PartidoDTO \n" 
     + "\n idTabla=" + idTabla
     + "\n idPartido=" + idPartido 
     + "\n marcadorEquipo1=" + marcadorEquipo2
     + "\n numeroCancha=" + numeroCancha;
    }
    
}

  
        
    
    


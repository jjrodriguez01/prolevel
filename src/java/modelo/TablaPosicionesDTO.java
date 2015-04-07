/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class TablaPosicionesDTO {

    private int Idtorneo;
    private int Idequipo;
    private int posicion;
    private int puntos;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesAnotados;
    private int golesRecibidos;

    /**
     * @return the Idtorneo
     */
    public int getIdtorneo() {
        return Idtorneo;
    }

    /**
     * @param Idtorneo the Idtorneo to set
     */
    public void setIdtorneo(int Idtorneo) {
        this.Idtorneo = Idtorneo;
    }

    /**
     * @return the Idequipo
     */
    public int getIdequipo() {
        return Idequipo;
    }

    /**
     * @param Idequipo the Idequipo to set
     */
    public void setIdequipo(int Idequipo) {
        this.Idequipo = Idequipo;
    }

    /**
     * @return the posicion
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * @return the partidosJugados
     */
    public int getPartidosJugados() {
        return partidosJugados;
    }

    /**
     * @param partidosJugados the partidosJugados to set
     */
    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    /**
     * @return the partidosGanados
     */
    public int getPartidosGanados() {
        return partidosGanados;
    }

    /**
     * @param partidosGanados the partidosGanados to set
     */
    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    /**
     * @return the partidosEmpatados
     */
    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    /**
     * @param partidosEmpatados the partidosEmpatados to set
     */
    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    /**
     * @return the partidosPerdidos
     */
    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    /**
     * @param partidosPerdidos the partidosPerdidos to set
     */
    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    /**
     * @return the golesAnotados
     */
    public int getGolesAnotados() {
        return golesAnotados;
    }

    /**
     * @param golesAnotados the golesAnotados to set
     */
    public void setGolesAnotados(int golesAnotados) {
        this.golesAnotados = golesAnotados;
    }

    /**
     * @return the golesRecibidos
     */
    public int getGolesRecibidos() {
        return golesRecibidos;
    }

    /**
     * @param golesRecibidos the golesRecividos to set
     */
    public void setGolesRecibidos(int golesRecibidos) {
        this.golesRecibidos = golesRecibidos;
    }

    @Override
    public String toString() {
        return "TablaPosicionesDTO{" + "Idtorneo=" + Idtorneo + ", Idequipo=" + Idequipo + ", posicion=" + posicion + ", puntos=" + puntos + ", partidosJugados=" + partidosJugados + ", partidosGanados=" + partidosGanados + ", partidosEmpatados=" + partidosEmpatados + ", partidosPerdidos=" + partidosPerdidos + ", golesAnotados=" + golesAnotados + ", golesRecibidos=" + golesRecibidos + '}';
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Rambo
 */
public class CalendarioDTO {
  private int codigoCalendario;
  private int equipo1;
  private int equipo2;
  private Date fecha;
  private  Time hora;
  private int idTorneo;
  private String calendariocol;

    /**
     * @return the codigoCalendario
     */
    public int getCodigoCalendario() {
        return codigoCalendario;
    }

    /**
     * @param codigoCalendario the codigoCalendario to set
     */
    public void setCodigoCalendario(int codigoCalendario) {
        this.codigoCalendario = codigoCalendario;
    }

    /**
     * @return the equipo1
     */
    public int getEquipo1() {
        return equipo1;
    }

    /**
     * @param equipo1 the equipo1 to set
     */
    public void setEquipo1(int equipo1) {
        this.equipo1 = equipo1;
    }

    /**
     * @return the equipo2
     */
    public int getEquipo2() {
        return equipo2;
    }

    /**
     * @param equipo2 the equipo2 to set
     */
    public void setEquipo2(int equipo2) {
        this.equipo2 = equipo2;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public Time getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Time hora) {
        this.hora = hora;
    }

    /**
     * @return the idTorneo
     */
    public int getIdTorneo() {
        return idTorneo;
    }

    /**
     * @param idTorneo the idTorneo to set
     */
    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

    /**
     * @return the calendariocol
     */
    public String getCalendariocol() {
        return calendariocol;
    }

    /**
     * @param calendariocol the calendariocol to set
     */
    public void setCalendariocol(String calendariocol) {
        this.calendariocol = calendariocol;
    }

    @Override
    public String toString() {
        return "CalendarioDTO \n" 
     + "\n codigoCalendario=" + codigoCalendario
     + "\n equipo1=" + equipo1
     + "\n equipo2=" + equipo2
     + "\n fecha=" + fecha
     + "\n hora=" + hora
     + "\n idTorneo="+ idTorneo
     +"\n calendariocol"+ calendariocol;
    }

   
        }
    


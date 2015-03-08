package modelo;

import modelo.TorneoDTO;

/**
 *
 * @author jeisson
 */
public class LigaDTO extends TorneoDTO{
    
    private int idLiga;
    private boolean idaVuelta;

    public LigaDTO(int idLiga, boolean idaVuelta, int idTorneo, String nombre, String fechaInicio, String fechaFin, String genero, int capacidadEquipos) {
        super(idTorneo, nombre, fechaInicio, fechaFin, genero, capacidadEquipos);
        this.idLiga = idLiga;
        this.idaVuelta = idaVuelta;
    }

   

    public LigaDTO() {
    }

    
    
    @Override
    public String toString() {
        return "LigaDTO\n" 
                + super.toString()
                + "\nidLiga: " + getIdLiga() 
                + "\nidaVuelta: " + isIdaVuelta();
    }


    public int getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(int idLiga) {
        this.idLiga = idLiga;
    }

    public boolean isIdaVuelta() {
        return idaVuelta;
    }

    public void setIdaVuelta(boolean idaVuelta) {
        this.idaVuelta = idaVuelta;
    }
    
    
}

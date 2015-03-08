package modelo;

import modelo.TorneoDTO;

/**
 *
 * @author jeisson
 */
public class EliminatoriaDTO extends TorneoDTO{
    
    private int idEliminatoria;
    private boolean idaVuelta;

    public EliminatoriaDTO(int idEliminatoria, boolean idaVuelta, int idTorneo, String nombre, String fechaInicio, String fechaFin, String genero, int capacidadEquipos) {
        super(idTorneo, nombre, fechaInicio, fechaFin, genero, capacidadEquipos);
        this.idEliminatoria = idEliminatoria;
        this.idaVuelta = idaVuelta;
    }

    public EliminatoriaDTO() {
    }

    
    
    @Override
    public String toString() {
        return "EliminatoriaDTO\n" 
                + super.toString()
                + "\nidEliminatoria: " + getIdEliminatoria() 
                + "\nidaVuelta: " + isIdaVuelta();
    }

    public int getIdEliminatoria() {
        return idEliminatoria;
    }

    public void setIdEliminatoria(int idEliminatoria) {
        this.idEliminatoria = idEliminatoria;
    }

    public boolean isIdaVuelta() {
        return idaVuelta;
    }

    public void setIdaVuelta(boolean idaVuelta) {
        this.idaVuelta = idaVuelta;
    }
    
    
}

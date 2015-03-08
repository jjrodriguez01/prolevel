package modelo;

import modelo.TorneoDTO;

/**
 *
 * @author jeisson
 */
public class CopaDTO extends TorneoDTO {
    
    private int idCopa;
    private boolean tercerPuesto;
    private int equiposGrupos;
    private boolean octavosCuartosSemifinalFinalIdaVuelta;
    private boolean finalidavuelta;

    public CopaDTO(int idCopa, boolean tercerPuesto, int equiposGrupos, boolean octavosCuartosSemifinalFinalIdaVuelta, boolean finalidavuelta, int idTorneo, String nombre, String fechaInicio, String fechaFin, String genero, int capacidadEquipos) {
        super(idTorneo, nombre, fechaInicio, fechaFin, genero, capacidadEquipos);
        this.idCopa = idCopa;
        this.tercerPuesto = tercerPuesto;
        this.equiposGrupos = equiposGrupos;
        this.octavosCuartosSemifinalFinalIdaVuelta = octavosCuartosSemifinalFinalIdaVuelta;
        this.finalidavuelta = finalidavuelta;
    }

    public CopaDTO() {
    }

    public int getIdCopa() {
        return idCopa;
    }

    public void setIdCopa(int idCopa) {
        this.idCopa = idCopa;
    }

    public boolean isTercerPuesto() {
        return tercerPuesto;
    }

    public void setTercerPuesto(boolean tercerPuesto) {
        this.tercerPuesto = tercerPuesto;
    }

    public boolean isOctavosCuartosSemifinalFinalIdaVuelta() {
        return octavosCuartosSemifinalFinalIdaVuelta;
    }

    public void setOctavosCuartosSemifinalFinalIdaVuelta(boolean octavosCuartosSemifinalFinalIdaVuelta) {
        this.octavosCuartosSemifinalFinalIdaVuelta = octavosCuartosSemifinalFinalIdaVuelta;
    }

    public boolean isFinalidavuelta() {
        return finalidavuelta;
    }

    public void setFinalidavuelta(boolean finalidavuelta) {
        this.finalidavuelta = finalidavuelta;
    }

    public int getEquiposGrupos() {
        return equiposGrupos;
    }

    public void setEquiposGrupos(int equiposGrupos) {
        this.equiposGrupos = equiposGrupos;
    }

    @Override
    public String toString() {
        return "CopaDTO{" 
                + super.toString()
                + "\nidCopa=" + idCopa 
                + "\ntercerPuesto=" + tercerPuesto 
                + "\nequiposGrupos=" + equiposGrupos 
                + "\noctavosCuartosSemifinalFinalIdaVuelta=" + octavosCuartosSemifinalFinalIdaVuelta 
                + "\nfinalidavuelta=" + finalidavuelta + '}';
    }
    
    
}

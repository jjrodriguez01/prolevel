package modelo;

/**
 *
 * @author jeisson
 */
public class TorneoDTO {
    
    private int idTorneo;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private String genero;
    private int capacidadEquipos;

    public TorneoDTO(int idTorneo, String nombre, String fechaInicio, String fechaFin, String genero, int capacidadEquipos) {
        this.idTorneo = idTorneo;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.genero = genero;
        this.capacidadEquipos = capacidadEquipos;
    }

    public TorneoDTO() {
    }
    
    

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getCapacidadEquipos() {
        return capacidadEquipos;
    }

    public void setCapacidadEquipos(int capacidadEquipos) {
        this.capacidadEquipos = capacidadEquipos;
    }

    @Override
    public String toString() {
        return "TorneoDTO" 
                + "\nidTorneo :" + idTorneo 
                + "\nnombre :" + nombre 
                + "\nfechaInicio :" + fechaInicio 
                + "\nfechaFin : " + fechaFin 
                + "\ngenero: " + genero 
                + "\ncapacidadEquipos: " + capacidadEquipos;
    }
    
    
}

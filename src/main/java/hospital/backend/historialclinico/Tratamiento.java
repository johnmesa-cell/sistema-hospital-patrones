/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.historialclinico;

public class Tratamiento {
    private String idTratamiento;
    private String descripcion;
    private int duracionDias;
    private String frecuencia;
    private String fechaInicio;
    private String idDiagnostico;
    private String fechaFin;
    private String estado;

    public Tratamiento() {}
    
    public Tratamiento(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdTratamiento() { return idTratamiento; }
    public void setIdTratamiento(String idTratamiento) { this.idTratamiento = idTratamiento; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public int getDuracionDias() { return duracionDias; }
    public void setDuracionDias(int duracionDias) { this.duracionDias = duracionDias; }
    
    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    @Override
    public String toString() {
        return "Tratamiento: " + descripcion;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setIdDiagnostico(String idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
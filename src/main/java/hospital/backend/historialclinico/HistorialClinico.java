/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.historialclinico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistorialClinico {
    private String idHistorial;
    private String idPaciente;
    private Date fechaCreacion;
    private Date fechaUltimaActualizacion;
    private List<Diagnostico> diagnosticos;
    private List<NotaMedica> notas;
    private List<Tratamiento> tratamientos;

    public HistorialClinico() {
        this.fechaCreacion = new Date();
        this.fechaUltimaActualizacion = new Date();
        this.diagnosticos = new ArrayList<>();
        this.notas = new ArrayList<>();
        this.tratamientos = new ArrayList<>();
    }

    // Getters y Setters
    public String getIdHistorial() { return idHistorial; }
    public void setIdHistorial(String idHistorial) { 
        this.idHistorial = idHistorial; 
        this.fechaUltimaActualizacion = new Date();
    }

    public String getIdPaciente() { return idPaciente; }
    public void setIdPaciente(String idPaciente) { 
        this.idPaciente = idPaciente; 
        this.fechaUltimaActualizacion = new Date();
    }

    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public Date getFechaUltimaActualizacion() { return fechaUltimaActualizacion; }
    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) { 
        this.fechaUltimaActualizacion = fechaUltimaActualizacion; 
    }

    public List<Diagnostico> getDiagnosticos() { return new ArrayList<>(diagnosticos); }
    public void setDiagnosticos(List<Diagnostico> diagnosticos) { 
        this.diagnosticos = diagnosticos != null ? new ArrayList<>(diagnosticos) : new ArrayList<>();
        this.fechaUltimaActualizacion = new Date();
    }

    public List<NotaMedica> getNotas() { return new ArrayList<>(notas); }
    public void setNotas(List<NotaMedica> notas) { 
        this.notas = notas != null ? new ArrayList<>(notas) : new ArrayList<>();
        this.fechaUltimaActualizacion = new Date();
    }

    public List<Tratamiento> getTratamientos() { return new ArrayList<>(tratamientos); }
    public void setTratamientos(List<Tratamiento> tratamientos) { 
        this.tratamientos = tratamientos != null ? new ArrayList<>(tratamientos) : new ArrayList<>();
        this.fechaUltimaActualizacion = new Date();
    }

    // Métodos de negocio
    public void agregarDiagnostico(Diagnostico diagnostico) {
        if (diagnostico != null) {
            this.diagnosticos.add(diagnostico);
            this.fechaUltimaActualizacion = new Date();
        }
    }

    public void agregarNota(NotaMedica nota) {
        if (nota != null) {
            this.notas.add(nota);
            this.fechaUltimaActualizacion = new Date();
        }
    }

    public void agregarTratamiento(Tratamiento tratamiento) {
        if (tratamiento != null) {
            this.tratamientos.add(tratamiento);
            this.fechaUltimaActualizacion = new Date();
        }
    }

    public int getTotalEntradas() {
        return diagnosticos.size() + notas.size() + tratamientos.size();
    }

    @Override
    public String toString() {
        return "HistorialClinico{" +
                "idHistorial='" + idHistorial + '\'' +
                ", idPaciente='" + idPaciente + '\'' +
                ", totalEntradas=" + getTotalEntradas() +
                '}';
    }
}
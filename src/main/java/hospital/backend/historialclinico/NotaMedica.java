/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.historialclinico;

import java.util.Date;

public class NotaMedica {
    private String idNota;
    private String idHistorial;
    private String tipoNota;
    private String contenidoMedico;
    private String contenidoPaciente;
    private boolean visibleParaPaciente;
    private Date fechaCreacion;
    private String idPaciente;
    private String idMedico;

    public NotaMedica() {
        this.fechaCreacion = new Date();
        this.visibleParaPaciente = true;
    }

    public String getIdNota() { return idNota; }
    public void setIdNota(String idNota) { this.idNota = idNota; }
    
    public String getIdHistorial() { return idHistorial; }
    public void setIdHistorial(String idHistorial) { this.idHistorial = idHistorial; }
    
    public String getTipoNota() { return tipoNota; }
    public void setTipoNota(String tipoNota) { this.tipoNota = tipoNota; }
    
    public String getContenidoMedico() { return contenidoMedico; }
    public void setContenidoMedico(String contenidoMedico) { this.contenidoMedico = contenidoMedico; }
    
    public String getContenidoPaciente() { return contenidoPaciente; }
    public void setContenidoPaciente(String contenidoPaciente) { this.contenidoPaciente = contenidoPaciente; }
    
    public boolean isVisibleParaPaciente() { return visibleParaPaciente; }
    public void setVisibleParaPaciente(boolean visibleParaPaciente) { this.visibleParaPaciente = visibleParaPaciente; }
    
    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    @Override
    public String toString() {
        return tipoNota + ": " + (contenidoPaciente != null ? contenidoPaciente : contenidoMedico);
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }
}
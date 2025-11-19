/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.citas;


import java.util.Date;

public class Cita {
    private String idCita;
    private String idPaciente;
    private String idMedico;
    private Date fechaHora;
    private String motivo;
    private String tipoCita; // CONSULTA, URGENCIA, SEGUIMIENTO, etc.
    private String estado; // PROGRAMADA, CONFIRMADA, COMPLETADA, CANCELADA, NO_ASISTIO
    private String consultorio;
    private String notas;
    private Integer duracionMinutos;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public Cita() {
        this.fechaCreacion = new Date();
        this.fechaActualizacion = new Date();
        this.estado = "PROGRAMADA";
        this.duracionMinutos = 30; // Duración por defecto
    }

    public Cita(String idCita, String idPaciente, String idMedico, Date fechaHora, String motivo) {
        this();
        this.idCita = idCita;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
    }

    // Getters y Setters
    public String getIdCita() { return idCita; }
    public void setIdCita(String idCita) { this.idCita = idCita; }

    public String getIdPaciente() { return idPaciente; }
    public void setIdPaciente(String idPaciente) { this.idPaciente = idPaciente; }

    public String getIdMedico() { return idMedico; }

    public void setIdMedico(String idMedico) {

    }

    public Date getFechaHora() { return fechaHora; }
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
        this.fechaActualizacion = new Date();
    }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
        this.fechaActualizacion = new Date();
    }

    public String getTipoCita() { return tipoCita; }
    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
        this.fechaActualizacion = new Date();
    }

    public String getEstado() { return estado; }
    public void setEstado(String estado) {
        this.estado = estado;
        this.fechaActualizacion = new Date();
    }

    public String getConsultorio() { return consultorio; }
    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
        this.fechaActualizacion = new Date();
    }

    public String getNotas() { return notas; }
    public void setNotas(String notas) {
        this.notas = notas;
        this.fechaActualizacion = new Date();
    }

    public Integer getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(Integer duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
        this.fechaActualizacion = new Date();
    }

    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public Date getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(Date fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    // Métodos de negocio
    public boolean isProgramada() {
        return "PROGRAMADA".equals(estado);
    }

    public boolean isCompletada() {
        return "COMPLETADA".equals(estado);
    }

    public boolean isCancelada() {
        return "CANCELADA".equals(estado);
    }

    public boolean isPasada() {
        return fechaHora != null && fechaHora.before(new Date());
    }

    public boolean isHoy() {
        if (fechaHora == null) return false;

        java.util.Calendar hoy = java.util.Calendar.getInstance();
        java.util.Calendar citaCal = java.util.Calendar.getInstance();
        citaCal.setTime(fechaHora);

        return hoy.get(java.util.Calendar.YEAR) == citaCal.get(java.util.Calendar.YEAR) &&
                hoy.get(java.util.Calendar.DAY_OF_YEAR) == citaCal.get(java.util.Calendar.DAY_OF_YEAR);
    }

    // Método para calcular fecha de fin
    public Date getFechaHoraFin() {
        if (fechaHora == null || duracionMinutos == null) return null;

        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(fechaHora);
        cal.add(java.util.Calendar.MINUTE, duracionMinutos);
        return cal.getTime();
    }

    @Override
    public String toString() {
        return "Cita: " + (fechaHora != null ? fechaHora.toString() : "Sin fecha") +
                " - " + motivo + " - " + estado;
    }

    public void setFechaHora(String trim) {
    }
}
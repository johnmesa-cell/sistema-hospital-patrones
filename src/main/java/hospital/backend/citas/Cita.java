/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.citas;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Cita {
    private String idCita;
    private String idPaciente;
    private String idMedico;
    private LocalDateTime fechaHora;       // <-- Cambia a LocalDateTime
    private String motivo;
    private String tipoCita;               // CONSULTA, URGENCIA, SEGUIMIENTO, etc.
    private String estado;                 // PROGRAMADA, CONFIRMADA, COMPLETADA, CANCELADA, NOASISTIO
    private String consultorio;
    private String notas;
    private Integer duracionMinutos;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    // Constructor por defecto
    public Cita() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
        this.estado = "PROGRAMADA";
        this.duracionMinutos = 30; // Duración por defecto
    }

    // Constructor parametrizado
    public Cita(String idCita, String idPaciente, String idMedico, LocalDateTime fechaHora, String motivo) {
        this();
        this.idCita = idCita;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
    }

    // Getters y setters principales
    public String getIdCita() { return idCita; }
    public void setIdCita(String idCita) { this.idCita = idCita; }

    public String getIdPaciente() { return idPaciente; }
    public void setIdPaciente(String idPaciente) { this.idPaciente = idPaciente; }

    public String getIdMedico() { return idMedico; }
    public void setIdMedico(String idMedico) { this.idMedico = idMedico; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    // Compatibilidad con JDBC: también setter/getter con java.util.Date
    public void setFechaHora(Date fecha) {
        if (fecha != null) {
            this.fechaHora = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        } else {
            this.fechaHora = null;
        }
    }
    public Date getFechaHoraAsDate() {
        if (fechaHora != null) {
            return Date.from(fechaHora.atZone(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getTipoCita() { return tipoCita; }
    public void setTipoCita(String tipoCita) { this.tipoCita = tipoCita; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getConsultorio() { return consultorio; }
    public void setConsultorio(String consultorio) { this.consultorio = consultorio; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }

    public Integer getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(Integer min) { this.duracionMinutos = min; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public void setFechaCreacion(Date fecha) {
        if (fecha != null) {
            this.fechaCreacion = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
    }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    public void setFechaActualizacion(Date fecha) {
        if (fecha != null) {
            this.fechaActualizacion = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
    }
}

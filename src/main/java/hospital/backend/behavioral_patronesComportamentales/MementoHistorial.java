/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.behavioral_patronesComportamentales;

import hospital.historialclinico.HistorialClinico;
import java.util.Date;

public class MementoHistorial {
    private String idHistorial;
    private String idPaciente;
    private Date fechaCreacion;
    private Date fechaUltimaActualizacion;

    public MementoHistorial(HistorialClinico estado) {
        this.idHistorial = estado.getIdHistorial();
        this.idPaciente = estado.getIdPaciente();
        this.fechaCreacion = estado.getFechaCreacion();
        this.fechaUltimaActualizacion = estado.getFechaUltimaActualizacion();
    }

    public HistorialClinico getEstado() {
        HistorialClinico historial = new HistorialClinico();
        historial.setIdHistorial(this.idHistorial);
        historial.setIdPaciente(this.idPaciente);
        historial.setFechaCreacion(this.fechaCreacion);
        historial.setFechaUltimaActualizacion(this.fechaUltimaActualizacion);
        return historial;
    }
}

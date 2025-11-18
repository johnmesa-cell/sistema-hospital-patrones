/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.creational_patronesCreacionales;

import hospital.backend.historialclinico.HistorialClinico;
import hospital.backend.historialclinico.Diagnostico;
import hospital.backend.historialclinico.NotaMedica;
import hospital.backend.historialclinico.Tratamiento;

import java.util.ArrayList;
import java.util.List;

public class HistorialBuilder {
    private String idHistorial;
    private String idPaciente;
    private List<Diagnostico> diagnosticos;
    private List<NotaMedica> notas;
    private List<Tratamiento> tratamientos;

    public HistorialBuilder() {
        this.diagnosticos = new ArrayList<>();
        this.notas = new ArrayList<>();
        this.tratamientos = new ArrayList<>();
    }

    public HistorialBuilder setIdHistorial(String idHistorial) {
        this.idHistorial = idHistorial;
        return this;
    }

    public HistorialBuilder setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
        return this;
    }

    public HistorialBuilder agregarDiagnostico(Diagnostico diagnostico) {
        this.diagnosticos.add(diagnostico);
        return this;
    }

    public HistorialBuilder agregarNota(NotaMedica nota) {
        this.notas.add(nota);
        return this;
    }

    public HistorialBuilder agregarTratamiento(Tratamiento tratamiento) {
        this.tratamientos.add(tratamiento);
        return this;
    }

    public HistorialClinico construir() {
        HistorialClinico historial = new HistorialClinico();
        historial.setIdHistorial(this.idHistorial);
        historial.setIdPaciente(this.idPaciente);
        
        if (this.diagnosticos != null) {
            for (Diagnostico diag : this.diagnosticos) {
                historial.agregarDiagnostico(diag);
            }
        }
        
        if (this.notas != null) {
            for (NotaMedica nota : this.notas) {
                historial.agregarNota(nota);
            }
        }
        
        if (this.tratamientos != null) {
            for (Tratamiento trat : this.tratamientos) {
                historial.agregarTratamiento(trat);
            }
        }
        
        return historial;
    }
}

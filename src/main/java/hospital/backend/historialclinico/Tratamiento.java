/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.historialclinico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa un tratamiento médico
 * Admite fechas tanto en formato String (yyyy-MM-dd) como en LocalDate,
 * y las guarda internamente como String para compatibilidad con base de datos SQL.
 */
public class Tratamiento {
    private String idTratamiento;
    private String descripcion;
    private int duracionDias;
    private String frecuencia;

    // Fechas guardadas como String en formato yyyy-MM-dd para compatibilidad SQL
    private String fechaInicio;
    private String fechaFin;

    private String idDiagnostico;   // Relación con Diagnóstico
    private String estado;

    // Formato estándar para fechas tipo SQL
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Tratamiento() {}

    public Tratamiento(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getters y Setters básicos para los campos principales
    public String getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(String idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    // === GESTIÓN DE FECHAS ===

    /**
     * Permite establecer la fecha de inicio desde un String (formato yyyy-MM-dd).
     * Utilizado para escritura directa desde formularios o compatibilidad DAO.
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Permite establecer la fecha de inicio desde un LocalDate.
     * Utilizado al leer datos de la base de datos (por ejemplo, rs.getDate().toLocalDate()).
     * Convierte automáticamente a String para uso interno.
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio != null) {
            this.fechaInicio = fechaInicio.format(FORMATTER);
        }
    }

    /**
     * Devuelve la fecha de inicio en formato String (yyyy-MM-dd).
     * Usado en la DAO para Date.valueOf(fechaInicio).
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Permite establecer la fecha de fin desde un String (formato yyyy-MM-dd).
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Permite establecer la fecha de fin desde un LocalDate.
     * Convierte automáticamente a String para uso interno.
     */
    public void setFechaFin(LocalDate fechaFin) {
        if (fechaFin != null) {
            this.fechaFin = fechaFin.format(FORMATTER);
        }
    }

    /**
     * Devuelve la fecha de fin en formato String (yyyy-MM-dd).
     */
    public String getFechaFin() {
        return fechaFin;
    }

    public String getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(String idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Método para representación rápida
    @Override
    public String toString() {
        return "Tratamiento: " + descripcion;
    }
}

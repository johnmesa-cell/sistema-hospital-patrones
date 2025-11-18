/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.historialclinico;

public class Diagnostico {
    private String idDiagnostico;
    private String descripcion;
    private String codigoCIE10;
    private String descripcionTecnica;
    private String explicacionPaciente;
    private String estado;

    public Diagnostico() {}
    
    public Diagnostico(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public String getIdDiagnostico() { return idDiagnostico; }
    public void setIdDiagnostico(String idDiagnostico) { this.idDiagnostico = idDiagnostico; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public String getCodigoCIE10() { return codigoCIE10; }
    public void setCodigoCIE10(String codigoCIE10) { this.codigoCIE10 = codigoCIE10; }
    
    public String getDescripcionTecnica() { return descripcionTecnica; }
    public void setDescripcionTecnica(String descripcionTecnica) { this.descripcionTecnica = descripcionTecnica; }
    
    public String getExplicacionPaciente() { return explicacionPaciente; }
    public void setExplicacionPaciente(String explicacionPaciente) { this.explicacionPaciente = explicacionPaciente; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Diagnóstico: " + descripcion;
    }
}

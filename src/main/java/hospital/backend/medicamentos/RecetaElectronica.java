/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.medicamentos;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class RecetaElectronica {
    private String idReceta;
    private String idMedico;
    private String idPaciente;
    private Date fechaEmision;
    private Date fechaVencimiento;
    private String firmaDigital;
    private String codigoVerificacion;
    private String estado; // ACTIVA, UTILIZADA, EXPIRADA, CANCELADA
    private String instrucciones;
    private List<MedicamentoRecetado> medicamentos;

    public RecetaElectronica() {
        this.fechaEmision = new Date();
        this.estado = "ACTIVA";
        this.medicamentos = new ArrayList<>();

        // Calcular fecha de vencimiento (30 días por defecto)
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(fechaEmision);
        cal.add(java.util.Calendar.DAY_OF_MONTH, 30);
        this.fechaVencimiento = cal.getTime();

        // Generar código de verificación único
        this.codigoVerificacion = "REC" + System.currentTimeMillis();
    }

    public RecetaElectronica(String idReceta, String idMedico, String idPaciente, String instrucciones) {
        this();
        this.idReceta = idReceta;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.instrucciones = instrucciones;
    }

    // Getters y Setters
    public String getIdReceta() { return idReceta; }
    public void setIdReceta(String idReceta) { this.idReceta = idReceta; }

    public String getIdMedico() { return idMedico; }
    public void setIdMedico(String idMedico) { this.idMedico = idMedico; }

    public String getIdPaciente() { return idPaciente; }
    public void setIdPaciente(String idPaciente) { this.idPaciente = idPaciente; }

    public Date getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(Date fechaEmision) { this.fechaEmision = fechaEmision; }

    public Date getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(Date fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public String getFirmaDigital() { return firmaDigital; }
    public void setFirmaDigital(String firmaDigital) { this.firmaDigital = firmaDigital; }

    public String getCodigoVerificacion() { return codigoVerificacion; }
    public void setCodigoVerificacion(String codigoVerificacion) { this.codigoVerificacion = codigoVerificacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getInstrucciones() { return instrucciones; }
    public void setInstrucciones(String instrucciones) { this.instrucciones = instrucciones; }

    public List<MedicamentoRecetado> getMedicamentos() { return medicamentos; }
    public void setMedicamentos(List<MedicamentoRecetado> medicamentos) { this.medicamentos = medicamentos; }

    // Métodos para gestionar medicamentos
    public void agregarMedicamento(MedicamentoRecetado medicamento) {
        this.medicamentos.add(medicamento);
    }

    public void removerMedicamento(MedicamentoRecetado medicamento) {
        this.medicamentos.remove(medicamento);
    }

    // Método para calcular total de la receta
    public double calcularTotal() {
        return medicamentos.stream()
                .mapToDouble(MedicamentoRecetado::getSubtotal)
                .sum();
    }

    // Método para verificar si la receta está expirada
    public boolean isExpirada() {
        return new Date().after(fechaVencimiento);
    }

    // Método para verificar si la receta puede ser utilizada
    public boolean isUtilizable() {
        return "ACTIVA".equals(estado) && !isExpirada();
    }

    @Override
    public String toString() {
        return "Receta #" + idReceta + " - " + estado + " - Total: $" + calcularTotal();
    }

    // Clase interna para medicamentos recetados
    public static class MedicamentoRecetado {
        private Medicamento medicamento;
        private int cantidad;
        private String dosis;
        private String frecuencia;
        private String duracion;
        private String instruccionesEspeciales;

        public MedicamentoRecetado() {}

        public MedicamentoRecetado(Medicamento medicamento, int cantidad, String dosis,
                                   String frecuencia, String duracion) {
            this.medicamento = medicamento;
            this.cantidad = cantidad;
            this.dosis = dosis;
            this.frecuencia = frecuencia;
            this.duracion = duracion;
        }

        // Getters y Setters
        public Medicamento getMedicamento() { return medicamento; }
        public void setMedicamento(Medicamento medicamento) { this.medicamento = medicamento; }

        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }

        public String getDosis() { return dosis; }
        public void setDosis(String dosis) { this.dosis = dosis; }

        public String getFrecuencia() { return frecuencia; }
        public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

        public String getDuracion() { return duracion; }
        public void setDuracion(String duracion) { this.duracion = duracion; }

        public String getInstruccionesEspeciales() { return instruccionesEspeciales; }
        public void setInstruccionesEspeciales(String instruccionesEspeciales) {
            this.instruccionesEspeciales = instruccionesEspeciales;
        }

        // Método para calcular subtotal
        public double getSubtotal() {
            return medicamento != null ? medicamento.getPrecio() * cantidad : 0.0;
        }

        @Override
        public String toString() {
            return medicamento != null ?
                    medicamento.getNombre() + " - " + dosis + " - " + cantidad + " unidades" :
                    "Medicamento no especificado";
        }
    }
}
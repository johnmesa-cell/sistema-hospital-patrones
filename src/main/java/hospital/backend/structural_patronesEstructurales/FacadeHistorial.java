/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.structural_patronesEstructurales;

import hospital.backend.auditoria.AuditoriaSingleton;

public class FacadeHistorial {
    private AuditoriaSingleton auditoria;

    public FacadeHistorial() {
        this.auditoria = AuditoriaSingleton.getInstance();
    }

    public void consultarHistorial(String pacienteID) {
        auditoria.registrarAccion("Consulta historial paciente: " + pacienteID);
        System.out.println("📊 Consultando historial del paciente: " + pacienteID);
    }

    public void actualizarHistorial(String pacienteID, IEntradaHistorial entrada) {
        auditoria.registrarAccion("Actualizar historial paciente: " + pacienteID);
        System.out.println("✏️ Actualizando historial con entrada: " + entrada.getTipo());
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.structural_patronesEstructurales;

import hospital.backend.auditoria.AuditoriaSingleton;
import hospital.backend.historialclinico.HistorialClinico;
import hospital.backend.historialclinico.Diagnostico;
import hospital.backend.historialclinico.Tratamiento;
import hospital.backend.servicios.HistorialClinicoDAO;
import hospital.backend.servicios.HistorialClinicoDAOImpl;
import hospital.backend.servicios.DiagnosticoDAO;
import hospital.backend.servicios.DiagnosticoDAOImpl;
import hospital.backend.servicios.TratamientoDAO;
import hospital.backend.servicios.TratamientoDAOImpl;
import java.util.ArrayList;
import java.util.List;

public class FacadeHistorial {
    private AuditoriaSingleton auditoria;

    public FacadeHistorial() {
        this.auditoria = AuditoriaSingleton.getInstance();
    }

    public HistorialClinico consultarHistorial(String pacienteID) {
        auditoria.registrarAccion("Consulta historial paciente: " + pacienteID);

        HistorialClinicoDAO historialDAO = new HistorialClinicoDAOImpl();
        HistorialClinico historial = historialDAO.buscarPorPaciente(pacienteID);

        if (historial != null) {
            System.out.println("📊 Historial encontrado - Total entradas: " + historial.getTotalEntradas());
        } else {
            System.out.println("⚠️ No se encontró historial para el paciente: " + pacienteID);
        }

        return historial;
    }

    public void actualizarHistorial(String pacienteID, IEntradaHistorial entrada) {
        auditoria.registrarAccion("Actualizar historial paciente: " + pacienteID);
        System.out.println("✏️ Actualizando historial con entrada: " + entrada.getTipo());
    }
}

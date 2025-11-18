/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.auditoria;

public class AuditoriaSingleton {
    private static AuditoriaSingleton instance;

    private AuditoriaSingleton() {}

    public static AuditoriaSingleton getInstance() {
        if (instance == null) {
            instance = new AuditoriaSingleton();
        }
        return instance;
    }

    public void registrarAccion(String accion) {
        System.out.println("📋 Auditoría: " + accion);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.behavioral_patronesComportamentales;

public class SistemaNotificacion implements Observador {
    
    @Override
    public void actualizar(String mensaje) {
        System.out.println("🔔 Sistema de notificación: " + mensaje);
    }
}
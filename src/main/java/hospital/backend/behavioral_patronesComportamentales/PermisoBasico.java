/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.behavioral_patronesComportamentales;


public class PermisoBasico extends ManejadorPermiso {
    
    @Override
    public boolean validarAcceso(String usuario, String accion) {
        if (accion.equals("CONSULTAR_CITA") || accion.equals("VER_PERFIL")) {
            System.out.println("✅ Permiso básico concedido para: " + accion);
            return true;
        }
        
        if (siguiente != null) {
            return siguiente.validarAcceso(usuario, accion);
        }
        
        return false;
    }
}
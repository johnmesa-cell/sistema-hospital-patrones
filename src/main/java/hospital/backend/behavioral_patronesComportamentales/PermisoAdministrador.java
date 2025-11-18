/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.behavioral_patronesComportamentales;


public class PermisoAdministrador extends ManejadorPermiso {
    
    @Override
    public boolean validarAcceso(String usuario, String accion) {
        if (accion.equals("GESTIONAR_USUARIOS") || accion.equals("GENERAR_REPORTES") || 
            accion.equals("CONFIGURAR_SISTEMA")) {
            System.out.println("🔧 Permiso de administrador concedido para: " + accion);
            return true;
        }
        
        if (siguiente != null) {
            return siguiente.validarAcceso(usuario, accion);
        }
        
        return false;
    }
}
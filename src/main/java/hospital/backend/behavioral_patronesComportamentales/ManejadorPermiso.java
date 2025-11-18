/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.behavioral_patronesComportamentales;

public abstract class ManejadorPermiso {
    protected ManejadorPermiso siguiente;

    public void setSiguiente(ManejadorPermiso m) {
        this.siguiente = m;
    }

    public abstract boolean validarAcceso(String usuario, String accion);
}
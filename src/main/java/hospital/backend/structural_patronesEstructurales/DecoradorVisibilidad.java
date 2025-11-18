/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.structural_patronesEstructurales;

public class DecoradorVisibilidad implements IEntradaHistorial {
    private IEntradaHistorial entrada;
    private boolean visibleParaPaciente;

    public DecoradorVisibilidad(IEntradaHistorial entrada, boolean visibleParaPaciente) {
        this.entrada = entrada;
        this.visibleParaPaciente = visibleParaPaciente;
    }

    @Override
    public void mostrarDetalles() {
        if (visibleParaPaciente) {
            entrada.mostrarDetalles();
        } else {
            System.out.println("🚫 Información restringida - Solo visible para personal médico");
        }
    }

    @Override
    public String getId() {
        return entrada.getId();
    }

    @Override
    public String getTipo() {
        return entrada.getTipo() + " (Decorado)";
    }

    public boolean isVisibleParaPaciente() {
        return visibleParaPaciente;
    }

    public void setVisibleParaPaciente(boolean visibleParaPaciente) {
        this.visibleParaPaciente = visibleParaPaciente;
    }
}

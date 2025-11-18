/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.behavioral_patronesComportamentales;

import java.util.Stack;

public class CareTaker {
    private Stack<MementoHistorial> historialVersiones;

    public CareTaker() {
        this.historialVersiones = new Stack<>();
    }

    public void guardarMemento(MementoHistorial memento) {
        historialVersiones.push(memento);
    }

    public MementoHistorial restaurarUltimaVersion() {
        if (!historialVersiones.isEmpty()) {
            return historialVersiones.pop();
        }
        return null;
    }

    public boolean hayVersionesAnteriores() {
        return !historialVersiones.isEmpty();
    }

    public int getTotalVersiones() {
        return historialVersiones.size();
    }
}
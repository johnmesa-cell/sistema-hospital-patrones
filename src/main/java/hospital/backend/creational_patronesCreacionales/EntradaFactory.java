/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.creational_patronesCreacionales;

import hospital.backend.structural_patronesEstructurales.IEntradaHistorial;

public class EntradaFactory {
    
    public static IEntradaHistorial crearEntrada(String tipo, String contenido) {
        switch (tipo.toUpperCase()) {
            case "DIAGNOSTICO":
                return new DiagnosticoEntrada(contenido);
            case "NOTA_MEDICA":
                return new NotaMedicaEntrada(contenido);
            case "TRATAMIENTO":
                return new TratamientoEntrada(contenido);
            default:
                throw new IllegalArgumentException("Tipo de entrada no válido: " + tipo);
        }
    }

    // Implementaciones concretas
    private static class DiagnosticoEntrada implements IEntradaHistorial {
        private String id;
        private String contenido;

        public DiagnosticoEntrada(String contenido) {
            this.id = "DIAG_" + System.currentTimeMillis();
            this.contenido = contenido;
        }

        @Override
        public void mostrarDetalles() {
            System.out.println("🩺 Diagnóstico: " + contenido);
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getTipo() {
            return "DIAGNOSTICO";
        }
    }

    private static class NotaMedicaEntrada implements IEntradaHistorial {
        private String id;
        private String contenido;

        public NotaMedicaEntrada(String contenido) {
            this.id = "NOTA_" + System.currentTimeMillis();
            this.contenido = contenido;
        }

        @Override
        public void mostrarDetalles() {
            System.out.println("📝 Nota Médica: " + contenido);
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getTipo() {
            return "NOTA_MEDICA";
        }
    }

    private static class TratamientoEntrada implements IEntradaHistorial {
        private String id;
        private String contenido;

        public TratamientoEntrada(String contenido) {
            this.id = "TRAT_" + System.currentTimeMillis();
            this.contenido = contenido;
        }

        @Override
        public void mostrarDetalles() {
            System.out.println("💊 Tratamiento: " + contenido);
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getTipo() {
            return "TRATAMIENTO";
        }
    }
}

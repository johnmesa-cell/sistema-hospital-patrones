/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.medicamentos;


public class Medicamento {
    private String idMedicamento;
    private String nombre;
    private String descripcion;
    private String principioActivo;
    private String laboratorio;
    private String presentacion;
    private int stockActual;
    private int stockMinimo;
    private double precio;

    public Medicamento() {}

    public Medicamento(String idMedicamento, String nombre, String descripcion,
                       String principioActivo, String laboratorio, String presentacion,
                       int stockActual, int stockMinimo, double precio) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.principioActivo = principioActivo;
        this.laboratorio = laboratorio;
        this.presentacion = presentacion;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.precio = precio;
    }

    // Getters y Setters
    public String getIdMedicamento() { return idMedicamento; }
    public void setIdMedicamento(String idMedicamento) { this.idMedicamento = idMedicamento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getPrincipioActivo() { return principioActivo; }
    public void setPrincipioActivo(String principioActivo) { this.principioActivo = principioActivo; }

    public String getLaboratorio() { return laboratorio; }
    public void setLaboratorio(String laboratorio) { this.laboratorio = laboratorio; }

    public String getPresentacion() { return presentacion; }
    public void setPresentacion(String presentacion) { this.presentacion = presentacion; }

    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }

    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    // Método para verificar si hay stock bajo
    public boolean isStockBajo() {
        return stockActual <= stockMinimo;
    }

    // Método para actualizar stock
    public void actualizarStock(int cantidad) {
        this.stockActual += cantidad;
        if (this.stockActual < 0) {
            this.stockActual = 0;
        }
    }

    @Override
    public String toString() {
        return nombre + " - " + presentacion + " (Stock: " + stockActual + ")";
    }
}
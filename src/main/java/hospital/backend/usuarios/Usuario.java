/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.usuarios;


public class Usuario {
    private String idUsuario;
    private String nombre;
    private String email;
    private String tipo;
    private String contraseña;
    
    public Usuario() {}
    
    public Usuario(String idUsuario, String nombre, String email, String tipo, String contraseña) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.tipo = tipo;
        this.contraseña = contraseña;
    }
    
    // Getters y Setters
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    
    @Override
    public String toString() {
        return nombre + " (" + tipo + ")";
    }
}

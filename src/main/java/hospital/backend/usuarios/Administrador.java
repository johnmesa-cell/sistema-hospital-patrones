/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.usuarios;

public class Administrador {
    private String idAdministrador;
    private String idUsuario;
    private Usuario usuario;

    public Administrador() {}
    
    public Administrador(String idAdministrador, String idUsuario) {
        this.idAdministrador = idAdministrador;
        this.idUsuario = idUsuario;
    }

    public String getIdAdministrador() { return idAdministrador; }
    public void setIdAdministrador(String idAdministrador) { this.idAdministrador = idAdministrador; }
    
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    @Override
    public String toString() {
        return usuario != null ? usuario.getNombre() + " (Administrador)" : "Administrador " + idAdministrador;
    }
}

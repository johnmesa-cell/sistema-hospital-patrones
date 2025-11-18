/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.usuarios;


public class Medico {
    private String idMedico;
    private String idUsuario;
    private String especialidad;
    private Usuario usuario;

    public Medico() {}
    
    public Medico(String idMedico, String idUsuario, String especialidad) {
        this.idMedico = idMedico;
        this.idUsuario = idUsuario;
        this.especialidad = especialidad;
    }

    public String getIdMedico() { return idMedico; }
    public void setIdMedico(String idMedico) { this.idMedico = idMedico; }
    
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    @Override
    public String toString() {
        return usuario != null ? usuario.getNombre() + " (" + especialidad + ")" : "Médico " + idMedico;
    }
}
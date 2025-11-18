/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.backend.usuarios;

import java.util.Date;

public class Paciente {
    private String idPaciente;
    private String idUsuario;
    private Date fechaNacimiento;
    private String sexo;
    private Usuario usuario;

    public Paciente() {}
    
    public Paciente(String idPaciente, String idUsuario, Date fechaNacimiento, String sexo) {
        this.idPaciente = idPaciente;
        this.idUsuario = idUsuario;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }

    public String getIdPaciente() { return idPaciente; }
    public void setIdPaciente(String idPaciente) { this.idPaciente = idPaciente; }
    
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    
    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    @Override
    public String toString() {
        return usuario != null ? usuario.getNombre() : "Paciente " + idPaciente;
    }
}

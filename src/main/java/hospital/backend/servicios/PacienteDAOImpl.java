package hospital.backend.servicios;

import hospital.backend.usuarios.Paciente;

import java.sql.Date;
import java.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOImpl implements PacienteDAO {

    @Override
    public void agregarPaciente(Paciente paciente) {
        String sql = "INSERT INTO paciente (id_paciente, id_usuario, fecha_nacimiento) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, paciente.getIdPaciente());
            ps.setString(2, paciente.getIdUsuario());
            ps.setDate(3, Date.valueOf(paciente.getFechaNacimiento()));

            ps.executeUpdate();
            System.out.println("Paciente agregado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al agregar paciente: " + e.getMessage());
        }
    }

    @Override
    public void actualizarPaciente(Paciente paciente) {
        String sql = "UPDATE paciente SET id_usuario = ?, fecha_nacimiento = ? WHERE id_paciente = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, paciente.getIdUsuario());
            ps.setDate(2, Date.valueOf(paciente.getFechaNacimiento()));
            ps.setString(3, paciente.getIdPaciente());

            ps.executeUpdate();
            System.out.println("Paciente actualizado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al actualizar paciente: " + e.getMessage());
        }
    }

    @Override
    public void eliminarPaciente(String id) {
        String sql = "DELETE FROM paciente WHERE id_paciente = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Paciente eliminado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al eliminar paciente: " + e.getMessage());
        }
    }

    @Override
    public Paciente buscarPorId(String id) {
        String sql = "SELECT * FROM paciente WHERE id_paciente = ?";
        Paciente paciente = null;

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                paciente = new Paciente();
                paciente.setIdPaciente(rs.getString("id_paciente"));
                paciente.setIdUsuario(rs.getString("id_usuario"));
                paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar paciente: " + e.getMessage());
        }
        return paciente;
    }

    @Override
    public List<Paciente> listarPacientes() {
        String sql = "SELECT * FROM paciente";
        List<Paciente> lista = new ArrayList<>();

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setIdPaciente(rs.getString("id_paciente"));
                p.setIdUsuario(rs.getString("id_usuario"));
                p.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar pacientes: " + e.getMessage());
        }
        return lista;
    }
}




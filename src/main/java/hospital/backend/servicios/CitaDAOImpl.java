package hospital.backend.servicios;

import hospital.backend.citas.Cita;
import java.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAOImpl implements CitaDAO {

    @Override
    public void agregarCita(Cita c) {
        String sql = "INSERT INTO cita (id_cita, id_paciente, fecha, medico, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getIdCita());
            ps.setString(2, c.getIdPaciente());
            ps.setTimestamp(3, Timestamp.valueOf(c.getFechaHora())); // LocalDateTime a Timestamp
            ps.setString(4, c.getIdMedico());
            ps.setString(5, c.getEstado());
            ps.executeUpdate();
            System.out.println("Cita agregada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al agregar cita: " + e.getMessage());
        }
    }

    @Override
    public void actualizarCita(Cita c) {
        String sql = "UPDATE cita SET id_paciente = ?, fecha = ?, medico = ?, estado = ? WHERE id_cita = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getIdPaciente());
            ps.setTimestamp(2, Timestamp.valueOf(c.getFechaHora()));
            ps.setString(3, c.getIdMedico());
            ps.setString(4, c.getEstado());
            ps.setString(5, c.getIdCita());
            ps.executeUpdate();
            System.out.println("Cita actualizada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar cita: " + e.getMessage());
        }
    }

    @Override
    public void eliminarCita(String id) {
        String sql = "DELETE FROM cita WHERE id_cita = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Cita eliminada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar cita: " + e.getMessage());
        }
    }

    @Override
    public Cita buscarPorId(String id) {
        String sql = "SELECT * FROM cita WHERE id_cita = ?";
        Cita c = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Cita();
                c.setIdCita(rs.getString("id_cita"));
                c.setIdPaciente(rs.getString("id_paciente"));
                c.setIdMedico(rs.getString("medico"));
                // Convierte Timestamp (BD) en LocalDateTime (modelo)
                Timestamp ts = rs.getTimestamp("fecha");
                if (ts != null) {
                    c.setFechaHora(ts.toLocalDateTime());
                }
                c.setEstado(rs.getString("estado"));
                // agregar más setters si tienes más campos en la tabla/modelo
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cita: " + e.getMessage());
        }
        return c;
    }

    @Override
    public List<Cita> listarCitasPorPaciente(String pacienteId) {
        String sql = "SELECT * FROM cita WHERE id_paciente = ?";
        List<Cita> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pacienteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cita c = new Cita();
                c.setIdCita(rs.getString("id_cita"));
                c.setIdPaciente(rs.getString("id_paciente"));
                c.setIdMedico(rs.getString("medico"));
                Timestamp ts = rs.getTimestamp("fecha");
                if (ts != null) {
                    c.setFechaHora(ts.toLocalDateTime());
                }
                c.setEstado(rs.getString("estado"));
                // agregar más setters si tienes más columnas
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar citas: " + e.getMessage());
        }
        return lista;
    }
}




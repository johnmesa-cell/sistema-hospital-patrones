package hospital.backend.servicios;

import hospital.backend.historialclinico.NotaMedica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaMedicaDAOImpl implements NotaMedicaDAO {

    @Override
    public void agregarNota(NotaMedica nota) {
        String sql = "INSERT INTO nota_medica " +
                "(id_nota, id_historial, id_paciente, id_medico, tipo_nota, contenido_medico, contenido_paciente, visible_para_paciente, fecha) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nota.getIdNota());
            ps.setString(2, nota.getIdHistorial());
            ps.setString(3, nota.getIdPaciente());
            ps.setString(4, nota.getIdMedico());
            ps.setString(5, nota.getTipoNota());
            ps.setString(6, nota.getContenidoMedico());
            ps.setString(7, nota.getContenidoPaciente());
            ps.setBoolean(8, nota.isVisibleParaPaciente());
            ps.setTimestamp(9, new Timestamp(nota.getFechaCreacion().getTime()));
            ps.executeUpdate();
            System.out.println("Nota médica agregada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al agregar nota médica: " + e.getMessage());
        }
    }

    @Override
    public void actualizarNota(NotaMedica nota) {
        String sql = "UPDATE nota_medica SET id_historial = ?, id_paciente = ?, id_medico = ?, tipo_nota = ?, contenido_medico = ?, contenido_paciente = ?, visible_para_paciente = ?, fecha = ? WHERE id_nota = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nota.getIdHistorial());
            ps.setString(2, nota.getIdPaciente());
            ps.setString(3, nota.getIdMedico());
            ps.setString(4, nota.getTipoNota());
            ps.setString(5, nota.getContenidoMedico());
            ps.setString(6, nota.getContenidoPaciente());
            ps.setBoolean(7, nota.isVisibleParaPaciente());
            ps.setTimestamp(8, new Timestamp(nota.getFechaCreacion().getTime()));
            ps.setString(9, nota.getIdNota());
            ps.executeUpdate();
            System.out.println("Nota médica actualizada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar nota médica: " + e.getMessage());
        }
    }

    @Override
    public void eliminarNota(String idNota) {
        String sql = "DELETE FROM nota_medica WHERE id_nota = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idNota);
            ps.executeUpdate();
            System.out.println("Nota médica eliminada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar nota médica: " + e.getMessage());
        }
    }

    @Override
    public NotaMedica buscarPorId(String idNota) {
        String sql = "SELECT * FROM nota_medica WHERE id_nota = ?";
        NotaMedica nota = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idNota);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nota = mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar nota médica: " + e.getMessage());
        }
        return nota;
    }

    @Override
    public List<NotaMedica> listarNotasPorHistorial(String idHistorial) {
        String sql = "SELECT * FROM nota_medica WHERE id_historial = ?";
        List<NotaMedica> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idHistorial);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar notas médicas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<NotaMedica> listarNotasPorPaciente(String idPaciente) {
        String sql = "SELECT * FROM nota_medica WHERE id_paciente = ?";
        List<NotaMedica> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idPaciente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar notas médicas: " + e.getMessage());
        }
        return lista;
    }

    private NotaMedica mapRow(ResultSet rs) throws SQLException {
        NotaMedica nota = new NotaMedica();
        nota.setIdNota(rs.getString("id_nota"));
        nota.setIdHistorial(rs.getString("id_historial"));
        nota.setIdPaciente(rs.getString("id_paciente"));
        nota.setIdMedico(rs.getString("id_medico"));
        nota.setTipoNota(rs.getString("tipo_nota"));
        nota.setContenidoMedico(rs.getString("contenido_medico"));
        nota.setContenidoPaciente(rs.getString("contenido_paciente"));
        nota.setVisibleParaPaciente(rs.getBoolean("visible_para_paciente"));
        nota.setFechaCreacion(rs.getTimestamp("fecha"));
        return nota;
    }
}


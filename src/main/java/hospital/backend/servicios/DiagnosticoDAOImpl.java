package hospital.backend.servicios;

import hospital.backend.historialclinico.Diagnostico;
import java.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticoDAOImpl implements DiagnosticoDAO {

    @Override
    public void agregarDiagnostico(Diagnostico d) {
        String sql = "INSERT INTO diagnostico " +
                "(id_diagnostico, id_paciente, descripcion, codigo_cie10, " +
                " descripcion_tecnica, explicacion_paciente, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getIdDiagnostico());
            ps.setString(2, d.getPacienteId());
            ps.setString(3, d.getDescripcion());
            ps.setString(4, d.getCodigoCIE10());
            ps.setString(5, d.getDescripcionTecnica());
            ps.setString(6, d.getExplicacionPaciente());
            ps.setString(7, d.getEstado());

            ps.executeUpdate();
            System.out.println("Diagnóstico agregado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al agregar diagnóstico: " + e.getMessage());
        }
    }

    @Override
    public void actualizarDiagnostico(Diagnostico d) {
        String sql = "UPDATE diagnostico SET " +
                "id_paciente = ?, descripcion = ?, codigo_cie10 = ?, " +
                "descripcion_tecnica = ?, explicacion_paciente = ?, estado = ? " +
                "WHERE id_diagnostico = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getPacienteId());
            ps.setString(2, d.getDescripcion());
            ps.setString(3, d.getCodigoCIE10());
            ps.setString(4, d.getDescripcionTecnica());
            ps.setString(5, d.getExplicacionPaciente());
            ps.setString(6, d.getEstado());
            ps.setString(7, d.getIdDiagnostico());

            ps.executeUpdate();
            System.out.println("Diagnóstico actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar diagnóstico: " + e.getMessage());
        }
    }

    @Override
    public void eliminarDiagnostico(String id) {
        String sql = "DELETE FROM diagnostico WHERE id_diagnostico = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Diagnóstico eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar diagnóstico: " + e.getMessage());
        }
    }

    @Override
    public Diagnostico buscarPorId(String id) {
        String sql = "SELECT * FROM diagnostico WHERE id_diagnostico = ?";
        Diagnostico d = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                d = mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar diagnóstico: " + e.getMessage());
        }
        return d;
    }

    @Override
    public List<Diagnostico> listarDiagnosticosPorPaciente(String pacienteId) {
        String sql = "SELECT * FROM diagnostico WHERE id_paciente = ?";
        List<Diagnostico> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pacienteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar diagnósticos: " + e.getMessage());
        }
        return lista;
    }

    private Diagnostico mapRow(ResultSet rs) throws SQLException {
        Diagnostico d = new Diagnostico();
        d.setIdDiagnostico(rs.getString("id_diagnostico"));
        d.setPacienteId(rs.getString("id_paciente"));
        d.setDescripcion(rs.getString("descripcion"));
        d.setCodigoCIE10(rs.getString("codigo_cie10"));
        d.setDescripcionTecnica(rs.getString("descripcion_tecnica"));
        d.setExplicacionPaciente(rs.getString("explicacion_paciente"));
        d.setEstado(rs.getString("estado"));
        return d;
    }
}




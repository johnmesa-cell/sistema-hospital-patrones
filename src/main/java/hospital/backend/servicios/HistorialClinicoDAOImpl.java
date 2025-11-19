package hospital.backend.servicios;

import hospital.backend.historialclinico.HistorialClinico;
import hospital.backend.historialclinico.Diagnostico;
import hospital.backend.historialclinico.Tratamiento;
import hospital.backend.historialclinico.NotaMedica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialClinicoDAOImpl implements HistorialClinicoDAO {

    @Override
    public void agregarHistorialClinico(HistorialClinico historial) {
        String sql = "INSERT INTO historial_clinico " +
                "(id_historial, id_paciente, fecha_creacion, fecha_ultima_actualizacion) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, historial.getIdHistorial());
            ps.setString(2, historial.getIdPaciente());
            ps.setTimestamp(3, new Timestamp(historial.getFechaCreacion().getTime()));
            ps.setTimestamp(4, new Timestamp(historial.getFechaUltimaActualizacion().getTime()));

            ps.executeUpdate();
            System.out.println("Historial clínico agregado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al agregar historial clínico: " + e.getMessage());
        }
    }

    @Override
    public void actualizarHistorialClinico(HistorialClinico historial) {
        String sql = "UPDATE historial_clinico SET " +
                "id_paciente = ?, fecha_ultima_actualizacion = ? " +
                "WHERE id_historial = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, historial.getIdPaciente());
            ps.setTimestamp(2, new Timestamp(historial.getFechaUltimaActualizacion().getTime()));
            ps.setString(3, historial.getIdHistorial());

            ps.executeUpdate();
            System.out.println("Historial clínico actualizado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al actualizar historial clínico: " + e.getMessage());
        }
    }

    @Override
    public void eliminarHistorialClinico(String id) {
        String sql = "DELETE FROM historial_clinico WHERE id_historial = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Historial clínico eliminado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al eliminar historial clínico: " + e.getMessage());
        }
    }

    @Override
    public HistorialClinico buscarPorId(String id) {
        String sql = "SELECT * FROM historial_clinico WHERE id_historial = ?";
        HistorialClinico historial = null;

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                historial = mapRow(rs);
                cargarDatosCompletos(historial);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar historial clínico: " + e.getMessage());
        }

        return historial;
    }

    @Override
    public HistorialClinico buscarPorPaciente(String pacienteId) {
        String sql = "SELECT * FROM historial_clinico WHERE id_paciente = ?";
        HistorialClinico historial = null;

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pacienteId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                historial = mapRow(rs);
                cargarDatosCompletos(historial);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar historial clínico por paciente: " + e.getMessage());
        }

        return historial;
    }

    @Override
    public List<HistorialClinico> listarTodos() {
        String sql = "SELECT * FROM historial_clinico";
        List<HistorialClinico> lista = new ArrayList<>();

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HistorialClinico historial = mapRow(rs);
                cargarDatosCompletos(historial);
                lista.add(historial);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar historiales clínicos: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Mapea un registro de la base de datos a un objeto HistorialClinico
     */
    private HistorialClinico mapRow(ResultSet rs) throws SQLException {
        HistorialClinico historial = new HistorialClinico();
        historial.setIdHistorial(rs.getString("id_historial"));
        historial.setIdPaciente(rs.getString("id_paciente"));
        historial.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
        historial.setFechaUltimaActualizacion(rs.getTimestamp("fecha_ultima_actualizacion"));
        return historial;
    }

    /**
     * Carga los diagnósticos, tratamientos y notas médicas asociados al historial
     */
    private void cargarDatosCompletos(HistorialClinico historial) {
        if (historial == null) return;

        // Cargar diagnósticos - CORRECTO
        DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAOImpl();
        List<Diagnostico> diagnosticos = diagnosticoDAO.listarDiagnosticosPorPaciente(historial.getIdPaciente());
        historial.setDiagnosticos(diagnosticos);

        // Cargar tratamientos - CORREGIDO
        TratamientoDAO tratamientoDAO = new TratamientoDAOImpl();
        List<Tratamiento> tratamientos = new ArrayList<>();

        // Para cada diagnóstico, obtener sus tratamientos
        for (Diagnostico diag : diagnosticos) {
            List<Tratamiento> tratsDiag = tratamientoDAO.listarTratamientosPorDiagnostico(diag.getIdDiagnostico());
            tratamientos.addAll(tratsDiag);
        }
        historial.setTratamientos(tratamientos);

        // Cargar notas médicas
        cargarNotasMedicas(historial);
    }


    /**
     * Carga las notas médicas del paciente
     */
    private void cargarNotasMedicas(HistorialClinico historial) {
        String sql = "SELECT * FROM nota_medica WHERE id_historial = ?";
        List<NotaMedica> notas = new ArrayList<>();

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, historial.getIdHistorial());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NotaMedica nota = new NotaMedica();
                nota.setIdNota(rs.getString("id_nota"));
                nota.setIdHistorial(rs.getString("id_historial"));
                nota.setTipoNota(rs.getString("tipo_nota"));
                nota.setContenidoMedico(rs.getString("contenido_medico"));
                nota.setContenidoPaciente(rs.getString("contenido_paciente"));
                nota.setVisibleParaPaciente(rs.getBoolean("visible_para_paciente"));
                nota.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                notas.add(nota);
            }

            historial.setNotas(notas);

        } catch (SQLException e) {
            System.err.println("Error al cargar notas médicas: " + e.getMessage());
        }
    }

}


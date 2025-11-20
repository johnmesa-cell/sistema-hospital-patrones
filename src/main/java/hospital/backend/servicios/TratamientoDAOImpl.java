package hospital.backend.servicios;

import hospital.backend.historialclinico.Tratamiento;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de TratamientoDAO para operaciones de base de datos
 * relacionadas con tratamientos médicos.
 * Incluye validaciones para manejo seguro de fechas nulas.
 */
public class TratamientoDAOImpl implements TratamientoDAO {

    @Override
    public void agregarTratamiento(Tratamiento t) {
        String sql = "INSERT INTO tratamiento (id_tratamiento, id_diagnostico, descripcion, fecha_inicio, fecha_fin, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, t.getIdTratamiento());
            ps.setString(2, t.getIdDiagnostico());
            ps.setString(3, t.getDescripcion());

            // Validación para fecha de inicio
            if (t.getFechaInicio() != null && !t.getFechaInicio().trim().isEmpty()) {
                ps.setDate(4, Date.valueOf(t.getFechaInicio()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            // Validación para fecha de fin
            if (t.getFechaFin() != null && !t.getFechaFin().trim().isEmpty()) {
                ps.setDate(5, Date.valueOf(t.getFechaFin()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }

            ps.setString(6, t.getEstado());
            ps.executeUpdate();

            System.out.println("Tratamiento agregado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al agregar tratamiento: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: Formato de fecha inválido. Use yyyy-MM-dd. " + e.getMessage());
        }
    }

    @Override
    public void actualizarTratamiento(Tratamiento t) {
        String sql = "UPDATE tratamiento SET id_diagnostico = ?, descripcion = ?, " +
                "fecha_inicio = ?, fecha_fin = ?, estado = ? " +
                "WHERE id_tratamiento = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, t.getIdDiagnostico());
            ps.setString(2, t.getDescripcion());

            // Validación para fecha de inicio
            if (t.getFechaInicio() != null && !t.getFechaInicio().trim().isEmpty()) {
                ps.setDate(3, Date.valueOf(t.getFechaInicio()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }

            // Validación para fecha de fin
            if (t.getFechaFin() != null && !t.getFechaFin().trim().isEmpty()) {
                ps.setDate(4, Date.valueOf(t.getFechaFin()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            ps.setString(5, t.getEstado());
            ps.setString(6, t.getIdTratamiento());
            ps.executeUpdate();

            System.out.println("Tratamiento actualizado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al actualizar tratamiento: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: Formato de fecha inválido. Use yyyy-MM-dd. " + e.getMessage());
        }
    }

    @Override
    public void eliminarTratamiento(String id) {
        String sql = "DELETE FROM tratamiento WHERE id_tratamiento = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Tratamiento eliminado correctamente.");
            } else {
                System.out.println("No se encontró tratamiento con ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar tratamiento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Tratamiento buscarPorId(String id) {
        String sql = "SELECT * FROM tratamiento WHERE id_tratamiento = ?";
        Tratamiento t = null;

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                t = mapearTratamiento(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar tratamiento: " + e.getMessage());
            e.printStackTrace();
        }

        return t;
    }

    @Override
    public List<Tratamiento> listarTratamientosPorDiagnostico(String diagnosticoId) {
        String sql = "SELECT * FROM tratamiento WHERE id_diagnostico = ?";
        List<Tratamiento> lista = new ArrayList<>();

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, diagnosticoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tratamiento t = mapearTratamiento(rs);
                lista.add(t);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar tratamientos: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Método auxiliar para mapear un ResultSet a un objeto Tratamiento.
     * Incluye validación de fechas nulas para evitar NullPointerException.
     *
     * @param rs ResultSet con los datos del tratamiento
     * @return Objeto Tratamiento con los datos mapeados
     * @throws SQLException si hay error al leer los datos
     */
    private Tratamiento mapearTratamiento(ResultSet rs) throws SQLException {
        Tratamiento t = new Tratamiento();

        t.setIdTratamiento(rs.getString("id_tratamiento"));
        t.setIdDiagnostico(rs.getString("id_diagnostico"));
        t.setDescripcion(rs.getString("descripcion"));

        // VALIDACIÓN CRÍTICA: Verificar si fecha_inicio no es NULL antes de convertir
        Date fechaInicioSQL = rs.getDate("fecha_inicio");
        if (fechaInicioSQL != null) {
            t.setFechaInicio(fechaInicioSQL.toLocalDate());
        } else {
            // Si la fecha es NULL, establecer como null o cadena vacía
            t.setFechaInicio((String) null);
        }

        // VALIDACIÓN CRÍTICA: Verificar si fecha_fin no es NULL antes de convertir
        Date fechaFinSQL = rs.getDate("fecha_fin");
        if (fechaFinSQL != null) {
            t.setFechaFin(fechaFinSQL.toLocalDate());
        } else {
            // Si la fecha es NULL, establecer como null o cadena vacía
            t.setFechaFin((String) null);
        }

        t.setEstado(rs.getString("estado"));

        return t;
    }
}




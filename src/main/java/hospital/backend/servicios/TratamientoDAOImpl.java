package hospital.backend.servicios;

import hospital.backend.historialclinico.Tratamiento;

import java.sql.Date;
import java.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TratamientoDAOImpl implements TratamientoDAO {

    @Override
    public void agregarTratamiento(Tratamiento t) {
        String sql = "INSERT INTO tratamiento (id_tratamiento, id_diagnostico, descripcion, fecha_inicio, fecha_fin, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getIdTratamiento());
            ps.setString(2, t.getIdDiagnostico());
            ps.setString(3, t.getDescripcion());
            ps.setDate(4, Date.valueOf(t.getFechaInicio()));
            ps.setDate(5, Date.valueOf(t.getFechaFin()));
            ps.setString(6, t.getEstado());
            ps.executeUpdate();
            System.out.println("Tratamiento agregado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al agregar tratamiento: " + e.getMessage());
        }
    }

    @Override
    public void actualizarTratamiento(Tratamiento t) {
        String sql = "UPDATE tratamiento SET id_diagnostico = ?, descripcion = ?, fecha_inicio = ?, fecha_fin = ?, estado = ? WHERE id_tratamiento = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getIdDiagnostico());
            ps.setString(2, t.getDescripcion());
            ps.setDate(3, Date.valueOf(t.getFechaInicio()));
            ps.setDate(4, Date.valueOf(t.getFechaFin()));
            ps.setString(5, t.getEstado());
            ps.setString(6, t.getIdTratamiento());
            ps.executeUpdate();
            System.out.println("Tratamiento actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar tratamiento: " + e.getMessage());
        }
    }

    @Override
    public void eliminarTratamiento(String id) {
        String sql = "DELETE FROM tratamiento WHERE id_tratamiento = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Tratamiento eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar tratamiento: " + e.getMessage());
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
                t = new Tratamiento();
                t.setIdTratamiento(rs.getString("id_tratamiento"));
                t.setIdDiagnostico(rs.getString("id_diagnostico"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                t.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
                t.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar tratamiento: " + e.getMessage());
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
                Tratamiento t = new Tratamiento();
                t.setIdTratamiento(rs.getString("id_tratamiento"));
                t.setIdDiagnostico(rs.getString("id_diagnostico"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                t.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
                t.setEstado(rs.getString("estado"));
                lista.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar tratamientos: " + e.getMessage());
        }
        return lista;
    }
}



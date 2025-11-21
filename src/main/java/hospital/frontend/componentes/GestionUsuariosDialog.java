package hospital.frontend.componentes;

// Clase para el diálogo de gestión de usuarios
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import hospital.backend.usuarios.Usuario;
import hospital.backend.servicios.UsuarioService;

public class GestionUsuariosDialog extends JDialog {
    private UsuarioService usuarioService;
    private DefaultTableModel modeloUsuarios;
    private JTable tablaUsuarios;
    private JButton btnAgregar, btnEditar, btnEliminar, btnCerrar;

    public GestionUsuariosDialog(JFrame parent, UsuarioService usuarioService) {
        super(parent, "Gestión de Usuarios", true);
        this.usuarioService = usuarioService;

        setSize(600, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Modelo y tabla
        modeloUsuarios = new DefaultTableModel(new Object[]{"ID", "Nombre", "Correo", "Rol"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false; // No editable directamente en la tabla
            }
        };
        tablaUsuarios = new JTable(modeloUsuarios);
        JScrollPane scroll = new JScrollPane(tablaUsuarios);
        add(scroll, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        // Cargar usuarios
        cargarUsuarios();

        // Listeners
        btnAgregar.addActionListener(e -> agregarUsuario());
        btnEditar.addActionListener(e -> editarUsuario());
        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnCerrar.addActionListener(e -> dispose());
    }

    private void cargarUsuarios() {
        modeloUsuarios.setRowCount(0); // Limpiar tabla
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        for (Usuario u : usuarios) {
            modeloUsuarios.addRow(new Object[]{u.getId_Usuario(), u.getNombre(), u.getEmail(), u.getTipo()});
        }
    }

    private void agregarUsuario() {
        UsuarioFormDialog dialog = new UsuarioFormDialog(this, "Agregar Usuario", null);
        dialog.setVisible(true);
        Usuario nuevo = dialog.getUsuario();
        if (nuevo != null) {
            usuarioService.agregarUsuario(nuevo);
            cargarUsuarios();
        }
    }

    private void editarUsuario() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para editar");
            return;
        }
        int idUsuario = (int) modeloUsuarios.getValueAt(fila, 0);
        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        UsuarioFormDialog dialog = new UsuarioFormDialog(this, "Editar Usuario", usuario);
        dialog.setVisible(true);
        Usuario modificado = dialog.getUsuario();
        if (modificado != null) {
            usuarioService.actualizarUsuario(modificado);
            cargarUsuarios();
        }
    }

    private void eliminarUsuario() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar");
            return;
        }
        int idUsuario = (int) modeloUsuarios.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar al usuario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            usuarioService.eliminarUsuario(idUsuario);
            cargarUsuarios();
        }
    }
}

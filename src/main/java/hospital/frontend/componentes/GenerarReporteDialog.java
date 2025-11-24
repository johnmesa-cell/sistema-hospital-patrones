package hospital.frontend.componentes;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import hospital.backend.usuarios.Usuario;
import hospital.backend.servicios.UsuarioService;
import hospital.backend.auditoria.ReporteAuditoria;

public class GenerarReporteDialog extends JDialog {
    private UsuarioService usuarioService;
    private ReporteAuditoria reporteAuditoria;

    private JTextArea textAreaReporte;
    private JButton btnGenerar, btnGuardar, btnCerrar;

    public GenerarReporteDialog(Frame parent, UsuarioService usuarioService) {
        super(parent, "Generar Reporte de Usuarios", true);
        this.usuarioService = usuarioService;
        // Usa la misma ruta que usas en tu clase ReporteAuditoria
        this.reporteAuditoria = new ReporteAuditoria("C:/Users/ao184/Documents/GitHub/sistema-hospital-patrones/src/main/resources/mis_reportes/");
        inicializarComponentes();
        setSize(600, 500);
        setLocationRelativeTo(parent);
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        textAreaReporte = new JTextArea();
        textAreaReporte.setEditable(false);
        JScrollPane scroll = new JScrollPane(textAreaReporte);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        btnGenerar = new JButton("Generar");
        btnGenerar.addActionListener(e -> mostrarReporte());

        btnGuardar = new JButton("Guardar TXT");
        btnGuardar.addActionListener(e -> guardarReporteTXT());

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnGenerar);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void mostrarReporte() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Usuarios\n\n");
        for (Usuario u : usuarios) {
            reporte.append("ID: ").append(u.getId_Usuario())
                    .append(", Nombre: ").append(u.getNombre())
                    .append(", Rol: ").append(u.getTipo())
                    .append("\n");
        }
        textAreaReporte.setText(reporte.toString());
    }

    private void guardarReporteTXT() {
        String textoReporte = textAreaReporte.getText();
        // Usa el método privado de ReporteAuditoria usando solo el texto
        reporteAuditoria.guardarEnArchivo(textoReporte);
        JOptionPane.showMessageDialog(this, "Reporte guardado exitosamente en carpeta: " + "mis_reportes");
    }
}


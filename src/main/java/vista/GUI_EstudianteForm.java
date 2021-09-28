package vista;

import dao.EstudianteDAO;
import modelo.Estudiante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_EstudianteForm extends JDialog {
    private boolean nuevoRegistro;
    private Estudiante estudianteEditado;

    private JPanel pnlPrincipal;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JTextField txtPriNombre;
    private JTextField txtSegNombre;
    private JTextField txtPriApellido;
    private JTextField txtSegApellido;


    public GUI_EstudianteForm(JFrame ventanaPadre, String titulo){
        super(ventanaPadre, titulo);
        this.setModalityType(ModalityType.DOCUMENT_MODAL);
        cargarComponentes();
        setMinimumSize( new Dimension(307, 220));
        setLocationRelativeTo(null);
        setResizable(false);

        nuevoRegistro = true;
        estudianteEditado = null;
    }

    public GUI_EstudianteForm(JFrame ventanaPadre, String titulo, Estudiante estudiante){
        this(ventanaPadre, titulo);
        nuevoRegistro = false;
        estudianteEditado = estudiante;
        cargarFormulario();
    }

    private void cargarComponentes(){
        pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(null);
        setContentPane(pnlPrincipal);

        JLabel lblPriNombre = new JLabel("Primer Nombre: ");
        lblPriNombre.setBounds(10,10, 120, 24);
        pnlPrincipal.add(lblPriNombre);

        JLabel lblSegNombre = new JLabel("Segundo Nombre: ");
        lblSegNombre.setBounds(10,40, 120, 24);
        pnlPrincipal.add(lblSegNombre);

        JLabel lblPriApellido = new JLabel("Primer Apellido: ");
        lblPriApellido.setBounds(10,70, 120, 24);
        pnlPrincipal.add(lblPriApellido);

        JLabel lblSegApellido = new JLabel("Segundo Apellido: ");
        lblSegApellido.setBounds(10,100, 120, 24);
        pnlPrincipal.add(lblSegApellido);

        txtPriNombre = new JTextField();
        txtPriNombre.setBounds(120,10, 170, 24);
        pnlPrincipal.add(txtPriNombre);

        txtSegNombre = new JTextField();
        txtSegNombre.setBounds(120,40, 170, 24);
        pnlPrincipal.add(txtSegNombre);

        txtPriApellido = new JTextField();
        txtPriApellido.setBounds(120,70, 170, 24);
        pnlPrincipal.add(txtPriApellido);

        txtSegApellido = new JTextField();
        txtSegApellido.setBounds(120,100, 170, 24);
        pnlPrincipal.add(txtSegApellido);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(10,150,100,24);
        pnlPrincipal.add(btnGuardar);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarRegistro();
            }
        });

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(120, 150, 100, 24);
        pnlPrincipal.add(btnCancelar);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVentanta();
            }
        });

        pack();
    }

    private void cerrarVentanta(){
        this.dispose();
    }

    private void guardarRegistro(){
        if(nuevoRegistro){
            Estudiante nuevoEstudiante = new Estudiante();
            nuevoEstudiante.setPrimerNombre(txtPriNombre.getText());
            nuevoEstudiante.setSegundoNombre(txtSegNombre.getText());
            nuevoEstudiante.setPrimerApellido(txtPriApellido.getText());
            nuevoEstudiante.setSegundoApellido(txtSegApellido.getText());

            EstudianteDAO.Registrar(nuevoEstudiante);
            this.dispose();
        }
        else{
            estudianteEditado.setPrimerNombre(txtPriNombre.getText());
            estudianteEditado.setSegundoNombre(txtSegNombre.getText());
            estudianteEditado.setPrimerApellido(txtPriApellido.getText());
            estudianteEditado.setSegundoApellido(txtSegApellido.getText());

            EstudianteDAO.Editar(estudianteEditado);
            this.dispose();
        }
    }

    private void cargarFormulario(){
        txtPriNombre.setText(estudianteEditado.getPrimerNombre());
        txtSegNombre.setText(estudianteEditado.getSegundoNombre());
        txtPriApellido.setText(estudianteEditado.getPrimerApellido());
        txtSegApellido.setText(estudianteEditado.getSegundoApellido());
    }
}

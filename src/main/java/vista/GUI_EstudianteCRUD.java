package vista;

import dao.EstudianteDAO;
import modelo.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI_EstudianteCRUD extends JFrame {
    private JPanel pnlPrincipal;
    private JButton btnRegistrar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JTable tblEstudiantes;
    private JScrollPane pnlTabla;
    private List<Estudiante> listaEstudiantes;


    public GUI_EstudianteCRUD(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cargarComponentes();
        setMinimumSize( new Dimension(607, 425));
        setLocationRelativeTo(null);
        setResizable(false);

        cargarDatosTabla();
    }

    private void cargarComponentes(){
        pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(null);
        setContentPane(pnlPrincipal);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(290,10, 90,24);
        pnlPrincipal.add(btnRegistrar);
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioRegistro();
            }
        });

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(390,10, 90,24);
        pnlPrincipal.add(btnEditar);
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abriFormularioEditar();
            }
        });

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(490,10, 90,24);
        pnlPrincipal.add(btnEliminar);
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarRegistro();
            }
        });

        tblEstudiantes = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblEstudiantes.setFocusable(false);
        pnlTabla = new JScrollPane();
        pnlTabla.setBounds(10,44,580,346);
        pnlTabla.setViewportView(tblEstudiantes);

        pnlPrincipal.add(pnlTabla);

        pack();
    }

    private void eliminarRegistro(){
        int indice = tblEstudiantes.getSelectedRow();
        if (indice >= 0){
            int respuesta = JOptionPane.showOptionDialog(this, "¿Estas seguro qu deseas eliminar el contacto?","Cerrar cesión", JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
            if(respuesta==JOptionPane.YES_OPTION){

                int idEstudiante = listaEstudiantes.get(indice).getIdEstudiante();
                EstudianteDAO.Eliminar(idEstudiante);
                cargarDatosTabla();
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Selecciona una registro");
        }
    }

    private void cargarDatosTabla(){
        if (tblEstudiantes != null) {
            String titulos[] = { "Apellido Paterno", "Apellido Materno", "Primer nombre", "Segundo Nombre" };
            DefaultTableModel modelo = new DefaultTableModel(new Object[0][0], titulos);
            listaEstudiantes = EstudianteDAO.ObtenerEstudiantes();

            if(listaEstudiantes.size() > 0){
                for (Estudiante e : listaEstudiantes) {
                    Object fila[] = new Object[4];
                    fila[0] = e.getPrimerApellido();
                    fila[1] = e.getSegundoApellido();
                    fila[2] = e.getPrimerNombre();
                    fila[3] = e.getSegundoNombre();
                    modelo.addRow(fila);
                }
            }

            tblEstudiantes.setModel(modelo);
        }
    }

    private void abrirFormularioRegistro(){
        GUI_EstudianteForm formulario = new GUI_EstudianteForm(this, "Registrar estudiante");
        formulario.setVisible(true);
        cargarDatosTabla();
    }

    private void abriFormularioEditar(){
        int indice = tblEstudiantes.getSelectedRow();
        if(indice >= 0){
            Estudiante estudiante = listaEstudiantes.get(indice);
            GUI_EstudianteForm formulario = new GUI_EstudianteForm(this, "Editar estudiante", estudiante);

            formulario.setVisible(true);
            cargarDatosTabla();
        }
    }
}

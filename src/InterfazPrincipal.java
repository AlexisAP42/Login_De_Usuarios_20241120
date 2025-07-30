

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class InterfazPrincipal extends JFrame {
    private Usuario[] usuarios;
    private int cantidadUsuarios;
    private InterfazLogin interfazLogin;
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;
    private JButton botonCerrarSesion, botonActualizar, botonEliminar;

    public InterfazPrincipal(Usuario[] usuarios, int cantidadUsuarios, InterfazLogin interfazLogin) {
        this.usuarios = usuarios;
        this.cantidadUsuarios = cantidadUsuarios;
        this.interfazLogin = interfazLogin;
        
        setTitle("Gestión de Usuarios");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(45, 45, 45));
        
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(45, 45, 45));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        
        JLabel titulo = new JLabel("CENTRO DE USUARIOS ITLA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24)); 
        titulo.setForeground(new Color(0, 204, 255)); 
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        
        
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Usuario");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Correo");
        
        actualizarTabla();
        
        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setBackground(new Color(60, 60, 60)); 
        tablaUsuarios.setForeground(Color.WHITE);
        tablaUsuarios.setGridColor(new Color(100, 100, 100)); 
        
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.getViewport().setBackground(new Color(45, 45, 45));
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setOpaque(false);
        
        botonActualizar = new JButton("Actualizar Usuario");
        botonActualizar.setBackground(new Color(0, 204, 255)); 
        botonActualizar.setForeground(Color.BLACK);
        botonActualizar.setPreferredSize(new Dimension(150, 30));
        botonActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });
        panelBotones.add(botonActualizar);
        
        botonEliminar = new JButton("Eliminar Usuario");
        botonEliminar.setBackground(new Color(255, 80, 80));
        botonEliminar.setForeground(Color.BLACK);
        botonEliminar.setPreferredSize(new Dimension(150, 30));
        botonEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });
        panelBotones.add(botonEliminar);
        
        botonCerrarSesion = new JButton("Cerrar Sesión");
        botonCerrarSesion.setBackground(new Color(100, 100, 100));
        botonCerrarSesion.setForeground(Color.WHITE);
        botonCerrarSesion.setPreferredSize(new Dimension(150, 30));
        botonCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
        panelBotones.add(botonCerrarSesion);
        
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    
    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        
        for(int i = 0; i < cantidadUsuarios; i++) {
            Object[] fila = {
                usuarios[i].getNombreUsuario(),
                usuarios[i].getNombre(),
                usuarios[i].getApellido(),
                usuarios[i].getTelefono(),
                usuarios[i].getCorreo()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    private void actualizarUsuario() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        
        if(filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para actualizar");
            return;
        }
        
        String nombreUsuario = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
        
        for(int i = 0; i < cantidadUsuarios; i++) {
            if(usuarios[i].getNombreUsuario().equals(nombreUsuario)) {
                
                JPanel panelDialogo = new JPanel(new GridLayout(5, 2, 5, 5));
                panelDialogo.setBackground(new Color(45, 45, 45));
                
                JLabel lblNombre = new JLabel("Nombre:");
                lblNombre.setForeground(Color.WHITE);
                JTextField campoNombre = new JTextField(usuarios[i].getNombre());
                campoNombre.setBackground(new Color(60, 60, 60));
                campoNombre.setForeground(Color.WHITE);
                
                JLabel lblApellido = new JLabel("Apellido:");
                lblApellido.setForeground(Color.WHITE);
                JTextField campoApellido = new JTextField(usuarios[i].getApellido());
                campoApellido.setBackground(new Color(60, 60, 60));
                campoApellido.setForeground(Color.WHITE);
                
                JLabel lblTelefono = new JLabel("Teléfono:");
                lblTelefono.setForeground(Color.WHITE);
                JTextField campoTelefono = new JTextField(usuarios[i].getTelefono());
                campoTelefono.setBackground(new Color(60, 60, 60));
                campoTelefono.setForeground(Color.WHITE);
                
                JLabel lblCorreo = new JLabel("Correo:");
                lblCorreo.setForeground(Color.WHITE);
                JTextField campoCorreo = new JTextField(usuarios[i].getCorreo());
                campoCorreo.setBackground(new Color(60, 60, 60));
                campoCorreo.setForeground(Color.WHITE);
                
                JLabel lblContrasena = new JLabel("Nueva Contraseña:");
                lblContrasena.setForeground(Color.WHITE);
                JPasswordField campoContrasena = new JPasswordField();
                campoContrasena.setBackground(new Color(60, 60, 60));
                campoContrasena.setForeground(Color.WHITE);
                
                panelDialogo.add(lblNombre);
                panelDialogo.add(campoNombre);
                panelDialogo.add(lblApellido);
                panelDialogo.add(campoApellido);
                panelDialogo.add(lblTelefono);
                panelDialogo.add(campoTelefono);
                panelDialogo.add(lblCorreo);
                panelDialogo.add(campoCorreo);
                panelDialogo.add(lblContrasena);
                panelDialogo.add(campoContrasena);
                
                int opcion = JOptionPane.showConfirmDialog(this, panelDialogo, "Actualizar Usuario", JOptionPane.OK_CANCEL_OPTION);
                
                if(opcion == JOptionPane.OK_OPTION) {
                    usuarios[i].setNombre(campoNombre.getText());
                    usuarios[i].setApellido(campoApellido.getText());
                    usuarios[i].setTelefono(campoTelefono.getText());
                    usuarios[i].setCorreo(campoCorreo.getText());
                    
                    String nuevaContrasena = new String(campoContrasena.getPassword());
                    if(!nuevaContrasena.isEmpty()) {
                        usuarios[i].setContrasena(nuevaContrasena);
                    }
                    
                    actualizarTabla();
                    JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente");
                }
                return;
            }
        }
    }
    
    private void eliminarUsuario() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        
        if(filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar");
            return;
        }
        
        String nombreUsuario = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
        
        
        UIManager.put("OptionPane.background", new Color(45, 45, 45));
        UIManager.put("Panel.background", new Color(45, 45, 45));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro que desea eliminar al usuario " + nombreUsuario + "?", 
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        
        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
        UIManager.put("OptionPane.messageForeground", null);
        
        if(confirmacion == JOptionPane.YES_OPTION) {
            for(int i = 0; i < cantidadUsuarios; i++) {
                if(usuarios[i].getNombreUsuario().equals(nombreUsuario)) {
                    for(int j = i; j < cantidadUsuarios - 1; j++) {
                        usuarios[j] = usuarios[j+1];
                    }
                    cantidadUsuarios--;
                    
                    interfazLogin.actualizarUsuarios(usuarios, cantidadUsuarios);
                    actualizarTabla();
                    JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente");
                    return;
                }
            }
        }
    }
    
    private void cerrarSesion() {
        this.dispose();
        interfazLogin.setVisible(true);
    }
}
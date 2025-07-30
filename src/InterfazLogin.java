

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfazLogin extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JButton botonLogin, botonRegistro;
    private Usuario[] usuarios;
    private int cantidadUsuarios;

    public InterfazLogin(Usuario[] usuarios, int cantidadUsuarios) {
        this.usuarios = usuarios;
        this.cantidadUsuarios = cantidadUsuarios;
        
        setTitle("Centro de Usuarios ITLA");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(45, 45, 45));
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(45, 45, 45));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        
        JLabel titulo = new JLabel("CENTRO DE USUARIOS ITLA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(titulo, gbc);
        
        // aqui van los campos de usuario y contraseña
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(lblUsuario, gbc);
        
        campoUsuario = new JTextField(15);
        campoUsuario.setBackground(new Color(60, 60, 60));
        campoUsuario.setForeground(Color.WHITE);
        campoUsuario.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campoUsuario, gbc);
        
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(lblContrasena, gbc);
        
        campoContrasena = new JPasswordField(15);
        campoContrasena.setBackground(new Color(60, 60, 60));
        campoContrasena.setForeground(Color.WHITE);
        campoContrasena.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campoContrasena, gbc);
        
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotones.setOpaque(false);
        
        botonLogin = new JButton("Iniciar Sesión");
        botonLogin.setBackground(new Color(0, 255, 0));
        botonLogin.setForeground(Color.BLACK);
        botonLogin.setPreferredSize(new Dimension(120, 30));
        botonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validarLogin();
            }
        });
        panelBotones.add(botonLogin);
        
        botonRegistro = new JButton("Registrarse");
        botonRegistro.setBackground(new Color(0, 204, 255));
        botonRegistro.setForeground(Color.BLACK);
        botonRegistro.setPreferredSize(new Dimension(120, 30));
        botonRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirRegistro();
            }
        });
        panelBotones.add(botonRegistro);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(panelBotones, gbc);
        
        add(panel);
    }
    
    private void validarLogin() {
        String usuario = campoUsuario.getText();
        String contrasena = new String(campoContrasena.getPassword());
        
        if(usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarError("Debe ingresar su usuario y contraseña, si no está registrado debe registrarse");
            return;
        }
        
        for(int i = 0; i < cantidadUsuarios; i++) {
            if(usuarios[i].getNombreUsuario().equals(usuario) && usuarios[i].getContrasena().equals(contrasena)) {
                JOptionPane.showMessageDialog(this, "Login exitoso");
                abrirInterfazPrincipal();
                this.dispose();
                return;
            }
        }
        
        mostrarError("Usuario o contraseña incorrectos");
    }
    
    private void abrirRegistro() {
        InterfazRegistro registro = new InterfazRegistro(usuarios, cantidadUsuarios, this);
        registro.setVisible(true);
    }
    
    private void abrirInterfazPrincipal() {
        InterfazPrincipal principal = new InterfazPrincipal(usuarios, cantidadUsuarios, this);
        principal.setVisible(true);
    }
    
    public void actualizarUsuarios(Usuario[] nuevosUsuarios, int nuevaCantidad) {
        this.usuarios = nuevosUsuarios;
        this.cantidadUsuarios = nuevaCantidad;
    }
    
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
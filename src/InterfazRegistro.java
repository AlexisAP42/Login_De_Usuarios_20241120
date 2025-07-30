

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfazRegistro extends JFrame {
    private JTextField campoNombreUsuario, campoNombre, campoApellido, campoTelefono, campoCorreo;
    private JPasswordField campoContrasena, campoConfirmarContrasena;
    private JButton botonRegistrar;
    private Usuario[] usuarios;
    private int cantidadUsuarios;
    private InterfazLogin interfazLogin;

    public InterfazRegistro(Usuario[] usuarios, int cantidadUsuarios, InterfazLogin interfazLogin) {
        this.usuarios = usuarios;
        this.cantidadUsuarios = cantidadUsuarios;
        this.interfazLogin = interfazLogin;
        
        setTitle("Registro de Usuario");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(45, 45, 45));
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(45, 45, 45));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        
        Dimension dimensionCampos = new Dimension(200, 25);
        Color colorFondoCampo = new Color(60, 60, 60);
        Color colorTextoCampo = Color.WHITE;
        
        
        agregarCampo(panel, gbc, "Nombre de Usuario:", 0);
        campoNombreUsuario = crearCampoTexto(dimensionCampos, colorFondoCampo, colorTextoCampo);
        panel.add(campoNombreUsuario, gbc);
        
        agregarCampo(panel, gbc, "Nombre:", 1);
        campoNombre = crearCampoTexto(dimensionCampos, colorFondoCampo, colorTextoCampo);
        panel.add(campoNombre, gbc);
        
        agregarCampo(panel, gbc, "Apellido:", 2);
        campoApellido = crearCampoTexto(dimensionCampos, colorFondoCampo, colorTextoCampo);
        panel.add(campoApellido, gbc);
        
        agregarCampo(panel, gbc, "Teléfono:", 3);
        campoTelefono = crearCampoTexto(dimensionCampos, colorFondoCampo, colorTextoCampo);
        panel.add(campoTelefono, gbc);
        
        agregarCampo(panel, gbc, "Correo Electrónico:", 4);
        campoCorreo = crearCampoTexto(dimensionCampos, colorFondoCampo, colorTextoCampo);
        panel.add(campoCorreo, gbc);
        
        agregarCampo(panel, gbc, "Contraseña:", 5);
        campoContrasena = crearCampoContrasena(dimensionCampos, colorFondoCampo, colorTextoCampo);
        panel.add(campoContrasena, gbc);
        
        agregarCampo(panel, gbc, "Confirmar Contraseña:", 6);
        campoConfirmarContrasena = crearCampoContrasena(dimensionCampos, colorFondoCampo, colorTextoCampo);
        panel.add(campoConfirmarContrasena, gbc);
        
        
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        
        botonRegistrar = new JButton("Registrarse");
        botonRegistrar.setBackground(new Color(0, 204, 255));
        botonRegistrar.setForeground(Color.BLACK);
        botonRegistrar.setPreferredSize(new Dimension(150, 30));
        botonRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
        panel.add(botonRegistrar, gbc);
        
        add(panel);
    }
    
    private void agregarCampo(JPanel panel, GridBagConstraints gbc, String texto, int fila) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(etiqueta, gbc);
        
        gbc.gridx = 1;
    }
    
    private JTextField crearCampoTexto(Dimension dimension, Color fondo, Color texto) {
        JTextField campo = new JTextField();
        campo.setPreferredSize(dimension);
        campo.setBackground(fondo);
        campo.setForeground(texto);
        campo.setCaretColor(texto);
        return campo;
    }
    
    private JPasswordField crearCampoContrasena(Dimension dimension, Color fondo, Color texto) {
        JPasswordField campo = new JPasswordField();
        campo.setPreferredSize(dimension);
        campo.setBackground(fondo);
        campo.setForeground(texto);
        campo.setCaretColor(texto);
        return campo;
    }
    
    private void registrarUsuario() {
        String nombreUsuario = campoNombreUsuario.getText();
        String nombre = campoNombre.getText();
        String apellido = campoApellido.getText();
        String telefono = campoTelefono.getText();
        String correo = campoCorreo.getText();
        String contrasena = new String(campoContrasena.getPassword());
        String confirmarContrasena = new String(campoConfirmarContrasena.getPassword());
        
        if(nombreUsuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || 
           telefono.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty()) {
            mostrarError("Todos los campos son obligatorios");
            return;
        }
        
        if(!contrasena.equals(confirmarContrasena)) {
            mostrarError("Las contraseñas no coinciden");
            return;
        }
        
        for(int i = 0; i < cantidadUsuarios; i++) {
            if(usuarios[i].getNombreUsuario().equals(nombreUsuario)) {
                mostrarError("El nombre de usuario ya existe");
                return;
            }
        }
        
        Usuario nuevoUsuario = new Usuario(nombreUsuario, nombre, apellido, telefono, correo, contrasena);
        
        if(cantidadUsuarios < usuarios.length) {
            usuarios[cantidadUsuarios] = nuevoUsuario;
            cantidadUsuarios++;
            
            interfazLogin.actualizarUsuarios(usuarios, cantidadUsuarios);
            JOptionPane.showMessageDialog(this, "Registro exitoso");
            this.dispose();
        } else {
            mostrarError("No se pueden registrar más usuarios");
        }
    }
    
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
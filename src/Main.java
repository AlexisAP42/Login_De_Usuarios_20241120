


public class Main {
    public static void main(String[] args) {
        // se creo un array para almacenar los usuarios, can capacidad para aguantar hasta 100 usuarios con su info
        Usuario[] usuarios = new Usuario[100];
        int cantidadUsuarios = 0;
        
        // aqui creo la interfaz de usuario
        InterfazLogin login = new InterfazLogin(usuarios, cantidadUsuarios);
        login.setVisible(true);
    }
}
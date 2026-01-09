import java.util.Scanner;

public class ValidadorFormulario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] u_regs = new String[10];
        String[] e_regs = new String[10];
        int n_regs = 0;

        System.out.println("Sistema de Registro - Portal Web v3.2");

        while(true) {
            System.out.println("[1] Registrar Nuevo Usuario");
            System.out.println("[2] Ver Usuarios Registrados y Salir");
            System.out.print("Opcion: ");
            String opt = sc.nextLine();

            if (opt.equals("2")) {
                System.out.println("Usuarios Registrados");
                if (n_regs == 0) {
                    System.out.println("(No hay usuarios registrados)");
                } else {
                    for(int i = 0; i < n_regs; i++) {
                        System.out.println((i+1) + ". Usuario: " + u_regs[i] + ", Email: " + e_regs[i]);
                    }
                }
                System.out.println("Saliendo del sistema...");
                break;
            }
            if (!opt.equals("1")) {
                System.out.println("Opcion no valida.");
                continue;
            }

            System.out.println("Nuevo Registro");
            System.out.print("Nombre de usuario: ");
            String usuario = sc.nextLine();
            if (usuario.length() < 3) {
                System.out.println("ERROR: El nombre debe tener al menos 3 caracteres");
                continue;
            }
            if (usuario.length() > 20) {
                System.out.println("ERROR: El nombre no puede exceder 20 caracteres");
                continue;
            }
            boolean tieneEspacio = false;
            for (int i = 0; i < usuario.length(); i++) {
                if (usuario.charAt(i) == ' ') {
                    tieneEspacio = true;
                }
            }
            if (tieneEspacio) {
                System.out.println("ERROR: El nombre no puede contener espacios");
                continue;
            }

            System.out.print("Contrasena: ");
            String password = sc.nextLine();
            if (password.length() < 8) {
                System.out.println("ERROR: La contrasena debe tener al menos 8 caracteres");
                continue;
            }
            if (password.length() > 30) {
                System.out.println("ERROR: La contrasena no puede exceder 30 caracteres");
                continue;
            }
            boolean tieneNumero = false;
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
                    tieneNumero = true;
                }
            }
            if (!tieneNumero) {
                System.out.println("ERROR: La contrasena debe contener al menos un numero");
                continue;
            }

            System.out.print("Email: ");
            String email = sc.nextLine();
            if (email.length() < 5) {
                System.out.println("ERROR: El email debe tener al menos 5 caracteres");
                continue;
            }
            if (email.length() > 50) {
                System.out.println("ERROR: El email no puede exceder 50 caracteres");
                continue;
            }
            boolean tieneArroba = false;
            int posicionArroba = -1;
            for (int i = 0; i < email.length(); i++) {
                if (email.charAt(i) == '@') {
                    tieneArroba = true;
                }
            }
            if (!tieneArroba) {
                System.out.println("ERROR: El email debe contener @");
                continue;
            }
            boolean tienePunto = false;
            for (int i = 0; i < email.length(); i++) {
                if (email.charAt(i) == '.') {
                    tienePunto = true;
                }
            }
            if (!tienePunto) {
                System.out.println("ERROR: El email debe contener un punto");
                continue;
            }

            System.out.print("Edad: ");
            int edad = sc.nextInt();
            sc.nextLine(); // Consumir newline
            if (edad < 18) {
                System.out.println("ERROR: Debes ser mayor de edad");
                continue;
            }
            if (edad > 120) {
                System.out.println("ERROR: Edad no valida");
                continue;
            }

            System.out.println("--> Registro completado exitosamente!");
            if (n_regs < 10) {
                u_regs[n_regs] = usuario;
                e_regs[n_regs] = email;
                n_regs++;
            } else {
                System.out.println("ADVERTENCIA: No hay mas espacio para guardar nuevos usuarios.");
            }
        }
        sc.close();
    }
}

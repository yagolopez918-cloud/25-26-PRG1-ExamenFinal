import java.util.Scanner;

public class SistemaReservas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Sistema de Reservas - Hotel Continental");
        procesarReserva(sc);
    }

    static void procesarReserva(Scanner sc) {
        System.out.print("Nombre del cliente: ");
        String n = sc.nextLine();
        if (n.length() < 2) {
            System.out.println("Nombre demasiado corto");
            return;
        }
        System.out.print("DNI: ");
        String d = sc.nextLine();
        if (d.length() != 9) {
            System.out.println("DNI inválido");
            return;
        }
        System.out.print("Email: ");
        String e = sc.nextLine();
        boolean ta = false;
        for (int i = 0; i < e.length(); i++) {
            if (e.charAt(i) == '@') {
                ta = true;
            }
        }
        if (!ta) {
            System.out.println("Email inválido");
            return;
        }
        System.out.print("Tipo de habitación (1=Individual, 2=Doble, 3=Suite): ");
        int t = sc.nextInt();
        double pb = 0;
        if (t == 1) {
            pb = 50;
        } else if (t == 2) {
            pb = 80;
        } else if (t == 3) {
            pb = 150;
        } else {
            System.out.println("Tipo de habitación inválido");
            return;
        }
        System.out.print("Número de noches: ");
        int no = sc.nextInt();
        if (no < 1) {
            System.out.println("Número de noches inválido");
            return;
        }
        if (no > 30) {
            System.out.println("No se pueden reservar más de 30 noches");
            return;
        }
        System.out.print("¿Es temporada alta? (s/n): ");
        char temp = sc.next().charAt(0);
        double pt = pb * no;
        if (temp == 's') {
            pt = pt * 1.30;
        }
        System.out.print("¿Desayuno incluido? (s/n): ");
        char des = sc.next().charAt(0);
        if (des == 's') {
            pt = pt + (10 * no);
        }
        System.out.print("¿Parking? (s/n): ");
        char par = sc.next().charAt(0);
        if (par == 's') {
            pt = pt + (15 * no);
        }
        System.out.print("¿Es cliente VIP? (s/n): ");
        char v = sc.next().charAt(0);
        if (v == 's') {
            pt = pt * 0.85;
        }
        int pu = 0;
        if (v == 's') {
            pu = (int)(pt * 0.10);
        } else {
            pu = (int)(pt * 0.05);
        }
        System.out.println("Resumen de tu Reserva");
        System.out.println("Cliente: " + n);
        System.out.println("DNI: " + d);
        System.out.println("Email: " + e);
        String th = "";
        if (t == 1) {
            th = "Individual";
        } else if (t == 2) {
            th = "Doble";
        } else {
            th = "Suite";
        }
        System.out.println("Tipo: " + th);
        System.out.println("Noches: " + no);
        System.out.println("Precio base por noche: " + pb + "€");
        if (temp == 's') {
            System.out.println("Recargo temporada alta: +30%");
        }
        if (des == 's') {
            System.out.println("Desayuno: " + (10 * no) + "€");
        }
        if (par == 's') {
            System.out.println("Parking: " + (15 * no) + "€");
        }
        if (v == 's') {
            System.out.println("Descuento VIP: -15%");
        }
        System.out.println("PRECIO TOTAL: " + pt + "€");
        System.out.println("Puntos ganados: " + pu);
        System.out.println("¡Reserva confirmada!");
        String nr = "RES" + (int)(Math.random() * 10000);
        System.out.println("Número de reserva: " + nr);
    }
}

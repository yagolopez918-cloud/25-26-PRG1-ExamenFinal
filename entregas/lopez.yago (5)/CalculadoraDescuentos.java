import java.util.Scanner;

public class CalculadoraDescuentos {

    private static final int MAX_PRODUCTOS = 10;

    private static final int CLIENTE_NORMAL = 1;
    private static final int CLIENTE_ESTUDIANTE = 2;
    private static final int CLIENTE_JUBILADO = 3;
    private static final int CLIENTE_VIP = 4;

    private static final double DESC_NORMAL = 0.00;
    private static final double DESC_ESTUDIANTE = 0.15;
    private static final double DESC_JUBILADO = 0.20;
    private static final double DESC_VIP = 0.30;

    private static final double DESC_REBAJAS_NORMAL = 0.10;
    private static final double DESC_REBAJAS_ESTUDIANTE = 0.10;
    private static final double DESC_REBAJAS_JUBILADO = 0.15;
    private static final double DESC_REBAJAS_VIP = 0.20;

    private static final double DESC_CANT_NORMAL = 0.05;
    private static final double DESC_CANT_ESTUDIANTE = 0.08;
    private static final double DESC_CANT_JUBILADO = 0.10;
    private static final double DESC_CANT_VIP = 0.15;

    private static final int MIN_NORMAL = 5;
    private static final int MIN_ESTUDIANTE = 3;
    private static final int MIN_JUBILADO = 2;
    private static final int MIN_VIP = 1;

    private static final double LIMITE_DESCUENTO_FIJO = 500;
    private static final double DESCUENTO_FIJO = 50;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean sistemaActivo = true;

        System.out.println("Calculadora de Descuentos - Tienda Online");

        while (sistemaActivo) {

            System.out.println("[1] Realizar Nueva Compra");
            System.out.println("[2] Salir");
            System.out.print("Opcion: ");
            int opcionMenu = scanner.nextInt();
            scanner.nextLine();

            if (opcionMenu == 2) {
                sistemaActivo = false;
                continue;
            }

            if (opcionMenu != 1) {
                System.out.println("Opcion no valida.");
                continue;
            }

            String[] nombresProductos = new String[MAX_PRODUCTOS];
            double[] preciosProductos = new double[MAX_PRODUCTOS];
            int[] cantidadesProductos = new int[MAX_PRODUCTOS];
            int numeroProductos = 0;

            while (numeroProductos < MAX_PRODUCTOS) {
                System.out.print("Nombre del producto (o 'fin'): ");
                String nombreProducto = scanner.nextLine();
                if (nombreProducto.equalsIgnoreCase("fin")) break;

                nombresProductos[numeroProductos] = nombreProducto;

                System.out.print("Precio: ");
                preciosProductos[numeroProductos] = scanner.nextDouble();

                System.out.print("Cantidad: ");
                cantidadesProductos[numeroProductos] = scanner.nextInt();
                scanner.nextLine();

                numeroProductos++;
            }

            if (numeroProductos == 0) {
                System.out.println("Carrito vacio.");
                continue;
            }

            double precioTotal = 0;
            int cantidadTotal = 0;

            for (int i = 0; i < numeroProductos; i++) {
                precioTotal += preciosProductos[i] * cantidadesProductos[i];
                cantidadTotal += cantidadesProductos[i];
            }

            System.out.print("Tipo cliente (1-4): ");
            int tipoCliente = scanner.nextInt();

            System.out.print("Rebajas? (1=Sí / 2=No): ");
            boolean hayRebajas = scanner.nextInt() == 1;

            double porcentajeDescuento = switch (tipoCliente) {
                case CLIENTE_NORMAL -> {
                    double d = DESC_NORMAL;
                    if (hayRebajas) d += DESC_REBAJAS_NORMAL;
                    if (cantidadTotal >= MIN_NORMAL) d += DESC_CANT_NORMAL;
                    yield d;
                }
                case CLIENTE_ESTUDIANTE -> {
                    double d = DESC_ESTUDIANTE;
                    if (hayRebajas) d += DESC_REBAJAS_ESTUDIANTE;
                    if (cantidadTotal >= MIN_ESTUDIANTE) d += DESC_CANT_ESTUDIANTE;
                    yield d;
                }
                case CLIENTE_JUBILADO -> {
                    double d = DESC_JUBILADO;
                    if (hayRebajas) d += DESC_REBAJAS_JUBILADO;
                    if (cantidadTotal >= MIN_JUBILADO) d += DESC_CANT_JUBILADO;
                    yield d;
                }
                case CLIENTE_VIP -> {
                    double d = DESC_VIP;
                    if (hayRebajas) d += DESC_REBAJAS_VIP;
                    if (cantidadTotal >= MIN_VIP) d += DESC_CANT_VIP;
                    yield d;
                }
                default -> 0.0;
            };

            double precioFinal = precioTotal * (1 - porcentajeDescuento);

            if (precioFinal > LIMITE_DESCUENTO_FIJO) {
                precioFinal -= DESCUENTO_FIJO;
            }

            System.out.println("Precio final: " + precioFinal + " €");
        }

        scanner.close();
    }
}

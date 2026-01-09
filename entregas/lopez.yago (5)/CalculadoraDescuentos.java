import java.util.Scanner;

public class CalculadoraDescuentos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Calculadora de Descuentos - Tienda Online");

        boolean sistemaActivo = true;

        while (sistemaActivo) {
            System.out.println("[1] Realizar Nueva Compra");
            System.out.println("[2] Salir");
            System.out.print("Opcion: ");
            int opcionMenu = scanner.nextInt();
            scanner.nextLine();

            if (opcionMenu == 2) {
                System.out.println("Saliendo del sistema.");
                sistemaActivo = false;
                continue;
            }

            if (opcionMenu != 1) {
                System.out.println("Opcion no valida.");
                continue;
            }

            String[] nombresProductos = new String[10];
            double[] preciosProductos = new double[10];
            int[] cantidadesProductos = new int[10];
            int numeroProductos = 0;

            System.out.println("Carrito de Compra");
            while (numeroProductos < 10) {
                System.out.print("Nombre del producto (o 'fin' para terminar): ");
                String nombreProducto = scanner.nextLine();

                if (nombreProducto.equalsIgnoreCase("fin")) {
                    break;
                }

                nombresProductos[numeroProductos] = nombreProducto;

                System.out.print("Precio de '" + nombreProducto + "': ");
                preciosProductos[numeroProductos] = scanner.nextDouble();

                System.out.print("Cantidad de '" + nombreProducto + "': ");
                cantidadesProductos[numeroProductos] = scanner.nextInt();
                scanner.nextLine();

                numeroProductos++;
            }

            if (numeroProductos == 0) {
                System.out.println("Carrito vacio, no se puede calcular el descuento.");
                continue;
            }

            double precioTotal = 0;
            int cantidadTotal = 0;

            for (int i = 0; i < numeroProductos; i++) {
                precioTotal += preciosProductos[i] * cantidadesProductos[i];
                cantidadTotal += cantidadesProductos[i];
            }

            System.out.println("Configuracion del Descuento");
            System.out.print("Tipo de cliente (1=Normal, 2=Estudiante, 3=Jubilado, 4=VIP): ");
            int tipoCliente = scanner.nextInt();

            System.out.print("Es temporada de rebajas? (1: Sí / 2: No): ");
            boolean hayRebajas = scanner.nextInt() == 1;

            double precioFinal = switch (tipoCliente) {
                case 1 -> {
                    double resultado = precioTotal;
                    if (hayRebajas) resultado -= resultado * 0.10;
                    if (cantidadTotal >= 5) resultado -= resultado * 0.05;
                    yield resultado;
                }
                case 2 -> {
                    double resultado = precioTotal;
                    resultado -= resultado * 0.15;
                    if (hayRebajas) resultado -= resultado * 0.10;
                    if (cantidadTotal >= 3) resultado -= resultado * 0.08;
                    yield resultado;
                }
                case 3 -> {
                    double resultado = precioTotal;
                    resultado -= resultado * 0.20;
                    if (hayRebajas) resultado -= resultado * 0.15;
                    if (cantidadTotal >= 2) resultado -= resultado * 0.10;
                    yield resultado;
                }
                case 4 -> {
                    double resultado = precioTotal;
                    resultado -= resultado * 0.30;
                    if (hayRebajas) resultado -= resultado * 0.20;
                    if (cantidadTotal >= 1) resultado -= resultado * 0.15;
                    yield resultado;
                }
                default -> precioTotal;
            };

            if (precioFinal > 500) {
                precioFinal = precioFinal - 50;
            }

            System.out.println("Resumen de Compra");
            System.out.println("Precio original total: " + precioTotal + " euros");
            System.out.println("Numero total de productos: " + cantidadTotal);
            System.out.println("Precio final con descuento: " + precioFinal + " euros");
            System.out.println("Ahorro total: " + (precioTotal - precioFinal) + " euros");

            if (precioTotal > 0) {
                double porcentajeAhorro = ((precioTotal - precioFinal) / precioTotal) * 100;
                System.out.println("Porcentaje de ahorro: " + porcentajeAhorro + "%");
            }
        }

        scanner.close();
    }
}


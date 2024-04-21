import java.util.Random;
import java.util.HashSet;
import java.util.Scanner;

public class Auxiliar {
    public static Tablero seleccionarFuncion(int opcion, Tablero tablero) {
        switch (opcion) {
            case 1:
                return tableroPropio(tablero);
            case 2:
                return tableroAlAzar(tablero);
            case 3:
                return tableroPredeterminado(tablero);
            default:
                throw new IllegalArgumentException("Opción no válida");
        }
    }

    public static Tablero tableroPredeterminado(Tablero tablero) {
        tablero = new Tablero(5, 5, 8);

        tablero.agregarAuto(1, 0, 0, 2, true);
        tablero.agregarAuto(1, 0, 1, 3, true);
        tablero.agregarAuto(2, 0, 3, 2, true);
        tablero.agregarAuto(3, 0, 4, 0, true);
        tablero.agregarAuto(1, 3, 4, 2, true);
        tablero.agregarAuto(1, 4, 0, 0, true);
        tablero.agregarAuto(1, 4, 3, 1, true);
        tablero.agregarAuto(3, 4, 4, 3, true);
        return tablero;
    }

    public static Tablero tableroAlAzar(Tablero tablero) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese las dimensiones del tablero (5, 6, o 7): ");
        int dimensiones = scanner.nextInt();
        
        if (dimensiones < 5 || dimensiones > 7) {
            throw new IllegalArgumentException("Las dimensiones deben estar entre 5 y 7.");
        }

        System.out.print("Ingrese la cantidad de autos (de 3 a 12): ");
        int cantAutos = scanner.nextInt();
        
        if (cantAutos < 3 || cantAutos > 12) {
            throw new IllegalArgumentException("La cantidad de autos debe estar entre 3 y 12.");
        }

        Random random = new Random();

        do {
            tablero = new Tablero(dimensiones, dimensiones, cantAutos);

            HashSet<String> occupiedposicions = new HashSet<>();
        
            for (int i = 0; i < cantAutos; i++) {
                int fila;
                int columna;
                String posicion;

                do {
                    fila = random.nextInt(dimensiones);
                    columna = random.nextInt(dimensiones);
                    posicion = fila + "," + columna;
                } while (occupiedposicions.contains(posicion));

                occupiedposicions.add(posicion);

                int color = random.nextInt(8) + 1;
                int orientacion = random.nextInt(4);

                tablero.agregarAuto(color, fila, columna, orientacion, true);
            }
        } while (!tablero.hayMovimientoValido());
        return tablero;
    }

    public static Tablero tableroPropio(Tablero tablero) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Ingrese las dimensiones del tablero (5, 6, o 7): ");
        int dimensiones = scanner.nextInt();
        if (dimensiones < 5 || dimensiones > 7) {
            throw new IllegalArgumentException("Las dimensiones deben ser entre 5 y 7.");
        }

        System.out.print("Ingrese la cantidad de autos (entre 3 y 12): ");
        int cantAutos = scanner.nextInt();
        if (cantAutos < 3 || cantAutos > 12) {
            throw new IllegalArgumentException("La cantidad de autos debe ser entre 3 y 12.");
        }

        tablero = new Tablero(dimensiones, dimensiones, cantAutos);

        for (int i = 0; i < cantAutos; i++) {
            System.out.print("Ingrese las coordenadas y dirección del auto " + (i + 1) + " (ejemplo: A12): ");
            String input = scanner.next();

            char filaChar = input.charAt(0);
            int columna = Character.getNumericValue(input.charAt(1));
            int direccion = Character.getNumericValue(input.charAt(2));

            int fila = filaChar - 'A';
            int color = random.nextInt(8) + 1;

            tablero.agregarAuto(color, fila, columna - 1, direccion, true);
        }

        if (!tablero.hayMovimientoValido()) {
            throw new IllegalStateException("El tablero no tiene movimientos válidos. Revise las coordenadas y direcciones.");
        }

        return tablero;
    }
}

import java.util.Random;
import java.util.HashSet;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Auxiliar {

    private static final String resetColor = "\u001B[0m";
    private static final String negrita = "\033[1m";
    private static final String colorRojo = "\u001B[31m";
    private static final String colorAmarillo = "\u001B[33m";
    private static final String colorAzul = "\u001B[34m";
    private static final String colorVerde = "\u001B[32m";

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

        System.out.print(negrita + " - Ingrese las dimensiones del tablero (5, 6, o 7): " + resetColor + "\n");
        int dimensiones = scanner.nextInt();
        
        if (dimensiones < 5 || dimensiones > 7) {
            throw new IllegalArgumentException("Las dimensiones deben estar entre 5 y 7.");
        }

        System.out.print(negrita + " - Ingrese la cantidad de autos (de 3 a 12): " + resetColor + "\n");
        int cantAutos = scanner.nextInt();
        
        if (cantAutos < 3 || cantAutos > 12) {
            throw new IllegalArgumentException("La cantidad de autos debe estar entre 3 y 12.");
        }

        Random random = new Random();

        do {
            tablero = new Tablero(dimensiones, dimensiones, cantAutos);

            HashSet<String> posicionesOcupadas = new HashSet<>();
        
            for (int i = 0; i < cantAutos; i++) {
                int fila;
                int columna;
                String posicion;

                do {
                    fila = random.nextInt(dimensiones);
                    columna = random.nextInt(dimensiones);
                    posicion = fila + "," + columna;
                } while (posicionesOcupadas.contains(posicion));

                posicionesOcupadas.add(posicion);

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

    public static int tirarMoneda() {
        Random random = new Random();
        return random.nextInt(2) + 1;
    }

    public static void printBienvenida() {
        System.out.println(colorRojo + "              ____----------- _____");
        System.out.println(" \\~~~~~~~~~~/~_--~~~------~~~~~     \\");
        System.out.println("  `---`\\  _-~      |                   \\");
        System.out.println("    _-~  <_         |                     \\[]");
        System.out.println("  / ___     ~~--[\"\"\"\"\"\"\" |      ________-------'_");
        System.out.println(" > /~` \\    |-.   `\\~~.~~~~~                _ ~ - _");
        System.out.println("  ~|  ||\\%  |       |    ~  ._                ~ _   ~ ._");
        System.out.println("    `_//|_%  \\      |          ~  .              ~-_   /\\");
        System.out.println("           `--__     |    _-____  /\\               ~-_ \\/.");
        System.out.println("                ~--_ /  ,/ -~-_ \\ \\/          _______---~/");
        System.out.println("                    ~~-/._<   \\ \\`~~~~~~~~~~~~~     ##--~/");
        System.out.println("                          \\    ) |`------##---~~~~-~  ) )");
        System.out.println("                           ~-_/_/                  ~~ ~~~" + resetColor);

        System.out.println("#####################################################");
        System.out.println("#####################################################");
        System.out.println("######### " + negrita + colorAzul + "BIENVENIDOS A AUTITOS CHOCADORES" + resetColor + " ##########");
        System.out.println("#####################################################");
        System.out.println("#####################################################");
        System.out.println();
    }

    public static void printDespedida() {
        System.out.println("Gracias por jugar!");
        System.out.println(colorAmarillo + "  ___");
        System.out.println("    _-_-  _/\\______\\__");
        System.out.println(" _-_-__  / ,-. -|-  ,-.`-.");
        System.out.println("    _-_- `( o )----( o )-'");
        System.out.println("           `-'      `-'" + resetColor);
    }

    public static void imprimirAutosConMovimientos(List<Auto> autos) {
        for (int i = 0; i < autos.size(); i++) {
            Auto car = autos.get(i);
            int fila = car.getPosicion()[0][0];  // Get row
            int columna = car.getPosicion()[0][1];  // Get column
            char filaChar = (char) ('A' + fila);  // Convert row index to letter
            String columnaStr = Integer.toString(columna + 1);  // Convert column index to 1-based number

            System.out.println(
                negrita + colorVerde + (i + 1) + ". " + resetColor + 
                negrita + filaChar + columnaStr + resetColor
            );
        }
    }
}

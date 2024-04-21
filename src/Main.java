import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String colorRojo = "\u001B[31m";
        String colorAmarillo = "\u001B[33m";
        String colorAzul = "\u001B[34m";
        String resetColor = "\u001B[0m";
        String negrita = "\033[1m";

        Scanner scanner = new Scanner(System.in);

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

        boolean estadoJuego = true;

        while (estadoJuego) {
            Usuario jugador1 = new Usuario();
            System.out.println("Ingresar el nombre del primer jugador:");
            String nombre1 = scanner.next();
            System.out.println("Ingresar la edad del primer jugador:");
            int edad1 = scanner.nextInt();
            System.out.println("Ingresar el alias del primer jugador:");
            String alias1 = scanner.next();
            jugador1.setUsuario(nombre1, edad1, alias1);

            System.out.println("#####################################################");

            Usuario jugador2 = new Usuario();
            System.out.println("Ingresar el nombre del segundo jugador:");
            String nombre2 = scanner.next();
            System.out.println("Ingresar la edad del segundo jugador:");
            int edad2 = scanner.nextInt();
            System.out.println("Ingresar el alias del segundo jugador:");
            String alias2 = (alias2 = scanner.next());
            jugador2.setUsuario(nombre2, edad2, alias2);

            int coinFlipResult = Auxiliar.tirarMoneda();
            Usuario currentPlayer = (coinFlipResult == 1) ? jugador1 : jugador2;

            System.out.println("Seleccione la configuración del tablero:");
            System.out.println("1. Tablero propio");
            System.out.println("2. Tablero al azar");
            System.out.println("3. Tablero predeterminado");

            System.out.print("Ingrese su elección (1-3): ");
            int opcion = scanner.nextInt();

            Tablero tablero = new Tablero(5, 5, 8);
            try {
                tablero = Auxiliar.seleccionarFuncion(opcion, tablero);
            } catch (IllegalArgumentException e) {
                System.out.println("Opción inválida, inténtelo de nuevo.");
                continue;
            } catch (IllegalStateException e) {
                System.out.println("El tablero no tiene movimientos válidos. Revise las coordenadas y direcciones.");
                continue;
            }

            System.out.println("Configuración del tablero:");
            tablero.mostrarTablero();

            boolean gameOver = false;

            while (!gameOver) {
                System.out.println("Es el turno de " + currentPlayer.getInfo().get(2));
                System.out.print("Ingresar la jugada (e.g., A1, E4): ");
                String jugada = scanner.next();

                try {
                    tablero.hacerJugada(jugada);
                    tablero.mostrarTablero();
                } catch (IllegalArgumentException | IllegalStateException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                if (!tablero.hayMovimientoValido()) {
                    System.out.println("No hay más movimientos válidos. El juego termino.");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == jugador1) ? jugador2 : jugador1;
                }
            }

            System.out.print("Queres jugar de nuevo? (s/n): ");
            char playAgain = scanner.next().charAt(0);
            if (playAgain == 'n') {
                estadoJuego = false;
            }
        }

        System.out.println("Gracias por jugar!");
        System.out.println(colorAmarillo + "  ___");
        System.out.println("    _-_-  _/\\______\\__");
        System.out.println(" _-_-__  / ,-. -|-  ,-.`-.");
        System.out.println("    _-_- `( o )----( o )-'");
        System.out.println("           `-'      `-'" + resetColor);
    }
}

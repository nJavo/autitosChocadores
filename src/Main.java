import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String colorRojo = "\u001B[31m";
        String colorAmarillo = "\u001B[33m";
        String colorAzul = "\u001B[34m";
        String resetColor = "\u001B[0m";
        String negrita = "\033[1m";

        Scanner scanner = new Scanner(System.in);

        Auxiliar.printBienvenida();

        boolean estadoJuego = true;

        while (estadoJuego) {
            Usuario jugador1 = new Usuario();
            System.out.println(negrita + "Ingresar el nombre del primer jugador:" + resetColor);
            String nombre1 = scanner.next();
            System.out.println(negrita + "Ingresar la edad del primer jugador:" + resetColor);
            int edad1 = scanner.nextInt();
            System.out.println(negrita + "Ingresar el alias del primer jugador:" + resetColor);
            String alias1 = scanner.next();
            jugador1.setUsuario(nombre1, edad1, alias1);

            System.out.println();
            System.out.println(negrita + "------------------------------------------------------"+ resetColor);
            System.out.println();

            Usuario jugador2 = new Usuario();
            System.out.println(negrita + "Ingresar el nombre del segundo jugador:" + resetColor);
            String nombre2 = scanner.next();
            System.out.println(negrita + "Ingresar la edad del segundo jugador:" + resetColor);
            int edad2 = scanner.nextInt();
            System.out.println(negrita + "Ingresar el alias del segundo jugador:" + resetColor);
            String alias2 = (alias2 = scanner.next());
            jugador2.setUsuario(nombre2, edad2, alias2);

            System.out.println();
            System.out.println(negrita + "------------------------------------------------------"+ resetColor);
            System.out.println();

            int coinFlipResult = Auxiliar.tirarMoneda();
            Usuario currentPlayer = (coinFlipResult == 1) ? jugador1 : jugador2;

            System.out.println(negrita + "Seleccione la configuración del tablero:\n" + resetColor);
            System.out.println(colorRojo + "1." + resetColor + negrita + "Tablero propio" + resetColor);
            System.out.println(colorRojo + "2." + resetColor + negrita + "Tablero al azar" + resetColor);
            System.out.println(colorRojo + "3." + resetColor + negrita + "Tablero predeterminado" + resetColor);

            System.out.println();

            System.out.print(negrita + "Ingrese su elección (1-3): " + resetColor + "\n");
            int opcion = scanner.nextInt();

            Tablero tablero = new Tablero(5, 5, 8);
            try {
                tablero = Auxiliar.seleccionarFuncion(opcion, tablero);
            } catch (IllegalArgumentException e) {
                System.out.println(negrita + colorRojo + "Opción inválida, inténtelo de nuevo." + resetColor);
                continue;
            } catch (IllegalStateException e) {
                System.out.println(negrita + colorRojo + "El tablero no tiene movimientos válidos. Revise las coordenadas y direcciones." + resetColor);
                continue;
            }

            System.out.println(negrita + "Configuración del tablero:" + resetColor);
            tablero.mostrarTablero();

            boolean gameOver = false;

            while (!gameOver) {
                System.out.println();
                System.out.println(negrita + "Es el turno de " + colorRojo + currentPlayer.getInfo().get(2) + resetColor);
                System.out.println();
                System.out.println(negrita + "Opciones:" + resetColor);
                System.out.println(" - " + negrita + "S" + resetColor + ": Mostrar autos con movimientos válidos");
                System.out.println(" - " + negrita + "X" + resetColor + ": Salir del juego (perder)");
                System.out.println(" - " + negrita + "R" + resetColor + ": Cambiar de turno");
                System.out.println(" - Ingrese una jugada (e.g., A1, E4)");

                System.out.print(negrita + "Ingresar la jugada: " + resetColor);

                String userInput = scanner.next();

                if (userInput.equalsIgnoreCase("S")) {
                    List<Auto> autosConMovimientos = tablero.obtenerAutosConMovimientosValidos();
                    Auxiliar.imprimirAutosConMovimientos(autosConMovimientos);
                    continue;
                }

                if (userInput.equalsIgnoreCase("X")) {
                    System.out.println("El jugador se rinde. El juego ha terminado.");
                    gameOver = true;
                    break;
                }

                if (userInput.equalsIgnoreCase("R")) {
                    tablero.rotarTablero90();
                    tablero.mostrarTablero();
                    currentPlayer = (currentPlayer == jugador1) ? jugador2 : jugador1;
                    continue;
                }

                try {
                    tablero.hacerJugada(userInput);
                    tablero.mostrarTablero();
                } catch (IllegalArgumentException | IllegalStateException e) {
                    System.out.println(colorRojo + negrita + e.getMessage() + resetColor);
                    continue;
                }

                if (!tablero.hayMovimientoValido()) {
                    System.out.println();
                    System.out.println(negrita + "No hay más movimientos válidos. El juego termino." + resetColor);
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

        System.out.println();
        System.out.println(negrita + "------------------------------------------------------"+ resetColor);
        System.out.println();
        Auxiliar.printDespedida();
    }
}

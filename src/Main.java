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
            System.out.println(negrita + "Queres jugar contra otro jugador o contra la máquina?" + resetColor);
            System.out.println(" - " + negrita + "1" + resetColor + ": 2 jugadores");
            System.out.println(" - " + negrita + "2" + resetColor + ": Jugar contra la máquina");

            System.out.print(negrita + "Elegi tu opcion: (1 o 2): " + resetColor);
            int gameMode = scanner.nextInt();

            Usuario jugador1 = new Usuario();
            System.out.println(negrita + "Ingresar el nombre del primer jugador:" + resetColor);
            String nombre1 = scanner.next();
            System.out.println(negrita + "Ingresar la edad del primer jugador:" + resetColor);
            int edad1 = scanner.nextInt();
            System.out.println(negrita + "Ingresar el alias del primer jugador:" + resetColor);
            String alias1 = scanner.next();
            jugador1.setUsuario(nombre1, edad1, alias1);

            System.out.println();
            System.out.println(negrita + "------------------------------------------------------" + resetColor);
            System.out.println();

            Usuario jugador2 = null;
            Maquina bot = null;
            int maxDepth = 0;

            if (gameMode == 1) {
                jugador2 = new Usuario();
                System.out.println(negrita + "Ingresar el nombre del segundo jugador:" + resetColor);
                String nombre2 = scanner.next();
                System.out.println(negrita + "Ingresar la edad del segundo jugador:" + resetColor);
                int edad2 = scanner.nextInt();
                System.out.println(negrita + "Ingresar el alias del segundo jugador:" + resetColor);
                String alias2 = scanner.next();
                jugador2.setUsuario(nombre2, edad2, alias2);
            } else if (gameMode == 2) {
                System.out.println(negrita + "Elegi la dificultad de la máquina:" + resetColor);
                System.out.println(" - " + negrita + "1" + resetColor + ": Fácil");
                System.out.println(" - " + negrita + "2" + resetColor + ": Medio");
                System.out.println(" - " + negrita + "3" + resetColor + ": Difícil");
                System.out.print(negrita + "Ingresa tu elección (1-3): " + resetColor);
                int dificultad = scanner.nextInt();

                switch (dificultad) {
                    case 1:
                        maxDepth = 1; // Facil
                        break;
                    case 2:
                        maxDepth = 3; // Mediano
                        break;
                    case 3:
                        maxDepth = 8; // Dificl
                        break;
                }

                bot = new Maquina();
                jugador2 = null;
            }

            System.out.println();
            System.out.println(negrita + "------------------------------------------------------" + resetColor);
            System.out.println();

            int coinFlipResult = Auxiliar.tirarMoneda();
            

            Tablero tablero = new Tablero(5, 5, 8);
            try {
                System.out.println(negrita + "Elegi la configuración del tablero:" + resetColor);
                System.out.println(colorRojo + "1." + resetColor + negrita + "Tablero propio" + resetColor);
                System.out.println(colorRojo + "2." + resetColor + negrita + "Tablero al azar" + resetColor);
                System.out.println(colorRojo + "3." + resetColor + negrita + "Tablero predeterminado" + resetColor);

                int opcion = scanner.nextInt();

                tablero = Auxiliar.seleccionarFuncion(opcion, tablero);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(negrita + colorRojo + e.getMessage() + resetColor);
                return;
            }

            tablero.mostrarTablero();

            boolean gameOver = false;
            boolean isBotMode = (gameMode == 2);
            Usuario currentPlayer = (coinFlipResult == 1) ? jugador1 : (isBotMode ? jugador1 : jugador2);


            while (!gameOver) {
                System.out.println(negrita + "Es el turno de " + (currentPlayer == null ? "la máquina" : currentPlayer.getInfo().get(2)) + resetColor);

                if (isBotMode && currentPlayer == null) {
                    System.out.println(negrita + "La máquina está pensando..." + resetColor);
                    String bestMove = bot.encontrarMejorMovimiento(tablero, maxDepth);
                    tablero.hacerJugada(bestMove);
                    tablero.mostrarTablero();
                    System.out.println(negrita + "La máquina hizo su jugada: " + bestMove + resetColor);
                    currentPlayer = jugador1;
                } else {
                    System.out.println();
                    System.out.println(negrita + "Opciones:" + resetColor);
                    System.out.println(" - " + negrita + "S" + resetColor + ": Mostrar autos con movimientos válidos");
                    System.out.println(" - " + negrita + "X" + resetColor + ": Salir del juego (perder)");
                    System.out.println(" - " + negrita + "R" + resetColor + ": Rotar el tablero (Skippeas el turno)");
                    System.out.println(" - Ingrese una jugada (e.g., A1, E4)");
                    String jugada = scanner.next();

                    if (jugada.equalsIgnoreCase("S")) {
                        List<Auto> autosConMovimientos = tablero.obtenerAutosConMovimientosValidos();
                        Auxiliar.imprimirAutosConMovimientos(autosConMovimientos);
                        continue;
                    }

                    if (jugada.equalsIgnoreCase("X")) {
                        System.out.println("El jugador se rinde. El juego termino.");
                        gameOver = true;
                        break;
                    }

                    if (jugada.equalsIgnoreCase("R")) {
                        tablero.rotarTablero90();
                        tablero.mostrarTablero();
                        currentPlayer = (isBotMode ? jugador1 : (currentPlayer == jugador1) ? jugador2 : jugador1);
                        continue;
                    }

                    try {
                        tablero.hacerJugada(jugada);
                        tablero.mostrarTablero();
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println(negrita + colorRojo + e.getMessage() + resetColor);
                        continue;
                    }

                    currentPlayer = (isBotMode ? null : (currentPlayer == jugador1) ? jugador2 : jugador1);
                }

                if (!tablero.hayMovimientoValido()) {
                    System.out.println(negrita + "No hay más movimientos válidos. El juego terminó." + resetColor);
                    gameOver = true;
                }
            }

            System.out.print("Queres jugar de nuevo? (s/n): ");
            char playAgain = scanner.next().charAt(0);
            if (playAgain == 'n') {

                System.out.println();
                System.out.println(negrita + "------------------------------------------------------" + resetColor);
                System.out.println();

                Auxiliar.printDespedida();
                estadoJuego = false;
            }
        }
    }
}

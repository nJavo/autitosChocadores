import java.util.*;

public class Test {
    public static void main(String[] args) {
        String colorRojo = "\u001B[31m";
        String resetColor = "\u001B[0m";
        String negrita = "\033[1m";

        Random random = new Random();
        int scoreCalculator = 0;
        int scoreRandom = 0;
        int n = 0;
        while (n < 1000) {
            Tablero tableroTest = Auxiliar.tableroCompletamenteAlAzar();
            Maquina calculador = new Maquina();
            Maquina calculador2 = new Maquina();
            boolean isCalculatorTurn = false;
            boolean gameOver = false;

            while (!gameOver) {
                List<Auto> autosConMovimientos = tableroTest.obtenerAutosConMovimientosValidos();
                for (Auto auto : autosConMovimientos) {
                }
                if (isCalculatorTurn) {
                    String mejorMovimiento = calculador.encontrarMejorMovimiento(tableroTest, 7);
                    System.out.println("Mejor movimiento: " + mejorMovimiento);
                    tableroTest.hacerJugada(mejorMovimiento);
                } else {
                    // Auto auto = autosConMovimientos.get(random.nextInt(autosConMovimientos.size()));
                    // String jugada = obtenerJugada(auto);
                    // System.out.println("Jugada random: " + jugada);
                    String jugada = calculador2.encontrarMejorMovimiento(tableroTest, 5);
                    tableroTest.hacerJugada(jugada);
                }

                if (!tableroTest.hayMovimientoValido()) {
                    gameOver = true;
                    if (isCalculatorTurn) {
                        scoreCalculator++;
                    } else {
                        scoreRandom++;
                    }
                } else {
                    isCalculatorTurn = !isCalculatorTurn;
                }
            }

            System.out.println(negrita + "Score Calculator: " + colorRojo + scoreCalculator + resetColor);
            System.out.println(negrita + "Score Random: " + colorRojo + scoreRandom + resetColor);
            n++;
        }
    }

    private static String obtenerJugada(Auto auto) {
        int fila = auto.getPosicion()[0][0];
        int columna = auto.getPosicion()[0][1];
        char filaChar = (char) ('A' + fila);
        String columnaStr = Integer.toString(columna + 1);
        return filaChar + columnaStr;
    }
}

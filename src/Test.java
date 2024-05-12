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
        boolean stillTrue = true;
        int value = 1;
        boolean isCalculatorTurn = false;
        // for (int i = 0; i < 15; i++) {
        //     n = 0;
        //     scoreCalculator = 0;
        while (true) {
            Tablero tableroTest = Auxiliar.tableroCompletamenteAlAzar();
            Maquina calculador = new Maquina();
            Maquina calculadorMax = new Maquina();
            Maquina calculador2 = new Maquina();
            isCalculatorTurn = !isCalculatorTurn;
            boolean gameOver = false;

            while (!gameOver) {
                List<Auto> autosConMovimientos = tableroTest.obtenerAutosConMovimientosValidos();
                for (Auto auto : autosConMovimientos) {
                }
                System.out.println("Calculadora max: " + calculadorMax.encontrarMejorMovimiento(tableroTest, Integer.MAX_VALUE) + "\n");
                if (isCalculatorTurn) {
                    String mejorMovimiento = calculador.encontrarMejorMovimiento(tableroTest, 5);
                    System.out.println("Movimiento depth 5: " + mejorMovimiento + "\n \n");
                    tableroTest.hacerJugada(mejorMovimiento);
                } else {
                    // Auto auto = autosConMovimientos.get(random.nextInt(autosConMovimientos.size()));
                    // String jugada = obtenerJugada(auto);
                    String jugada = calculador2.encontrarMejorMovimiento(tableroTest, 6);
                    System.out.println("Movimiento depth 6: " + jugada + "\n \n");
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

            System.out.println(negrita + "Score Calculator 1: " + colorRojo + scoreCalculator + resetColor);
            System.out.println(negrita + "Score Calculator 2: " + colorRojo + scoreRandom + resetColor);
            System.out.println();
            n++;
        }
        //     System.out.println(value);
        //     System.out.println(negrita + "Score Calculator: " + colorRojo + scoreCalculator + resetColor);            
        //     value++;
        // }
    }

    private static String obtenerJugada(Auto auto) {
        int fila = auto.getPosicion()[0][0];
        int columna = auto.getPosicion()[0][1];
        char filaChar = (char) ('A' + fila);
        String columnaStr = Integer.toString(columna + 1);
        return filaChar + columnaStr;
    }
}

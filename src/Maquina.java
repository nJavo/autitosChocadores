import java.util.*;
import java.util.AbstractMap;

public class Maquina {

    public String encontrarMejorMovimiento(Tablero tablero, int maxDepth) {
        Tablero clone = new Tablero(tablero);
        Map.Entry<String, Integer> mejorMovimiento = simular(clone, true, 0, maxDepth);
        System.out.println("Puntuación: " + mejorMovimiento.getValue());
        return mejorMovimiento.getKey();
    }

    private Map.Entry<String, Integer> simular(Tablero tablero, boolean turnoMaximizador, int profundidad, int maxDepth) {
        if (profundidad >= maxDepth || !tablero.hayMovimientoValido()) {
            return new AbstractMap.SimpleEntry<>("", evaluarTablero(tablero, !turnoMaximizador));
        }

        List<Auto> autosConMovimientos = tablero.obtenerAutosConMovimientosValidos();
        Map.Entry<String, Integer> mejorMovimiento = null;

        if (turnoMaximizador) {
            int mejorPuntuacion = Integer.MIN_VALUE;

            for (Auto auto : autosConMovimientos) {
                String jugada = obtenerJugada(auto);
                Tablero nuevoTablero = clonarTablero(tablero);
                Auto nuevoAuto = obtenerAutoEnPosicion(nuevoTablero, auto.getPosicion());

                nuevoTablero.hacerJugada(jugada);

                Map.Entry<String, Integer> resultado = simular(nuevoTablero, false, profundidad + 1, maxDepth);
                int puntuacion = resultado.getValue();

                if (puntuacion > mejorPuntuacion) {
                    mejorPuntuacion = puntuacion;
                    mejorMovimiento = new AbstractMap.SimpleEntry<>(jugada, puntuacion);
                }
            }
        } else {
            int peorPuntuacion = Integer.MAX_VALUE;

            for (Auto auto : autosConMovimientos) {
                String jugada = obtenerJugada(auto);
                Tablero nuevoTablero = clonarTablero(tablero);
                Auto nuevoAuto = obtenerAutoEnPosicion(nuevoTablero, auto.getPosicion());

                if (nuevoAuto != null && nuevoTablero.tieneMovimientosValidos(nuevoAuto)) {
                    nuevoTablero.hacerJugada(jugada);

                    Map.Entry<String, Integer> resultado = simular(nuevoTablero, true, profundidad + 1, maxDepth);
                    int puntuacion = resultado.getValue();

                    if (puntuacion < peorPuntuacion) {
                        peorPuntuacion = puntuacion;
                        mejorMovimiento = new AbstractMap.SimpleEntry<>(jugada, puntuacion);
                    }
                }
            }
        }

        return mejorMovimiento != null ? mejorMovimiento : new AbstractMap.SimpleEntry<>("", 0);
    }

    private int evaluarTablero(Tablero tablero, boolean turnoMaximizador) {
        int availableMoves = tablero.obtenerAutosConMovimientosValidos().size();
        int clusterBonus = turnoMaximizador ? calcularCluster(tablero) : -calcularCluster(tablero);
        int bonusForWin = !tablero.hayMovimientoValido() ? (turnoMaximizador ? 100 : -100) : 0;
        int blockedMoves = contarMovimientosBloqueados(tablero);

        return availableMoves - blockedMoves + clusterBonus + bonusForWin;
    }

    private int calcularCluster(Tablero tablero) {
        int clusters = 0;

        for (int i = 0; i < tablero.getFilas() - 1; i++) {
            for (int j = 0; j < tablero.getColumnas() - 1; j++) {
                int count = 0;

                if (tablero.getMatriz()[i][j] != null) count++;
                if (tablero.getMatriz()[i + 1][j] != null) count++;
                if (tablero.getMatriz()[i][j + 1] != null) count++;
                if (tablero.getMatriz()[i + 1][j + 1] != null) count++;

                if (count >= 3) {
                    clusters++;
                }
            }
        }

        return clusters * 10; // Bonus for each cluster of autos
    }

    private int contarMovimientosBloqueados(Tablero tablero) {
        int blockedCount = 0;
        for (Auto[] fila : tablero.getMatriz()) {
            for (Auto auto : fila) {
                if (auto != null && !tablero.tieneMovimientosValidos(auto)) {
                    blockedCount++;
                }
            }
        }
        return blockedCount;
    }


    private String obtenerJugada(Auto auto) {
        int fila = auto.getPosicion()[0][0];
        int columna = auto.getPosicion()[0][1] + 1;
        char filaChar = (char) ('A' + fila);
        return filaChar + String.valueOf(columna);
    }

    private Tablero clonarTablero(Tablero original) {
        return new Tablero(original);
    }

    private Auto obtenerAutoEnPosicion(Tablero tablero, int[][] posicion) {
        int fila = posicion[0][0];
        int columna = posicion[0][1];
        return tablero.getMatriz()[fila][columna];
    }
}

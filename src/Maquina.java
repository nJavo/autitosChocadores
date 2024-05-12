import java.util.*;
import java.util.AbstractMap;

public class Maquina {

    public String encontrarMejorMovimiento(Tablero tablero, int maxDepth) {
        Tablero clone = new Tablero(tablero);
        Map.Entry<String, Integer> mejorMovimiento = simular(clone, true, 0, maxDepth);
        System.out.println("Puntuación: " + mejorMovimiento.getValue() + " - MaxDepth: " + maxDepth);
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
        int bonusForWin = !tablero.hayMovimientoValido() ? (turnoMaximizador ? 100 : -100) : 0;

        return availableMoves + bonusForWin;
    }

    // private int evaluarTablero(Tablero tablero, boolean turnoMaximizador) {
    //     int cantidadMovimientosValidos = tablero.obtenerAutosConMovimientosValidos().size();
        
    //     int movimientosValidos = cantidadMovimientosValidos == 1 ? 100 : cantidadMovimientosValidos;

    //     int bonusPar = (movimientosValidos != 0) ? ((movimientosValidos % 2 == 0) ? 0 : 10) : 0;

    //     int bonusForWin = !tablero.hayMovimientoValido() ? (turnoMaximizador ? 100 : -100) : 0;

    //     return movimientosValidos + bonusPar + bonusForWin;
    // }

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

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Tablero {
    private int filas;
    private int columnas;
    private int cantidadAutos;
    private Auto[][] matriz;

    public Tablero(int filas, int columnas, int cantidadAutos) {
        this.filas = filas;
        this.columnas = columnas;
        this.cantidadAutos = cantidadAutos;
        this.matriz = new Auto[filas][columnas];
        inicializarTablero();
    }

    public Auto[][] getMatriz() {
        return matriz;
    }

    private void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = null;
            }
        }
    }

    public void rotarTablero90() {
        int nuevasFilas = columnas;
        int nuevasColumnas = filas;
        Auto[][] nuevaMatriz = new Auto[nuevasFilas][nuevasColumnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Auto auto = matriz[i][j];
                if (auto != null) {
                    int nuevaFila = j;
                    int nuevaColumna = nuevasColumnas - 1 - i;
                    
                    int nuevaOrientacion = (auto.getOrientacion() + 1) % 4;

                    auto.cambiarPosicion(nuevaFila, nuevaColumna);
                    auto.cambiarOrientacion(nuevaOrientacion);

                    nuevaMatriz[nuevaFila][nuevaColumna] = auto;
                }
            }
        }

        this.filas = nuevasFilas;
        this.columnas = nuevasColumnas;
        this.matriz = nuevaMatriz;
    }

    public void agregarAuto(int color, int fila, int columna, int orientacion, boolean estado) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            throw new IllegalArgumentException("Índices de fila o columna fuera de límites");
        }

        Auto auto = new Auto(color, fila, columna, orientacion, estado);
        matriz[fila][columna] = auto;
    }

    public boolean esMovimientoValido(Auto auto, int nuevaOrientacion) {
        int filaActual = auto.getPosicion()[0][0];
        int columnaActual = auto.getPosicion()[0][1];
        int orientacionActual = auto.getOrientacion();

        if (nuevaOrientacion == orientacionActual) {
            return false;
        }

        switch (nuevaOrientacion) {
            case 0:  // Arriba
                for (int i = filaActual - 1; i >= 0; i--) {
                    if (matriz[i][columnaActual] != null) {
                        return true;
                    }
                }
                break;

            case 1:  // Derecha
                for (int i = columnaActual + 1; i < columnas; i++) {
                    if (matriz[filaActual][i] != null) {
                        return true;
                    }
                }
                break;

            case 2:  // Abajo
                for (int i = filaActual + 1; i < filas; i++) {
                    if (matriz[i][columnaActual] != null) {
                        return true;
                    }
                }
                break;

            case 3:  // Izquierda
                for (int i = columnaActual - 1; i >= 0; i--) {
                    if (matriz[filaActual][i] != null) {
                        return true;
                    }
                }
                break;

            default:
                throw new IllegalArgumentException("Orientación no válida");
        }

        return false;
    }

    public boolean tieneMovimientosValidos(Auto auto) {
        for (int orientacion = 0; orientacion < 4; orientacion++) {
            if (esMovimientoValido(auto, orientacion)) {
                return true;
            }
        }
        return false;
    }

    public boolean hayMovimientoValido() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Auto auto = matriz[i][j];
                if (auto != null) {
                    if (tieneMovimientosValidos(auto)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Auto> obtenerAutosConMovimientosValidos() {
        List<Auto> autosConMovimientos = new ArrayList<>();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Auto auto = matriz[i][j];
                if (auto != null && tieneMovimientosValidos(auto)) {
                    autosConMovimientos.add(auto);
                }
            }
        }

        return autosConMovimientos;
    }

    public void moverAuto(Auto auto, int orientacion) {
        int fila = auto.getPosicion()[0][0];
        int columna = auto.getPosicion()[0][1];

        switch (orientacion) {
            case 0:  // Arriba
                for (int r = fila - 1; r >= 0; r--) {
                    if (matriz[r][columna] != null) {
                        matriz[r][columna] = auto;
                        matriz[fila][columna] = null;
                        auto.cambiarPosicion(r, columna);
                        auto.cambiarOrientacion(orientacion);
                        return;
                    }
                }
                break;

            case 1:  // Derecha
                for (int c = columna + 1; c < columnas; c++) {
                    if (matriz[fila][c] != null) {
                        matriz[fila][c] = auto;
                        matriz[fila][columna] = null;
                        auto.cambiarPosicion(fila, c);
                        auto.cambiarOrientacion(orientacion);
                        return;
                    }
                }
                break;

            case 2:  // Abajo
                for (int r = fila + 1; r < filas; r++) {
                    if (matriz[r][columna] != null) {
                        matriz[r][columna] = auto;
                        matriz[fila][columna] = null;
                        auto.cambiarPosicion(r, columna);
                        auto.cambiarOrientacion(orientacion);
                        return;
                    }
                }
                break;

            case 3:  // Izquierda
                for (int c = columna - 1; c >= 0; c--) {
                    if (matriz[fila][c] != null) {
                        matriz[fila][c] = auto;
                        matriz[fila][columna] = null;
                        auto.cambiarPosicion(fila, c);
                        auto.cambiarOrientacion(orientacion);
                        return;
                    }
                }
                break;
        }
    }

    public void hacerJugada(String jugada) {
        char filaChar = jugada.charAt(0);
        int columna = Character.getNumericValue(jugada.charAt(1)) - 1;
        int fila = filaChar - 'A';

        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            throw new IllegalArgumentException("Coordenadas fuera de límites.");
        }

        Auto auto = matriz[fila][columna];
        if (auto == null) {
            throw new IllegalArgumentException("No hay auto en las coordenadas especificadas.");
        }

        if (!tieneMovimientosValidos(auto)) {
            throw new IllegalStateException("El auto no tiene movimientos válidos.");
        }

        boolean moveMade = false;

        int currentOrientation = auto.getOrientacion();
        for (int i = 1; i <= 4; i++) {
            int nuevaOrientacion = (currentOrientation + i) % 4;
            if (esMovimientoValido(auto, nuevaOrientacion)) {
                System.out.println("Moviendo auto en dirección " + nuevaOrientacion);
                moverAuto(auto, nuevaOrientacion);
                moveMade = true;
                break;
            }
        }

        if (!moveMade) {
            throw new IllegalStateException("No se pudo mover el auto en ninguna dirección.");
        }
    }

    public void mostrarTablero() {
        String[] identColumna = new String[columnas];
        for (int i = 0; i < columnas; i++) {
            identColumna[i] = String.valueOf(i + 1);
        }

        String[] identFila = new String[filas];
        for (int i = 0; i < filas; i++) {
            identFila[i] = String.valueOf((char) ('A' + i));
        }

        System.out.print(" ");
        for (String colId : identColumna) {
            System.out.print("  " + colId + "  ");
        }
        System.out.println();

        for (int fila = 0; fila < filas; fila++) {
            System.out.print("  +");
            for (int j = 0; j < columnas; j++) {
                System.out.print("----+");
            }
            System.out.println();

            for (int row = 0; row < 4; row++) {
                if (row == 0) {
                    System.out.print(identFila[fila] + " |");
                } 
                else System.out.print("  |");
                
                for (int j = 0; j < columnas; j++) {
                    Auto auto = matriz[fila][j];
                    if (auto != null) {
                        String[] lines = auto.toString().split("\n");
                        if (auto.getOrientacion() == 0 || auto.getOrientacion() == 2) {
                            System.out.print(" " + lines[row] + " |");
                        }
                        if (auto.getOrientacion() == 1 || auto.getOrientacion() == 3) {
                            if (row == 0 || row == 3) {
                                System.out.print("    |");
                            } else {
                                System.out.print("" + lines[row / 2] + "|");
                            }
                        }
                    } else {
                        System.out.print("    |");
                    }
                }
                System.out.println();
            }
        }

        System.out.print("  ");
        for (int j = 0; j < columnas; j++) {
            System.out.print("+----");
        }
        System.out.println("+");
    }
}
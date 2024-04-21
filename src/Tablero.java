import java.util.Scanner;

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

    public boolean tieneMovimientosValidos(Auto car) {
        for (int orientacion = 0; orientacion < 4; orientacion++) {
            if (esMovimientoValido(car, orientacion)) {
                return true;
            }
        }
        return false;
    }

    public boolean hayMovimientoValido() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Auto car = matriz[i][j];
                if (car != null) {
                    if (tieneMovimientosValidos(car)) {
                        return true;
                    }
                }
            }
        }
        return false;
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
                    Auto car = matriz[fila][j];
                    if (car != null) {
                        String[] lines = car.toString().split("\n");
                        if (car.getOrientacion() == 0 || car.getOrientacion() == 2) {
                            System.out.print(" " + lines[row] + " |");
                        }
                        if (car.getOrientacion() == 1 || car.getOrientacion() == 3) {
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
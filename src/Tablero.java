import java.util.Scanner;

public class Tablero {
    private int filas;
    private int columnas;
    private int cantidadAutos;
    public Auto[][] matriz;

    public Tablero(int filas, int columnas, int cantidadAutos) {
        this.filas = filas;
        this.columnas = columnas;
        this.cantidadAutos = cantidadAutos;
        this.matriz = new Auto[filas][columnas];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = null;
            }
        }
    }

    public void configurarTablero() {
        Scanner scanner = new Scanner(System.in);
        String[] colores = {"R", "A", "V", "Y", "N", "M", "C", "P"};

        for (int i = 0; i < cantidadAutos; i++) {
            System.out.print("Ingresar las coordenadas y dirección del auto " + (i + 1) + " (ejemplo: A12): ");
            String input = scanner.nextLine();

            char fila = input.charAt(0);
            int columna = Character.getNumericValue(input.charAt(1));
            int direccion = Character.getNumericValue(input.charAt(2));

            int filaIndex = fila - 'A';
            int columnaIndex = columna - 1;

            // matriz[filaIndex][columnaIndex] = colores[i];
        }
    }

    public boolean verificarTablero() {
        return true; // Placeholder, cambiar después tengo que hacer la lógica
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
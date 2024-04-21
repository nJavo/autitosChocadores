import java.util.Scanner;

public class Tablero {
    private int filas;
    private int columnas;
    private int cantidadAutos;
    private String[][] matriz;

    public Tablero(int filas, int columnas, int cantidadAutos) {
        this.filas = filas;
        this.columnas = columnas;
        this.cantidadAutos = cantidadAutos;
        this.matriz = new String[filas][columnas];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = ".";
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

            matriz[filaIndex][columnaIndex] = colores[i];
        }
    }

    public boolean verificarTablero() {
        return true; // Placeholder, cambiar después tengo que hacer la lógica
    }

    public void mostrarTablero() {
        for (String[] fila : matriz) {
            for (String celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }
}
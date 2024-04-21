public class Tablero {
    private int filas;
    private int columnas;
    private int cantidadAutos;
    private String[][] matriz;
    private List<Auto> listaAutos;

    public Tablero(int filas, int columnas, int cantidadAutos) {
        this.filas = filas;
        this.columnas = columnas;
        this.cantidadAutos = cantidadAutos;
        this.matriz = new String[filas][columnas];
        this.listaAutos = new ArrayList<>();
    }

    public void configurarTablero() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < cantidadAutos; i++) {
            System.out.print("Ingresar las coordenadas y direccion del auto " + (i + 1) + " (ejemplo: A12): ");
            String input = scanner.nextLine();

            char fila = input.charAt(0);
            int columna = Character.getNumericValue(input.charAt(1));
            int direccion = Character.getNumericValue(input.charAt(2));

            int filaIndex = fila - 'A';
            int columnaIndex = columna - 1;

            Auto auto = new Auto(fila, columna, direccion);
            listaAutos.add(auto);

            matriz[filaIndex][columnaIndex] = auto.getColor();
        }
    }

    public boolean verificarTablero() {
        return true; // Placeholder, cambiar despues tengo que hacer la logica
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
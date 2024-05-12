import java.util.Random;

public class Auto {
    private static final String[] COLORS = {
        "\u001B[31m", // Rojo
        "\u001B[34m", // Azul
        "\u001B[32m", // Verde
        "\u001B[33m", // Amarillo
        "\u001B[35m", // Violeta
        "\u001B[36m"  // Cyan
    };

    private int colorIndex;
    private String color;
    private int[][] posicion;
    private int orientacion;
    private boolean estado;

    public Auto(int colorIndex, int fila, int columna, int orientacion, boolean estado) {
        if (colorIndex == 0) {
            Random random = new Random();
            this.colorIndex = random.nextInt(COLORS.length) + 1;
        } else if (colorIndex > 0 && colorIndex <= COLORS.length) {
            this.colorIndex = colorIndex;
        } else {
            throw new IllegalArgumentException("Indice fuera de rango.");
        }
        this.color = COLORS[this.colorIndex - 1];
        this.posicion = new int[2][2];
        this.posicion[0][0] = fila;
        this.posicion[0][1] = columna;
        this.orientacion = orientacion;
        this.estado = estado;
    }

    public int getColor() {
        return this.colorIndex;
    }

    public int[][] getPosicion() {
        return posicion;
    }

    public int getOrientacion() {
        return orientacion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void cambiarOrientacion(int nuevaOrientacion) {
        this.orientacion = nuevaOrientacion;
    }

    public void cambiarEstado(boolean nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void cambiarPosicion(int nuevaFila, int nuevaColumna) {
        this.posicion[0][0] = nuevaFila;
        this.posicion[0][1] = nuevaColumna;
    }

    public String toString() {
        String autoReturn = "";
        String colorAzul = "\u001B[34m";
        String colorRojo = "\u001B[31m";
        String colorAmarillo = "\u001B[43m";
        String resetColor = "\u001B[0m";

        switch (orientacion) {
            case 0: // Arriba
                autoReturn = colorAmarillo + colorRojo + "oo" + resetColor + "\n" + color + "**" + resetColor + "\n" + color + "**" + resetColor + "\n" + color + "**" + resetColor + "\n";
                break;
            case 1: // Derecha
                autoReturn = color + "***" + colorAmarillo + colorRojo + "o" + resetColor + "\n"
                        + color + "***" + colorAmarillo + colorRojo + "o" + resetColor;
                break;
            case 2: // Abajo
                autoReturn = color + "**" + resetColor + "\n" + color + "**" + resetColor + "\n" + color + "**" + resetColor + "\n" + colorAmarillo + colorRojo + "oo" + resetColor + "\n";
                break;
            case 3: // Izquierda
                autoReturn = colorAmarillo + colorRojo + "o" + resetColor + color + "***" + resetColor + "\n" +
                        colorAmarillo + colorRojo + "o" + resetColor + color + "***" + resetColor;
                break;
        }

        return autoReturn;
    }
}
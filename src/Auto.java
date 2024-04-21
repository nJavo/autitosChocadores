public class Auto {
    private int color;
    private int[][] posicion;
    private int orientacion;
    private boolean estado;

    public Auto(int color, int fila, int columna, int orientacion, boolean estado) {
        this.color = color;
        this.posicion = new int[2][2];
        this.posicion[0][0] = fila;
        this.posicion[0][1] = columna;
        this.orientacion = orientacion;
        this.estado = estado;
    }

    public int getColor() {
        return color;
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
                autoReturn = colorAmarillo + colorRojo + "oo" + resetColor + "\n" + colorAzul + "**" + "\n" + colorAzul + "**" + "\n" + colorAzul + "**" + "\n"  + resetColor;
                break;
            case 1: // Derecha
                autoReturn = colorAzul + "***" + colorAmarillo + colorRojo + "o" + resetColor + "\n"
                        + colorAzul + "***" + colorAmarillo + colorRojo + "o" + resetColor;
                break;
            case 2: // Abajo
                autoReturn = colorAzul + "**" + resetColor + "\n" + colorAzul + "**" + "\n" + colorAmarillo + colorRojo + "oo" + resetColor + "\n";
                break;
            case 3: // Izquierda
                autoReturn = colorAmarillo + colorRojo + "o" + resetColor + colorAzul + "***" + "\n" +
                        colorAmarillo + colorRojo + "o" + resetColor + colorAzul + "***" + resetColor;
                break;
        }

        return autoReturn;
    }
}
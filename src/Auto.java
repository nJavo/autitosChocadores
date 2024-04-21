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
}
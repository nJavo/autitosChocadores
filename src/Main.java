public class Main {
    public static void main(String[] args) {
        Tablero tablero = new Tablero(5, 5, 8);

        tablero.matriz[0][0] = "R";
        tablero.matriz[0][2] = "A";
        tablero.matriz[1][1] = "V";
        tablero.matriz[1][3] = "Y";
        tablero.matriz[2][1] = "N";
        tablero.matriz[3][0] = "M";
        tablero.matriz[3][3] = "C";
        tablero.matriz[4][3] = "P";

        tablero.mostrarTablero();
    }
}
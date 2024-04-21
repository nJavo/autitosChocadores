public class Main {
    public static void main(String[] args) {
        Tablero tablero = new Tablero(5, 5, 8);

        Auto auto1 = new Auto(1, 0, 0, 0, true);
        Auto auto2 = new Auto(2, 0, 2, 1, true);
        Auto auto3 = new Auto(3, 1, 1, 2, true);
        Auto auto4 = new Auto(4, 1, 3, 3, true);
        Auto auto5 = new Auto(5, 2, 1, 0, true);
        Auto auto6 = new Auto(6, 3, 0, 1, true);
        Auto auto7 = new Auto(7, 3, 3, 2, true);
        Auto auto8 = new Auto(8, 4, 3, 3, true);
        System.out.println(auto1.toString());
        System.out.println("\n");
        System.out.println(auto2.toString());
        System.out.println("\n");
        System.out.println(auto3.toString());
        System.out.println("\n");
        System.out.println(auto4.toString());
        System.out.println("\n");

        tablero.matriz[0][0] = auto1;
        tablero.matriz[0][2] = auto2;
        tablero.matriz[1][1] = auto3;
        tablero.matriz[1][3] = auto4;
        tablero.matriz[2][1] = auto5;
        tablero.matriz[3][0] = auto6;
        tablero.matriz[3][3] = auto7;
        tablero.matriz[4][3] = auto8;

        tablero.mostrarTablero();
    }
}
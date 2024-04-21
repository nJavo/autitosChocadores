import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("              ____----------- _____");
        System.out.println(" \\~~~~~~~~~~/~_--~~~------~~~~~     \\");
        System.out.println("  `---`\\  _-~      |                   \\");
        System.out.println("    _-~  <_         |                     \\[]");
        System.out.println("  / ___     ~~--[\"\"\"\"\"\"\" |      ________-------'_");
        System.out.println(" > /~` \\    |-.   `\\~~.~~~~~                _ ~ - _");
        System.out.println("  ~|  ||\\%  |       |    ~  ._                ~ _   ~ ._");
        System.out.println("    `_//|_%  \\      |          ~  .              ~-_   /\\");
        System.out.println("           `--__     |    _-____  /\\               ~-_ \\/.");
        System.out.println("                ~--_ /  ,/ -~-_ \\ \\/          _______---~/");
        System.out.println("                    ~~-/._<   \\ \\`~~~~~~~~~~~~~     ##--~/");
        System.out.println("                          \\    ) |`------##---~~~~-~  ) )");
        System.out.println("                           ~-_/_/                  ~~ ~~~");

        System.out.println("#####################################################");
        System.out.println("#####################################################");
        System.out.println("########## BIENVENIDOS A AUTITOS CHOCADORES #########");
        System.out.println("#####################################################");
        System.out.println("#####################################################");
        boolean estadoJuego = true;

        while (estadoJuego) {
            System.out.println("Selecciona la configuración del tablero:");
            System.out.println("1. Tablero propio");
            System.out.println("2. Tablero al azar");
            System.out.println("3. Tablero predeterminado");

            System.out.print("Ingresa su elección (1-3): ");
            int opcion = scanner.nextInt();

            Tablero tablero = new Tablero(5, 5, 8);
            try {
                tablero = Auxiliar.seleccionarFuncion(opcion, tablero);
            } catch (IllegalArgumentException e) {
                System.out.println("Opcion inválida, intentalo de nuevo.");
                continue;
            } catch (IllegalStateException e) {
                System.out.println("El tablero no tiene movimientos validos. Volve a configurarlo.");
                continue;
            }

            System.out.println("Configuracion del tablero:");
            tablero.mostrarTablero();

            System.out.print("Queres seguir jugando? (s/n): ");
            char continuar = scanner.next().charAt(0);
            if (continuar == 'n') {
                estadoJuego = false;
            }
        }

        System.out.println("Gracias por jugar!");
        System.out.println("  ___");
        System.out.println("    _-_-  _/\\______\\__");
        System.out.println(" _-_-__  / ,-. -|-  ,-.`-.");
        System.out.println("    _-_- `( o )----( o )-'");
        System.out.println("           `-'      `-'");
    }
}

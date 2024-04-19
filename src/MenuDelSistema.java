import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuDelSistema {

    public static double calcularElValor(double cantidad, Divisa divisa, String nombreDivisa) {
        return cantidad * divisa.conversion_rates().get(nombreDivisa);
    }

    public static String mensajeDeLaConversion(Scanner scanner, String nombreDivisa, String nombreDivisaAConvertir) {

        // Crear un objeto DecimalFormat con el formato deseado
        DecimalFormat df = new DecimalFormat("#.###");


        ManejadorHttp consulta = new ManejadorHttp();
        Divisa divisa = null;
        try {
            divisa = consulta.obtenerDivisa(nombreDivisa);
            System.out.println("Ingrese la cantidad que desea convertir: ");
            double cantidad = scanner.nextDouble();

            double valorDeLaConversion = calcularElValor(cantidad, divisa, nombreDivisaAConvertir);
            // Aplicar el formato al número
            String resultadoFormateado = df.format(valorDeLaConversion);
            return "\n*******************************************************************\n" +
                    "El valor " + cantidad + " [" + nombreDivisa + "] " + "corresponden al valor final de ===> " + resultadoFormateado + "[" + nombreDivisaAConvertir + "]" +
                    "\n*******************************************************************\n";
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

    public static int menuEntrada(Scanner leer) throws InputMismatchException {
        int opcion;

        menuString();

        System.out.println("\ningrese una opcion: ");
        opcion = leer.nextInt();
        return opcion;
    }

    public static void menuString() {
        System.out.println("\n*******************************************************************\n");
        System.out.println("     ***** Sea bienvenidos a el comvertidor de Divisaas *****");
        System.out.println("\n*******************************************************************\n");
        System.out.println("1) Dolares ==> Peso Argentino");
        System.out.println("2) Peso Argentino ==> Dolar");
        System.out.println("3) Dolares ==> Real brazilero");
        System.out.println("4) Real brazilero ==> Dolar");
        System.out.println("5) Dolares ==> Peso Colombiano");
        System.out.println("6) Peso Colombiano ==> Dolares");
        System.out.println("7) Salir");
    }

    public static void sistemaCompleto() {
        Scanner leer = new Scanner(System.in);
        int opcion;
        try {
            opcion = menuEntrada(leer);

            do {
                switch (opcion) {
                    case 1:
                        System.out.println(mensajeDeLaConversion(leer, "USD", "ARS"));
                        opcion = menuEntrada(leer);
                        break;
                    case 2:
                        System.out.println(mensajeDeLaConversion(leer, "ARS", "USD"));
                        opcion = menuEntrada(leer);
                        break;
                    case 3:
                        System.out.println(mensajeDeLaConversion(leer, "USD", "BRL"));
                        opcion = menuEntrada(leer);
                        break;
                    case 4:
                        System.out.println(mensajeDeLaConversion(leer, "BRL", "USD"));
                        opcion = menuEntrada(leer);
                        break;
                    case 5:
                        System.out.println(mensajeDeLaConversion(leer, "USD", "COP"));
                        opcion = menuEntrada(leer);
                        break;
                    case 6:
                        System.out.println(mensajeDeLaConversion(leer, "COP", "USD"));
                        opcion = menuEntrada(leer);
                        break;
                    default:
                        opcion = 7;
                        break;
                }

            } while (opcion >= 0 && opcion < 7);
        } catch (InputMismatchException e) {
            System.out.println("\nError: Solo se permite ingresar numeros!!\n");
            sistemaCompleto();

        }

    }
}

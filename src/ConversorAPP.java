import java.util.Map;
import java.util.Scanner;

public class ConversorAPP {
    private static final String MENSAJE_CONVERTIR = "Ingrese el valor que desea convertir: ";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ConsultaApi consulta = new ConsultaApi();

        try {
            // Consulta las tasas de cambio con respecto a USD
            Map<String, Double> rates = consulta.buscarTasa("USD");

            // Muestra el menú al usuario
            mostrarMenu(rates);

        } catch (Exception e) {
            System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
        }
    }

    // Método para mostrar el menú principal
    private static void mostrarMenu(Map<String, Double> rates) {
        int opcion = 0;

        while (opcion != 7) {
            System.out.println("Seleccione la opción de conversión:");
            System.out.println("1. Dólar ----> Peso Argentino");
            System.out.println("2. Peso Argentino ----> Dólar");
            System.out.println("3. Real Brasileño ----> Dólar");
            System.out.println("4. Dólar ----> Real Brasileño");
            System.out.println("5. Dólar ----> Peso Colombiano");
            System.out.println("6. Peso Colombiano ----> Dólar");
            System.out.println("7. Salir");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    convertir(rates, "USD", "ARS");
                    break;
                case 2:
                    convertir(rates, "ARS", "USD");
                    break;
                case 3:
                    convertir(rates, "BRL", "USD");
                    break;
                case 4:
                    convertir(rates, "USD", "BRL");
                    break;
                case 5:
                    convertir(rates, "USD", "COP");
                    break;
                case 6:
                    convertir(rates, "COP", "USD");
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
            System.out.println("_______________________________________________________________________________________");
        }
    }
    
    private static void convertir(Map<String, Double> rates, String monedaOrigen, String monedaDestino) {
        System.out.println("Conversión de " + monedaOrigen + " a " + monedaDestino);
        System.out.println(MENSAJE_CONVERTIR);

        double cantidad = scanner.nextDouble();
        Double tasaOrigen = rates.get(monedaOrigen);
        Double tasaDestino = rates.get(monedaDestino);

        if (tasaOrigen == null || tasaDestino == null) {
            System.out.println("Error: No se pudo obtener la tasa de conversión para " + monedaOrigen + " o " + monedaDestino);
            return;
        }

        double resultado = (monedaOrigen.equals("USD") ? cantidad * tasaDestino : cantidad / tasaOrigen);
        System.out.println("El valor de " + cantidad + " " + monedaOrigen + " equivale a: " + resultado + " " + monedaDestino);
    }
}

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ConsultaApi {

    public Map<String, Double> buscarTasa(String monedaBase) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/96ea112a36efca5a48e97882/latest/" + monedaBase);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            // Realiza la solicitud HTTP y obtiene la respuesta como String
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Utiliza Gson para convertir el JSON en un objeto Java
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(response.body(), Respuesta.class);

            // Retorna las tasas de conversión (conversion_rates)
            return respuesta.getConversionRates();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener tasas de cambio: " + e.getMessage(), e);
        }
    }
}


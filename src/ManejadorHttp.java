import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ManejadorHttp {
    public Divisa obtenerDivisa(String base_code){

        String contrasenia = "9499baf964471de7da287d3b";
        //Se crea la variable direccion de tipo URI
        //a la cual se le asignamos la creación de la
        // direccion URL de la API, concatenanado la
        // Key y codigo de la divisa .
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/9499baf964471de7da287d3b/latest/"+base_code);
        //Creacion de la variable Client de tipo HttpClient,
        // se utiliza para hacer la consulta a la API.
        HttpClient cliente = HttpClient.newHttpClient();
        //Se crea un objeto HttpRequest que representa
        // la solicitus Http que se enviará a la API.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            // Objeto HttpResponse utilizado para enviar la solicitud HTTP y
            // espera una respuesta que se almacena el la variable response
            HttpResponse<String> response = cliente
                    .send(request, HttpResponse.BodyHandlers.ofString());
            // Se utiliza la bibleoteca Gson para comvertir la respuesta
            // JSON de la Api en un objero de la clase Sivisa.
            return new Gson().fromJson(response.body(), Divisa.class);
        } catch (Exception e) {
            throw new RuntimeException("no encontre encontro Divisa!");
        }

    }
}
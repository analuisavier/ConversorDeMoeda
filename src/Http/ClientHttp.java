package Http;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {

    private static final String BASE_URL = "https://api.exchangerate-api.com/v4/latest/";

    public RespostaCambio obterTaxasDeCambio(String moedaBase) throws Exception {
        var client = HttpClient.newHttpClient();
        String url = BASE_URL + moedaBase;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        return gson.fromJson(response.body(), RespostaCambio.class);
    }
}

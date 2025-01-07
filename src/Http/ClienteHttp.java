package Conversor;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class ClienteHttp{

    private static final String BASE_URL = "https://api.exchangerate-api.com/v4/latest/USD";

    public RespostaCambio obterTaxasDeCambio(String moedaBase) throws IOException,  InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        String urlCompleta = BASE_URL + moedaBase;

        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(urlCompleta))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        RespostaCambio respostaCambio = gson.fromJson(resposta.body(), RespostaCambio.class);

        Map<String, Double> taxasFiltradas = respostaCambio.filtrarMoedas(respostaCambio.getRates(), moedasSelecionadas);

        respostaCambio.setRates(taxasFiltradas);

        return respostaCambio;

    }
}

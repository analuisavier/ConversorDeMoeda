package Conversor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AnalisadorJson {
    JsonObject jsonObject = JsonParser.parseString(jsonResposta).getAsJsonObject();

    String moedaBase = jsonObject.get("base").getAsString();
        System.out.println("Moeda base: "+moedaBase);

    String data = jsonObject.get("date").getAsString();
        System.out.println("Data: "+data);

    JsonObject taxas = jsonObject.getAsJsonObject("rates");
        taxas.entrySet().

    forEach(entry ->

    {
        String moeda = entry.getKey();
        double taxa = entry.getValue().getAsDouble();
        System.out.println(moeda + ": " + taxa);
    });

    }
}

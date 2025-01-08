package Json;

import Http.RespostaCambio;

public class AnalisadorJson {

    public RespostaCambio analisarJson(String jsonResposta) {
        return new com.google.gson.Gson().fromJson(jsonResposta, RespostaCambio.class);
    }
}

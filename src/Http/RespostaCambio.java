package Conversor;

import java.util.Map;

public class RespostaCambio {
    private String base;
    private String date;
    private Map<String,Double> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Map<String, Double> filtrarMoedas(Map<String, Double> rates, String[] moedasSelecionadas) {
        Map<String, Double> moedasFiltradas = new java.util.HashMap<>();
        for (String moeda : moedasSelecionadas) {
            if (rates.containsKey(moeda)) {
                moedasFiltradas.put(moeda, rates.get(moeda));
            }
        }
        return moedasFiltradas;
    }

}

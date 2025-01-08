package Conversor;

import Http.ClientHttp;
import Http.RespostaCambio;

import java.util.Scanner;

public class ConversorMoeda {
    private ClientHttp clientHttp = new ClientHttp();

    public void iniciarConversao() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Conversor de Moedas!");

        System.out.println("Digite a moeda base (ex: BRL, USD):");
        String moedaBase = scanner.nextLine().toUpperCase();

        System.out.println("Digite a moeda de destino (ex: ARS, BOB, USD):");
        String moedaDestino = scanner.nextLine().toUpperCase();

        System.out.println("Agora, digite o valor a ser convertido:");
        double valor = scanner.nextDouble();

        try {
            RespostaCambio resposta = clientHttp.obterTaxasDeCambio(moedaBase);

            if (resposta.getRates().containsKey(moedaDestino)) {
                double taxaDeCambio = resposta.getRates().get(moedaDestino);
                double valorConvertido = valor * taxaDeCambio;
                System.out.println("O valor convertido é: " + valorConvertido + " " + moedaDestino);
            } else {
                System.out.println("A moeda destino não está disponível.");
            }

        } catch (Exception e) {
            System.err.println("Erro ao obter taxas de câmbio: " + e.getMessage());
        }
    }
}

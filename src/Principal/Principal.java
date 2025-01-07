package Conversor;

import Conversor.ClienteHttp;
import Conversor.RespostaCambio;
import Conversor.AnalisadorJson;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteHttp clienteHttp = new ClienteHttp();

        while(true) {
            System.out.println("=== Conversor de Moedas ===");
            System.out.println("1. Converter moedas");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            if (opcao == 2) {
                System.out.println("Saindo do programa...");
                break;
            }

            if (opcao == 1) {
                System.out.println("Digite a moeda base (ex: BRL, USD, EUR):");
                String moedaBase = scanner.nextLine().toUpperCase();

                String[] moedasDisponiveis = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

                System.out.println("Selecione as moedas a serem filtradas (separadas por vírgula):");
                String moedasInput = scanner.nextLine().toUpperCase();
                String[] moedasSelecionadas = moedasInput.split(",");

                try {
                    RespostaCambio resposta = clienteHttp.obterTaxasDeCambio(moedaBase, moedasSelecionadas);

                    System.out.println("Moeda base: " + resposta.getBase());
                    System.out.println("Taxas de câmbio filtradas:");
                    resposta.getRates().forEach((moeda, taxa) ->
                            System.out.println(moeda + ": " + taxa)
                    );

                    System.out.print("Digite o valor a ser convertido: ");
                    double valorOriginal = scanner.nextDouble();

                    System.out.print("Digite a moeda de destino para conversão (ex: BRL, ARS, USD): ");
                    String moedaDestino = scanner.next().toUpperCase();

                    if (resposta.getRates().containsKey(moedaDestino)) {
                        double taxaDeCambioDestino = resposta.getRates().get(moedaDestino);
                        double valorConvertido = converterMoeda(valorOriginal, taxaDeCambioDestino);
                        System.out.println("Valor convertido: " + valorConvertido + " " + moedaDestino);
                    } else {
                        System.out.println("Moeda de destino não encontrada.");
                    }

                } catch (Exception e) {
                    System.err.println("Erro ao obter taxas de câmbio: " + e.getMessage());
                }

            } else {
                System.out.println("Opção inválida! Tente novamente.");

            }
        }

        scanner.close();

    }

    public static double converterMoeda(double valorOriginal, double taxaDeCambio) {
        return valorOriginal * taxaDeCambio;
    }
}
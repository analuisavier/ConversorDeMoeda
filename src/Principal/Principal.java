package Principal;

import Http.ClientHttp;
import Http.RespostaCambio;
import Log.HistoricoConversoes;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var clientHttp = new ClientHttp();
        var historico = new HistoricoConversoes();

        while (true) {
            System.out.println("Bem-vindo(a) ao Conversor de Moedas!");
            System.out.println("1. Converter moedas");
            System.out.println("2. Exibir histórico");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 3) {
                System.out.println("Saindo do programa...");
                break;
            }

            if (opcao == 2) {
                historico.exibirHistorico();
                continue;
            }

            String[] moedasDisponiveis = {
                    "ARS - Peso argentino",
                    "BOB - Boliviano boliviano",
                    "BRL - Real brasileiro",
                    "CLP - Peso chileno",
                    "COP - Peso colombiano",
                    "USD - Dólar americano"
            };

            System.out.println("Moedas disponíveis para conversão:");
            for (String moeda : moedasDisponiveis) {
                System.out.println(moeda);
            }

            if (opcao == 1) {
                String moedaBase = obterMoeda(scanner, moedasDisponiveis, "Digite a moeda de origem para a conversão:");

                if (moedaBase == null) {
                    continue;
                }

                String moedaDestino = obterMoeda(scanner, moedasDisponiveis, "Digite a moeda para a qual você deseja converter o valor:");

                if (moedaDestino == null) {
                    continue;
                }

                double valorOriginal = obterValor(scanner);
                if (valorOriginal < 0) {
                    continue;
                }

                realizarConversao(clientHttp, historico, moedaBase, moedaDestino, valorOriginal);
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }

    private static String obterMoeda(Scanner scanner, String[] moedasDisponiveis, String mensagem) {
        System.out.println(mensagem);
        String moeda = scanner.nextLine().toUpperCase();

        if (!isMoedaValida(moeda, moedasDisponiveis)) {
            System.out.println("Moeda inválida! Tente novamente.");
            return null;
        }

        return moeda;
    }

    private static double obterValor(Scanner scanner) {
        System.out.print("Digite o valor que deseja converter: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        if (valor <= 0) {
            System.out.println("Valor inválido! Tente novamente.");
            return -1;
        }

        return valor;
    }

    private static void realizarConversao(ClientHttp clientHttp, HistoricoConversoes historico,
                                          String moedaBase, String moedaDestino, double valorOriginal) {
        try {
            RespostaCambio resposta = clientHttp.obterTaxasDeCambio(moedaBase);

            if (resposta != null && resposta.getRates().containsKey(moedaDestino)) {
                double taxaDeCambioDestino = resposta.getRates().get(moedaDestino);
                double valorConvertido = converterMoeda(valorOriginal, taxaDeCambioDestino);

                if (Double.isNaN(valorConvertido)) {
                    System.out.println("Erro na conversão. O valor convertido não é válido.");
                } else {
                    System.out.println("Valor convertido: " + valorConvertido + " " + moedaDestino);
                    System.out.println("Conversão: " + moedaBase + " para " + moedaDestino);

                    String conversao = moedaBase + " para " + moedaDestino + ": " +
                            valorOriginal + " " + moedaBase + " = " + valorConvertido + " " + moedaDestino;

                    historico.adicionarConversao(conversao);
                }
            } else {
                System.out.println("Moeda de destino não encontrada ou resposta inválida.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao obter taxas de câmbio: " + e.getMessage());
        }
    }

    private static boolean isMoedaValida(String moeda, String[] moedasDisponiveis) {
        for (String m : moedasDisponiveis) {
            if (m.split(" - ")[0].equals(moeda)) {
                return true;
            }
        }
        return false;
    }

    public static double converterMoeda(double valorOriginal, double taxaDeCambio) {
        return valorOriginal * taxaDeCambio;
    }
}

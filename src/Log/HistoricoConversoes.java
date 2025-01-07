package Conversor;

import java.util.ArrayList;
import java.util.List;

public class HistoricoConversoes {
    private List<String> historico = new ArrayList<>();

    public void adicionarConversao(String conversao) {
        historico.add(conversao);
    }

    public void exibirHistorico() {
        if (historico.isEmpty()) {
            System.out.println("Nenhuma conversão realizada até o momento.");
        } else {
            System.out.println("=== Histórico de Conversões ===");
            for (String conversao : historico) {
                System.out.println(conversao);
            }
        }
    }
}

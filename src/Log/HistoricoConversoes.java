package Log;

import java.util.ArrayList;
import java.util.List;

public class HistoricoConversoes {
    private final List<String> historico = new ArrayList<>();

    public void adicionarConversao(String conversao) {
        historico.add(conversao);
    }

    public void exibirHistorico() {
        if (historico.isEmpty()) {
            System.out.println("Nenhuma conversão realizada.");
        } else {
            System.out.println("Histórico de Conversões:");
            for (String conversao : historico) {
                System.out.println(conversao);
            }
        }
    }
}

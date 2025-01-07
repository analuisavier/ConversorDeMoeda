package Conversor;

import java.util.ArrayList;
import java.util.List;

public class LogConversoes {
    private List<String> logs = new ArrayList<>();

    public void adicionarLog(String log) {
        logs.add(log);
    }

    public void exibirLogs() {
        if (logs.isEmpty()) {
            System.out.println("Nenhum log registrado até o momento.");
        } else {
            System.out.println("=== Registros de Logs ===");
            for (String log : logs) {
                System.out.println(log);
            }
        }
    }
}

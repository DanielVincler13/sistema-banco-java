import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Banco {

    private HashMap<Integer, Conta> contas = new HashMap<>();
    private List<Transacao> historico = new ArrayList<>();
    private int proximoId = 1;

    public Banco() {}

    public Conta adicionarConta(String titular, double saldoInicial, String senha) {
        if (titular == null || titular.trim().isEmpty()) {
            System.out.println("Titular inválido.");
            return null;
        }
        if (senha == null || senha.trim().isEmpty()) {
            System.out.println("Senha inválida.");
            return null;
        }
        if (saldoInicial < 0) {
            System.out.println("Saldo inicial não pode ser negativo.");
            return null;
        }

        Conta novaConta = new Conta(proximoId, titular.trim(), saldoInicial, senha);
        contas.put(proximoId, novaConta);
        proximoId++;
        return novaConta;
    }

    public Conta buscarConta(int id) {
        return contas.get(id);
    }

    public Conta autenticar(int id, String senha) {
        Conta c = buscarConta(id);
        if (c == null) return null;
        if (!c.confereSenha(senha)) return null;
        return c;
    }

    private void registrar(String tipo, double valor, int origemId, Integer destinoId) {
        historico.add(new Transacao(tipo, valor, origemId, destinoId));
    }

    public void depositar(int id, double valor) {
        Conta c = buscarConta(id);
        if (c == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
        if (c.depositar(valor)) {
            registrar("DEPOSITO", valor, id, null);
        }
    }

    public void sacar(int id, double valor) {
        Conta c = buscarConta(id);
        if (c == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
        if (c.sacar(valor)) {
            registrar("SAQUE", valor, id, null);
        }
    }

    public void transferir(int origemId, int destinoId, double valor) {
        if (origemId == destinoId) {
            System.out.println("Não pode transferir para a mesma conta.");
            return;
        }

        Conta origem = buscarConta(origemId);
        Conta destino = buscarConta(destinoId);

        if (origem == null || destino == null) {
            System.out.println("Conta origem ou destino não encontrada.");
            return;
        }

        // 1) saca da origem
        if (!origem.sacar(valor)) return;

        // 2) deposita no destino
        destino.depositar(valor);

        // 3) registra
        registrar("TRANSFERENCIA", valor, origemId, destinoId);
    }

    public void extrato(int idConta) {
        System.out.println("\n===== EXTRATO DA CONTA " + idConta + " =====");

        boolean achou = false;

        for (Transacao t : historico) {
            boolean ehOrigem = t.getOrigemId() == idConta;
            boolean ehDestino = t.getDestinoId() != null && t.getDestinoId() == idConta;

            if (ehOrigem || ehDestino) {
                System.out.println(t);
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Nenhuma transação encontrada.");
        }

        System.out.println("==============================\n");
    }
}

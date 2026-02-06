import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Banco banco = new Banco();

    static final String RESET = "\u001B[0m";
    static final String AZUL = "\u001B[34m";
    static final String VERDE = "\u001B[32m";
    static final String VERMELHO = "\u001B[31m";
    static final String AMARELO = "\u001B[33m";

    // ================= MENU 1 =================
    static void menuInicial() {
        int opcao;

        do {
            System.out.println(AZUL + "\n╔══════════════════════╗");
            System.out.println("║   BEM-VINDO AO BANCO ║");
            System.out.println("╠══════════════════════╣");
            System.out.println("║ 1 - Criar Conta      ║");
            System.out.println("║ 2 - Entrar           ║");
            System.out.println("║ 0 - Sair             ║");
            System.out.println("╚══════════════════════╝" + RESET);
            System.out.print(AMARELO + "➤ Opção: " + RESET);

            opcao = lerInt();

            switch (opcao) {
                case 1 -> menuCriarConta();
                case 2 -> menuEntrar();
                case 0 -> System.out.println(VERMELHO + "Saindo..." + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }

        } while (opcao != 0);
    }

    // ================= MENU 2A =================
    static void menuCriarConta() {
        System.out.println(VERDE + "\n=== CRIAR CONTA ===" + RESET);

        System.out.print("Nome do titular: ");
        String nome = lerLinha();

        System.out.print("Saldo inicial: ");
        double saldo = lerDouble();

        System.out.print("Senha do titular: ");
        String senha = lerLinha();

        Conta conta = banco.adicionarConta(nome, saldo, senha);

        System.out.println(VERDE + "Conta criada com sucesso!" + RESET);
        System.out.println("ID da conta: " + conta.getId());

        menuConta(conta);
    }

    // ================= MENU 2B =================
    static void menuEntrar() {
        System.out.println(AZUL + "\n=== ENTRAR ===" + RESET);

        System.out.print("Digite o ID da conta: ");
        int id = lerInt();

        System.out.print("Digite a senha: ");
        String senha = lerLinha();

        Conta conta = banco.autenticar(id, senha);

        if (conta == null) {
            System.out.println(VERMELHO + "ID ou senha incorretos!" + RESET);
            return;
        }

        System.out.println(VERDE + "Login realizado com sucesso!" + RESET);
        menuConta(conta);
    }

    // ================= MENU 3 =================
    static void menuConta(Conta conta) {
        int opcao;

        do {
            System.out.println(AZUL + "\n╔══════════════════════╗");
            System.out.println("║   CONTA: " + conta.getTitular());
            System.out.println("╠══════════════════════╣");
            System.out.printf("║ Saldo: R$ %.2f%n", conta.getSaldo());
            System.out.println("╠══════════════════════╣");
            System.out.println("║ 1 - Depositar        ║");
            System.out.println("║ 2 - Sacar            ║");
            System.out.println("║ 3 - Transferir       ║");
            System.out.println("║ 4 - Extrato          ║");
            System.out.println("║ 0 - Sair da conta    ║");
            System.out.println("╚══════════════════════╝" + RESET);
            System.out.print(AMARELO + "➤ Opção: " + RESET);

            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Valor do depósito: ");
                    double valor = lerDouble();
                    banco.depositar(conta.getId(), valor);
                }
                case 2 -> {
                    System.out.print("Valor do saque: ");
                    double saque = lerDouble();
                    banco.sacar(conta.getId(), saque);
                }
                case 3 ->{
                    System.out.print("Valor da transferencia: ");
                    double valor = lerDouble();
                    System.out.print("id do destinario: ");
                    int idDestino = sc.nextInt();
                    banco.transferir(conta.getId(), idDestino, valor);
                }

                case 4 -> {
    banco.extrato(conta.getId()); // ✅ extrato da conta logada
}

                case 0 -> System.out.println(VERMELHO + "Saindo da conta...\n" + RESET);
                default -> System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }

            // Atualiza referência da conta (por segurança)
            conta = banco.buscarConta(conta.getId());

        } while (opcao != 0);
    }

    // ================= FUNÇÕES DE LEITURA =================
    static String lerLinha() {
        String linha = sc.nextLine();
        while (linha.isEmpty()) { // evita pegar vazio por causa de enter perdido
            linha = sc.nextLine();
        }
        return linha.trim();
    }

    static int lerInt() {
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print(VERMELHO + "Digite um número válido: " + RESET);
        }
        int v = sc.nextInt();
        sc.nextLine(); // limpa o \n
        return v;
    }

    static double lerDouble() {
        while (!sc.hasNextDouble()) {
            sc.nextLine();
            System.out.print(VERMELHO + "Digite um valor válido: " + RESET);
        }
        double v = sc.nextDouble();
        sc.nextLine(); // limpa o \n
        return v;
    }

    public static void main(String[] args) {
        menuInicial();
        sc.close();
    }
}

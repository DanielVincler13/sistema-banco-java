public class Conta {

    private int id;
    private String titular;
    private double saldo;
    private String senha;

    public Conta(int id, String titular, double saldo, String senha) {
        this.id = id;
        this.titular = titular;
        this.saldo = saldo;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean confereSenha(String senhaDigitada) {
        if (senhaDigitada == null) return false;
        return this.senha.equals(senhaDigitada);
    }

   public boolean depositar(double valor) {
    if (valor <= 0) {
        System.out.println("Depósito precisa ser maior que zero.");
        return false;
    }
    saldo += valor;
    System.out.println("Depósito realizado com sucesso!");
    return true;
}

    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Saque precisa ser maior que zero.");
            return false;
        }
        if (saldo < valor) {
            System.out.println("Saldo insuficiente.");
            return false;
        }
        saldo -= valor;
        System.out.println("Saque realizado com sucesso!");
        return true;
    }


    public void transferir(double valor){
        if (valor <= 0) {
            System.out.println("Transferencia precisa ser maior que zero.");
            return;
        }
        if (saldo < valor) {
            System.out.println("Saldo insuficiente.");
            return;
        }
        System.out.println("Transferencia realizada com sucesso!");
        depositar(valor);
    }
    

    @Override
    public String toString() {
        return "Conta " + id + " | Titular: " + titular + " | Saldo: R$ " + String.format("%.2f", saldo);
    }
}

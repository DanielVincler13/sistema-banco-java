import java.time.LocalDateTime;

public class Transacao {
    private String tipo; // "DEPOSITO", "SAQUE", "TRANSFERENCIA"
    private double valor;
    private LocalDateTime dataHora;
    private int origemId;
    private Integer destinoId; // null quando não tiver

    public Transacao(String tipo, double valor, int origemId, Integer destinoId) {
        this.tipo = tipo;
        this.valor = valor;
        this.origemId = origemId;
        this.destinoId = destinoId;
        this.dataHora = LocalDateTime.now();
    }

    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
    public LocalDateTime getDataHora() { return dataHora; }
    public int getOrigemId() { return origemId; }
    public Integer getDestinoId() { return destinoId; }

    @Override
    public String toString() {
        if ("TRANSFERENCIA".equals(tipo) && destinoId != null) {
            return dataHora + " | " + tipo + " | R$ " + String.format("%.2f", valor)
                    + " | " + origemId + " -> " + destinoId;
        }
        return dataHora + " | " + tipo + " | R$ " + String.format("%.2f", valor)
                + " | conta: " + origemId;
    }
}

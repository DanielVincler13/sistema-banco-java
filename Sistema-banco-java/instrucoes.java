/*
========================
PROJETO: MINI BANCO (terminal)
========================

IDEIA PRINCIPAL:
- O usuário digita comandos no terminal.
- O sistema encontra a conta certa, executa a operação e registra tudo.

========================
1) MAIN (menu / entrada)
========================
- Lê comando do usuário (Scanner)
- Para cada comando, chama um método do BANCO
- NÃO faz regra de negócio (não mexe em saldo diretamente)

Fluxo no Main:
- criar conta
- entrar/selecionar conta (opcional)
- depositar / sacar / transferir
- extrato
- desfazer
- sair

========================
2) BANCO (regras e controle)
========================
BANCO guarda 3 "memórias":
1) contas (HashMap) -> acesso rápido por número
   Map<Integer, Conta> contas

2) historico (List) -> todas as transações registradas
   List<Transacao> historico

3) pilhaDesfazer (Stack) -> última transação (CTRL+Z)
   Stack<Transacao> pilha

BANCO faz:
- criarConta(titular) -> cria conta com número automático e guarda no Map
- buscarConta(numero) -> pega no HashMap
- depositar(numero, valor)
- sacar(numero, valor)
- transferir(origem, destino, valor)
- extrato(numero) -> filtra transações do histórico
- desfazerUltima() -> pega última transação da Stack e reverte

========================
3) CONTA (saldo e operações) ✔
========================
CONTA guarda:
- numero ✔
- titular ✔
- saldo ✔

CONTA faz:
- depositar(valor) ✔
- sacar(valor) ✔
- (opcional) transferirPara(destino, valor) ✔

REGRAS dentro da Conta:
- valor precisa ser > 0 ✔
- saque só pode se saldo >= valor ✔

========================
4) TRANSACAO (registro do que aconteceu)
========================
Transacao guarda:
- tipo (DEPOSITO / SAQUE / TRANSFERENCIA)
- valor
- dataHora
- contaOrigem
- contaDestino (só se transferência)

Transacao NÃO altera saldo.
Ela só registra.

========================
COMO UMA OPERAÇÃO ACONTECE (ex: DEPÓSITO)
========================

PASSO A PASSO:
1) Main lê: "depositar"
2) Main pergunta: numero da conta e valor
3) Main chama: banco.depositar(numero, valor)
4) Banco busca a conta no HashMap
5) Banco chama: conta.depositar(valor)
6) Banco cria: Transacao(tipo=DEPOSITO, valor=..., origem=numero)
7) Banco registra:
   - historico.add(transacao)
   - pilha.push(transacao)
8) Banco retorna sucesso pro Main
9) Main imprime confirmação

========================
DESFAZER (CTRL+Z) - como funciona
========================

PASSO A PASSO:
1) Main lê: "desfazer"
2) Main chama: banco.desfazerUltima()
3) Banco verifica se pilha está vazia:
   - se vazia: "nada pra desfazer"
4) Banco pega a última transação:
   Transacao t = pilha.pop()

5) Banco reverte o que aconteceu:
   - Se foi DEPOSITO -> subtrai o valor da conta origem
   - Se foi SAQUE -> soma o valor na conta origem
   - Se foi TRANSFERENCIA -> devolve valor para origem e tira do destino

6) Banco remove do histórico geral:
   historico.remove(t)

7) Main imprime: "desfeito com sucesso"

========================
CHECKPOINTS (ordem para implementar)
========================
[1] Criar classe Conta (com depositar/sacar)
[2] Criar classe Transacao (só dados + toString)
[3] Criar classe Banco (Map + depositar/sacar registrando transação)
[4] Fazer Main com menu e comandos básicos
[5] Implementar transferir
[6] Implementar desfazer (Stack)
[7] Implementar extrato (filtrar List)

*/

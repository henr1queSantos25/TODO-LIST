# TODO List - Java MVP

Aplicação de linha de comando (CLI) para gerenciamento de tarefas, desenvolvida em Java puro sem o uso de frameworks externos. O projeto implementa um sistema CRUD completo com funcionalidades de priorização automática e filtragem de dados.

## Funcionalidades

### Gestão de Tarefas
* **Adicionar Tarefa:** Criação de tarefas com nome, descrição, data de término, nível de prioridade (1-5) e categoria.
* **Listagem Automática:** As tarefas são ordenadas automaticamente pela prioridade assim que inseridas ou editadas.
* **Edição Completa:** Permite alterar todos os dados de uma tarefa existente.
* **Exclusão:** Remoção de tarefas pelo ID.

### Controle de Fluxo (Status)
As tarefas possuem um ciclo de vida definido por três estados:
1.  TODO (A Fazer)
2.  DOING (Em Andamento)
3.  DONE (Concluído)

### Filtros e Relatórios
O sistema permite visualizar os dados de diferentes formas:
* Listagem geral (ordenada por prioridade).
* Filtro por Categoria (ex: Trabalho, Pessoal).
* Filtro por Nível de Prioridade.
* Filtro por Status.
* Filtro por Data de Término.
* **Dashboard:** Exibe a contagem quantitativa de tarefas em cada status.

## Estrutura do Projeto

O código segue uma organização modular simples:

* `src/com/aczg/todolist/`
    * `Main.java`: Ponto de entrada e interface com o usuário (Menu CLI).
    * `Tarefa.java`: Modelo de dados representando a entidade Tarefa. Implementa `Comparable` para ordenação.
    * `Status.java`: Enumerador (Enum) para padronização dos estados da tarefa.
    * `GerenciadorTarefas.java`: Classe responsável pela lógica de negócios (CRUD, filtros e ordenação).

## Pré-requisitos

* Java JDK 8 ou superior instalado.

## Como Executar

### Via IDE (IntelliJ IDEA, Eclipse, VS Code)
1.  Importe a pasta do projeto.
2.  Localize o arquivo `src/com/aczg/todolist/Main.java`.
3.  Execute o método `main`.

### Via Terminal
Navegue até a pasta `src` do projeto e compile os arquivos:

```bash
javac com/aczg/todolist/*.java
# TODO List - Java MVP

Aplicação de linha de comando (CLI) para gerenciamento de tarefas, desenvolvida em Java puro sem o uso de frameworks externos. O projeto implementa um sistema CRUD completo com funcionalidades de priorização automática e filtragem de dados. Além do CLI, conta com uma interface web desenvolvida com tecnologias puras (HTML, CSS e JavaScript) para uso diretamente no navegador.

---

## Funcionalidades (CLI)

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

---

## Estrutura do Projeto

```
TODO-List/
├── frontend/
│   ├── index.html
│   ├── script.js
│   └── utils/
        ├── validacoes.js
│   └── styles/
│       ├── style.css
│       └── responsive.css
└── src/
    └── com/
        └── aczg/
            └── todolist/
                ├── Main.java
                ├── Tarefa.java
                ├── Status.java
                └── GerenciadorTarefas.java
```

### Backend (Java CLI)
* `Main.java`: Ponto de entrada e interface com o usuário (Menu CLI).
* `Tarefa.java`: Modelo de dados representando a entidade Tarefa. Implementa `Comparable` para ordenação.
* `Status.java`: Enumerador (Enum) para padronização dos estados da tarefa.
* `GerenciadorTarefas.java`: Classe responsável pela lógica de negócios (CRUD, filtros e ordenação).

### Frontend (Interface Web)
* `index.html`: Estrutura da página com formulário de criação/edição e painel de listagem.
* `script.js`: Lógica completa de CRUD, filtragem, seleção em massa e renderização dinâmica.
* `utils/validacoes.js`: Módulo dedicado à validação de dados com Expressões Regulares (Regex) e regras temporais de negócio.
* `styles/style.css`: Estilos base e variáveis de tema CSS.
* `styles/responsive.css`: Regras de responsividade para diferentes tamanhos de tela.

---

## Pré-requisitos

* Java JDK 8 ou superior instalado (para o CLI).
* Gradle instalado (ou Gradle Wrapper, quando adicionado ao projeto).
* Navegador moderno (para o frontend).

---

## Como Executar

### CLI Java

#### Via Gradle (recomendado)
Na raiz do projeto, execute:

```bash
gradle clean build
gradle run
```

Com Gradle Wrapper (quando disponível no projeto):

```bash
./gradlew clean build
./gradlew run
```

#### Via IDE (IntelliJ IDEA, Eclipse, VS Code)
1.  Importe a pasta do projeto.
2.  Localize o arquivo `src/main/java/com/aczg/todolist/Main.java`.
3.  Execute o método `main`.

#### Via Terminal
Navegue até a pasta `src` do projeto e compile os arquivos:

```bash
javac com/aczg/todolist/*.java
```

Em seguida, execute a aplicação:

```bash
java com.aczg.todolist.Main
```

### Frontend Web

Não é necessário nenhum servidor ou instalação adicional. Basta abrir o arquivo `frontend/index.html` diretamente no navegador:

```bash
# A partir da raiz do projeto:
open frontend/index.html
```

Ou arraste o arquivo `index.html` para uma janela do navegador.

---

## Funcionalidades do Frontend

* **Formulário dinâmico:** Cria e edita tarefas com os mesmos campos do CLI — nome, descrição, data de término, prioridade (1-5), categoria e status.
* **Listagem em cards:** As tarefas são exibidas como cartões ordenados automaticamente por prioridade.
* **Filtro por Status:** Filtra a visualização por TODO, DOING ou DONE em tempo real.
* **Ações em massa:** Permite selecionar múltiplas tarefas via checkbox e alterar o status de todas de uma só vez.
* **Persistência local:** Os dados são salvos automaticamente no `localStorage` do navegador, sem necessidade de servidor ou banco de dados.
* **Validação de Formulários:** Tratamento de erros e segurança de dados utilizando Expressões Regulares (Regex) para garantir o formato correto dos campos (como nível de prioridade restrito de 1 a 5) e regras de negócio temporais (impedindo o agendamento de tarefas para datas no passado), com feedback visual imediato para o usuário.
* **Responsivo:** A interface se adapta a dispositivos móveis e desktops.

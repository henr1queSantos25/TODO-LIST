let tarefas = carregarDoLocalStorage();
let proximoId = tarefas.length > 0 ? Math.max(...tarefas.map(t => t.id)) + 1 : 1;

function salvarNoLocalStorage() {
    localStorage.setItem('tarefas', JSON.stringify(tarefas));
}

function carregarDoLocalStorage() {
    const dados = localStorage.getItem('tarefas');
    return dados ? JSON.parse(dados) : [];
}

// --- SELETORES DO DOM ---

const form = document.getElementById('form-tarefa');
const inputId = document.getElementById('tarefa-id');
const inputNome = document.getElementById('nome');
const inputDescricao = document.getElementById('descricao');
const inputData = document.getElementById('data-termino');
const inputPrioridade = document.getElementById('prioridade');
const inputCategoria = document.getElementById('categoria');
const selectStatus = document.getElementById('status');

const btnCancelar = document.getElementById('btn-cancelar');
const tituloForm = document.getElementById('titulo-form');
const containerTarefas = document.getElementById('container-tarefas');
const filtroStatus = document.getElementById('filtro-status');

const barraAcoesMassa = document.getElementById('barra-acoes-massa');
const selectStatusMassa = document.getElementById('status-massa');
const btnAplicarMassa = document.getElementById('btn-aplicar-massa');

const msgErroData = document.getElementById('erro-data');
const msgErroPrioridade = document.getElementById('erro-prioridade');

// --- EVENT LISTENERS ---

form.addEventListener('submit', salvarTarefa);
btnCancelar.addEventListener('click', resetarFormulario);
filtroStatus.addEventListener('change', renderizarTarefas);
btnAplicarMassa.addEventListener('click', aplicarStatusEmMassa);

// --- FUNÇÕES DE CRUD ---

function salvarTarefa(event) {
    event.preventDefault();
    limparErros();

    const valPrioridade = inputPrioridade.value;
    const valData = inputData.value;
    let formValido = true;

    if (!isPrioridadeValida(valPrioridade)) {
        mostrarErro(inputPrioridade, msgErroPrioridade, "A prioridade deve ser apenas um número entre 1 e 5.");
        formValido = false;
    }

    if (!isDataFormatoValido(valData)) {
        mostrarErro(inputData, msgErroData, "Formato de data inválido.");
        formValido = false;
    } else if (!isDataFuturaOuHoje(valData)) {
        mostrarErro(inputData, msgErroData, "A data de término não pode ser anterior a hoje.");
        formValido = false;
    }

    if (!formValido) {
        return; 
    }

    const idAtual = inputId.value;
    const dadosTarefa = {
        nome: inputNome.value,
        descricao: inputDescricao.value,
        dataTermino: valData,
        prioridade: parseInt(valPrioridade),
        categoria: inputCategoria.value,
        status: selectStatus.value
    };

    if (idAtual) {
        // UPDATE
        const index = tarefas.findIndex(t => t.id == idAtual);
        if (index !== -1) {
            tarefas[index] = { ...tarefas[index], ...dadosTarefa };
        }
    } else {
        // CREATE
        dadosTarefa.id = proximoId++;
        tarefas.push(dadosTarefa);
    }

    tarefas.sort((a, b) => a.prioridade - b.prioridade);
    
    salvarNoLocalStorage(); 
    resetarFormulario();
    renderizarTarefas();
}

function deletarTarefa(id) {
    tarefas = tarefas.filter(t => t.id !== id);
    salvarNoLocalStorage();
    renderizarTarefas();
}

function prepararEdicao(id) {
    const tarefa = tarefas.find(t => t.id === id);
    if (!tarefa) return;

    inputId.value = tarefa.id;
    inputNome.value = tarefa.nome;
    inputDescricao.value = tarefa.descricao;
    inputData.value = tarefa.dataTermino;
    inputPrioridade.value = tarefa.prioridade;
    inputCategoria.value = tarefa.categoria;
    selectStatus.value = tarefa.status;

    tituloForm.textContent = "Editar Tarefa";
    btnCancelar.style.display = 'inline-block';
}

// --- FUNÇÕES DE AÇÕES EM MASSA ---

function verificarSelecao() {
    const temSelecionado = document.querySelectorAll('.selecao-tarefa:checked').length > 0;
    barraAcoesMassa.style.display = temSelecionado ? 'flex' : 'none';
}

function aplicarStatusEmMassa() {
    const checkboxes = document.querySelectorAll('.selecao-tarefa:checked');
    const novoStatus = selectStatusMassa.value;

    checkboxes.forEach(chk => {
        const idTarefa = parseInt(chk.value);
        const index = tarefas.findIndex(t => t.id === idTarefa);
        if (index !== -1) {
            tarefas[index].status = novoStatus;
        }
    });

    salvarNoLocalStorage(); 
    renderizarTarefas();   
}

// --- FUNÇÕES DE INTERFACE (UI) ---

function resetarFormulario() {
    form.reset();
    inputId.value = '';
    tituloForm.textContent = "Nova Tarefa";
    btnCancelar.style.display = 'none';
    limparErros();
}

function mostrarErro(inputElement, spanElement, mensagem) {
    spanElement.textContent = mensagem;
    spanElement.style.display = 'block';
    inputElement.classList.add('input-erro');
}

function limparErros() {
    msgErroData.style.display = 'none';
    msgErroPrioridade.style.display = 'none';
    inputData.classList.remove('input-erro');
    inputPrioridade.classList.remove('input-erro');
}

function renderizarTarefas() {
    containerTarefas.innerHTML = '';
    const statusFiltrado = filtroStatus.value;

    const tarefasFiltradas = statusFiltrado === 'TODOS' 
        ? tarefas 
        : tarefas.filter(t => t.status === statusFiltrado);

    if (tarefasFiltradas.length === 0) {
        containerTarefas.innerHTML = '<p>Nenhuma tarefa encontrada.</p>';
        verificarSelecao();
        return;
    }

    tarefasFiltradas.forEach(tarefa => {
        const cartao = document.createElement('article');
        cartao.className = 'cartao-tarefa';

        const dataFormatada = tarefa.dataTermino.split('-').reverse().join('/');

        cartao.innerHTML = `
            <input type="checkbox" class="selecao-tarefa" value="${tarefa.id}" onchange="verificarSelecao()">
            <div class="cartao-conteudo">
                <h3>${tarefa.nome}</h3>
                <p>${tarefa.descricao}</p>
                <div class="etiqueta-grupo">
                    <span class="etiqueta">Prioridade: ${tarefa.prioridade}</span>
                    <span class="etiqueta">Data: ${dataFormatada}</span>
                    <span class="etiqueta">Cat: ${tarefa.categoria}</span>
                    <span class="etiqueta">Status: <strong>${tarefa.status}</strong></span>
                </div>
            </div>
            <div class="cartao-acoes">
                <button class="btn-secundario" onclick="prepararEdicao(${tarefa.id})">Editar</button>
                <button class="btn-perigo" onclick="deletarTarefa(${tarefa.id})">Excluir</button>
            </div>
        `;
        containerTarefas.appendChild(cartao);
    });

    verificarSelecao(); 
}

renderizarTarefas();
let tarefas = [];
let proximoId = 1;

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

// --- EVENT LISTENERS ---
form.addEventListener('submit', salvarTarefa);
btnCancelar.addEventListener('click', resetarFormulario);
filtroStatus.addEventListener('change', renderizarTarefas);

// --- FUNÇÕES DE CRUD ---

function salvarTarefa(event) {
    event.preventDefault(); // Evita o recarregamento da página

    const idAtual = inputId.value;
    const dadosTarefa = {
        nome: inputNome.value,
        descricao: inputDescricao.value,
        dataTermino: inputData.value,
        prioridade: parseInt(inputPrioridade.value),
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

    resetarFormulario();
    renderizarTarefas();
}

function deletarTarefa(id) {
    tarefas = tarefas.filter(t => t.id !== id);
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

// --- FUNÇÕES DE INTERFACE (UI) ---

function resetarFormulario() {
    form.reset();
    inputId.value = '';
    tituloForm.textContent = "Nova Tarefa";
    btnCancelar.style.display = 'none';
}

function renderizarTarefas() {
    containerTarefas.innerHTML = '';
    const statusFiltrado = filtroStatus.value;

    const tarefasFiltradas = statusFiltrado === 'TODOS' 
        ? tarefas 
        : tarefas.filter(t => t.status === statusFiltrado);

    if (tarefasFiltradas.length === 0) {
        containerTarefas.innerHTML = '<p>Nenhuma tarefa encontrada.</p>';
        return;
    }

    tarefasFiltradas.forEach(tarefa => {
        const cartao = document.createElement('article');
        cartao.className = 'cartao-tarefa';

        const dataFormatada = tarefa.dataTermino.split('-').reverse().join('/');

        cartao.innerHTML = `
            <div class="cartao-conteudo">
                <h3>${tarefa.nome}</h3>
                <p>${tarefa.descricao}</p>
                <div style="margin-top: 10px;">
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
}
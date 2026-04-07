function isPrioridadeValida(prioridadeStr) {
    const regexPrioridade = /^[1-5]$/;
    return regexPrioridade.test(prioridadeStr);
}


function isDataFormatoValido(dataStr) {
    const regexData = /^\d{4}-\d{2}-\d{2}$/;
    return regexData.test(dataStr);
}


function isDataFuturaOuHoje(dataStr) {
    const dataEscolhida = new Date(dataStr + "T00:00:00");
    
    const hoje = new Date();
    hoje.setHours(0, 0, 0, 0);

    return dataEscolhida >= hoje;
}
let jogoSelecionado = null;

function abrirModalCadastrar() {
    document.getElementById("modalCadastrar").style.display = "flex";
}

function abrirModalEditar() {
    document.getElementById("modalEditar").style.display = "flex";
}

function abrirModalExcluir() {
    document.getElementById("modalExcluir").style.display = "flex";
}

function fecharTodos() {
    document.querySelectorAll('.modal-overlay')
            .forEach(m => m.style.display = 'none');
    jogoSelecionado = null;
}

function selecionarJogoParaEditar(btn) {
    const tr = btn.closest('tr');

    document.querySelectorAll('tr')
            .forEach(r => r.classList.remove('selecionado'));

    tr.classList.add('selecionado');

    jogoSelecionado = {id: tr.cells[0].innerText};

    editarId.value = tr.cells[0].innerText;
    editarNome.value = tr.cells[1].innerText;
    editarCategoria.value = tr.cells[2].innerText;
    editarPlataforma.value = tr.cells[3].innerText;
    editarPreco.value = tr.cells[4].innerText;
    editarQuantidade.value = tr.cells[5].innerText;
}

function salvarEdicao() {
    if (!jogoSelecionado) {
        alert("Selecione um jogo!");
        return;
    }

    formEditarId.value = editarId.value;
    formEditarNome.value = editarNome.value;
    formEditarCategoria.value = editarCategoria.value;
    formEditarPlataforma.value = editarPlataforma.value;
    formEditarPreco.value = editarPreco.value;
    formEditarQuantidade.value = editarQuantidade.value;

    document.getElementById("formEditar").submit();
}

function selecionarJogoParaExcluir(btn) {
    const tr = btn.closest('tr');

    document.querySelectorAll('tr')
            .forEach(r => r.classList.remove('selecionado'));

    tr.classList.add('selecionado');

    jogoSelecionado = {id: tr.cells[0].innerText};
}

function confirmarExclusao() {
    if (!jogoSelecionado) {
        alert("Selecione um jogo!");
        return;
    }

    formExcluirId.value = jogoSelecionado.id;
    document.getElementById("formExcluir").submit();
}

function ativarBusca(inputId, tabelaId) {
    const input = document.getElementById(inputId);
    if (!input)
        return;

    input.addEventListener("keyup", function () {
        const filtro = this.value.toLowerCase();
        document.querySelectorAll(`#${tabelaId} tbody tr`)
                .forEach(tr => {
                    const nome = tr.cells[1].innerText.toLowerCase();
                    tr.style.display = nome.includes(filtro) ? "" : "none";
                });
    });
}

ativarBusca("buscaPrincipal", "tabelaPrincipal");
ativarBusca("buscaEditar", "tabelaEditarJogos");
ativarBusca("buscaExcluir", "tabelaExcluirJogos");




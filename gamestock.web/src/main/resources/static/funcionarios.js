let funcionarioSelecionado = null;

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
    document.querySelectorAll(".modal-overlay")
            .forEach(m => m.style.display = "none");
    funcionarioSelecionado = null;
}

function selecionarFuncionarioParaEditar(btn) {
    const tr = btn.closest("tr");

    document.getElementById("editarId").value = tr.cells[0].innerText;
    document.getElementById("editarNome").value = tr.cells[1].innerText;
    document.getElementById("editarUsuario").value = tr.cells[2].innerText;

    funcionarioSelecionado = true;
}

function salvarEdicaoFuncionario() {
    if (!funcionarioSelecionado) {
        alert("Selecione um funcionário!");
        return;
    }

    document.getElementById("formEditarId").value =
            document.getElementById("editarId").value;
    document.getElementById("formEditarNome").value =
            document.getElementById("editarNome").value;
    document.getElementById("formEditarUsuario").value =
            document.getElementById("editarUsuario").value;
    document.getElementById("formEditarSenha").value =
            document.getElementById("editarSenha").value;

    document.getElementById("formEditar").submit();
}

function selecionarFuncionarioParaExcluir(btn) {
    const tr = btn.closest("tr");
    funcionarioSelecionado = tr.cells[0].innerText;
}

function confirmarExclusaoFuncionario() {
    if (!funcionarioSelecionado) {
        alert("Selecione um funcionário!");
        return;
    }
    document.getElementById("formExcluirId").value = funcionarioSelecionado;
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
                    tr.style.display =
                            tr.innerText.toLowerCase().includes(filtro) ? "" : "none";
                });
    });
}

ativarBusca("buscaFuncionario", "tabelaFuncionarios");
ativarBusca("buscaEditarFuncionario", "tabelaEditarFuncionarios");
ativarBusca("buscaExcluirFuncionario", "tabelaExcluirFuncionarios");





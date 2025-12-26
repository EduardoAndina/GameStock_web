let vendaSelecionada = null;
let jogoSelecionadoVenda = null;

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

    vendaSelecionada = null;
    jogoSelecionadoVenda = null;
}

const buscaJogosVenda = document.getElementById("buscaJogosVenda");
if (buscaJogosVenda) {
    buscaJogosVenda.addEventListener("keyup", function () {
        const filtro = this.value.toLowerCase();

        document.querySelectorAll("#tabelaJogosVenda tbody tr")
                .forEach(tr => {
                    const nome = tr.cells[1].innerText.toLowerCase();
                    tr.style.display = nome.includes(filtro) ? "" : "none";
                });
    });
}

function selecionarJogoVenda(botao) {
    const tr = botao.closest("tr");

    tr.parentElement.querySelectorAll("tr")
            .forEach(l => l.classList.remove("selecionado"));

    tr.classList.add("selecionado");

    jogoSelecionadoVenda = {
        id: tr.cells[0].innerText,
        nome: tr.cells[1].innerText,
        categoria: tr.cells[2].innerText
    };

    document.getElementById("cadIdJogo").value = jogoSelecionadoVenda.id;
    document.getElementById("cadNomeJogo").value = jogoSelecionadoVenda.nome;
    document.getElementById("cadCategoria").value = jogoSelecionadoVenda.categoria;
}

const buscaEditarVenda = document.getElementById("buscaEditarVenda");
if (buscaEditarVenda) {
    buscaEditarVenda.addEventListener("keyup", function () {
        const filtro = this.value.toLowerCase();

        document.querySelectorAll("#tabelaEditarVendas tbody tr")
                .forEach(tr => {
                    const nome = tr.cells[2].innerText.toLowerCase();
                    tr.style.display = nome.includes(filtro) ? "" : "none";
                });
    });
}

function selecionarVendaParaEditar(botao) {
    const tr = botao.closest("tr");

    tr.parentElement.querySelectorAll("tr")
            .forEach(l => l.classList.remove("selecionado"));

    tr.classList.add("selecionado");

    vendaSelecionada = tr.cells[0].innerText;

    document.getElementById("editarId").value = tr.cells[0].innerText;
    document.getElementById("editarJogoId").value = tr.cells[1].innerText;
    document.getElementById("editarNomeJogo").value = tr.cells[2].innerText;
    document.getElementById("editarCategoria").value = tr.cells[3].innerText;
    document.getElementById("editarQuantidade").value = tr.cells[4].innerText;
    document.getElementById("editarValor").value = tr.cells[5].innerText;
    document.getElementById("editarCliente").value = tr.cells[6].innerText;
    document.getElementById("editarTelefone").value = tr.cells[7].innerText;
}

const buscaExcluirVenda = document.getElementById("buscaExcluirVenda");
if (buscaExcluirVenda) {
    buscaExcluirVenda.addEventListener("keyup", function () {
        const filtro = this.value.toLowerCase();

        document.querySelectorAll("#tabelaExcluirVendas tbody tr")
                .forEach(tr => {
                    const nome = tr.cells[2].innerText.toLowerCase();
                    tr.style.display = nome.includes(filtro) ? "" : "none";
                });
    });
}

function selecionarVendaParaExcluir(botao) {
    const tr = botao.closest("tr");

    tr.parentElement.querySelectorAll("tr")
            .forEach(l => l.classList.remove("selecionado"));

    tr.classList.add("selecionado");

    document.getElementById("excluirId").value = tr.cells[0].innerText;
    document.getElementById("excluirJogoId").value = tr.cells[1].innerText;
    document.getElementById("excluirNomeJogo").value = tr.cells[2].innerText;
    document.getElementById("excluirCategoria").value = tr.cells[3].innerText;
    document.getElementById("excluirQuantidade").value = tr.cells[4].innerText;
    document.getElementById("excluirValor").value = tr.cells[5].innerText;
    document.getElementById("excluirCliente").value = tr.cells[6].innerText;
    document.getElementById("excluirTelefone").value = tr.cells[7].innerText;
}

const buscaVendas = document.getElementById("buscaVendas");

if (buscaVendas) {
    buscaVendas.addEventListener("keyup", function () {
        const filtro = this.value.toLowerCase();

        document.querySelectorAll("#tabelaVendas tbody tr")
                .forEach(tr => {

                    const nomeJogo = tr.cells[2].innerText.toLowerCase();
                    const cliente = tr.cells[6].innerText.toLowerCase();

                    if (
                            nomeJogo.includes(filtro) ||
                            cliente.includes(filtro)
                            ) {
                        tr.style.display = "";
                    } else {
                        tr.style.display = "none";
                    }
                });
    });
}






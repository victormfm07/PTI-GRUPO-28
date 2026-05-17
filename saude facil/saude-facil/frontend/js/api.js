// SAÚDE FÁCIL – Comunicação com a API (Spring Boot em localhost:8080)

const URL_BASE = 'http://localhost:8080/api';

async function requisicao(url, opcoes = {}) {
    const resposta = await fetch(URL_BASE + url, {
        headers: { 'Content-Type': 'application/json' },
        ...opcoes
    });
    if (!resposta.ok) throw new Error(`Erro ${resposta.status}`);
    return resposta.json();
}

const Api = {
    // Usuários
    cadastrarUsuario: (dados)         => requisicao('/usuarios/cadastro', { method: 'POST', body: JSON.stringify(dados) }),
    login:            (email, senha)  => requisicao('/usuarios/login',    { method: 'POST', body: JSON.stringify({ email, senha }) }),

    // Unidades de saúde
    listarUnidades:           ()       => requisicao('/unidades'),
    buscarUnidadesPorTipo:    (tipo)   => requisicao(`/unidades/tipo/${encodeURIComponent(tipo)}`),
    buscarUnidadesPorCidade:  (cidade) => requisicao(`/unidades/cidade/${encodeURIComponent(cidade)}`),

    // Agendamentos
    criarAgendamento:         (usuarioId, unidadeId, dados) =>
        requisicao(`/agendamentos/${usuarioId}/${unidadeId}`, { method: 'POST', body: JSON.stringify(dados) }),
    listarAgendamentosUsuario: (usuarioId) => requisicao(`/agendamentos/usuario/${usuarioId}`),
    cancelarAgendamento:       (id)        => requisicao(`/agendamentos/${id}/cancelar`, { method: 'PUT' }),
};

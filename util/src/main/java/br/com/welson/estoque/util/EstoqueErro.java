package br.com.welson.estoque.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.ws.rs.core.Response;

@AllArgsConstructor
@Getter
public enum EstoqueErro {

    FUNCIONALIDADE_REQUER_AUTENTICACAO("Funcionalidade requer autenticação.", Response.Status.UNAUTHORIZED),
    VOCE_NAO_POSSUI_AUTORIZACAO_PARA_ACESSAR_ESSA_FUNCIONALIDADE("Você não possui permição para acessar essa funcionalidade.", Response.Status.FORBIDDEN),
    CLIENTE_JA_CADASTRADO("Este usuário já está cadastrado.", Response.Status.BAD_REQUEST),
    NOME_DE_USUARIO_JA_EXISTE("Nome de usuário já existe.", Response.Status.BAD_REQUEST);

    private String mensagem;

    private Response.Status codigoResposta;

}

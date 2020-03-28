package br.com.welson.estoque.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.ws.rs.core.Response;

@AllArgsConstructor
@Getter
public enum EstoqueErro
{

    FUNCIONALIDADE_REQUER_AUTENTICACAO("Funcionalidade requer autenticação.", Response.Status.UNAUTHORIZED),
    VOCE_NAO_POSSUI_AUTORIZACAO_PARA_ACESSAR_ESSA_FUNCIONALIDADE("Você não possui permissão para acessar essa funcionalidade.", Response.Status.FORBIDDEN),
    CLIENTE_JA_CADASTRADO("Este usuário já está cadastrado.", Response.Status.BAD_REQUEST),
    NOME_DE_USUARIO_JA_EXISTE("Nome de usuário já existe.", Response.Status.BAD_REQUEST),
    USUARIO_OU_SENHA_INVALIDOS("Nome de usuário ou senha invalidos", Response.Status.UNAUTHORIZED),
    TOKEN_EXPIRADO("Sua sessão expirou, faça o login novamente.", Response.Status.UNAUTHORIZED),
    GRUPO_INVALIDO("Grupo invalido.", Response.Status.BAD_REQUEST),
    FUNCIONALIDADE_INVALIDA("Funcinalidade invalida", Response.Status.BAD_REQUEST),
    NUMERO_DE_PAGINA_INEXISTENTE("Número de página invalida.", Response.Status.BAD_REQUEST),
    NENHUM_RESULTADO("Nenhum resultado encontrado", Response.Status.NOT_FOUND),
    TOKEN_INVALIDO("Sem pemissão para acessar o sistema, acesse com suas credencias", Response.Status.UNAUTHORIZED),
    NENHUM_RELATORIO_ENCONTRADO("Nenhum relatório encontrado", Response.Status.NOT_FOUND);

    private String mensagem;

    private Response.Status codigoResposta;

}

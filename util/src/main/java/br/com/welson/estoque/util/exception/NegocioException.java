package br.com.welson.estoque.util.exception;

import lombok.Getter;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

import br.com.welson.estoque.util.EstoqueErro;

@Getter
public class NegocioException extends Exception {

    private List<String> erros;

    private Response.Status codigo;

    public NegocioException(List<String> erros, Response.Status codigo) {
        super(codigo.toString() + " - " + erros.toString());
        this.erros = erros;
        this.codigo = codigo;
    }

    public NegocioException(EstoqueErro erro) {
        super(erro.getMensagem());
        this.erros = Collections.singletonList(erro.getMensagem());
        this.codigo = erro.getCodigoResposta();
    }

}

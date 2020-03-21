package br.com.welson.estoque.relatorio;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

import br.com.welson.estoque.requisicao.RequisicaoDTO;

@Getter
@Setter
public class RelatorioRequisicaoDTO<T extends RelatorioRespostaDTO<?>> extends RequisicaoDTO<T> {

    @NotNull
    @QueryParam("formato")
    private FormatoRelatorio formato;

}

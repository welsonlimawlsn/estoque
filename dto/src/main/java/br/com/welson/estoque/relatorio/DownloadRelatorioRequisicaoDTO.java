package br.com.welson.estoque.relatorio;

import br.com.welson.estoque.requisicao.RequisicaoDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.PathParam;

@Getter
@Setter
public class DownloadRelatorioRequisicaoDTO extends RequisicaoDTO<DownloadRelatorioRespostaDTO>
{
    @NotEmpty
    @PathParam("nomeRelatorio")
    private String nomeRelatorio;
}

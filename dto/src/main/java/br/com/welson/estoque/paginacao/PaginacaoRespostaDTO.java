package br.com.welson.estoque.paginacao;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import br.com.welson.estoque.requisicao.RespostaDTO;

@Getter
@Setter
public class PaginacaoRespostaDTO<T> extends RespostaDTO {

    private List<T> lista;

    private Integer paginaAtual;

    private Integer totalPaginas;

    private Integer quantidadePorPagina;

}

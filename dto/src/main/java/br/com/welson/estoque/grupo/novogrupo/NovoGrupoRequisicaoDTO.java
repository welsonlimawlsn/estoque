package br.com.welson.estoque.grupo.novogrupo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import br.com.welson.estoque.requisicao.RequisicaoDTO;

@Getter
@Setter
public class NovoGrupoRequisicaoDTO extends RequisicaoDTO<NovoGrupoRespostaDTO> {

    private String nome;

    private List<Long> funcionalidades;

}

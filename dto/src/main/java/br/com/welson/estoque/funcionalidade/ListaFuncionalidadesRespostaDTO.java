package br.com.welson.estoque.funcionalidade;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import br.com.welson.estoque.requisicao.RespostaDTO;

@Getter
@Setter
public class ListaFuncionalidadesRespostaDTO extends RespostaDTO {

    private List<FuncionalidadeDTO> funcionalidades;

}

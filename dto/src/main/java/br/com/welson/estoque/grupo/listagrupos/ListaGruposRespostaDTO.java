package br.com.welson.estoque.grupo.listagrupos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import br.com.welson.estoque.grupo.GrupoDTO;
import br.com.welson.estoque.requisicao.RespostaDTO;

@Getter
@Setter
public class ListaGruposRespostaDTO extends RespostaDTO {

    private List<GrupoDTO> grupos;

}

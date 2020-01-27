package br.com.welson.estoque.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MenuDTO {

    private String caminho;

    private String nome;

    private String descricao;

    private List<MenuDTO> menusFilhos;

}

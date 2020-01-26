package br.com.welson.estoque.funcionalidade;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FuncionalidadeDTO {

    private Long codigo;

    private String nome;

    private String descricao;

}

package br.com.welson.estoque.grupo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GrupoDTO {

    private Long id;

    private String nome;

}

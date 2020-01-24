package br.com.welson.estoque.cliente;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClienteDTO {

    private String nome;

    private String email;

    private String nomeUsuario;

}

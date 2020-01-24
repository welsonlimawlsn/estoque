package br.com.welson.estoque.cliente.login;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

import br.com.welson.estoque.requisicao.RespostaDTO;

@Getter
@Setter
public class LoginClienteRespostaDTO extends RespostaDTO {

    private String token;

    private ZonedDateTime expiracao;

}

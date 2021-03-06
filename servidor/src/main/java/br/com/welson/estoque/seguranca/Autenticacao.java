package br.com.welson.estoque.seguranca;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

import br.com.welson.estoque.cliente.ClienteDTO;
import br.com.welson.estoque.cliente.entidade.Cliente;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Autenticacao {

    private String token;

    private ZonedDateTime expiracao;

    private ClienteDTO clienteDTO;

    private Cliente cliente;

}

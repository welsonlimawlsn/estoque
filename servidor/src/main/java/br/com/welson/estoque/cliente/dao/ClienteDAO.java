package br.com.welson.estoque.cliente.dao;

import java.util.List;
import java.util.Optional;

import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.grupo.entidade.Grupo;
import br.com.welson.estoque.util.dao.DAO;

public interface ClienteDAO extends DAO<Cliente, String> {

    Optional<Cliente> buscaPorEmail(String email);

    Optional<Cliente> buscaPorNomeUsuario(String usuario);

    Optional<Cliente> buscaPorUsuarioESenha(String usuario, String senha);

    List<Cliente> buscaComFiltrosPaginada(String cpf, String nomeCliente, Grupo grupo, Integer numeroPagina, Integer tamanhoPagina);

    Integer totalRegistrosComFitro(String cpf, String nomeCliente, Grupo grupo, Integer tamanhoPagina);

}

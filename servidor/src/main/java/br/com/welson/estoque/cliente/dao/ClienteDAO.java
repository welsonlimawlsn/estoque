package br.com.welson.estoque.cliente.dao;

import java.util.Optional;

import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.util.dao.DAO;

public interface ClienteDAO extends DAO<Cliente, String> {

    Optional<Cliente> buscaPorEmail(String email);

    Optional<Cliente> buscaPorNomeUsuario(String usuario);

}

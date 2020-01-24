package br.com.welson.estoque.cliente.dao;

import javax.persistence.TypedQuery;
import java.util.Optional;

import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.util.dao.DAOImpl;

public class ClienteDAOImpl extends DAOImpl<Cliente, String> implements ClienteDAO {

    public ClienteDAOImpl() {
        super(Cliente.class);
    }

    @Override
    public Optional<Cliente> buscaPorEmail(String email) {
        TypedQuery<Cliente> query = entityManager.createNamedQuery("buscaPorEmail", Cliente.class);
        query.setParameter("email", email);
        return getSingleResultWithOptional(query);
    }

    @Override
    public Optional<Cliente> buscaPorNomeUsuario(String usuario) {
        TypedQuery<Cliente> query = entityManager.createNamedQuery("buscaPorNomeUsuario", Cliente.class);
        query.setParameter("nomeUsuario", usuario);
        return getSingleResultWithOptional(query);
    }

    @Override
    public Optional<Cliente> buscaPorUsuarioESenha(String usuario, String senha) {
        TypedQuery<Cliente> query = entityManager.createNamedQuery("buscaPorUsuarioESenha", Cliente.class);
        query.setParameter("nomeUsuario", usuario);
        query.setParameter("senha", senha);
        return getSingleResultWithOptional(query);
    }

}

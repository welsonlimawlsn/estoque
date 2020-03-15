package br.com.welson.estoque.cliente.dao;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.grupo.entidade.Grupo;
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

    @Override
    public Optional<Cliente> buscaPorUsuario(String usuario) {
        TypedQuery<Cliente> query = entityManager.createNamedQuery("buscaPorUsuario", Cliente.class);
        query.setParameter("nomeUsuario", usuario);
        return getSingleResultWithOptional(query);
    }

    @Override
    public List<Cliente> buscaComFiltrosPaginada(String cpf, String nomeCliente, Grupo grupo, Integer numeroPagina, Integer tamanhoPagina) {
        TypedQuery<Cliente> query = criaQueryComFiltros(cpf, nomeCliente, grupo);

        query.setFirstResult((numeroPagina - 1) * tamanhoPagina);
        query.setMaxResults(tamanhoPagina);

        return query.getResultList();
    }

    private TypedQuery<Cliente> criaQueryComFiltros(String cpf, String nomeCliente, Grupo grupo) {
        StringBuilder queryString = new StringBuilder("SELECT c FROM Cliente c WHERE 1=1");

        Map<String, Object> parameters = appendFiltros(cpf, nomeCliente, grupo, queryString);

        TypedQuery<Cliente> query = entityManager.createQuery(queryString.toString(), Cliente.class);

        setParameters(parameters, query);
        return query;
    }

    @Override
    public List<Cliente> buscaComFiltros(String cpf, String nomeCliente, Grupo grupo) {
        return criaQueryComFiltros(cpf, nomeCliente, grupo).getResultList();
    }

    @Override
    public Integer totalRegistrosComFitro(String cpf, String nomeCliente, Grupo grupo, Integer tamanhoPagina) {
        StringBuilder queryString = new StringBuilder("SELECT count(c) FROM Cliente c WHERE 1=1");

        Map<String, Object> parameters = appendFiltros(cpf, nomeCliente, grupo, queryString);

        TypedQuery<Long> query = entityManager.createQuery(queryString.toString(), Long.class);

        setParameters(parameters, query);

        Long total = query.getSingleResult();

        return (int) (total / tamanhoPagina) + (total % tamanhoPagina == 0 ? 0 : 1);
    }

    private void setParameters(Map<String, Object> parameters, TypedQuery<?> query) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    private Map<String, Object> appendFiltros(String cpf, String nomeCliente, Grupo grupo, StringBuilder queryString) {
        Map<String, Object> parameters = new HashMap<>();
        if (cpf != null && !cpf.isEmpty()) {
            queryString.append(" AND c.cpf = :cpf");
            parameters.put("cpf", cpf);
        }

        if (nomeCliente != null && !nomeCliente.isEmpty()) {
            queryString.append(" AND upper(c.nome) like upper(:nome)");
            parameters.put("nome", "%" + nomeCliente + "%");
        }

        if (grupo != null) {
            queryString.append(" AND c.grupo = :grupo");
            parameters.put("grupo", grupo);
        }
        return parameters;
    }

}

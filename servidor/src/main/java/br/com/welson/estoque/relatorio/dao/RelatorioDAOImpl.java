package br.com.welson.estoque.relatorio.dao;

import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.relatorio.entidade.Relatorio;
import br.com.welson.estoque.util.dao.DAOImpl;

import javax.persistence.TypedQuery;
import java.util.Optional;

public class RelatorioDAOImpl extends DAOImpl<Relatorio, String> implements RelatorioDAO
{
    public RelatorioDAOImpl()
    {
        super(Relatorio.class);
    }

    @Override
    public Optional<Relatorio> buscaRelatorioValidoPorNomeECliente(String nome, Cliente cliente)
    {
        TypedQuery<Relatorio> query = entityManager.createNamedQuery("buscaRelariosValidosPorNomeECliente", Relatorio.class);
        query.setParameter("cliente", cliente);
        query.setParameter("nome", nome);
        return getSingleResultWithOptional(query);
    }
}

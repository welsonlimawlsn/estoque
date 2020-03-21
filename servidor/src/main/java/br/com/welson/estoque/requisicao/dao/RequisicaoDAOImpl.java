package br.com.welson.estoque.requisicao.dao;

import java.util.List;

import br.com.welson.estoque.requisicao.entidade.Requisicao;
import br.com.welson.estoque.util.dao.DAOImpl;

public class RequisicaoDAOImpl extends DAOImpl<Requisicao, Long> implements RequisicaoDAO {

    public RequisicaoDAOImpl() {
        super(Requisicao.class);
    }

    @Override
    public List<Requisicao> listaTodosOrdenado() {
        return entityManager.createQuery("SELECT r FROM Requisicao r ORDER BY r.dataHora DESC", Requisicao.class).getResultList();
    }

}

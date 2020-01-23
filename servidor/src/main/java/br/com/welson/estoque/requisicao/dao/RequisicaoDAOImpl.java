package br.com.welson.estoque.requisicao.dao;

import br.com.welson.estoque.requisicao.entidade.Requisicao;
import br.com.welson.estoque.util.dao.DAOImpl;

public class RequisicaoDAOImpl extends DAOImpl<Requisicao, Long> implements RequisicaoDAO {

    public RequisicaoDAOImpl() {
        super(Requisicao.class);
    }

}

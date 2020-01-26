package br.com.welson.estoque.funcionalidade.dao;

import java.util.List;

import br.com.welson.estoque.funcionalidade.entidade.Funcionalidade;
import br.com.welson.estoque.util.dao.DAOImpl;

public class FuncionalidadeDAOImpl extends DAOImpl<Funcionalidade, Long> implements FuncionalidadeDAO {

    public FuncionalidadeDAOImpl() {
        super(Funcionalidade.class);
    }

    @Override
    public List<Funcionalidade> buscaFuncionalidades() {
        return entityManager.createNamedQuery("buscaFuncionalidades", Funcionalidade.class).getResultList();
    }

}

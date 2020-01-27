package br.com.welson.estoque.menu.dao;

import javax.persistence.TypedQuery;
import java.util.List;

import br.com.welson.estoque.funcionalidade.entidade.Funcionalidade;
import br.com.welson.estoque.menu.entidade.Menu;
import br.com.welson.estoque.util.dao.DAOImpl;

public class MenuDAOImpl extends DAOImpl<Menu, Long> implements MenuDAO {

    public MenuDAOImpl() {
        super(Menu.class);
    }

    @Override
    public List<Menu> buscaMenusPorPaiEFuncionalidades(Menu pai, List<Funcionalidade> funcionalidades) {
        TypedQuery<Menu> query = entityManager.createNamedQuery("buscaFilhosPorFuncionalidades", Menu.class);
        query.setParameter("menuPai", pai);
        query.setParameter("funcionalidades", funcionalidades);
        return query.getResultList();
    }

    @Override
    public List<Menu> buscaMenusPai() {
        return entityManager.createNamedQuery("buscaMenusPai", Menu.class).getResultList();
    }

}

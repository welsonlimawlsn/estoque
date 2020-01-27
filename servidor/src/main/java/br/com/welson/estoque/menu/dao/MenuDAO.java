package br.com.welson.estoque.menu.dao;

import java.util.List;

import br.com.welson.estoque.funcionalidade.entidade.Funcionalidade;
import br.com.welson.estoque.menu.entidade.Menu;
import br.com.welson.estoque.util.dao.DAO;

public interface MenuDAO extends DAO<Menu, Long> {

    List<Menu> buscaMenusPorPaiEFuncionalidades(Menu pai, List<Funcionalidade> funcionalidades);

    List<Menu> buscaMenusPai();

}

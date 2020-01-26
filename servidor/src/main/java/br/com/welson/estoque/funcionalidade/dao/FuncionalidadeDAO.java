package br.com.welson.estoque.funcionalidade.dao;

import java.util.List;

import br.com.welson.estoque.funcionalidade.entidade.Funcionalidade;
import br.com.welson.estoque.util.dao.DAO;

public interface FuncionalidadeDAO extends DAO<Funcionalidade, Long> {

    List<Funcionalidade> buscaFuncionalidades();

}

package br.com.welson.estoque.requisicao.dao;

import java.util.List;

import br.com.welson.estoque.requisicao.entidade.Requisicao;
import br.com.welson.estoque.util.dao.DAO;

public interface RequisicaoDAO extends DAO<Requisicao, Long> {

    List<Requisicao> listaTodosOrdenado();

}

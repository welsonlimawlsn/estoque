package br.com.welson.estoque.relatorio.dao;

import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.relatorio.entidade.Relatorio;
import br.com.welson.estoque.util.dao.DAO;

import java.util.Optional;

public interface RelatorioDAO extends DAO<Relatorio, String>
{

    Optional<Relatorio> buscaRelatorioValidoPorNomeECliente(String nome, Cliente cliente);
}

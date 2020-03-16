package br.com.welson.estoque.requisicao.relatorio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class RequisicaoRelatorioDTO {

    private Long idRequisicao;

    private String nomeFuncionalidade;

    private Date dataHora;

    private String ipOrigem;

    private String cpfCliente;

    private String nomeCliente;

    private String grupoCliente;

}

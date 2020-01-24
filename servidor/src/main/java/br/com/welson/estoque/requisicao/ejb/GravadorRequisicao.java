package br.com.welson.estoque.requisicao.ejb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.ext.ContextResolver;

import br.com.welson.estoque.requisicao.RequisicaoDTO;
import br.com.welson.estoque.requisicao.dao.RequisicaoDAO;
import br.com.welson.estoque.requisicao.entidade.Requisicao;
import br.com.welson.estoque.util.exception.InfraestruturaException;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class GravadorRequisicao {

    @Inject
    private RequisicaoDAO requisicaoDAO;

    @Inject
    private ContextResolver<ObjectMapper> objectMapper;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void gravaNovaRequisicao(RequisicaoDTO<?> requisicaoDTO) throws InfraestruturaException {
        Requisicao requisicao = new Requisicao();

        preencheRequisicao(requisicaoDTO, requisicao);

        requisicaoDAO.insere(requisicao);

        requisicaoDTO.setId(requisicao.getId());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void atualizaRequisicao(RequisicaoDTO<?> requisicaoDTO) throws InfraestruturaException {
        Requisicao requisicao = requisicaoDAO.buscaPorId(requisicaoDTO.getId()).get();

        preencheRequisicao(requisicaoDTO, requisicao);
    }

    private void preencheRequisicao(RequisicaoDTO<?> requisicaoDTO, Requisicao requisicao) throws InfraestruturaException {
        requisicao.setCliente(requisicaoDTO.getCliente());
        requisicao.setDataHora(requisicaoDTO.getDataHora());
        requisicao.setFuncionalidade(requisicaoDTO.getFuncionalidade());
        requisicao.setIpOrigem(requisicaoDTO.getIpOrigem());
        requisicao.setParticao(toJson(requisicaoDTO));
        requisicao.setMotivoFalha(requisicaoDTO.getMotivoFalha());
    }

    private String toJson(RequisicaoDTO<?> requisicaoDTO) throws InfraestruturaException {
        try {
            ObjectMapper objectMapper = this.objectMapper.getContext(null);
            return objectMapper.writeValueAsString(requisicaoDTO);
        } catch (JsonProcessingException e) {
            throw new InfraestruturaException(e);
        }
    }

}

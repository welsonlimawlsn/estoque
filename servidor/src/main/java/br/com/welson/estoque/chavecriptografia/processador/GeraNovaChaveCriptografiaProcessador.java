package br.com.welson.estoque.chavecriptografia.processador;

import javax.crypto.KeyGenerator;
import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import br.com.welson.estoque.chavecriptografia.gerachave.GeraNovaChaveRequisicaoDTO;
import br.com.welson.estoque.chavecriptografia.gerachave.GeraNovaChaveRespostaDTO;
import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.parametro.CodigoParametro;
import br.com.welson.estoque.parametro.dao.ParametroDAO;
import br.com.welson.estoque.parametro.entidade.Parametro;
import br.com.welson.estoque.requisicao.anotacao.Processador;
import br.com.welson.estoque.requisicao.processador.AbstractProcessadorRequisicao;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Processador(CodigoFuncionalidade.GERA_NOVA_CHAVE_CRIPTOGRAFIA_TOKEN)
public class GeraNovaChaveCriptografiaProcessador extends AbstractProcessadorRequisicao<GeraNovaChaveRequisicaoDTO, GeraNovaChaveRespostaDTO> {

    public static final String ALGORITHM = "HmacSHA256";

    public static final String DESCRICAO_PARAMETRO = "Chave de criptografia token";

    @Inject
    private ParametroDAO parametroDAO;

    @Override
    protected void executaRequisicao(GeraNovaChaveRequisicaoDTO requisicao, GeraNovaChaveRespostaDTO resposta) throws NegocioException, InfraestruturaException {
        try {

            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);

            String secretKey = Base64.getEncoder().encodeToString(generator.generateKey().getEncoded());

            Parametro parametroChave = parametroDAO.buscaPorId(CodigoParametro.CHAVE_CRIPTOGRAFIA_TOKEN).orElseGet(this::criaParametro);

            parametroChave.setValor(secretKey);

        } catch (NoSuchAlgorithmException e) {
            throw new InfraestruturaException(e);
        }
    }

    private Parametro criaParametro() {
        Parametro parametro = new Parametro();
        parametro.setCodigo(CodigoParametro.CHAVE_CRIPTOGRAFIA_TOKEN);
        parametro.setDescricao(DESCRICAO_PARAMETRO);
        parametro.setValor("valor padrão");
        parametroDAO.insere(parametro);
        return parametro;
    }

}

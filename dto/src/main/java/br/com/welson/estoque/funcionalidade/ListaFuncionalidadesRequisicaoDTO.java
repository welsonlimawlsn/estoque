package br.com.welson.estoque.funcionalidade;

import br.com.welson.estoque.requisicao.RequisicaoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaFuncionalidadesRequisicaoDTO extends RequisicaoDTO<ListaFuncionalidadesRespostaDTO> {

    public static void main(String[] args) {
        String mmmm = new java.text.SimpleDateFormat("'Relatório emitido dia' d 'de' MMMM 'de' yyyy 'às' HH:mm", java.util.Locale.forLanguageTag("pt-BR")).format(new java.util.Date());
        System.out.println(mmmm);
    }
}

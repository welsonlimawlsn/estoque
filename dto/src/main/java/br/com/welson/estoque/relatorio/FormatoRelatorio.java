package br.com.welson.estoque.relatorio;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormatoRelatorio {

    PDF(".pdf"),
    XLS(".xls"),
    RTF(".rtf");

    private String extensao;
}

package br.com.welson.estoque.requisicao.anotacao.seletor;

import lombok.AllArgsConstructor;

import java.lang.annotation.Annotation;

import br.com.welson.estoque.requisicao.anotacao.Processador;

@AllArgsConstructor
public class SeletorProcessador implements Processador {

    private long value;

    @Override
    public long value() {
        return value;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Processador.class;
    }

}

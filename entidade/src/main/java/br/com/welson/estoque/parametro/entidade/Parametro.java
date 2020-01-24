package br.com.welson.estoque.parametro.entidade;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "PARAMETRO")
public class Parametro {

    @Id
    @Column(name = "CODIGO")
    private Long codigo;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "VALOR", nullable = false, length = 10000)
    private String valor;

}

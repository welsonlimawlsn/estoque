package br.com.welson.estoque.funcionalidade.entidade;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "FUNCIONALIDADE")
public class Funcionalidade {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "CODIGO")
    private Long codigo;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "AUTENTICACAO_NECESSARIA", nullable = false)
    private Boolean autenticacaoNecessaria;

    @Column(name = "FUNCIONALIDADE_PUBLICA")
    private Boolean publica;

}

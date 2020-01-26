package br.com.welson.estoque.grupo.entidade;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

import br.com.welson.estoque.funcionalidade.entidade.Funcionalidade;

@Getter
@Setter
@Entity
@Table(name = "GRUPO")
public class Grupo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SEQ_GRUPO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_GRUPO", sequenceName = "SEQ_GRUPO", allocationSize = 1)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(
            name = "GRUPO_FUNCIONALIDADE",
            joinColumns = {@JoinColumn(name = "ID_GRUPO")},
            inverseJoinColumns = {@JoinColumn(name = "CODIGO_FUNCIONALIDADE")}
    )
    private List<Funcionalidade> funcionalidadesAcessiveis;

}

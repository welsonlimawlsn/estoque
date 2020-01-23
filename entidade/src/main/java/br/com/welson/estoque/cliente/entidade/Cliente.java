package br.com.welson.estoque.cliente.entidade;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.List;

import br.com.welson.estoque.funcionalidade.entidade.Funcionalidade;

@Getter
@Setter
@Entity
@Table(name = "CLIENTE")
@NamedQueries({
        @NamedQuery(name = "buscaPorEmail", query = "SELECT c FROM Cliente c WHERE email = :email"),
        @NamedQuery(name = "buscaPorNomeUsuario", query = "SELECT c FROM Cliente c WHERE usuario = :nomeUsuario")
})
public class Cliente {

    @Id
    @Column(name = "CPF", length = 11)
    protected String cpf;

    @Column(name = "NOME", length = 50, nullable = false)
    private String nome;

    @Column(name = "USUARIO", length = 20, nullable = false, unique = true)
    private String usuario;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "CLIENTE_FUNCIONALIDADE",
            joinColumns = {@JoinColumn(name = "CPF_CLIENTE")},
            inverseJoinColumns = {@JoinColumn(name = "CODIGO_FUNCIONALIDADE")}
    )
    private List<Funcionalidade> funcionalidadesAcessiveis;

}

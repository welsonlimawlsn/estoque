package br.com.welson.estoque.cliente.entidade;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.welson.estoque.grupo.entidade.Grupo;

@Getter
@Setter
@Entity
@Table(name = "CLIENTE")
@NamedQueries({
        @NamedQuery(name = "buscaPorEmail", query = "SELECT c FROM Cliente c WHERE email = :email"),
        @NamedQuery(name = "buscaPorNomeUsuario", query = "SELECT c FROM Cliente c WHERE usuario = :nomeUsuario"),
        @NamedQuery(name = "buscaPorUsuarioESenha", query = "SELECT c FROM Cliente c WHERE usuario = :nomeUsuario AND senha = :senha")
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

    @ManyToOne
    @JoinColumn(name = "GRUPO_ID")
    private Grupo grupo;

}

package br.com.welson.estoque.usuario.entidade;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @Column(name = "CPF")
    private String cpf;

    @Column(name = "NOME_COMPLETO", length = 50, nullable = false)
    private String nomeCompleto;

    @Column(name = "NOME_USUARIO", length = 20, nullable = false, unique = true)
    private String nomeUsuario;

    @Column(name = "SENHA")
    private String senha;

}

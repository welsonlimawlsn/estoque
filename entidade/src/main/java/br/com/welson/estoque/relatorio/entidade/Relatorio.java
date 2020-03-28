package br.com.welson.estoque.relatorio.entidade;

import br.com.welson.estoque.cliente.entidade.Cliente;
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

@Entity
@Table(name = "relatorio")
@NamedQueries({
        @NamedQuery(name = "buscaRelariosValidosPorNomeECliente",
                query = "SELECT r FROM Relatorio r WHERE r.nome = :nome AND r.cliente = :cliente AND invalidado = false")
})
@Getter
@Setter
public class Relatorio
{
    @Id
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @Column(nullable = false, name = "invalidado")
    private Boolean invalidado;
}

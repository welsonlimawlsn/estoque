package br.com.welson.estoque.requisicao.entidade;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.funcionalidade.entidade.Funcionalidade;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "REQUISICAO")
public class Requisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REQUISICAO")
    @SequenceGenerator(name = "SEQ_REQUISICAO", sequenceName = "SEQ_REQUISICAO", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "DATA_HORA", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "IP_ORIGEM", nullable = false)
    private String ipOrigem;

    @ManyToOne
    @JoinColumn(name = "CLIENTE")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FUNCIONALIDADE")
    private Funcionalidade funcionalidade;

    @Column(name = "PARTICAO", nullable = false, length = 10000)
    private String particao;

    @Column(name = "MOTIVO_FALHA")
    private String motivoFalha;

}

package br.com.welson.estoque.menu.entidade;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

import br.com.welson.estoque.funcionalidade.entidade.Funcionalidade;

@Getter
@Setter
@Entity
@Table(name = "MENU")
@NamedQueries({
        @NamedQuery(name = "buscaMenusPai", query = "SELECT m FROM Menu m WHERE menuPai is null"),
        @NamedQuery(name = "buscaFilhosPorFuncionalidades",
                query = "SELECT m FROM Menu m WHERE m.menuPai = :menuPai AND m.funcionalidade in (:funcionalidades)")
})
public class Menu {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CAMINHO")
    private String caminho;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNCIONALIDADE")
    private Funcionalidade funcionalidade;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_PAI")
    private Menu menuPai;

    @OneToMany(mappedBy = "menuPai", fetch = FetchType.EAGER)
    private List<Menu> menusFilhos;

}

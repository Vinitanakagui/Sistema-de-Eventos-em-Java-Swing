
package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import modelo.enuns.Ativo;

@Entity
@Table(name = "semanas")
@NamedQueries
({
    @NamedQuery(name = "Semana.buscarTodos", query = "SELECT s FROM Semana s"),
    @NamedQuery(name = "Semana.pesquisar", query = "SELECT s FROM Semana s WHERE s.nome LIKE :nome"),
    @NamedQuery(name = "Semana.buscarSemana", query = "SELECT s FROM Semana s WHERE s.nome LIKE :nome AND s.inicio = :inicio AND s.fim = :fim"),
    @NamedQuery(name = "Semana.buscarSemanaAtiva", query = "SELECT s FROM Semana s WHERE s.ativa = :ativa"),
    @NamedQuery(name = "Semana.pesquisarSemanaAtiva", query = "SELECT s FROM Semana s WHERE s.nome LIKE :nome AND s.ativa = :ativa"),
})

public class Semana implements Serializable
{
    @Id
    @Column(name = "idsemana")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSemana;
    
    @Column(name = "nome", length = 80)
    private String nome;
    
    @Column(name = "local", length = 80)
    private String local;
    
    @Column(name = "inicio")
    private LocalDate inicio;
    
    @Column(name = "fim")
    private LocalDate fim;
    
    @Column(name = "observacao", length = 255)
    private String observacao;
    
    @Column(name = "ativa", length = 1)
    @Enumerated(EnumType.STRING)
    private Ativo ativa;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semana", fetch = FetchType.EAGER)
    private List<Organizador> listaOrganizadores;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semana", fetch = FetchType.EAGER)
    private List<Evento> listaEventos;


    public Semana() {
        
    }

    public Integer getIdSemana() {
        return idSemana;
    }

    public void setIdSemana(Integer idSemana) {
        this.idSemana = idSemana;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Ativo getAtiva() {
        return ativa;
    }

    public void setAtiva(Ativo ativa) {
        this.ativa = ativa;
    }

    public List<Organizador> getListaOrganizadores() {
        return listaOrganizadores;
    }

    public void setListaOrganizadores(List<Organizador> listaOrganizadores) {
        this.listaOrganizadores = listaOrganizadores;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idSemana);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Semana other = (Semana) obj;
        return Objects.equals(this.idSemana, other.idSemana);
    }
    
    
    
    
}

package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;  
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "eventos")
@NamedQueries
({

    @NamedQuery(name = "Evento.buscarTodos", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.buscarListaEventoPorSemana", query = "SELECT e FROM Evento e WHERE e.semana.idSemana = :idSemana"),
    @NamedQuery(name = "Evento.pesquisar", query = "SELECT e FROM Evento e WHERE e.titulo LIKE :nome"),
    @NamedQuery(name = "Evento.buscarEvento", query = "SELECT e FROM Evento e WHERE e.titulo LIKE :Nome"),
    @NamedQuery(name = "Evento.buscarEventoPorSemana", query = "SELECT e FROM Evento e WHERE e.semana.idSemana = :idsemana AND e.titulo = :nomeevento"),
    @NamedQuery(name = "Evento.buscarEventoPorData", query = "SELECT e FROM Evento e WHERE e.titulo LIKE :nome AND e.data = :data"),
    @NamedQuery(name = "Evento.buscarPorNomeSemana", query = "SELECT e FROM Evento e WHERE e.titulo = :titulo AND e.semana.nome = :nome"),
    @NamedQuery(name = "Evento.contarEventos", query = "SELECT COUNT(e) FROM Evento e"),
@NamedQuery(name = "Evento.somarCargaHoraria", query = "SELECT SUM(e.cargaHoraria) FROM Evento e")

})
        
public class Evento implements Serializable 
{
    @Id
    @Column(name = "ideventos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "idsemana", nullable = false)
    private Semana semana;
    
    @Column(name = "titulo", length = 80)
    private String titulo;
    
    @Column(name = "cargahoraria")
    private Integer cargaHoraria;
    
    @Column(name = "numerovagas")
    private Integer numeroVagas;
    
    @Column(name = "qtdeinscrito")
    private Integer qntdeIncrito;
    
    @Column(name = "dataevento")
    private LocalDate data;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
    private List<Matricula> listaMatricula;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
    private List<Responsavel> listaResponsavel;

    public Evento() {
    }
    
    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Semana getSemana() {
        return semana;
    }

    public void setSemana(Semana semana) {
        this.semana = semana;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Integer getNumeroVagas() {
        return numeroVagas;
    }

    public void setNumeroVagas(Integer numeroVagas) {
        this.numeroVagas = numeroVagas;
    }

    public Integer getQntdeIncrito() {
        return qntdeIncrito;
    }

    public void setQntdeIncrito(Integer qntdeIncrito) {
        this.qntdeIncrito = qntdeIncrito;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Matricula> getListaMatricula() {
        return listaMatricula;
    }

    public void setListaMatricula(List<Matricula> listaMatricula) {
        this.listaMatricula = listaMatricula;
    }

    public List<Responsavel> getListaResponsavel() {
        return listaResponsavel;
    }

    public void setListaResponsavel(List<Responsavel> listaResponsavel) {
        this.listaResponsavel = listaResponsavel;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idEvento);
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
        final Evento other = (Evento) obj;
        return Objects.equals(this.idEvento, other.idEvento);
    }
    
    
}

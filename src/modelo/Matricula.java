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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import modelo.enuns.Status;

@Entity
@Table(name = "matriculas")
@NamedQueries
({
    @NamedQuery(name = "Matricula.buscarIncritos", query = "SELECT m FROM Matricula m WHERE m.evento.idEvento = :idevento"),
    @NamedQuery(name = "Matricula.totalInscritos", query = "SELECT COUNT(m) FROM Matricula m WHERE m.evento.idEvento = :idevento"),
    @NamedQuery(name = "Matricula.pesquisar", query = "SELECT m FROM Matricula m WHERE m.participante.nome LIKE :nome"),
    @NamedQuery(name = "Matricula.buscarPorEvento", query = "SELECT m FROM Matricula m WHERE m.participante.nome = :nome AND m.evento.idEvento = :idevento"),
    @NamedQuery(name = "Matricula.buscarUltimoId", query = "SELECT MAX(m.idMatricula) FROM Matricula m WHERE m.participante.prontuario = :prontuario"),
    @NamedQuery(name = "Matricula.buscarMatriculasEvento", query = "SELECT m FROM Matricula m WHERE m.participante.prontuario = :prontuario AND m.evento.semana.idSemana = :idsemana"),
    @NamedQuery(name = "Matricula.totalCargaHorariaPorProntuario", query = "SELECT SUM(m.evento.cargaHoraria) FROM Matricula m WHERE m.participante.prontuario = :prontuario"),
    @NamedQuery(name = "Matricula.totalEventosPorProntuario", query = "SELECT COUNT(DISTINCT m.evento.idEvento) FROM Matricula m WHERE m.participante.prontuario = :prontuario")
        


})

public class Matricula implements Serializable 
{
    @Id
    @Column(name = "idmatriculas")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMatricula;
    
    @ManyToOne
    @JoinColumn(name = "ideventos", nullable = false)
    public Evento evento;

    @ManyToOne
    @JoinColumn(name = "prontuario", nullable = false)
    public Participante participante;
   
    @Column(name = "data")
    private LocalDate data;
    
    @Column(name = "status", length = 1)
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matricula")
    private List<Presenca> listaPresenca;
      
    public Matricula() {
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public List<Presenca> getListaPresenca() {
        return listaPresenca;
    }

    public void setListaPresenca(List<Presenca> listaPresenca) {
        this.listaPresenca = listaPresenca;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.idMatricula);
        hash = 47 * hash + Objects.hashCode(this.evento);
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
        final Matricula other = (Matricula) obj;
        if (!Objects.equals(this.idMatricula, other.idMatricula)) {
            return false;
        }
        return Objects.equals(this.evento, other.evento);
    }
      
}

package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import modelo.pk.ResponsavelPK;

@Entity
@Table(name = "responsaveis")
@IdClass(ResponsavelPK.class)
@NamedQueries
({
    @NamedQuery
    (
    name = "Responsavel.buscarResponsavel",
    query = "SELECT r FROM Responsavel r " +
            "WHERE r.participante.prontuario = :prontuario " +
            "AND r.evento.data < CURRENT_DATE"
    ),
    @NamedQuery(name = "Responsavel.buscarResponsavelPorEvento", query = "SELECT r FROM Responsavel r WHERE r.evento.idEvento = :idevento")


})

public class Responsavel implements Serializable 
{
     
    @Id
    @Column(name = "idresponsaveis")
    private Integer idResponsavel;
    
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "ideventos")
    private Evento evento;
    
    @ManyToOne
    @JoinColumn(name = "prontuario", nullable = false)
    private Participante participante;

    public Responsavel() {
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public Evento getEvento() {
        return evento;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.idResponsavel);
        hash = 23 * hash + Objects.hashCode(this.evento);
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
        final Responsavel other = (Responsavel) obj;
        if (!Objects.equals(this.idResponsavel, other.idResponsavel)) {
            return false;
        }
        return Objects.equals(this.evento, other.evento);
    }

  
}

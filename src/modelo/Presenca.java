package modelo;

import java.io.Serializable;
import java.time.LocalDate;
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
import modelo.pk.PresencaPK;

@Entity
@Table(name = "presencas")
@IdClass(PresencaPK.class)
@NamedQueries
({
   @NamedQuery(name = "Presenca.buscarPresenca", query = "SELECT p FROM Presenca p WHERE p.matricula.idMatricula = :idmatricula"),
   @NamedQuery(name = "Presenca.buscarUltimoID", query = "SELECT MAX(p.idPresenca) FROM Presenca p WHERE p.matricula.idMatricula = :idmatricula")
})

public class Presenca implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idmatriculas")
    private Matricula matricula;

    @Id
    @Column(name = "idpresenca")
    private Integer idPresenca;  

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "qtdehoras")
    private Integer quantidadeHoras;

    public Presenca()
    {
    
    }


    public Integer getIdPresenca() {
        return idPresenca;
    }

    public void setIdPresenca(Integer idPresenca) {
        this.idPresenca = idPresenca;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getQuantidadeHoras() {
        return quantidadeHoras;
    }

    public void setQuantidadeHoras(Integer quantidadeHoras) {
        this.quantidadeHoras = quantidadeHoras;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.matricula);
        hash = 71 * hash + Objects.hashCode(this.idPresenca);
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
        final Presenca other = (Presenca) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return Objects.equals(this.idPresenca, other.idPresenca);
    }

    
}


package modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cursos")
@NamedQueries
({
    @NamedQuery(name = "Curso.buscarTodos", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.buscarCurso", query = "SELECT c FROM Curso c WHERE c.nome = :nome"),
    
})

public class Curso implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcursos")
    private Integer idCurso;
    
    @Column(name = "sigla", length = 12)
    private String sigla;
    
    @Column(name = "nomecurso", length = 45)
    private String nome;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    List<Organizador> listaOrganizadores;

    public Curso() {
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Organizador> getListaOrganizadores() {
        return listaOrganizadores;
    }

    public void setListaOrganizadores(List<Organizador> listaOrganizadores) {
        this.listaOrganizadores = listaOrganizadores;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.idCurso);
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
        final Curso other = (Curso) obj;
        return Objects.equals(this.idCurso, other.idCurso);
    }
    
    
}

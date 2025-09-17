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
import modelo.pk.OrganizadorPK;

@Entity
@Table(name = "organizadores")
@IdClass(OrganizadorPK.class)
@NamedQueries
({
   @NamedQuery(name = "Organizador.buscarOrganizadorPorSemana", query = "SELECT o FROM Organizador o WHERE o.nome = :nome AND o.semana.idSemana = :idsemana")



})
        
public class Organizador implements Serializable 
{
     
    @Id
    @Column(name = "idorganizador")
    private Integer idOrganizador;
    
    @Column(name = "nome", length = 60)
    private String nome;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "idsemana")
    private Semana semana;
    
    @ManyToOne
    @JoinColumn(name = "idcurso")
    private Curso curso;

    public Organizador() {
    }

    public Integer getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(Integer idOrganizador) {
        this.idOrganizador = idOrganizador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Semana getSemana() {
        return semana;
    }

    public void setSemana(Semana semana) {
        this.semana = semana;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.idOrganizador);
        hash = 47 * hash + Objects.hashCode(this.semana);
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
        final Organizador other = (Organizador) obj;
        if (!Objects.equals(this.idOrganizador, other.idOrganizador)) {
            return false;
        }
        return Objects.equals(this.semana, other.semana);
    }

    
    
    
}

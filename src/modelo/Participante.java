package modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "participantes")
@NamedQueries
({
    @NamedQuery(name = "Participante.buscarLogin", query = "SELECT p FROM Participante p WHERE p.usario = :usuario AND p.senha = :senha"),
    @NamedQuery(name = "Participante.buscarParticipante", query = "SELECT p FROM Participante p WHERE p.prontuario = :prontuario"),
    @NamedQuery(name = "Participante.pesquisarPorNome", query = "SELECT p FROM Participante p WHERE p.nome LIKE :nome"),
    @NamedQuery(name = "Participante.buscarTodos", query = "SELECT p FROM Participante p"),
    @NamedQuery(name = "Participante.pesquisarPorCurso", query = "SELECT p FROM Participante p WHERE p.nome LIKE :nome AND p.curso.nome = :curso"),
    @NamedQuery(name = "Participante.buscarProntuarioPorNome", query = "SELECT p.prontuario FROM Participante p WHERE p.nome = :nome")
})

public class Participante implements Serializable 
{
    @Id
    @Column(name = "prontuario", length = 9, nullable = false)
    private String prontuario;
    
    @ManyToOne
    @JoinColumn(name = "idcursos")
    private Curso curso;
    
    @Column(name = "nome", length = 45)
    private String nome;
   
    @Column(name = "endereco", length = 60)
    private String endereco;
    
    @Column(name = "cidade", length = 45)
    private String cidade;
    
    @Column(name = "uf", length = 2)
    private String uf;
    
    @Column(name = "cep", length = 9)
    private String cep;
    
    @Column(name = "email", length = 60)
    private String email;
    
    @Column(name = "telefone", length = 25)
    private String telefone;
    
    @Column(name = "cpf", length = 14)
    private String cpf;
    
    @Column(name = "usuario", length = 15)
    private String usario;
    
    @Column(name = "senha", length = 15)
    private String senha;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participante")
    List<Matricula> listaMatricula;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participante")
    List<Responsavel> listaResponsavel;

    public Participante() {
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUsario() {
        return usario;
    }

    public void setUsario(String usario) {
        this.usario = usario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.prontuario);
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
        final Participante other = (Participante) obj;
        return Objects.equals(this.prontuario, other.prontuario);
    }
    
    
    
}

package controle;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import modelo.Semana;
import modelo.enuns.Ativo;

public class SemanaDAO extends ControleDAO<Semana>
{
    
    public List<Semana> buscarTodos()
    {
        EntityManager em = getEntityManager();
        TypedQuery<Semana> query = em.createNamedQuery("Semana.buscarTodos", Semana.class);     
        
        return query.getResultList(); 
    }
    
    public List<Semana> pesquisar(String nome)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Semana> query = em.createNamedQuery("Semana.pesquisar", Semana.class)
                .setParameter("nome", "%" + nome + "%");
        
        return query.getResultList(); 
    }
    
    public Semana buscarSemana(String nome, LocalDate inicio, LocalDate fim)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Semana> query = em.createNamedQuery("Semana.buscarSemana", Semana.class)
                .setParameter("nome", nome)
                .setParameter("inicio", inicio)
                .setParameter("fim", fim);
        
        return query.getSingleResult(); 
    }
    
    public List<Semana> buscarSemanaAtiva()
    {
        EntityManager em = getEntityManager();
        TypedQuery<Semana> query = em.createNamedQuery("Semana.buscarSemanaAtiva", Semana.class)
                .setParameter("ativa", Ativo.S);
        
         try 
        {
           return query.getResultList();
        } 
        catch (NoResultException e) 
        {
            return null;
        }
    }
    
    public List<Semana> pesquisarSemanaAtiva(String nome)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Semana> query = em.createNamedQuery("Semana.pesquisarSemanaAtiva", Semana.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("ativa", Ativo.S);
        
         try 
        {
           return query.getResultList();
        } 
        catch (NoResultException e) 
        {
            return null;
        }
    }
   
}

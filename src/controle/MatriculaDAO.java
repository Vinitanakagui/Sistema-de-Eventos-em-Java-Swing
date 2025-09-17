package controle;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import modelo.Matricula;


public class MatriculaDAO extends ControleDAO<Matricula>
{
    public List<Matricula> buscarInscritos(Integer idEvento)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Matricula> query = em.createNamedQuery("Matricula.buscarIncritos", Matricula.class)
                .setParameter("idevento", idEvento);
            
        
        return query.getResultList();
    }
    
     public Long totalIncritos (Integer idEvento)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Long> query = em.createNamedQuery("Matricula.totalInscritos", Long.class)
                .setParameter("idevento", idEvento);
            
        
        return query.getSingleResult();
    }
     
     public List<Matricula> pesquisar(String nome)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Matricula> query = em.createNamedQuery("Matricula.pesquisar", Matricula.class)
                .setParameter("nome", "%" + nome + "%");
            
        
        return query.getResultList();
    }
     
     public Matricula buscarPorEvento(String nome, Integer idEvento)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Matricula> query = em.createNamedQuery("Matricula.buscarPorEvento", Matricula.class)
                .setParameter("nome", nome)
                .setParameter("idevento", idEvento);
            
        
        try 
        {
           return query.getSingleResult();
        } 
        catch (NoResultException e) 
        {
            return null;
        } 
    }
     
     public Integer buscarUltimoID(String prontuario)
     {
         EntityManager em = getEntityManager();
         TypedQuery<Integer> query = em.createNamedQuery("Matricula.buscarUltimoId", Integer.class)
                 .setParameter("prontuario", prontuario);
         
         try 
         {
            Integer resultado = query.getSingleResult();
            
            return resultado != null ? resultado : 0; 
         }    
         catch (NoResultException e) 
         {
            return 0;
         }
     }
     
     public List<Matricula> buscarMatriculasEvento(String prontuario, Integer idSemana)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Matricula> query = em.createNamedQuery("Matricula.buscarMatriculasEvento", Matricula.class)
                .setParameter("prontuario", prontuario)
                .setParameter("idsemana", idSemana);
            
        
        try 
        {
           return query.getResultList();
        } 
        catch (NoResultException e) 
        {
            return null;
        } 
    }
     
     public Long contarEventos(String prontuario)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Long> query = em.createNamedQuery("Matricula.totalEventosPorProntuario", Long.class)
                .setParameter("prontuario", prontuario);
        
        try 
        {
            return query.getSingleResult();
        } 
        catch (NoResultException e) 
        {
            return 0L;
        } 
    }
    
    public Long somarCargaHoraria(String prontuario)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Long> query = em.createNamedQuery("Matricula.totalCargaHorariaPorProntuario", Long.class)
                .setParameter("prontuario", prontuario);
        
        try 
        {
            return query.getSingleResult();
        } 
        catch (NoResultException e) 
        {
            return 0L;
        } 
    }
     

}

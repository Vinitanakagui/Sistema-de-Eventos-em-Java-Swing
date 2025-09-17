package controle;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import modelo.Evento;

public class EventoDAO extends ControleDAO<Evento>
{
    public List<Evento> buscarTodos()
    {
        EntityManager em = getEntityManager();
        TypedQuery<Evento> query = em.createNamedQuery("Evento.buscarTodos", Evento.class);
            
        
        return query.getResultList();
    }
    
    public List<Evento> buscarListaEventoPorSemana(Integer idSemana)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Evento> query = em.createNamedQuery("Evento.buscarPorSemana", Evento.class)
                .setParameter("idSemana", idSemana);
            
        
        return query.getResultList();
    }
    
    public List<Evento> pesquisar(String nome)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Evento> query = em.createNamedQuery("Evento.pesquisar", Evento.class)
                .setParameter("nome", "%" + nome + "%");
        
        return query.getResultList(); 
    }
    
    public Evento buscarEventoPorSemana(String nomeEvento, Integer idSemana)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Evento> query = em.createNamedQuery("Evento.buscarEventoPorSemana", Evento.class)
                .setParameter("nomeevento", nomeEvento)
                .setParameter("idsemana", idSemana);
        
        try 
        {
            return query.getSingleResult();
        } 
        catch (NoResultException e) 
        {
            return null;
        } 
    }
    
    public Evento buscarEventoPorData(String nomeEvento, LocalDate data)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Evento> query = em.createNamedQuery("Evento.buscarEventoPorData", Evento.class)
                .setParameter("nome", nomeEvento)
                .setParameter("data", data);
        
        try 
        {
            return query.getSingleResult();
        } 
        catch (NoResultException e) 
        {
            return null;
        } 
    }
    
    public Evento buscarPorNomeSemana(String nomeEvento, String nomeSemana)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Evento> query = em.createNamedQuery("Evento.buscarPorNomeSemana", Evento.class)
                .setParameter("titulo", nomeEvento)
                .setParameter("nome", nomeSemana);
        
        try 
        {
            return query.getSingleResult();
        } 
        catch (NoResultException e) 
        {
            return null;
        } 
    }
    
    public  Long contarEventos()
    {
        EntityManager em = getEntityManager();
        TypedQuery<Long> query = em.createNamedQuery("Evento.contarEventos", Long.class);
        
        try 
        {
            return query.getSingleResult();
        } 
        catch (NoResultException e) 
        {
            return 0L;
        } 
    }
    
    public Long somarCargaHoraria()
    {
        EntityManager em = getEntityManager();
        TypedQuery<Long> query = em.createNamedQuery("Evento.somarCargaHoraria", Long.class);
        
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

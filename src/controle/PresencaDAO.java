package controle;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import modelo.Presenca;


public class PresencaDAO extends ControleDAO<Presenca>
{
     public List<Presenca> buscarPresenca(Integer idMatricula)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Presenca> query = em.createNamedQuery("Presenca.buscarPresenca", Presenca.class)
                .setParameter("idmatricula", idMatricula);     
        
        return query.getResultList(); 
    }
    
    public Integer buscarUltimoID(Integer idMatricula)
     {
         EntityManager em = getEntityManager();
         TypedQuery<Integer> query = em.createNamedQuery("Presenca.buscarUltimoID", Integer.class)
                 .setParameter("idmatricula", idMatricula);
         
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
}

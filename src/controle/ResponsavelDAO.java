package controle;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static modelo.Participante_.prontuario;
import modelo.Responsavel;

public class ResponsavelDAO extends ControleDAO<Responsavel>
{
     public List<Responsavel> buscarResponsavel(String prontuario)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Responsavel> query = em.createNamedQuery("Responsavel.buscarResponsavel", Responsavel.class)
                .setParameter("prontuario", prontuario);
            
        
        return query.getResultList();
    }
     
     public List<Responsavel> buscarResponsavelPorEvento(Integer idEvento)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Responsavel> query = em.createNamedQuery("Responsavel.buscarResponsavel", Responsavel.class)
                .setParameter("prontuario", prontuario);
            
        
        return query.getResultList();
    }
}
